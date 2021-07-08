package io.github.treech.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.collection.LruCache;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.github.treech.net.cache.CacheType;
import io.github.treech.net.config.ConfigModule;
import io.github.treech.net.config.GlobalConfigModule;
import io.github.treech.net.config.ManifestParser;
import io.github.treech.net.cookie.CookieJarImpl;
import io.github.treech.net.cookie.store.PersistentCookieStore;
import io.github.treech.net.interceptor.RequestInterceptor;
import io.github.treech.net.log.DefaultFormatPrinter;
import io.github.treech.net.utils.ContextProvider;
import io.github.treech.net.utils.HttpsUtils;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.internal.Util;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitClient封装单例类, 实现网络请求
 */
public class RetrofitClient {

    static volatile RetrofitClient defaultInstance;

    //超时时间
    private static final int DEFAULT_TIMEOUT = 20;
    //缓存时间
    private static final int CACHE_TIMEOUT = 10 * 1024 * 1024;

    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;

    private okhttp3.Cache cache = null;
    private LruCache<String, Object> mRetrofitServiceCache;
    private File httpCacheDirectory;
    private List<ConfigModule> mModules;

    /**
     * Convenience singleton for apps using a process-wide RetrofitClient instance.
     */
    public static RetrofitClient getInstance() {
        if (defaultInstance == null) {
            synchronized (RetrofitClient.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RetrofitClient();
                }
            }
        }
        return defaultInstance;
    }

    private RetrofitClient() {
        final Context context = ContextProvider.get().getContext();

        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(context.getCacheDir(), "http_cache");
        }

        try {
            if (cache == null) {
                cache = new okhttp3.Cache(httpCacheDirectory, CACHE_TIMEOUT);
            }
        } catch (Exception e) {
            Log.e("RetrofitClient", e.getMessage());
        }

        // 用反射, 将 AndroidManifest.xml 中带有 ConfigModule 标签的 class 转成对象集合（List<ConfigModule>）
        this.mModules = new ManifestParser(context).parse();
        // 遍历之前获得的集合, 执行每一个 ConfigModule 实现类的某些方法
        GlobalConfigModule globalConfigModule = getGlobalConfigModule(context, mModules);
        String customBaseUrl = globalConfigModule.getBaseUrl();
        List<Interceptor> customInterceptors = globalConfigModule.getInterceptors();
        RequestInterceptor.Level logLevel = globalConfigModule.getLevel();

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cookieJar(new CookieJarImpl(new PersistentCookieStore(context)))
                .cache(cache)
                .addNetworkInterceptor(new RequestInterceptor(new DefaultFormatPrinter(), logLevel == null ? RequestInterceptor.Level.NONE : logLevel))
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .dispatcher(new Dispatcher(new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                        new SynchronousQueue<>(), Util.threadFactory("AI Executor", false))))
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS));

        if (customInterceptors != null && customInterceptors.size() > 0) {
            for (Interceptor interceptor : customInterceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        okHttpClient = RetrofitUrlManager.getInstance().with(builder).build();

        GsonBuilder gsonBuilder = new GsonBuilder();
        //支持将序列化key为object的map,默认只能序列化key为string的map
        gsonBuilder.enableComplexMapKeySerialization();
        Gson gson = gsonBuilder.create();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(TextUtils.isEmpty(customBaseUrl) ? "https://api.github.com/" : customBaseUrl)
                .build();

        mRetrofitServiceCache = new LruCache(CacheType.RETROFIT_SERVICE_CACHE.calculateCacheSize(context));
    }

    /**
     * 将app的全局配置信息封装进module 需要在AndroidManifest中声明{@link ConfigModule}的实现类,和Glide的配置方式相似
     *
     * @return GlobalConfigModule
     */
    private GlobalConfigModule getGlobalConfigModule(Context context, List<ConfigModule> modules) {
        GlobalConfigModule.Builder builder = GlobalConfigModule.builder();

        // 遍历 ConfigModule 集合, 给全局配置 GlobalConfigModule 添加参数
        for (ConfigModule module : modules) {
            module.applyOptions(context, builder);
        }

        return builder.build();
    }

    public <T> T create(@NonNull Class<T> serviceClass) {
        T retrofitService = (T) mRetrofitServiceCache.get(serviceClass.getCanonicalName());
        if (retrofitService == null) {
            retrofitService = retrofit.create(serviceClass);
            mRetrofitServiceCache.put(serviceClass.getCanonicalName(), retrofitService);
        }
        return retrofitService;
    }
}
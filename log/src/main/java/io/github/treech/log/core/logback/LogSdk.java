package io.github.treech.log.core.logback;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.joran.spi.DefaultNestedComponentRegistry;
import ch.qos.logback.core.joran.spi.JoranException;

public class LogSdk implements Thread.UncaughtExceptionHandler {

    protected static final String TAG = "LogSdk";

    private Logger mLogger;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private HandlerThread mHandlerThread = new HandlerThread(TAG);

    public void onCreate(@NonNull Context context) {
        mHandlerThread.start();
        Handler handler = new Handler(mHandlerThread.getLooper());
        handler.post(() -> {
            long time = System.currentTimeMillis();
            if (Build.VERSION.SDK_INT >= 23) {
                if (context.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.os.Process.myPid(),
                        android.os.Process.myUid()) == PackageManager.PERMISSION_GRANTED) {
                    doConfigure(context, isLogToFileReady());
                } else {
                    Log.w(TAG, "onCreate: No WRITE_EXTERNAL_STORAGE Permission");
                    doConfigure(context, false);
                }
            } else {
                doConfigure(context, isLogToFileReady());
            }
            mLogger = LoggerFactory.getLogger(TAG);
            mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(LogSdk.this);
            Log.i(TAG, String.format("App onCreate: Configure log used {%d} ms", System.currentTimeMillis() - time));
            if (Build.VERSION.SDK_INT >= 18) {
                mHandlerThread.quitSafely();
            } else {
                mHandlerThread.quit();
            }
        });
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        if (mLogger != null) {
            mLogger.error("uncaughtException", e);
        }
        mDefaultHandler.uncaughtException(t, e);
    }

    protected boolean isLogToFileReady() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static boolean getSystemProperty(String key, boolean def) {
        String value = System.getProperty(key, "");
        if (TextUtils.isEmpty(value)) {
            return def;
        } else {
            try {
                return Boolean.parseBoolean(value);
            } catch (Exception e) {
                return def;
            }
        }
    }

    public static int getSystemProperty(String key, int def) {
        String value = System.getProperty(key, "");
        if (TextUtils.isEmpty(value)) {
            return def;
        } else {
            try {
                return Integer.parseInt(value);
            } catch (Exception e) {
                return def;
            }
        }
    }

    public static String getSystemProperty(String key, String def) {
        String value = System.getProperty(key, "");
        if (TextUtils.isEmpty(value)) {
            return def;
        }
        return value;
    }

    public static void doConfigure(Context context, boolean writeFile) {
        // 第一次调用LoggerFactory.getILoggerFactory()会触发对配置文件的解析
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        // stop或者reset方法会清空所有预设的宏定义值，如需使用这些预设的宏定义则需要重新设置下
        loggerContext.stop();
        loggerContext.putProperty(CoreConstants.DATA_DIR_KEY,
                context.getFilesDir().getAbsolutePath());
        loggerContext.putProperty(CoreConstants.EXT_DIR_KEY,
                Environment.getExternalStorageDirectory().getAbsolutePath());
        loggerContext.putProperty(CoreConstants.PACKAGE_NAME_KEY,
                context.getPackageName());

        String logDir = LogSdk.getSystemProperty("LOG_DIR", "${EXT_DIR}/LogSdk/log");
        String logFileName = LogSdk.getSystemProperty("LOG_FILE_NAME", "LogSdk");
        loggerContext.putProperty("LOG_DIR", logDir);
        loggerContext.putProperty("LOG_FILE_NAME", logFileName);
        boolean useLogcat = LogSdk.getSystemProperty("LOG_LOGBACK_USE_LOGCAT", false);
        String logbackConfigXml = LogSdk.getSystemProperty("LOG_LOGBACK_CONFIG_XML",
                "logback_config.xml");

        JoranConfigurator configurator = new JoranConfigurator() {
            @Override
            protected void addDefaultNestedComponentRegistryRules(DefaultNestedComponentRegistry registry) {
                super.addDefaultNestedComponentRegistryRules(registry);
                CustomNestedComponentRules.addCustomNestedComponentRegistryRules(registry);
            }
        };
        configurator.setContext(loggerContext);

        // 读取asset目录下的资源
        try {
            InputStream inputStream = context.getAssets().open(logbackConfigXml);
            configurator.doConfigure(inputStream);
        } catch (IOException | JoranException e) {
            e.printStackTrace();
        }

        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger)
                LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

        Iterator<Appender<ILoggingEvent>> iterator = root.iteratorForAppenders();
        List<Appender<ILoggingEvent>> fileAppenders = new ArrayList<>();
        List<Appender<ILoggingEvent>> logcatAppenders = new ArrayList<>();
        while (iterator.hasNext()) {
            Appender<ILoggingEvent> appender = iterator.next();
            if (appender instanceof FileAppender) {
                fileAppenders.add(appender);
            } else if (appender instanceof LogcatAppender) {
                logcatAppenders.add(appender);
            } else if (appender instanceof ch.qos.logback.classic.android.LogcatAppender) {
                logcatAppenders.add(appender);
            }
        }
        if (!writeFile) {
            for (Appender<ILoggingEvent> appender : fileAppenders) {
                root.detachAppender(appender);
            }
        }
        if (!useLogcat) {
            for (Appender<ILoggingEvent> appender : logcatAppenders) {
                root.detachAppender(appender);
            }
        }
    }
}

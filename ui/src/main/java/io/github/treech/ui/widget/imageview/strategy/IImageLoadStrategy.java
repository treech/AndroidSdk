

package io.github.treech.ui.widget.imageview.strategy;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

/**
 * 图片加载策略
 *
 */
public interface IImageLoadStrategy {

    /**
     * 加载图片【最常用】
     *
     * @param imageView 图片控件
     * @param path      图片资源的索引
     */
    void loadImage(@NonNull ImageView imageView, Object path);

    /**
     * 加载图片【最常用】
     *
     * @param imageView 图片控件
     * @param path      图片资源的索引
     * @param listener  加载监听
     */
    void loadImage(@NonNull ImageView imageView, Object path, @NonNull ILoadListener listener);

    /**
     * 加载Gif图片【最常用】
     *
     * @param imageView 图片控件
     * @param path      图片资源的索引
     */
    void loadGifImage(@NonNull ImageView imageView, Object path);

    /**
     * 加载图片【最常用】
     *
     * @param imageView 图片控件
     * @param path      图片资源的索引
     * @param listener  加载监听
     */
    void loadGifImage(@NonNull ImageView imageView, Object path, @NonNull ILoadListener listener);

    //=============================================//

    /**
     * 加载图片
     *
     * @param imageView 图片控件
     * @param path      图片资源的索引
     * @param strategy  磁盘缓存策略
     */
    void loadImage(@NonNull ImageView imageView, Object path, DiskCacheStrategyEnum strategy);


    /**
     * 加载图片
     *
     * @param imageView 图片控件
     * @param path      图片资源的索引
     * @param strategy  磁盘缓存策略
     * @param listener  加载监听
     */
    void loadImage(@NonNull ImageView imageView, Object path, DiskCacheStrategyEnum strategy, ILoadListener listener);

    /**
     * 加载Gif图片
     *
     * @param imageView 图片控件
     * @param path      图片资源的索引
     * @param strategy  磁盘缓存策略
     */
    void loadGifImage(@NonNull ImageView imageView, Object path, DiskCacheStrategyEnum strategy);

    /**
     * 加载Gif图片
     *
     * @param imageView 图片控件
     * @param path      图片资源的索引
     * @param strategy  磁盘缓存策略
     * @param listener  加载监听
     */
    void loadGifImage(@NonNull ImageView imageView, Object path, DiskCacheStrategyEnum strategy, ILoadListener listener);

    //=============================================//

    /**
     * 加载图片
     *
     * @param imageView   图片控件
     * @param path        图片资源的索引
     * @param placeholder 占位图片
     * @param strategy    磁盘缓存策略
     */
    void loadImage(@NonNull ImageView imageView, Object path, Drawable placeholder, DiskCacheStrategyEnum strategy);

    /**
     * 加载图片
     *
     * @param imageView   图片控件
     * @param path        图片资源的索引
     * @param placeholder 占位图片
     * @param strategy    磁盘缓存策略
     * @param listener    加载监听
     */
    void loadImage(@NonNull ImageView imageView, Object path, Drawable placeholder, DiskCacheStrategyEnum strategy, ILoadListener listener);

    /**
     * 加载Gif图片
     *
     * @param imageView   图片控件
     * @param path        图片资源的索引
     * @param placeholder 占位图片
     * @param strategy    磁盘缓存策略
     */
    void loadGifImage(@NonNull ImageView imageView, Object path, Drawable placeholder, DiskCacheStrategyEnum strategy);

    /**
     * 加载Gif图片
     *
     * @param imageView   图片控件
     * @param path        图片资源的索引
     * @param placeholder 占位图片
     * @param strategy    磁盘缓存策略
     * @param listener    加载监听
     */
    void loadGifImage(@NonNull ImageView imageView, Object path, Drawable placeholder, DiskCacheStrategyEnum strategy, ILoadListener listener);

    //=============================================//

    /**
     * 加载图片
     *
     * @param imageView  图片控件
     * @param path       图片资源的索引
     * @param loadOption 加载选项
     */
    void loadImage(@NonNull ImageView imageView, Object path, LoadOption loadOption);

    /**
     * 加载图片
     *
     * @param imageView  图片控件
     * @param path       图片资源的索引
     * @param loadOption 加载选项
     * @param listener   加载监听
     */
    void loadImage(@NonNull ImageView imageView, Object path, LoadOption loadOption, ILoadListener listener);

    /**
     * 加载Gif图片
     *
     * @param imageView  图片控件
     * @param path       图片资源的索引
     * @param loadOption 加载选项
     */
    void loadGifImage(@NonNull ImageView imageView, Object path, LoadOption loadOption);

    /**
     * 加载Gif图片
     *
     * @param imageView  图片控件
     * @param path       图片资源的索引
     * @param loadOption 加载选项
     * @param listener   加载监听
     */
    void loadGifImage(@NonNull ImageView imageView, Object path, LoadOption loadOption, ILoadListener listener);

    //=============================================//

    /**
     * 清除缓存【内存和磁盘缓存】
     *
     * @param context
     */
    void clearCache(Context context);

    /**
     * 清除内存缓存
     *
     * @param context
     */
    void clearMemoryCache(Context context);

    /**
     * 清除磁盘缓存
     *
     * @param context
     */
    void clearDiskCache(Context context);

}

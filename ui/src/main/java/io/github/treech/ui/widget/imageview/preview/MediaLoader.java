

package io.github.treech.ui.widget.imageview.preview;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import io.github.treech.ui.widget.imageview.preview.loader.GlideMediaLoader;
import io.github.treech.ui.widget.imageview.preview.loader.IMediaLoader;
import io.github.treech.ui.widget.imageview.preview.loader.ISimpleTarget;

/**
 * 图片加载管理器
 *
 */
public class MediaLoader implements IMediaLoader {

    private static volatile MediaLoader sInstance = null;

    private volatile IMediaLoader mIMediaLoader;

    private MediaLoader() {
        mIMediaLoader = new GlideMediaLoader();
    }

    /**
     * 设置IMediaLoader
     *
     * @param loader
     */
    public MediaLoader setIMediaLoader(@NonNull IMediaLoader loader) {
        mIMediaLoader = loader;
        return this;
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static MediaLoader get() {
        if (sInstance == null) {
            synchronized (MediaLoader.class) {
                if (sInstance == null) {
                    sInstance = new MediaLoader();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void displayImage(@NonNull Fragment context, @NonNull String path, ImageView imageView, @NonNull ISimpleTarget simpleTarget) {
        mIMediaLoader.displayImage(context, path, imageView, simpleTarget);
    }

    @Override
    public void displayGifImage(@NonNull Fragment context, @NonNull String path, ImageView imageView, @NonNull ISimpleTarget simpleTarget) {
        mIMediaLoader.displayGifImage(context, path, imageView, simpleTarget);
    }

    @Override
    public void onStop(@NonNull Fragment context) {
        mIMediaLoader.onStop(context);
    }

    @Override
    public void clearMemory(@NonNull Context context) {
        mIMediaLoader.clearMemory(context);
    }
}

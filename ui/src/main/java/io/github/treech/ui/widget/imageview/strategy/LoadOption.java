

package io.github.treech.ui.widget.imageview.strategy;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

/**
 * 加载选项
 *
 */
public class LoadOption {

    /**
     * 默认加载的超时时间（2500ms）
     */
    public static final int DEFAULT_TIMEOUT = 2500;
    /**
     * 磁盘缓存策略
     */
    public DiskCacheStrategyEnum cacheStrategy;
    /**
     * 占位图
     */
    public Drawable placeholder;
    /**
     * 出错时显示的图片
     */
    public Drawable error;
    /**
     * 宽度
     */
    public int width;
    /**
     * 高度
     */
    public int height;
    /**
     * 对齐方式
     */
    public AlignEnum align = AlignEnum.DEFAULT;
    /**
     * 加载超时时间
     */
    public int timeoutMs = DEFAULT_TIMEOUT;

    public static LoadOption of(DiskCacheStrategyEnum cacheStrategy) {
        return new LoadOption(cacheStrategy);
    }

    public static LoadOption of(Drawable placeholder) {
        return new LoadOption(placeholder);
    }

    public LoadOption() {
    }

    public LoadOption(DiskCacheStrategyEnum cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
    }

    public LoadOption(Drawable placeholder) {
        this.placeholder = placeholder;
    }

    public DiskCacheStrategyEnum getCacheStrategy() {
        return cacheStrategy;
    }

    public LoadOption setCacheStrategy(DiskCacheStrategyEnum cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
        return this;
    }

    public Drawable getPlaceholder() {
        return placeholder;
    }

    public LoadOption setPlaceholder(Drawable placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    /**
     * 设置加载图片的宽高
     *
     * @param width  宽
     * @param height 高
     * @return
     */
    public LoadOption setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public boolean hasSize() {
        return width != 0 && height != 0;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public AlignEnum getAlign() {
        return align;
    }

    public LoadOption setAlign(AlignEnum align) {
        this.align = align;
        return this;
    }

    public int getTimeoutMs() {
        return timeoutMs;
    }

    public LoadOption setTimeoutMs(int timeoutMs) {
        this.timeoutMs = timeoutMs;
        return this;
    }

    public Drawable getError() {
        return error;
    }

    public LoadOption setError(Drawable error) {
        this.error = error;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        return "LoadOption{" +
                "cacheStrategy=" + cacheStrategy +
                ", placeholder=" + placeholder +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}

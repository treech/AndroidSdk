package io.github.treech.banner2.listener;

public interface OnBannerListener<T> {

    /**
     * 点击事件
     *
     * @param data     数据实体
     * @param position 当前位置
     */
    void OnBannerClick(T data, int position);

}

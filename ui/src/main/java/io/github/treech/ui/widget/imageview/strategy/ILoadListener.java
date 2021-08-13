

package io.github.treech.ui.widget.imageview.strategy;

/**
 * 加载监听
 *
 */
public interface ILoadListener {

    /**
     * 加载成功
     */
    void onLoadSuccess();

    /**
     * 加载失败
     *
     * @param error
     */
    void onLoadFailed(Throwable error);

}

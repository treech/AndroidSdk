package io.github.treech.ui.widget.layout.linkage;

import android.view.View;

/**
 * 子布局向 LinkageScrollLayout {@link LinkageScrollLayout} 提供的滚动处理实现接口
 *
 * @since 2020/3/11 5:46 PM
 */
public interface LinkageScrollHandler {

    /**
     * fling（抛，快速滑动后产生的动作) target with a velocity(速度）
     *
     * @param target
     * @param velocityY
     */
    void flingContent(View target, int velocityY);

    /**
     * 滚动到顶部
     */
    void scrollContentToTop();

    /**
     * 滚动到底部
     */
    void scrollContentToBottom();

    /**
     * 停止滚动
     *
     * @param target
     */
    void stopContentScroll(View target);

    /**
     * 是否可以垂直滚动
     *
     * @param direction
     * @return
     */
    boolean canScrollVertically(int direction);

    /**
     * 子布局是否可滚动
     *
     * @return
     */
    boolean isScrollable();

    /**
     * 获取垂直滚动条的大小
     *
     * @return extent
     */
    int getVerticalScrollExtent();

    /**
     * 获取垂直滚动条在垂直范围内的偏移
     *
     * @return extent
     */
    int getVerticalScrollOffset();

    /**
     * 获取垂直滚动条的滚动范围
     *
     * @return extent
     */
    int getVerticalScrollRange();
}

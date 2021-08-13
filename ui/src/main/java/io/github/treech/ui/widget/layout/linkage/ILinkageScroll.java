package io.github.treech.ui.widget.layout.linkage;

/**
 * LinkageScrollLayout {@link LinkageScrollLayout}的所有子布局必须要实现的接口
 *
 * @since 2020/3/11 4:57 PM
 */
public interface ILinkageScroll {
    /**
     * 设置内联事件的回调接口
     *
     * @param event ChildLinkageEvent that the top/bottom view holds
     */
    void setChildLinkageEvent(ChildLinkageEvent event);

    /**
     * 获取子布局向 LinkageScrollLayout {@link LinkageScrollLayout} 提供的滚动处理接口
     */
    LinkageScrollHandler provideScrollHandler();
}

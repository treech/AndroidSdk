package io.github.treech.ui.widget.layout.linkage;

import android.view.View;

/**
 * LinkageScrollLayout {@link LinkageScrollLayout}的子布局内联事件的回调接口
 *
 * @since 2020/3/11 4:47 PM
 */
public interface ChildLinkageEvent {
    /**
     * 当子控件滑动至其顶部，通知LinkageScrollLayout
     *
     * @param target
     */
    void onContentScrollToTop(View target);

    /**
     * 当子控件滑动至其底部，通知LinkageScrollLayout
     *
     * @param target
     */
    void onContentScrollToBottom(View target);

    /**
     * 当子控件在滑动时，通知LinkageScrollLayout
     *
     * @param target
     */
    void onContentScroll(View target);
}

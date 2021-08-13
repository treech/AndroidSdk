

package io.github.treech.ui.widget.slideback.callback;

/**
 * 侧滑事件监听（可监听是左滑还是右滑）
 *

 * @since 2019-08-30 9:32
 */
public abstract class SlideCallBack implements SlideBackCallBack {
    private SlideBackCallBack mCallback;

    public SlideCallBack() {
    }

    public SlideCallBack(SlideBackCallBack callBack) {
        mCallback = callBack;
    }

    @Override
    public void onSlideBack() {
        if (null != mCallback) {
            mCallback.onSlideBack();
        }
    }

    /**
     * 滑动来源： <br>
     * EDGE_LEFT    左侧侧滑 <br>
     * EDGE_RIGHT   右侧侧滑 <br>
     */
    public abstract void onSlide(int edgeFrom);
}


package io.github.treech.ui.widget.slideback.dispatcher;

/**
 * 侧滑更新监听事件
 *

 * @since 2019-08-30 15:50
 */
public interface OnSlideUpdateListener {

    /**
     * 更新侧滑长度
     *
     * @param isLeft 是否是左侧
     * @param length 长度
     */
    void updateSlideLength(boolean isLeft, float length);

    /**
     * 更新侧滑位置
     *
     * @param isLeft   是否是左侧
     * @param position 位置
     */
    void updateSlidePosition(boolean isLeft, int position);

}

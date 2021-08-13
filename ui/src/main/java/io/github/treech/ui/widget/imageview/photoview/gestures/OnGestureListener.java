
package io.github.treech.ui.widget.imageview.photoview.gestures;

/**
 * 手势监听器
 *

 * @since 2018/12/5 上午10:51
 */
public interface OnGestureListener {

    /**
     * 拖拽
     *
     * @param dx
     * @param dy
     */
    void onDrag(float dx, float dy);

    /**
     * 移动
     *
     * @param startX
     * @param startY
     * @param velocityX
     * @param velocityY
     */
    void onFling(float startX, float startY, float velocityX,
                 float velocityY);

    /**
     * 缩放
     *
     * @param scaleFactor
     * @param focusX
     * @param focusY
     */
    void onScale(float scaleFactor, float focusX, float focusY);

}
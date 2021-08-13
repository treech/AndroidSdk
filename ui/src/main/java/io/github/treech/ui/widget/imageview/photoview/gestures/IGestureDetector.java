
package io.github.treech.ui.widget.imageview.photoview.gestures;

import android.view.MotionEvent;

/**
 * 手势探测器
 *

 * @since 2018/12/5 上午10:50
 */
public interface IGestureDetector {

    boolean onTouchEvent(MotionEvent ev);

    boolean isScaling();

    boolean isDragging();

    void setOnGestureListener(OnGestureListener listener);

}

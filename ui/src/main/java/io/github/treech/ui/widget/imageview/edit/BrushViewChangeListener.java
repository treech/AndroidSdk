

package io.github.treech.ui.widget.imageview.edit;

/**
 * 画板状态变化监听
 *

 * @since 2019-10-28 9:55
 */
interface BrushViewChangeListener {
    void onViewAdd(BrushDrawingView brushDrawingView);

    void onViewRemoved(BrushDrawingView brushDrawingView);

    void onStartDrawing();

    void onStopDrawing();
}

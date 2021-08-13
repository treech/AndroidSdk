

package io.github.treech.ui.widget.imageview.edit;

import android.graphics.Bitmap;

/**
 * 图片保存监听
 *

 * @since 2019-10-28 10:07
 */
public interface OnBitmapSaveListener {
    /**
     * 图片开始保存
     *
     * @param saveBitmap
     */
    void onBitmapReady(Bitmap saveBitmap);

    /**
     * @param e
     */
    void onFailure(Exception e);
}

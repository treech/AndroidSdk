

package io.github.treech.ui.widget.imageview.preview.enitity;

import android.graphics.Rect;
import android.os.Parcelable;

import androidx.annotation.Nullable;

/**
 * 图片预览接口
 *
 */
public interface IPreviewInfo extends Parcelable {
    /**
     * @return 图片地址
     */
    String getUrl();

    /**
     * @return 记录坐标
     */
    Rect getBounds();

    /**
     * @return 获取视频链接
     */
    @Nullable
    String getVideoUrl();

}

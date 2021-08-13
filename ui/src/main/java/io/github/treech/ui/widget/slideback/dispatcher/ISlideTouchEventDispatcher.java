

package io.github.treech.ui.widget.slideback.dispatcher;

import android.view.View;

import androidx.annotation.NonNull;

import io.github.treech.ui.widget.slideback.SlideInfo;
import io.github.treech.ui.widget.slideback.callback.SlideCallBack;

/**
 * 侧滑触摸事件分发器
 *

 * @since 2019-08-30 15:42
 */
public interface ISlideTouchEventDispatcher extends View.OnTouchListener {

    /**
     * 初始化分发器
     *
     * @param slideInfo 侧滑信息
     * @param callBack  侧滑事件回调
     * @param listener  侧滑更新监听
     * @return
     */
    ISlideTouchEventDispatcher init(@NonNull SlideInfo slideInfo, @NonNull SlideCallBack callBack, @NonNull OnSlideUpdateListener listener);

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

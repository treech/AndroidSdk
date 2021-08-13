package io.github.treech.ui.widget.alpha;

import android.view.View;

/**
 * 透明度辅助工具
 */
public interface IAlphaViewHelper {

    /**
     * 处理setPressed
     *
     * @param current 当前的view
     * @param pressed 是否触摸
     */
    void onPressedChanged(View current, boolean pressed);

    /**
     * 处理setEnabled
     *
     * @param current 当前的view
     * @param enabled 是否可用
     */
    void onEnabledChanged(View current, boolean enabled);

    /**
     * 设置是否要在 press 时改变透明度
     *
     * @param changeAlphaWhenPress 是否要在 press 时改变透明度
     */
    void setChangeAlphaWhenPress(boolean changeAlphaWhenPress);

    /**
     * 设置是否要在 disabled 时改变透明度
     *
     * @param changeAlphaWhenDisable 是否要在 disabled 时改变透明度
     */
    void setChangeAlphaWhenDisable(boolean changeAlphaWhenDisable);
}

package io.github.treech.ui.widget.behavior;

/**
 * @since 2019-05-10 01:03
 */
public interface AnimateHelper {
    /**
     * 显示
     */
    int STATE_SHOW = 1;
    /**
     * 隐藏
     */
    int STATE_HIDE = 0;

    /**
     * 显示
     */
    void show();

    /**
     * 隐藏
     */
    void hide();

    /**
     * 设置开始的y轴位置
     *
     * @param y
     */
    void setStartY(float y);

    /**
     * 设置当前的模式
     *
     * @param mode
     */
    void setMode(int mode);

    /**
     * 获取当前的状态
     *
     * @return
     */
    int getState();
}

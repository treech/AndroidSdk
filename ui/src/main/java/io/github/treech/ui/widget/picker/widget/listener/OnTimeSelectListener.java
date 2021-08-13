package io.github.treech.ui.widget.picker.widget.listener;

import android.view.View;

import java.util.Date;

/**
 * 时间选择监听器
 *
 * @since 2019/1/1 下午7:03
 */
public interface OnTimeSelectListener {
    /**
     * 时间选择
     *
     * @param date
     * @param v
     */
    void onTimeSelected(Date date, View v);
}

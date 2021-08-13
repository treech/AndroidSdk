package io.github.treech.ui.widget.picker.widget.configure;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.ViewGroup;

import io.github.treech.ui.R;

import java.util.Calendar;

import io.github.treech.ui.widget.picker.wheelview.WheelView;
import io.github.treech.ui.widget.picker.widget.listener.CustomListener;
import io.github.treech.ui.widget.picker.widget.listener.OnOptionsSelectChangeListener;
import io.github.treech.ui.widget.picker.widget.listener.OnOptionsSelectListener;
import io.github.treech.ui.widget.picker.widget.listener.OnTimeSelectChangeListener;
import io.github.treech.ui.widget.picker.widget.listener.OnTimeSelectListener;

/**
 * Picker配置类
 *
 * @since 2019/1/1 下午6:57
 */
public class PickerOptions {

    //常量
    private static final int PICKER_VIEW_BTN_COLOR_NORMAL = 0xFF057DFF;
    private static final int PICKER_VIEW_BG_COLOR_TITLE = 0xFFF5F5F5;
    private static final int PICKER_VIEW_COLOR_TITLE = 0xFF000000;
    private static final int PICKER_VIEW_BG_COLOR_DEFAULT = 0xFFFFFFFF;

    public static final int TYPE_PICKER_OPTIONS = 1;
    public static final int TYPE_PICKER_TIME = 2;

    public OnOptionsSelectListener optionsSelectListener;
    public OnTimeSelectListener timeSelectListener;

    public OnTimeSelectChangeListener timeSelectChangeListener;
    public OnOptionsSelectChangeListener optionsSelectChangeListener;

    public CustomListener customListener;


    //options picker
    /**
     * 单位字符
     */
    public String label1, label2, label3;
    /**
     * 默认选中项
     */
    public int option1, option2, option3;
    /**
     * x轴偏移量
     */
    public int xOffsetOne, xOffsetTwo, xOffsetThree;
    /**
     * 是否循环，默认否
     */
    public boolean cyclic1 = false;
    public boolean cyclic2 = false;
    public boolean cyclic3 = false;

    /**
     * 切换时，还原第一项, 默认false
     */
    public boolean isRestoreItem = false;
    /**
     * 显示类型，默认显示： 年月日
     */
    public boolean[] type = new boolean[]{true, true, true, false, false, false};

    /**
     * 当前选中时间
     */
    public Calendar date;
    /**
     * 开始时间
     */
    public Calendar startDate;
    /**
     * 终止时间
     */
    public Calendar endDate;
    /**
     * 开始年份
     */
    public int startYear;
    /**
     * 结尾年份
     */
    public int endYear;

    /**
     * 是否循环
     */
    public boolean cyclic = false;
    /**
     * 是否显示农历
     */
    public boolean isLunarCalendar = false;

    /**
     * 单位
     */
    public String labelYear, labelMonth, labelDay, labelHours, labelMinutes, labelSeconds;
    /**
     * x轴偏移量
     */
    public int xOffsetYear, xOffsetMonth, xOffsetDay, xOffsetHours, xOffsetMinutes, xOffsetSeconds;


    public PickerOptions(int buildType) {
        if (buildType == TYPE_PICKER_OPTIONS) {
            layoutRes = R.layout.xui_layout_picker_view_options;
        } else {
            layoutRes = R.layout.xui_layout_picker_view_time;
        }
    }

    //******* 公有字段，样式  ******//
    public int layoutRes;
    public ViewGroup decorView;
    public int textGravity = Gravity.CENTER;
    public Context context;

    /**
     * 确定按钮文字
     */
    public String textContentConfirm;
    /**
     * 取消按钮文字
     */
    public String textContentCancel;
    /**
     * 标题文字
     */
    public String textContentTitle;
    /**
     * 确定按钮颜色
     */
    public int textColorConfirm = PICKER_VIEW_BTN_COLOR_NORMAL;
    /**
     * 取消按钮颜色
     */
    public int textColorCancel = PICKER_VIEW_BTN_COLOR_NORMAL;
    /**
     * 标题颜色
     */
    public int textColorTitle = PICKER_VIEW_COLOR_TITLE;

    /**
     * 滚轮背景颜色
     */
    public int bgColorWheel = PICKER_VIEW_BG_COLOR_DEFAULT;
    /**
     * 标题背景颜色
     */
    public int bgColorTitle = PICKER_VIEW_BG_COLOR_TITLE;

    /**
     * 确定取消按钮大小
     */
    public int textSizeSubmitCancel = 17;
    /**
     * 标题文字大小
     */
    public int textSizeTitle = 18;
    /**
     * 内容文字大小
     */
    public int textSizeContent = 18;

    /**
     * 分割线以外的文字颜色
     */
    public int textColorOut = 0xFFa8a8a8;
    /**
     * 分割线之间的文字颜色
     */
    public int textColorCenter = 0xFF2a2a2a;
    /**
     * 分割线的颜色
     */
    public int dividerColor = 0xFFd5d5d5;
    /**
     * 显示时的外部背景色颜色,默认是灰色
     */
    public int backgroundId = -1;

    /**
     * 条目间距倍数 默认1.6
     */
    public float lineSpacingMultiplier = 1.6f;
    /**
     * 是否是对话框模式
     */
    public boolean isDialog;

    /**
     * 是否能取消
     */
    public boolean cancelable = true;
    /**
     * 是否只显示中间的label,默认每个item都显示
     */
    public boolean isCenterLabel = false;
    /**
     * 字体样式
     */
    public Typeface font = Typeface.MONOSPACE;
    /**
     * 分隔线类型
     */
    public WheelView.DividerType dividerType = WheelView.DividerType.FILL;


}

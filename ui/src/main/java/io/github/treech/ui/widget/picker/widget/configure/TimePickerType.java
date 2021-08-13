package io.github.treech.ui.widget.picker.widget.configure;

/**
 * 时间选择器的类型<br>
 * <p>
 * 分别控制“年”“月”“日”“时”“分”“秒”的显示或隐藏。
 *
 * @since 2019/1/1 下午8:58
 */
public enum TimePickerType {

    /**
     * 显示“年”“月”“日”
     */
    DEFAULT(new boolean[]{true, true, true, false, false, false}),
    /**
     * 显示“年”“月”“日”“时”“分”“秒”
     */
    ALL(new boolean[]{true, true, true, true, true, true}),
    /**
     * 显示“时”“分”“秒”
     */
    TIME(new boolean[]{false, false, false, true, true, true}),
    /**
     * 显示“年”“月”“日”“时”“分”
     */
    DATE(new boolean[]{true, true, true, true, true, false});


    private final boolean[] mType;

    TimePickerType(boolean[] type) {
        mType = type;
    }

    public boolean[] getType() {
        return mType;
    }
}


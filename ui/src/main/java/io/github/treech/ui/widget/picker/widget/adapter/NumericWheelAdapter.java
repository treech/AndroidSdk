package io.github.treech.ui.widget.picker.widget.adapter;


import io.github.treech.ui.widget.picker.wheelview.adapter.WheelAdapter;

/**
 * 数字滚轮适配器
 *
 * @since 2019/1/1 下午6:37
 */
public class NumericWheelAdapter implements WheelAdapter {

    private int mMinValue;
    private int mMaxValue;

    /**
     * Constructor
     *
     * @param minValue the wheel min value
     * @param maxValue the wheel max value
     */
    public NumericWheelAdapter(int minValue, int maxValue) {
        mMinValue = minValue;
        mMaxValue = maxValue;
    }

    @Override
    public Object getItem(int index) {
        if (index >= 0 && index < getItemsCount()) {
            return mMinValue + index;
        }
        return 0;
    }

    @Override
    public int getItemsCount() {
        return mMaxValue - mMinValue + 1;
    }

    @Override
    public int indexOf(Object o) {
        try {
            return (int) o - mMinValue;
        } catch (Exception e) {
            return -1;
        }

    }
}

package io.github.treech.ui.widget.picker.widget.adapter;

import java.util.List;

import io.github.treech.ui.widget.picker.wheelview.adapter.WheelAdapter;

/**
 * 简单的数组滚轮适配器
 *
 * @since 2019/1/1 下午6:34
 */
public class ArrayWheelAdapter<T> implements WheelAdapter {

    private List<T> mItems;

    /**
     * Constructor
     *
     * @param items the mItems
     */
    public ArrayWheelAdapter(List<T> items) {
        mItems = items;
    }

    @Override
    public Object getItem(int index) {
        if (index >= 0 && index < mItems.size()) {
            return mItems.get(index);
        }
        return "";
    }

    @Override
    public int getItemsCount() {
        return mItems.size();
    }

    @Override
    public int indexOf(Object o) {
        return mItems.indexOf(o);
    }

}

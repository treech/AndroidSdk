

package io.github.treech.ui.adapter.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 简单的集合列表适配器
 *
 * @param <T>
 * @param <H>
 * @author XUE
 */
public abstract class BaseListAdapter<T, H> extends XListAdapter<T> {

    public BaseListAdapter(Context context) {
        super(context);
    }

    public BaseListAdapter(Context context, List<T> data) {
        super(context, data);
    }

    public BaseListAdapter(Context context, T[] data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        H holder;
        if (convertView == null) {
            convertView = View.inflate(getContext(), getLayoutId(), null);
            holder = newViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (H) convertView.getTag();
        }

        T item = getItem(position);
        if (item != null) {
            convert(holder, item, position);
        }
        return convertView;
    }

    /**
     * 创建ViewHolder
     *
     * @param convertView 内容
     * @return ViewHolder
     */
    protected abstract H newViewHolder(View convertView);

    /**
     * 获取适配的布局ID
     *
     * @return 布局ID
     */
    protected abstract int getLayoutId();

    /**
     * 转换布局
     *
     * @param holder   ViewHolder
     * @param item     数据项
     * @param position 位置
     */
    protected abstract void convert(H holder, T item, int position);
}

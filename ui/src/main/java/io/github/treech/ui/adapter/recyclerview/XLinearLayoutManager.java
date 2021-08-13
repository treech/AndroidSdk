

package io.github.treech.ui.adapter.recyclerview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 防止数据错位导致数组越界的错误
 *

 * @since 2019-08-14 00:15
 */
public class XLinearLayoutManager extends LinearLayoutManager {

    /**
     * 是否支持滑动
     */
    private boolean mIsScrollEnabled = true;

    public XLinearLayoutManager(Context context) {
        super(context);
    }

    public XLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public XLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否支持滑动
     *
     * @param flag
     */
    public XLinearLayoutManager setScrollEnabled(boolean flag) {
        mIsScrollEnabled = flag;
        return this;
    }

    @Override
    public boolean canScrollVertically() {
        return mIsScrollEnabled && super.canScrollVertically();
    }

    @Override
    public boolean canScrollHorizontally() {
        return mIsScrollEnabled && super.canScrollHorizontally();
    }
}

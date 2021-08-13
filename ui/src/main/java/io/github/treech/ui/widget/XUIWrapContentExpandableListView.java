

package io.github.treech.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

import io.github.treech.ui.R;

/**
 */
public class XUIWrapContentExpandableListView extends ExpandableListView {

    private int mMaxHeight = Integer.MAX_VALUE >> 2;

    public XUIWrapContentExpandableListView(Context context) {
        super(context);
    }

    public XUIWrapContentExpandableListView(Context context, int maxHeight) {
        super(context);
        mMaxHeight = maxHeight;
    }

    public XUIWrapContentExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public XUIWrapContentExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    /**
     * 初始化属性
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XUIWrapContentExpandableListView);
        if (ta != null) {
            mMaxHeight = ta.getDimensionPixelSize(R.styleable.XUIWrapContentExpandableListView_wcelv_max_height, mMaxHeight);
            ta.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(mMaxHeight, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    /**
     * 设置最大高度
     *
     * @param maxHeight 最大高度[px]
     */
    public void setMaxHeight(int maxHeight) {
        if (mMaxHeight != maxHeight) {
            mMaxHeight = maxHeight;
            requestLayout();
        }
    }
}

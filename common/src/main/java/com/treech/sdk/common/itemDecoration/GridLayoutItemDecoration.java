package com.treech.sdk.common.itemDecoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GridLayoutItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 每个item与左边以及右边的间距(如果需要设置每排item间距全部一样，需要mLeftRight=mLeftRightPadding)
     */
    private int mLeftRight;
    /**
     * 每个item与顶部以及顶部的间距(如果单独设置了mLeftRightPadding、mTopBottomPadding则此属性仅对item与item之间的间距生效)
     */
    private int mTopBottom;
    /**
     * 整个RecyclerView与左边以及右边的间距
     */
    private int mLeftRightPadding;
    /**
     * 整个RecyclerView与顶部以及底部的间距
     */
    private int mTopBottomPadding;

    /**
     * 如果需要设置水平均分每个item间距，请使用此方法
     *
     * @param leftRight
     * @param topBottom
     */
    public GridLayoutItemDecoration(@Dimension(unit = Dimension.DP) int leftRight,
                                    @Dimension(unit = Dimension.DP) int topBottom) {
        this(leftRight, topBottom, leftRight, topBottom);
    }

    /**
     * 如果整个recyclerview与上下左右的间距跟item与item之间的间距大小不一致请使用此方法
     *
     * @param leftRight
     * @param topBottom
     * @param leftRightPadding
     * @param topBottomPadding
     */
    public GridLayoutItemDecoration(@Dimension(unit = Dimension.DP) int leftRight,
                                    @Dimension(unit = Dimension.DP) int topBottom,
                                    @Dimension(unit = Dimension.DP) int leftRightPadding,
                                    @Dimension(unit = Dimension.DP) int topBottomPadding) {
        this.mLeftRight = leftRight;
        this.mTopBottom = topBottom;
        this.mLeftRightPadding = leftRightPadding;
        this.mTopBottomPadding = topBottomPadding;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        final int position = parent.getChildAdapterPosition(view);
        final int spanCount = layoutManager.getSpanCount();
        int maxSpanGroupIndex = layoutManager.getSpanSizeLookup()
                .getSpanGroupIndex(parent.getAdapter().getItemCount() - 1, spanCount);//最后一行
        GridLayoutManager.SpanSizeLookup spanSizeLookup = layoutManager.getSpanSizeLookup();
        int spanGroupIndex = spanSizeLookup.getSpanGroupIndex(position, spanCount);//每一排的index(排数)
        int spanSize = lp.getSpanSize();
        if (layoutManager.getOrientation() == RecyclerView.VERTICAL) {
            //判断是否在第一排
            if (spanGroupIndex == 0) {//第一排的需要上面
                outRect.top = mTopBottomPadding;
            } else if (spanGroupIndex == maxSpanGroupIndex) {//最后一排需要下面
                outRect.bottom = mTopBottomPadding;
            }
            outRect.bottom = mTopBottom;
            //这里忽略和合并项的问题，只考虑占满和单一的问题
            if (spanSize == spanCount) {//占满
                outRect.left = mLeftRightPadding;
                outRect.right = mLeftRightPadding;
            } else {
                outRect.left = (mLeftRight * lp.getSpanIndex() + (spanCount - lp.getSpanIndex() * 2) * mLeftRightPadding) / spanCount;
                outRect.right = (mLeftRightPadding * 2 + mLeftRight * (spanCount - 1)) / spanCount - outRect.left;
            }
        } else {
            //判断是否在第一排
            if (spanGroupIndex == 0) {//第一排的需要left
                outRect.left = mLeftRightPadding;
            } else if (spanGroupIndex == maxSpanGroupIndex) {//最后一排需要right
                outRect.right = mLeftRightPadding;
            }
            outRect.right = mLeftRight;
            //这里忽略和合并项的问题，只考虑占满和单一的问题
            if (spanSize == spanCount) {//占满
                outRect.top = mTopBottomPadding;
                outRect.bottom = mTopBottomPadding;
            } else {
                outRect.top = (mTopBottom * lp.getSpanIndex() + (spanCount - lp.getSpanIndex() * 2) * mTopBottomPadding) / spanCount;
                outRect.bottom = (mTopBottomPadding * 2 + mTopBottom * (spanCount - 1)) / spanCount - outRect.top;
            }
        }
    }
}

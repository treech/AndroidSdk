

package io.github.treech.ui.widget.slideback;

import androidx.annotation.NonNull;

/**
 * 侧滑信息
 *

 * @since 2019-08-30 11:13
 */
public class SlideInfo {

    /**
     * 控件高度
     */
    private float mBackViewHeight;
    /**
     * 箭头图标大小
     */
    private float mArrowSize;
    /**
     * 最大拉动距离
     */
    private float mMaxSlideLength;

    /**
     * 侧滑响应距离
     */
    private float mSideSlideLength;
    /**
     * 阻尼系数
     */
    private float mDragRate;

    /**
     * 使用左侧侧滑
     */
    private boolean mIsAllowEdgeLeft;
    /**
     * 使用右侧侧滑
     */
    private boolean mIsAllowEdgeRight;

    /**
     * 屏幕宽
     */
    private float mScreenWidth;

    public float getBackViewHeight() {
        return mBackViewHeight;
    }

    public SlideInfo setBackViewHeight(float backViewHeight) {
        mBackViewHeight = backViewHeight;
        return this;
    }

    public float getArrowSize() {
        return mArrowSize;
    }

    public SlideInfo setArrowSize(float arrowSize) {
        mArrowSize = arrowSize;
        return this;
    }

    public float getMaxSlideLength() {
        return mMaxSlideLength;
    }

    public SlideInfo setMaxSlideLength(float maxSlideLength) {
        mMaxSlideLength = maxSlideLength;
        return this;
    }

    public float getSideSlideLength() {
        return mSideSlideLength;
    }

    public SlideInfo setSideSlideLength(float sideSlideLength) {
        mSideSlideLength = sideSlideLength;
        return this;
    }

    public float getDragRate() {
        return mDragRate;
    }

    public SlideInfo setDragRate(float dragRate) {
        mDragRate = dragRate;
        return this;
    }

    public boolean isAllowEdgeLeft() {
        return mIsAllowEdgeLeft;
    }

    public SlideInfo setAllowEdgeLeft(boolean allowEdgeLeft) {
        mIsAllowEdgeLeft = allowEdgeLeft;
        return this;
    }

    public boolean isAllowEdgeRight() {
        return mIsAllowEdgeRight;
    }

    public SlideInfo setAllowEdgeRight(boolean allowEdgeRight) {
        mIsAllowEdgeRight = allowEdgeRight;
        return this;
    }

    public SlideInfo setEdgeMode(boolean allowEdgeLeft, boolean allowEdgeRight) {
        mIsAllowEdgeLeft = allowEdgeLeft;
        mIsAllowEdgeRight = allowEdgeRight;
        return this;
    }

    public float getScreenWidth() {
        return mScreenWidth;
    }

    public SlideInfo setScreenWidth(float screenWidth) {
        mScreenWidth = screenWidth;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        return "SlideInfo{" +
                "mBackViewHeight=" + mBackViewHeight +
                ", mArrowSize=" + mArrowSize +
                ", mMaxSlideLength=" + mMaxSlideLength +
                ", mSideSlideLength=" + mSideSlideLength +
                ", mDragRate=" + mDragRate +
                ", mIsAllowEdgeLeft=" + mIsAllowEdgeLeft +
                ", mIsAllowEdgeRight=" + mIsAllowEdgeRight +
                ", mScreenWidth=" + mScreenWidth +
                '}';
    }
}



package io.github.treech.ui.widget.slideback.dispatcher.impl;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import io.github.treech.ui.widget.slideback.SlideBack;
import io.github.treech.ui.widget.slideback.SlideInfo;
import io.github.treech.ui.widget.slideback.callback.SlideCallBack;
import io.github.treech.ui.widget.slideback.dispatcher.ISlideTouchEventDispatcher;
import io.github.treech.ui.widget.slideback.dispatcher.OnSlideUpdateListener;

/**
 * 默认的侧滑触摸事件处理
 *

 * @since 2019-08-30 11:05
 */
public class DefaultSlideTouchDispatcher implements ISlideTouchEventDispatcher {

    /**
     * 是否从左边边缘开始滑动
     */
    protected boolean mIsSideSlideLeft = false;
    /**
     * 是否从右边边缘开始滑动
     */
    protected boolean mIsSideSlideRight = false;
    /**
     * 按下的X轴坐标
     */
    protected float mDownX = 0;
    /**
     * 位移的X轴距离
     */
    protected float mMoveXLength = 0;

    protected SlideInfo mSlideInfo;
    protected SlideCallBack mCallBack;
    protected OnSlideUpdateListener mOnSlideUpdateListener;

    /**
     * 初始化接口
     *
     * @param slideInfo 侧滑信息
     * @param callBack  侧滑事件回调
     * @param listener  侧滑更新监听
     * @return
     */
    @Override
    public ISlideTouchEventDispatcher init(@NonNull SlideInfo slideInfo, @NonNull SlideCallBack callBack, @NonNull OnSlideUpdateListener listener) {
        mSlideInfo = slideInfo;
        mCallBack = callBack;
        mOnSlideUpdateListener = listener;
        return this;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 按下
                // 更新按下点的X轴坐标
                mDownX = event.getRawX();

                // 检验是否从边缘开始滑动，区分左右
                if (mSlideInfo.isAllowEdgeLeft() && mDownX <= mSlideInfo.getSideSlideLength()) {
                    mIsSideSlideLeft = true;
                } else if (mSlideInfo.isAllowEdgeRight() && mDownX >= mSlideInfo.getScreenWidth() - mSlideInfo.getSideSlideLength()) {
                    mIsSideSlideRight = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                // 移动
                if (mIsSideSlideLeft || mIsSideSlideRight) {
                    // 从边缘开始滑动
                    // 获取X轴位移距离
                    mMoveXLength = Math.abs(event.getRawX() - mDownX);
                    if (mMoveXLength / mSlideInfo.getDragRate() <= mSlideInfo.getMaxSlideLength()) {
                        // 如果位移距离在可拉动距离内，更新SlideBackIconView的当前拉动距离并重绘，区分左右
                        if (mSlideInfo.isAllowEdgeLeft() && mIsSideSlideLeft) {
                            updateSlideLength(true, mMoveXLength / mSlideInfo.getDragRate());
                        } else if (mSlideInfo.isAllowEdgeRight() && mIsSideSlideRight) {
                            updateSlideLength(false, mMoveXLength / mSlideInfo.getDragRate());
                        }
                    }

                    // 根据Y轴位置给SlideBackIconView定位
                    if (mSlideInfo.isAllowEdgeLeft() && mIsSideSlideLeft) {
                        updateSlidePosition(true, (int) (event.getRawY()));
                    } else if (mSlideInfo.isAllowEdgeRight() && mIsSideSlideRight) {
                        updateSlidePosition(false, (int) (event.getRawY()));
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                // 抬起
                // 是从边缘开始滑动 且 抬起点的X轴坐标大于某值(默认3倍最大滑动长度) 且 回调不为空
                if ((mIsSideSlideLeft || mIsSideSlideRight) && mMoveXLength / mSlideInfo.getDragRate() >= mSlideInfo.getMaxSlideLength() && null != mCallBack) {
                    // 区分左右
                    mCallBack.onSlide(mIsSideSlideLeft ? SlideBack.EDGE_LEFT : SlideBack.EDGE_RIGHT);
                }

                // 恢复SlideBackIconView的状态
                if (mSlideInfo.isAllowEdgeLeft() && mIsSideSlideLeft) {
                    updateSlideLength(true, 0);
                } else if (mSlideInfo.isAllowEdgeRight() && mIsSideSlideRight) {
                    updateSlideLength(false, 0);
                }

                // 从边缘开始滑动结束
                mIsSideSlideLeft = false;
                mIsSideSlideRight = false;
                break;
            default:
                break;
        }
        return mIsSideSlideLeft || mIsSideSlideRight;
    }


    /**
     * 更新侧滑长度
     *
     * @param isLeft
     * @param length
     */
    @Override
    public void updateSlideLength(boolean isLeft, float length) {
        if (mOnSlideUpdateListener != null) {
            mOnSlideUpdateListener.updateSlideLength(isLeft, length);
        }
    }

    /**
     * 更新侧滑位置
     *
     * @param isLeft
     * @param position
     */
    @Override
    public void updateSlidePosition(boolean isLeft, int position) {
        if (mOnSlideUpdateListener != null) {
            mOnSlideUpdateListener.updateSlidePosition(isLeft, position);
        }
    }

}

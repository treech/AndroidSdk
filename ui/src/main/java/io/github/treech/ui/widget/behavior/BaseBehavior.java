package io.github.treech.ui.widget.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

/**
 * 基础Behavior
 *
 * @since 2019-05-10 01:04
 */
abstract public class BaseBehavior extends CoordinatorLayout.Behavior<View> {

    protected final int mTouchSlop;
    protected boolean isFirstMove = true;
    protected boolean canInit = true;
    protected AnimateHelper mAnimateHelper;

    public BaseBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child,
                                       @NonNull View directTargetChild, @NonNull View target, int nestedScrollAxes) {

        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target,
                                  int dx, int dy, @NonNull int[] consumed) {
        onNestPreScrollInit(child);

        if (Math.abs(dy) > 2) {
            if (dy < 0) {
                if (mAnimateHelper.getState() == TranslateAnimateHelper.STATE_HIDE) {
                    mAnimateHelper.show();
                }
            } else if (dy > 0) {
                if (mAnimateHelper.getState() == TranslateAnimateHelper.STATE_SHOW) {
                    mAnimateHelper.hide();
                }
            }
        }
    }

    /**
     * 开始准备滑动的初始化准备
     *
     * @param child
     */
    protected abstract void onNestPreScrollInit(View child);

    public void show() {
        mAnimateHelper.show();
    }

    public void hide() {
        mAnimateHelper.hide();
    }

    public static BaseBehavior from(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (!(params instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params).getBehavior();
        if (!(behavior instanceof BaseBehavior)) {
            throw new IllegalArgumentException("The view is not associated with BaseBehavior");
        }
        return (BaseBehavior) behavior;
    }
}

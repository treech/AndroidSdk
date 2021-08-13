package io.github.treech.ui.widget.behavior;

import android.animation.ValueAnimator;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * 平移动画
 */
public class TranslateAnimateHelper implements AnimateHelper {
    public View mTarget;
    public float mStartY;
    public int mCurrentState = STATE_SHOW;
    public int mMode = MODE_TITLE;
    public static int MODE_TITLE = 233;
    public static int MODE_BOTTOM = 2333;
    private float mFirstY = 0;
    private float mMargin;

    private TranslateAnimateHelper(View view) {
        mTarget = view;
        mFirstY = mTarget.getY();
        mMargin = ((CoordinatorLayout.LayoutParams) mTarget.getLayoutParams()).topMargin
                + ((CoordinatorLayout.LayoutParams) mTarget.getLayoutParams()).bottomMargin;
    }

    public static TranslateAnimateHelper get(View target) {
        return new TranslateAnimateHelper(target);
    }

    @Override
    public void show() {
        if (mMode == MODE_TITLE) {
            showTitle();
        } else if (mMode == MODE_BOTTOM) {
            showBottom();
        }
    }

    private void hideTitle() {
        ValueAnimator va = ValueAnimator.ofFloat(mTarget.getY(), -mTarget.getHeight());
        va.setDuration(300);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mTarget.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        va.start();
        mCurrentState = STATE_HIDE;
    }

    private void showTitle() {
        ValueAnimator va = ValueAnimator.ofFloat(mTarget.getY(), 0);
        va.setDuration(300);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mTarget.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        va.start();
        mCurrentState = STATE_SHOW;
    }

    @Override
    public void hide() {
        if (mMode == MODE_TITLE) {
            hideTitle();
        } else if (mMode == MODE_BOTTOM) {
            hideBottom();
        }
    }

    private void showBottom() {
        ValueAnimator va = ValueAnimator.ofFloat(mTarget.getY(), mFirstY);
        va.setDuration(300);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mTarget.setY((Float) valueAnimator.getAnimatedValue());
            }
        });

        va.start();
        mCurrentState = STATE_SHOW;
    }

    private void hideBottom() {
        ValueAnimator va = ValueAnimator.ofFloat(mTarget.getY(), mFirstY + mTarget.getHeight() + mMargin);
        va.setDuration(300);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mTarget.setY((Float) valueAnimator.getAnimatedValue());
            }
        });

        va.start();
        mCurrentState = STATE_HIDE;
    }

    @Override
    public void setStartY(float y) {
        mStartY = y;
    }

    @Override
    public int getState() {
        return mCurrentState;
    }

    @Override
    public void setMode(int mode) {
        mMode = mode;
    }

    private void setState(int state) {
        mCurrentState = state;
    }
}

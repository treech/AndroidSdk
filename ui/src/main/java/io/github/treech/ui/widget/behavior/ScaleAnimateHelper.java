package io.github.treech.ui.widget.behavior;

import android.animation.ValueAnimator;
import android.view.View;

/**
 * 缩放动画
 *
 * @since 2019-05-10 01:07
 */
public class ScaleAnimateHelper implements AnimateHelper {

    public View mTarget;
    public int mCurrentState = STATE_SHOW;

    private ScaleAnimateHelper(View view) {
        mTarget = view;
    }

    public static ScaleAnimateHelper get(View view) {
        return new ScaleAnimateHelper(view);
    }

    @Override
    public void show() {
        ValueAnimator va = ValueAnimator.ofFloat(mTarget.getScaleX(), 1);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float scale = (Float) valueAnimator.getAnimatedValue();
                mTarget.setScaleX(scale);
                mTarget.setScaleY(scale);
            }
        });
        va.setDuration(300);
        va.start();

        mCurrentState = STATE_SHOW;
    }

    @Override
    public void hide() {
        ValueAnimator va = ValueAnimator.ofFloat(mTarget.getScaleX(), 0);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float scale = (Float) valueAnimator.getAnimatedValue();
                mTarget.setScaleX(scale);
                mTarget.setScaleY(scale);
            }
        });
        va.setDuration(300);
        va.start();
        mCurrentState = STATE_HIDE;
    }

    @Override
    public void setStartY(float y) {

    }

    @Override
    public void setMode(int modeBottom) {

    }

    @Override
    public int getState() {
        return mCurrentState;
    }
}

package io.github.treech.ui.widget.banner.anim.select;

import android.animation.ObjectAnimator;
import android.view.View;

import io.github.treech.ui.widget.banner.anim.BaseAnimator;

public class RotateEnter extends BaseAnimator {
    public RotateEnter() {
        this.mDuration = 200;
    }

    @Override
    public void setAnimation(View view) {
        this.mAnimatorSet.playTogether(ObjectAnimator.ofFloat(view, "rotation", 0, 180));
    }
}

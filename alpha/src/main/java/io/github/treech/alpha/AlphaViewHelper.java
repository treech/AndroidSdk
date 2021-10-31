package io.github.treech.alpha;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;


/**
 * 透明度辅助工具
 */
public class AlphaViewHelper implements IAlphaViewHelper {

    private WeakReference<View> mTarget;

    /**
     * 设置是否要在 press 时改变透明度
     */
    private boolean mChangeAlphaWhenPress;

    /**
     * 设置是否要在 disabled 时改变透明度
     */
    private boolean mChangeAlphaWhenDisable;

    private float mNormalAlpha = 1F;
    private float mPressedAlpha;
    private float mDisabledAlpha;

    public AlphaViewHelper(@NonNull View target) {
        mTarget = new WeakReference<>(target);
        mChangeAlphaWhenPress = resolveBoolean(target.getContext(), R.attr.switch_alpha_pressed, true);
        mChangeAlphaWhenDisable = resolveBoolean(target.getContext(), R.attr.switch_alpha_disabled, true);
        mPressedAlpha = resolveFloat(target.getContext(), R.attr.alpha_pressed, 0.5F);
        mDisabledAlpha = resolveFloat(target.getContext(), R.attr.alpha_disabled, 0.5F);
    }

    public AlphaViewHelper(@NonNull View target, float pressedAlpha, float disabledAlpha) {
        mTarget = new WeakReference<>(target);
        mPressedAlpha = pressedAlpha;
        mDisabledAlpha = disabledAlpha;
    }

    /**
     * @param current the view to be handled, maybe not equal to target view
     * @param pressed
     */
    @Override
    public void onPressedChanged(View current, boolean pressed) {
        View target = mTarget.get();
        if (target == null) {
            return;
        }
        if (current.isEnabled()) {
            target.setAlpha(mChangeAlphaWhenPress && pressed && current.isClickable() ? mPressedAlpha : mNormalAlpha);
        } else {
            if (mChangeAlphaWhenDisable) {
                target.setAlpha(mDisabledAlpha);
            }
        }
    }

    /**
     * @param current the view to be handled, maybe not  equal to target view
     * @param enabled
     */
    @Override
    public void onEnabledChanged(View current, boolean enabled) {
        View target = mTarget.get();
        if (target == null) {
            return;
        }
        float alphaForIsEnable;
        if (mChangeAlphaWhenDisable) {
            alphaForIsEnable = enabled ? mNormalAlpha : mDisabledAlpha;
        } else {
            alphaForIsEnable = mNormalAlpha;
        }
        if (current != target && target.isEnabled() != enabled) {
            target.setEnabled(enabled);
        }
        target.setAlpha(alphaForIsEnable);
    }

    /**
     * 设置是否要在 press 时改变透明度
     *
     * @param changeAlphaWhenPress 是否要在 press 时改变透明度
     */
    @Override
    public void setChangeAlphaWhenPress(boolean changeAlphaWhenPress) {
        mChangeAlphaWhenPress = changeAlphaWhenPress;
    }

    /**
     * 设置是否要在 disabled 时改变透明度
     *
     * @param changeAlphaWhenDisable 是否要在 disabled 时改变透明度
     */
    @Override
    public void setChangeAlphaWhenDisable(boolean changeAlphaWhenDisable) {
        mChangeAlphaWhenDisable = changeAlphaWhenDisable;
        View target = mTarget.get();
        if (target != null) {
            onEnabledChanged(target, target.isEnabled());
        }
    }

    private boolean resolveBoolean(Context context, @AttrRes int attr, boolean fallback) {
        TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
        try {
            return a.getBoolean(0, fallback);
        } finally {
            a.recycle();
        }
    }

    private float resolveFloat(Context context, int attrRes, float defaultValue) {
        TypedArray a = context.obtainStyledAttributes(new int[]{attrRes});
        try {
            return a.getFloat(0, defaultValue);
        } finally {
            a.recycle();
        }
    }

}

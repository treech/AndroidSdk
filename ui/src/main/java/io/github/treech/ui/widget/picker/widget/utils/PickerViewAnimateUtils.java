package io.github.treech.ui.widget.picker.widget.utils;

import android.view.Gravity;

import io.github.treech.ui.R;

/**
 * 选择器动画
 *
 * @since 2019/1/1 下午7:00
 */
public class PickerViewAnimateUtils {

    private static final int INVALID = -1;

    private PickerViewAnimateUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Get default animation resource when not defined by the user
     *
     * @param gravity       the animGravity of the dialog
     * @param isInAnimation determine if is in or out animation. true when is is
     * @return the id of the animation resource
     */
    public static int getAnimationResource(int gravity, boolean isInAnimation) {
        switch (gravity) {
            case Gravity.BOTTOM:
                return isInAnimation ? R.anim.picker_view_slide_in_bottom : R.anim.picker_view_slide_out_bottom;
            default:
                break;
        }
        return INVALID;
    }
}

package io.github.treech.ui.widget.dialog.materialdialog;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.Gravity;
import android.view.View;

/**
 * 对齐方式
 *
 * @since 2018/11/14 下午4:44
 */
public enum GravityEnum {
    /**
     * 头部对齐
     */
    START,
    /**
     * 居中对齐
     */
    CENTER,
    /**
     * 尾部对齐
     */
    END;

    private static final boolean HAS_RTL =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;

    @SuppressLint("RtlHardcoded")
    public int getGravityInt() {
        switch (this) {
            case START:
                return HAS_RTL ? Gravity.START : Gravity.LEFT;
            case CENTER:
                return Gravity.CENTER_HORIZONTAL;
            case END:
                return HAS_RTL ? Gravity.END : Gravity.RIGHT;
            default:
                throw new IllegalStateException("Invalid gravity constant");
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public int getTextAlignment() {
        switch (this) {
            case CENTER:
                return View.TEXT_ALIGNMENT_CENTER;
            case END:
                return View.TEXT_ALIGNMENT_VIEW_END;
            default:
                return View.TEXT_ALIGNMENT_VIEW_START;
        }
    }
}

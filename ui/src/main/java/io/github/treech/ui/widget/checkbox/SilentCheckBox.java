package io.github.treech.ui.widget.checkbox;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatCheckBox;

/**
 * 一个可以静默改变状态的CheckBox
 */
public class SilentCheckBox extends AppCompatCheckBox {

    private OnCheckedChangeListener mCheckedChangeListener;

    public SilentCheckBox(Context context) {
        super(context);
    }

    public SilentCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SilentCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        super.setOnCheckedChangeListener(listener);
        mCheckedChangeListener = listener;
    }

    /**
     * <p>Changes the checked state of this button.</p>
     *
     * @param checked true to check the button, false to uncheck it
     * @param silent  true to change the state silently, makes no one knows about the change!
     */
    public void setChecked(boolean checked, boolean silent) {
        if (silent) {
            super.setOnCheckedChangeListener(null);
        }
        setChecked(checked);
        if (silent) {
            super.setOnCheckedChangeListener(mCheckedChangeListener);
        }
    }
}

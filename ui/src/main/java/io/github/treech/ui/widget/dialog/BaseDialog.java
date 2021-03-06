package io.github.treech.ui.widget.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;

import io.github.treech.ui.R;
import io.github.treech.ui.utils.KeyboardUtils;
import io.github.treech.ui.utils.ResUtils;

/**
 * 基类Dialog
 * 触摸Dialog屏幕以外的区域，dialog消失同时隐藏键盘
 *
 * @since 2018/12/6 下午3:29
 */
public class BaseDialog extends AppCompatDialog {
    private View mContentView;

    public BaseDialog(Context context, int layoutId) {
        this(context, R.style.XUIDialog_Custom, layoutId);
    }

    public BaseDialog(Context context, View contentView) {
        this(context, R.style.XUIDialog_Custom, contentView);
    }

    public BaseDialog(Context context) {
        super(context, R.style.XUIDialog_Custom);
    }

    public BaseDialog(Context context, int theme, int layoutId) {
        super(context, theme);
        init(layoutId);

    }

    public BaseDialog(Context context, int theme, View contentView) {
        super(context, theme);
        init(contentView);
    }

    public void init(int layoutId) {
        View view = getLayoutInflater().inflate(layoutId, null);
        init(view);
    }

    private void init(View view) {
        setContentView(view);
        mContentView = view;
        setCanceledOnTouchOutside(true);
    }

    /**
     * 设置弹窗的宽和高
     *
     * @param width
     * @param height
     */
    public BaseDialog setDialogSize(int width, int height) {
        // 获取对话框当前的参数值
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams p = window.getAttributes();
            p.width = width;
            p.height = height;
            window.setAttributes(p);
        }
        return this;
    }

    /**
     * 设置弹框的宽(高度是wrap_content)
     *
     * @param width
     * @return
     */
    public BaseDialog setDialogWidth(int width) {
        // 获取对话框当前的参数值
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams p = window.getAttributes();
            p.width = width;
            window.setAttributes(p);
        }
        return this;
    }


    @Override
    public <T extends View> T findViewById(int resId) {
        return mContentView.findViewById(resId);
    }

    public String getString(int resId) {
        return getContext().getResources().getString(resId);
    }

    public Drawable getDrawable(int resId) {
        return ResUtils.getDrawable(getContext(), resId);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent ev) {
        KeyboardUtils.dispatchTouchEvent(ev, this);
        return super.onTouchEvent(ev);
    }

}

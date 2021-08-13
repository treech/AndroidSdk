package io.github.treech.ui.widget.dialog.materialdialog;

import android.content.Context;
import android.view.View;

import androidx.annotation.IdRes;

/**
 * 基础抽象的弹窗，可继承后自定义弹窗
 *
 * @since 2018/11/16 上午12:51
 */
public abstract class CustomMaterialDialog {

    /**
     * 弹窗窗体
     */
    protected MaterialDialog mDialog;

    /**
     * 构造窗体
     *
     * @param context
     */
    public CustomMaterialDialog(Context context) {
        mDialog = getDialogBuilder(context).build();

        initViews(context);
    }

    /**
     * 获取弹窗的构建者
     *
     * @param context
     * @return
     */
    protected abstract MaterialDialog.Builder getDialogBuilder(Context context);

    /**
     * 初始化控件
     *
     * @param context
     */
    protected abstract void initViews(Context context);

    /**
     * 显示弹窗
     *
     * @param <T>
     * @return
     */
    public <T extends CustomMaterialDialog> T show() {
        if (mDialog != null) {
            mDialog.show();
        }
        return (T) this;
    }

    /**
     * 隐藏弹窗
     *
     * @param <T>
     * @return
     */
    public <T extends CustomMaterialDialog> T dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
        return (T) this;
    }

    public MaterialDialog getDialog() {
        return mDialog;
    }

    public <T extends View> T findViewById(@IdRes int id) {
        return (T) mDialog.findViewById(id);
    }
}

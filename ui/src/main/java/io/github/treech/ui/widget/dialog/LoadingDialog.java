package io.github.treech.ui.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.StyleRes;

import io.github.treech.ui.R;

import io.github.treech.ui.widget.progress.loading.ARCLoadingView;
import io.github.treech.ui.widget.progress.loading.IMessageLoader;
import io.github.treech.ui.widget.progress.loading.LoadingCancelListener;

/**
 * loading加载框
 *
 * @since 2019/1/14 下午10:08
 */
public class LoadingDialog extends BaseDialog implements IMessageLoader {

    private ARCLoadingView mLoadingView;
    private TextView mTvTipMessage;

    private LoadingCancelListener mLoadingCancelListener;

    public LoadingDialog(Context context) {
        super(context, R.layout.xui_dialog_loading);
        initView(getString(R.string.xui_tip_loading_message));
    }

    public LoadingDialog(Context context, String tipMessage) {
        super(context, R.layout.xui_dialog_loading);
        initView(tipMessage);
    }

    public LoadingDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId, R.layout.xui_dialog_loading);
        initView(getString(R.string.xui_tip_loading_message));
    }

    public LoadingDialog(Context context, @StyleRes int themeResId, String tipMessage) {
        super(context, themeResId, R.layout.xui_dialog_loading);
        initView(tipMessage);
    }

    private void initView(String tipMessage) {
        mLoadingView = findViewById(R.id.arc_loading_view);
        mTvTipMessage = findViewById(R.id.tv_tip_message);

        updateMessage(tipMessage);

        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    /**
     * 更新提示信息
     *
     * @param tipMessage
     * @return
     */
    @Override
    public void updateMessage(String tipMessage) {
        if (mTvTipMessage != null) {
            if (!TextUtils.isEmpty(tipMessage)) {
                mTvTipMessage.setText(tipMessage);
                mTvTipMessage.setVisibility(View.VISIBLE);
            } else {
                mTvTipMessage.setText("");
                mTvTipMessage.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 更新提示信息
     *
     * @param tipMessageId
     * @return
     */
    @Override
    public void updateMessage(int tipMessageId) {
        updateMessage(getString(tipMessageId));
    }

    /**
     * 设置loading的图标
     *
     * @param icon
     * @return
     */
    public LoadingDialog setLoadingIcon(Drawable icon) {
        if (mLoadingView != null) {
            mLoadingView.setLoadingIcon(icon);
        }
        return this;
    }

    /**
     * 设置loading的图标
     *
     * @param iconResId
     * @return
     */
    public LoadingDialog setLoadingIcon(int iconResId) {
        return setLoadingIcon(getDrawable(iconResId));
    }

    /**
     * 设置图标的缩小比例
     *
     * @param iconScale
     * @return
     */
    public LoadingDialog setIconScale(float iconScale) {
        if (mLoadingView != null) {
            mLoadingView.setIconScale(iconScale);
        }
        return this;
    }

    /**
     * 设置loading旋转的速度
     *
     * @param speed
     * @return
     */
    public LoadingDialog setLoadingSpeed(int speed) {
        if (mLoadingView != null) {
            mLoadingView.setSpeedOfDegree(speed);
        }
        return this;
    }

    @Override
    public void show() {
        super.show();
        if (mLoadingView != null) {
            mLoadingView.start();
        }
    }

    @Override
    public void dismiss() {
        if (mLoadingView != null) {
            mLoadingView.stop();
        }
        super.dismiss();
    }

    /**
     * 资源释放
     */
    @Override
    public void recycle() {
        if (mLoadingView != null) {
            mLoadingView.recycle();
        }
    }

    @Override
    public boolean isLoading() {
        return isShowing();
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
        if (flag) {
            setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    if (mLoadingCancelListener != null) {
                        mLoadingCancelListener.onCancelLoading();
                    }
                }
            });
        }
    }

    @Override
    public void setLoadingCancelListener(LoadingCancelListener listener) {
        mLoadingCancelListener = listener;
    }
}

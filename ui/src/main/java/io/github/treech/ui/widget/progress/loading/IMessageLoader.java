package io.github.treech.ui.widget.progress.loading;

/**
 * 消息loading加载者
 */
public interface IMessageLoader {

    /**
     * 更新提示信息
     *
     * @param tipMessage
     * @return
     */
    void updateMessage(String tipMessage);

    /**
     * 更新提示信息
     *
     * @param tipMessageId
     * @return
     */
    void updateMessage(int tipMessageId);

    /**
     * 显示加载
     */
    void show();

    /**
     * 隐藏加载
     */
    void dismiss();

    /**
     * 资源释放
     */
    void recycle();

    /**
     * 是否在加载
     *
     * @return
     */
    boolean isLoading();

    /**
     * 设置是否可取消
     *
     * @param cancelable
     */
    void setCancelable(boolean cancelable);

    /**
     * 设置取消的回掉监听
     *
     * @param listener
     */
    void setLoadingCancelListener(LoadingCancelListener listener);
}

package io.github.treech.net.extend;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * custom {@link ErrorHandlerSingleObserver} for your project
 * @param <T>
 */
public abstract class ErrorHandlerSingleObserver<T> implements SingleObserver<T> {

    public ErrorHandlerSingleObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        //如果你某个地方不想使用全局错误处理,则重写 onError(Throwable) 并将 super.onError(e); 删掉
        //如果你不仅想使用全局错误处理,还想加入自己的逻辑,则重写 onError(Throwable) 并在 super.onError(e); 后面加入自己的逻辑
        ExceptionHandler.handleException(e);
    }
}

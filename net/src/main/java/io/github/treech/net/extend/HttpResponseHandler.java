package io.github.treech.net.extend;

import android.text.TextUtils;

import io.github.treech.net.exception.BadResultException;
import io.github.treech.net.exception.EmptyResultException;
import io.github.treech.net.exception.NullMessageException;

import io.github.treech.net.extend.BaseResponse;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.functions.Function;

public class HttpResponseHandler {

    public static <T> SingleTransformer<BaseResponse<T>, T> handleResultSingle() {
        return new SingleTransformer<BaseResponse<T>, T>() {
            @Override
            public SingleSource<T> apply(Single<BaseResponse<T>> upstream) {
                return upstream.flatMap(new Function<BaseResponse<T>, SingleSource<? extends T>>() {
                    @Override
                    public SingleSource<? extends T> apply(BaseResponse<T> result) {
                        if (result.isSuccess()) {
                            if (null == result.data) {
                                return Single.error(new EmptyResultException());
                            } else {
                                return createDataSingle(result.data);
                            }
                        } else {
                            if (TextUtils.isEmpty(result.message)) {
                                return Single.error(new NullMessageException());
                            } else {
                                return Single.error(new BadResultException(result.message));
                            }
                        }
                    }
                });
            }
        };
    }

    private static <T> Single<T> createDataSingle(final T data) {
        return Single.create(new SingleOnSubscribe<T>() {
            @Override
            public void subscribe(SingleEmitter<T> emitter) {
                try {
                    emitter.onSuccess(data);
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }
}

package io.github.treech.net.extend

/**
 * custom {@link HttpCallBack} for your project
 *
 * @param <T>
 */
interface HttpCallBack<T> {

    fun onProgress(msg: String)

    fun onSuccess(t: T)

    fun onError(e: Throwable)
}
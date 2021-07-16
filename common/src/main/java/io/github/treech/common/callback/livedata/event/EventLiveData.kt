package io.github.treech.common.callback.livedata.event

/**
 * 在Activity中observe 调用observeInActivity 在Fragment中使用调用 observeInFragment
 */
class EventLiveData<T> : UnPeekLiveData<T>()
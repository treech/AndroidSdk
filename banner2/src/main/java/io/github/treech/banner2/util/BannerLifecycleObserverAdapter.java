package io.github.treech.banner2.util;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class BannerLifecycleObserverAdapter implements LifecycleObserver {

    private static final String TAG = "BannerLifecycle";
    private final BannerLifecycleObserver mObserver;
    private final LifecycleOwner mLifecycleOwner;

    public BannerLifecycleObserverAdapter(LifecycleOwner lifecycleOwner, BannerLifecycleObserver observer) {
        mLifecycleOwner = lifecycleOwner;
        mObserver = observer;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.i(TAG, "onStart");
        mObserver.onStart(mLifecycleOwner);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.i(TAG, "onStop");
        mObserver.onStop(mLifecycleOwner);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        mObserver.onDestroy(mLifecycleOwner);
    }

}
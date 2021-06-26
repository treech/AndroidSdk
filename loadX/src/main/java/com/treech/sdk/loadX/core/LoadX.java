package com.treech.sdk.loadX.core;


import androidx.annotation.NonNull;

import com.treech.sdk.loadX.LoadXUtil;
import com.treech.sdk.loadX.callback.Callback;
import com.treech.sdk.loadX.target.ActivityTarget;
import com.treech.sdk.loadX.target.ITarget;
import com.treech.sdk.loadX.target.ViewTarget;

import java.util.ArrayList;
import java.util.List;

public class LoadX {
    private static volatile LoadX loadX;
    private Builder builder;

    public static LoadX getDefault() {
        if (loadX == null) {
            synchronized (LoadX.class) {
                if (loadX == null) {
                    loadX = new LoadX();
                }
            }
        }
        return loadX;
    }

    private LoadX() {
        this.builder = new Builder();
    }

    private void setBuilder(@NonNull Builder builder) {
        this.builder = builder;
    }

    private LoadX(Builder builder) {
        this.builder = builder;
    }

    public LoadService register(@NonNull Object target) {
        return register(target, null, null);
    }

    public LoadService register(Object target, Callback.OnReloadListener onReloadListener) {
        return register(target, onReloadListener, null);
    }

    public <T> LoadService register(Object target, Callback.OnReloadListener onReloadListener, Convertor<T>
            convertor) {
        ITarget targetContext = LoadXUtil.getTargetContext(target, builder.getTargetContextList());
        LoadLayout loadLayout = targetContext.replaceView(target, onReloadListener);
        return new LoadService<>(convertor,loadLayout,  builder);
    }

    public static Builder beginBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<Callback> callbacks = new ArrayList<>();
        private List<ITarget> targetContextList = new ArrayList<>();
        private Class<? extends Callback> defaultCallback;

        {
            targetContextList.add(new ActivityTarget());
            targetContextList.add(new ViewTarget());
        }

        public Builder addCallback(@NonNull Callback callback) {
            callbacks.add(callback);
            return this;
        }

        /**
         * @param targetContext
         * @return Builder
         * @since 1.3.8
         */
        public Builder addTargetContext(ITarget targetContext) {
            targetContextList.add(targetContext);
            return this;
        }

        public List<ITarget> getTargetContextList() {
            return targetContextList;
        }

        public Builder setDefaultCallback(@NonNull Class<? extends Callback> defaultCallback) {
            this.defaultCallback = defaultCallback;
            return this;
        }

        List<Callback> getCallbacks() {
            return callbacks;
        }

        Class<? extends Callback> getDefaultCallback() {
            return defaultCallback;
        }

        public void commit() {
            getDefault().setBuilder(this);
        }

        public LoadX build() {
            return new LoadX(this);
        }

    }
}

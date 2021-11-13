package io.treech.sdk;

import android.app.Application;

import io.github.treech.util.Utils;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}

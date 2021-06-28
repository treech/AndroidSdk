package io.github.treech.loadX;

import android.os.Looper;

import io.github.treech.loadX.target.ITarget;

import java.util.List;

public class LoadXUtil {

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static ITarget getTargetContext(Object target, List<ITarget> targetContextList) {
        for (ITarget targetContext : targetContextList) {
            if (targetContext.equals(target)) {
                return targetContext;
            }

        }
        throw new IllegalArgumentException("No TargetContext fit it");
    }
}

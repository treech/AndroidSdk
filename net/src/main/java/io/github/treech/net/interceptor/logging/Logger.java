package io.github.treech.net.interceptor.logging;

import okhttp3.internal.platform.Platform;

@SuppressWarnings({"WeakerAccess", "unused"})
public interface Logger {
    void log(int level, String tag, String msg);

    Logger DEFAULT = new Logger() {
        @Override
        public void log(int level, String tag, String message) {
            Platform.get().log(message, level, null);
        }
    };
}

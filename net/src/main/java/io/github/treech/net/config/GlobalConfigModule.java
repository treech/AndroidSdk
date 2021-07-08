package io.github.treech.net.config;

import java.util.ArrayList;
import java.util.List;

import io.github.treech.net.interceptor.RequestInterceptor;
import okhttp3.Interceptor;

public class GlobalConfigModule {

    private final String mBaseUrl;

    private final List<Interceptor> mInterceptors;
    private final RequestInterceptor.Level mLevel;

    private GlobalConfigModule(Builder builder) {
        this.mBaseUrl = builder.baseUrl;
        this.mInterceptors = builder.interceptors;
        this.mLevel = builder.level;
    }

    public static final class Builder {
        private String baseUrl;
        private List<Interceptor> interceptors;
        private RequestInterceptor.Level level;

        public Builder baseurl(String baseUrl) {
            if (baseUrl == null) {
                throw new NullPointerException("baseUrl can not be null.");
            }
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            if (interceptors == null) {
                interceptors = new ArrayList<>();
            }
            this.interceptors.add(interceptor);
            return this;
        }

        public Builder logLevel(RequestInterceptor.Level level) {
            this.level = level;
            return this;
        }

        public GlobalConfigModule build() {
            return new GlobalConfigModule(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public List<Interceptor> getInterceptors() {
        return mInterceptors;
    }

    public RequestInterceptor.Level getLevel() {
        return mLevel;
    }
}

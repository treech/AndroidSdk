

package io.github.treech.ui.widget.imageview.edit;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义滤镜效果
 *

 * @since 2019-10-28 9:55
 */
public class CustomEffect {

    private String mEffectName;
    private Map<String, Object> mParametersMap;

    private CustomEffect(Builder builder) {
        mEffectName = builder.mEffectName;
        mParametersMap = builder.mParametersMap;
    }

    /**
     * @return Custom effect name from {@link android.media.effect.EffectFactory#createEffect(String)}
     */
    public String getEffectName() {
        return mEffectName;
    }

    /**
     * @return map of key and value of parameters for {@link android.media.effect.Effect#setParameter(String, Object)}
     */
    public Map<String, Object> getParameters() {
        return mParametersMap;
    }

    /**
     * Set customize effect to image using this builder class
     */
    public static class Builder {

        private String mEffectName;
        private Map<String, Object> mParametersMap = new HashMap<>();

        /**
         * Initiate your custom effect
         *
         * @param effectName custom effect name from {@link android.media.effect.EffectFactory#createEffect(String)}
         * @throws RuntimeException exception when effect name is empty
         */
        public Builder(@NonNull String effectName) throws RuntimeException {
            if (TextUtils.isEmpty(effectName)) {
                throw new RuntimeException("Effect name cannot be empty.Please provide effect name from EffectFactory");
            }
            mEffectName = effectName;
        }

        /**
         * set parameter to the attributes with its value
         *
         * @param paramKey   attribute key for {@link android.media.effect.Effect#setParameter(String, Object)}
         * @param paramValue value for {@link android.media.effect.Effect#setParameter(String, Object)}
         * @return builder instance to setup multiple parameters
         */
        public Builder setParameter(@NonNull String paramKey, Object paramValue) {
            mParametersMap.put(paramKey, paramValue);
            return this;
        }

        /**
         * @return instance for custom effect
         */
        public CustomEffect build() {
            return new CustomEffect(this);
        }
    }
}

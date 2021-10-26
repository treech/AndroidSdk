package io.github.treech.banner2.indicator;

import android.view.View;

import androidx.annotation.NonNull;

import io.github.treech.banner2.config.IndicatorConfig;
import io.github.treech.banner2.listener.OnPageChangeListener;

public interface Indicator extends OnPageChangeListener {
    @NonNull
    View getIndicatorView();

    IndicatorConfig getIndicatorConfig();

    void onPageChanged(int count, int currentPosition);

}

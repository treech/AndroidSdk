package io.github.treech.ui.widget.tabbar.vertical;

import android.content.Context;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.treech.ui.widget.textview.badge.Badge;

/**
 * 抽象的tab选项卡
 *
 * @since 2018/12/28 下午1:32
 */
public abstract class TabView extends FrameLayout implements Checkable, ITabView {

    public TabView(Context context) {
        super(context);
    }

    @Override
    public TabView getTabView() {
        return this;
    }

    @Deprecated
    public abstract ImageView getIconView();

    public abstract TextView getTitleView();

    public abstract Badge getBadgeView();
}

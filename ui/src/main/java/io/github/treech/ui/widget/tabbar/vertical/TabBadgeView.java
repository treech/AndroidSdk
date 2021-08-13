package io.github.treech.ui.widget.tabbar.vertical;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import io.github.treech.ui.widget.textview.badge.BadgeView;

/**
 * 选项卡上的圆点小标记
 *
 * @since 2018/12/28 下午1:34
 */
public class TabBadgeView extends BadgeView {
    private TabBadgeView(Context context) {
        super(context);
    }

    public static TabBadgeView bindTab(TabView tab) {
        TabBadgeView badge = null;
        for (int i = 0; i < tab.getChildCount(); i++) {
            View child = tab.getChildAt(i);
            if (child instanceof TabBadgeView) {
                badge = (TabBadgeView) child;
                break;
            }
        }
        if (badge == null) {
            badge = new TabBadgeView(tab.getContext());
            tab.addView(badge, new TabView.LayoutParams(TabView.LayoutParams.MATCH_PARENT, TabView.LayoutParams.MATCH_PARENT));
        }
        badge.mTargetView = tab;
        return badge;
    }

    @Override
    protected void screenFromWindow(boolean screen) {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
        if (screen) {
            mActivityRoot.addView(this, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
        } else {
            if (mTargetView instanceof TabView) {
                ((TabView) mTargetView).addView(this,
                        new TabView.LayoutParams(TabView.LayoutParams.MATCH_PARENT,
                                TabView.LayoutParams.MATCH_PARENT));
            } else {
                bindTarget(mTargetView);
            }
        }
    }
}

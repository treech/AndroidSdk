package io.github.treech.ui.widget.tabbar.vertical;


/**
 * Tab适配器
 *
 * @since 2018/12/28 下午1:31
 */
public interface TabAdapter {
    int getCount();

    TabView.TabBadge getBadge(int position);

    TabView.TabIcon getIcon(int position);

    TabView.TabTitle getTitle(int position);

    int getBackground(int position);
}

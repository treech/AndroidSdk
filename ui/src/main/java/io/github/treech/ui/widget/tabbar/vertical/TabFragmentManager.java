package io.github.treech.ui.widget.tabbar.vertical;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import io.github.treech.ui.widget.tabbar.VerticalTabLayout;

/**
 * 选项卡的Fragment管理
 */
public class TabFragmentManager {
    private FragmentManager mManager;
    private int mContainerResId;
    private List<Fragment> mFragments;
    private VerticalTabLayout mTabLayout;
    private VerticalTabLayout.OnTabSelectedListener mListener;

    public TabFragmentManager(FragmentManager manager, List<Fragment> fragments, VerticalTabLayout tabLayout) {
        mManager = manager;
        mFragments = fragments;
        mTabLayout = tabLayout;
        mListener = new OnFragmentTabSelectedListener();
        mTabLayout.addOnTabSelectedListener(mListener);
    }

    public TabFragmentManager(FragmentManager manager, int containerResid, List<Fragment> fragments, VerticalTabLayout tabLayout) {
        this(manager, fragments, tabLayout);
        mContainerResId = containerResid;
        changeFragment();
    }

    public void changeFragment() {
        FragmentTransaction ft = mManager.beginTransaction();
        int position = mTabLayout.getSelectedTabPosition();
        List<Fragment> addedFragments = mManager.getFragments();
        for (int i = 0; i < mFragments.size(); i++) {
            Fragment fragment = mFragments.get(i);
            if ((addedFragments == null || !addedFragments.contains(fragment)) && mContainerResId != 0) {
                ft.add(mContainerResId, fragment);
            }
            if ((mFragments.size() > position && i == position)
                    || (mFragments.size() <= position && i == mFragments.size() - 1)) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
        }
        ft.commit();
        mManager.executePendingTransactions();
    }

    public void detach() {
        FragmentTransaction ft = mManager.beginTransaction();
        for (Fragment fragment : mFragments) {
            ft.remove(fragment);
        }
        ft.commit();
        mManager.executePendingTransactions();
        mManager = null;
        mFragments = null;
        mTabLayout.removeOnTabSelectedListener(mListener);
        mListener = null;
        mTabLayout = null;
    }


    private class OnFragmentTabSelectedListener implements VerticalTabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabView tab, int position) {
            changeFragment();
        }

        @Override
        public void onTabUnselected(TabView tab, int position) {

        }

        @Override
        public void onTabReselected(TabView tab, int position) {

        }
    }
}



package io.github.treech.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Fragment的适配器,详情参考{@link FragmentStatePagerAdapter}
 *

 * @since 2018/12/26 下午2:11
 */
public class FragmentStateAdapter<T extends Fragment> extends FragmentStatePagerAdapter {

    private List<T> mFragmentList = new ArrayList<>();

    private List<String> mTitleList = new ArrayList<>();

    public FragmentStateAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public FragmentStateAdapter(@NonNull FragmentManager fm, T[] fragments) {
        this(fm, Arrays.asList(fragments));
    }

    public FragmentStateAdapter(@NonNull FragmentManager fm, List<T> fragments) {
        super(fm);
        setFragments(fragments);
    }

    public FragmentStateAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public FragmentStateAdapter(@NonNull FragmentManager fm, int behavior, T[] fragments) {
        this(fm, behavior, Arrays.asList(fragments));
    }

    public FragmentStateAdapter(@NonNull FragmentManager fm, int behavior, List<T> fragments) {
        super(fm, behavior);
        setFragments(fragments);
    }

    public FragmentStateAdapter setFragments(List<T> fragments) {
        if (fragments != null && fragments.size() > 0) {
            mFragmentList.clear();
            mFragmentList.addAll(fragments);
        }
        return this;
    }

    public FragmentStateAdapter addFragments(List<T> fragments) {
        if (fragments != null && fragments.size() > 0) {
            mFragmentList.addAll(fragments);
        }
        return this;
    }

    public FragmentStateAdapter setTitles(List<String> titles) {
        if (titles != null && titles.size() > 0) {
            mTitleList.clear();
            mTitleList.addAll(titles);
        }
        return this;
    }

    public FragmentStateAdapter addTitles(List<String> titles) {
        if (titles != null && titles.size() > 0) {
            mTitleList.addAll(titles);
        }
        return this;
    }

    public FragmentStateAdapter addFragment(T fragment, String title) {
        if (fragment != null) {
            mFragmentList.add(fragment);
            mTitleList.add(title);
        }
        return this;
    }

    @NonNull
    @Override
    public T getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

    public List<T> getFragmentList() {
        return mFragmentList;
    }

    public List<String> getTitleList() {
        return mTitleList;
    }
}

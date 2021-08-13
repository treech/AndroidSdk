

package io.github.treech.ui.adapter.simple;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 简易的二级适配项
 *
 */
public class ExpandableItem {
    /**
     * 父项
     */
    private AdapterItem mGroup;
    /**
     * 子项
     */
    private List<AdapterItem> mChild;

    public static ExpandableItem of(@NonNull AdapterItem group) {
        return new ExpandableItem(group);
    }

    public ExpandableItem(@NonNull AdapterItem group) {
        mGroup = group;
        mChild = new ArrayList<>();
    }

    public ExpandableItem(@NonNull AdapterItem group, AdapterItem... child) {
        mGroup = group;
        mChild = new ArrayList<>(Arrays.asList(child));
    }

    public ExpandableItem(@NonNull AdapterItem group, @NonNull List<AdapterItem> child) {
        mGroup = group;
        mChild = child;
    }

    public ExpandableItem addChild(AdapterItem child) {
        if (mChild == null) {
            mChild = new ArrayList<>();
        }
        mChild.add(child);
        return this;
    }

    public ExpandableItem addChild(int index, AdapterItem child) {
        if (mChild == null) {
            mChild = new ArrayList<>();
        }
        mChild.add(index, child);
        return this;
    }

    public ExpandableItem addChild(AdapterItem... child) {
        if (mChild == null) {
            mChild = new ArrayList<>();
        }
        mChild.addAll(Arrays.asList(child));
        return this;
    }

    public AdapterItem getGroup() {
        return mGroup;
    }

    public ExpandableItem setGroup(AdapterItem group) {
        mGroup = group;
        return this;
    }

    public List<AdapterItem> getChild() {
        return mChild;
    }

    public int getChildSize() {
        return mChild != null ? mChild.size() : 0;
    }

    public AdapterItem getChildItem(int index) {
        return mChild != null ? mChild.get(index) : null;
    }

    public ExpandableItem setChild(List<AdapterItem> child) {
        mChild = child;
        return this;
    }
}

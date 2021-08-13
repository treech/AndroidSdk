

package io.github.treech.ui.widget.searchview;

import android.widget.Filter;

import java.util.List;

/**
 * 抽象的搜索过滤器
 *

 * @since 2019-12-03 00:04
 */
public abstract class AbstractSearchFilter extends Filter {

    private String[] mSuggestions;

    private OnFilterResultListener mOnFilterResultListener;

    public AbstractSearchFilter setSuggestions(String[] suggestions) {
        mSuggestions = suggestions;
        return this;
    }

    public AbstractSearchFilter setOnFilterResultListener(OnFilterResultListener onFilterResultListener) {
        mOnFilterResultListener = onFilterResultListener;
        return this;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        if (results.values != null && mOnFilterResultListener != null) {
            mOnFilterResultListener.publishResults((List<String>) results.values);
        }
    }

    public String[] getSuggestions() {
        return mSuggestions;
    }

    /**
     * 过滤搜索结果的监听
     */
    public interface OnFilterResultListener {

        /**
         * 过滤结果
         *
         * @param results 搜索结果
         */
        void publishResults(List<String> results);
    }

}

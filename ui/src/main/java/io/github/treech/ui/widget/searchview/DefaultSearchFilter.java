

package io.github.treech.ui.widget.searchview;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 默认的搜索过滤器
 *

 * @since 2019-12-03 00:00
 */
public class DefaultSearchFilter extends AbstractSearchFilter {

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults = new FilterResults();
        if (!TextUtils.isEmpty(constraint)) {

            // Retrieve the autocomplete results.
            List<String> searchData = new ArrayList<>();

            for (String suggestion : getSuggestions()) {
                if (filter(suggestion, constraint.toString())) {
                    searchData.add(suggestion);
                }
            }

            // Assign the data to the FilterResults
            filterResults.values = searchData;
            filterResults.count = searchData.size();
        }
        return filterResults;
    }


    /**
     * 过滤搜索条件【以输入为开头的过滤】
     *
     * @param suggestion 目标
     * @param input      输入
     * @return
     */
    protected boolean filter(String suggestion, String input) {
        return suggestion.toLowerCase().startsWith(input.toLowerCase());
    }


}

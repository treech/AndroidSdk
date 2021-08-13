

package io.github.treech.ui.adapter.recyclerview.sticky;

import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * 设置粘顶布局所占的宽度
 *
 */
public final class FullSpanUtils {

    private FullSpanUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 在Adapter的onAttachedToRecyclerView方法中使用
     *
     * @param recyclerView
     * @param adapter
     * @param pinnedHeaderType 粘顶布局的类型
     */
    public static void onAttachedToRecyclerView(RecyclerView recyclerView, final RecyclerView.Adapter adapter, final int pinnedHeaderType) {
        // 如果是网格布局，这里处理标签的布局占满一行
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup oldSizeLookup = gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (adapter.getItemViewType(position) == pinnedHeaderType) {
                        return gridLayoutManager.getSpanCount();
                    }
                    if (oldSizeLookup != null) {
                        return oldSizeLookup.getSpanSize(position);
                    }
                    return 1;
                }
            });
        }
    }

    /**
     * 在Adapter的onViewAttachedToWindow方法中使用
     *
     * @param holder
     * @param adapter
     * @param pinnedHeaderType 粘顶布局的类型
     */
    public static void onViewAttachedToWindow(RecyclerView.ViewHolder holder, RecyclerView.Adapter adapter, int pinnedHeaderType) {
        // 如果是瀑布流布局，这里处理标签的布局占满一行
        final ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            final StaggeredGridLayoutManager.LayoutParams slp = (StaggeredGridLayoutManager.LayoutParams) lp;
            slp.setFullSpan(adapter.getItemViewType(holder.getLayoutPosition()) == pinnedHeaderType);
        }
    }


}

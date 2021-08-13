

package io.github.treech.ui.widget.imageview.nine;

import android.widget.ImageView;

import java.util.List;

/**
 * 九宫图条目点击
 *

 * @since 2018/12/9 下午10:38
 */
public interface ItemImageClickListener<T> {
    /**
     * 九宫格条目点击
     *
     * @param imageView
     * @param index     索引
     * @param list      图片列表
     */
    void onItemImageClick(ImageView imageView, int index, List<T> list);
}

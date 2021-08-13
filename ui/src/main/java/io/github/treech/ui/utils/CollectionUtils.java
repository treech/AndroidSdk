package io.github.treech.ui.utils;

import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.List;

/**
 * 集合工具类
 *
 * @since 2020/8/25 11:58 PM
 */
public final class CollectionUtils {

    private CollectionUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 集合的索引是否有效
     *
     * @param collection 集合
     * @param index      索引
     * @return true: 有效，false：无效
     */
    public static <E> boolean isIndexValid(final Collection<E> collection, final int index) {
        return collection != null && index >= 0 && index < collection.size();
    }

    /**
     * 获取集合指定索引的元素
     *
     * @param list  集合
     * @param index 索引
     * @param <E>
     * @return 集合指定索引的元素
     */
    @Nullable
    public static <E> E getListItem(final List<E> list, final int index) {
        return isIndexValid(list, index) ? list.get(index) : null;
    }

    /**
     * 获取集合的大小
     *
     * @param collection 集合
     * @return 集合的大小
     */
    public static <E> int getSize(final Collection<E> collection) {
        return collection != null ? collection.size() : 0;
    }

    /**
     * 集合是否为空
     *
     * @param collection 集合
     * @return true: 为空，false：不为空
     */
    public static <E> boolean isEmpty(final Collection<E> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 清空集合
     *
     * @param collection 集合
     * @param <E>
     */
    public static <E> void clear(final Collection<E> collection) {
        if (!isEmpty(collection)) {
            collection.clear();
        }
    }
}

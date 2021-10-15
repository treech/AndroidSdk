package io.github.treech.log;

import java.util.List;

public class Constant {

    public static final String STRING_OBJECT_NULL = "Object[object is null]";

    // 解析属性最大层级
    public static final int MAX_CHILD_LEVEL = 2;

    public static final int MIN_STACK_OFFSET = 5;

    // 换行符
    public static final String BR = System.getProperty("line.separator");

    // 空格
    public static final String SPACE = "\t";

    // 默认支持解析库
    public static final Class<? extends Parser>[] DEFAULT_PARSE_CLASS = new Class[]{
            BundleParse.class, IntentParse.class, CollectionParse.class,
            MapParse.class, ThrowableParse.class, ReferenceParse.class
    };

    /**
     * 获取默认解析类
     *
     * @return
     */
    public static final List<Parser> getParsers() {
        return ParseList.getInstance().getParseList();
    }
}

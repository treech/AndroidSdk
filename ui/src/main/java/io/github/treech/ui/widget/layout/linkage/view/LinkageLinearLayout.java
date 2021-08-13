package io.github.treech.ui.widget.layout.linkage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import io.github.treech.ui.widget.layout.linkage.ChildLinkageEvent;
import io.github.treech.ui.widget.layout.linkage.ILinkageScroll;
import io.github.treech.ui.widget.layout.linkage.LinkageScrollHandler;
import io.github.treech.ui.widget.layout.linkage.LinkageScrollHandlerAdapter;

/**
 * 置于联动容器的LinearLayout
 *
 * @since 2020/3/11 6:14 PM
 */
public class LinkageLinearLayout extends LinearLayout implements ILinkageScroll {

    public LinkageLinearLayout(Context context) {
        this(context, null);
    }

    public LinkageLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LinkageLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setChildLinkageEvent(ChildLinkageEvent event) {
        //因为不可滚动，所以无需实现
    }

    @Override
    public LinkageScrollHandler provideScrollHandler() {
        return new LinkageScrollHandlerAdapter() {
            @Override
            public boolean isScrollable() {
                //不可滚动
                return false;
            }

            @Override
            public int getVerticalScrollExtent() {
                return getHeight();
            }

            @Override
            public int getVerticalScrollOffset() {
                //无滚动条
                return 0;
            }

            @Override
            public int getVerticalScrollRange() {
                return getHeight();
            }
        };
    }
}



package io.github.treech.ui.widget.grouplist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.github.treech.ui.R;

import io.github.treech.ui.utils.Utils;

/**
 * 用作通用列表 {@link XUIGroupListView} 里每个 {@link XUIGroupListView.Section} 的头部或尾部，也可单独使用。
 *
 */
public class XUIGroupListSectionHeaderFooterView extends LinearLayout {

    private TextView mTextView;

    public XUIGroupListSectionHeaderFooterView(Context context) {
        this(context, null, R.attr.XUIGroupListSectionViewStyle);
    }

    public XUIGroupListSectionHeaderFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.XUIGroupListSectionViewStyle);
    }

    public XUIGroupListSectionHeaderFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public XUIGroupListSectionHeaderFooterView(Context context, CharSequence titleText) {
        this(context);
        setText(titleText);
    }

    public XUIGroupListSectionHeaderFooterView(Context context, CharSequence titleText, boolean isFooter) {
        this(context);
        if (isFooter) {
            // Footer View 不需要 padding bottom
            setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), 0);
        }
        setText(titleText);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.xui_layout_group_list_section, this, true);
        setGravity(Gravity.BOTTOM);

        mTextView = findViewById(R.id.group_list_section_header_textView);
    }

    public void setText(CharSequence text) {
        if (Utils.isNullOrEmpty(text)) {
            mTextView.setVisibility(GONE);
        } else {
            mTextView.setVisibility(VISIBLE);
        }
        mTextView.setText(text);
    }

    public TextView getTextView() {
        return mTextView;
    }

    public void setTextGravity(int gravity) {
        mTextView.setGravity(gravity);
    }
}

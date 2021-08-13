package io.github.treech.ui.widget.dialog.strategy;

import androidx.annotation.NonNull;

/**
 * 输入信息
 *
 * @since 2018/11/16 上午12:18
 */
public class InputInfo {

    /**
     * 输入类型
     */
    private int mInputType;
    /**
     * 输入的提示
     */
    private String mHint;
    /**
     * 预先输入的内容
     */
    private String mPreFill;
    /**
     * 是否允许输入为空
     */
    private boolean mAllowEmptyInput;

    public InputInfo(int inputType) {
        mInputType = inputType;
        mHint = "";
        mPreFill = "";
        mAllowEmptyInput = false;
    }

    public InputInfo(int inputType, String hint) {
        mInputType = inputType;
        mHint = hint;
        mPreFill = "";
        mAllowEmptyInput = false;
    }

    public InputInfo(int inputType, String hint, String preFill, boolean allowEmptyInput) {
        mInputType = inputType;
        mHint = hint;
        mPreFill = preFill;
        mAllowEmptyInput = allowEmptyInput;
    }

    public int getInputType() {
        return mInputType;
    }

    public InputInfo setInputType(int inputType) {
        mInputType = inputType;
        return this;
    }

    public String getHint() {
        return mHint;
    }

    public InputInfo setHint(String hint) {
        mHint = hint;
        return this;
    }

    public String getPreFill() {
        return mPreFill;
    }

    public InputInfo setPreFill(String preFill) {
        mPreFill = preFill;
        return this;
    }

    public boolean isAllowEmptyInput() {
        return mAllowEmptyInput;
    }

    public InputInfo setAllowEmptyInput(boolean allowEmptyInput) {
        mAllowEmptyInput = allowEmptyInput;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        return "InputInfo{" +
                "mInputType=" + mInputType +
                ", mHint='" + mHint + '\'' +
                ", mPreFill='" + mPreFill + '\'' +
                ", mAllowEmptyInput=" + mAllowEmptyInput +
                '}';
    }
}

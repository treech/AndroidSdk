package io.github.treech.ui.widget.progress.materialprogressbar;

abstract class BaseProgressDrawable extends BasePaintDrawable implements IntrinsicPaddingDrawable {

    protected boolean mUseIntrinsicPadding = true;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getUseIntrinsicPadding() {
        return mUseIntrinsicPadding;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUseIntrinsicPadding(boolean useIntrinsicPadding) {
        if (mUseIntrinsicPadding != useIntrinsicPadding) {
            mUseIntrinsicPadding = useIntrinsicPadding;
            invalidateSelf();
        }
    }
}

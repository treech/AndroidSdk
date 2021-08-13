package io.github.treech.ui.widget.progress.materialprogressbar;

import android.content.Context;
import android.graphics.Canvas;

class HorizontalProgressBackgroundDrawable extends BaseSingleHorizontalProgressDrawable
        implements ShowBackgroundDrawable {

    private boolean mShow = true;

    public HorizontalProgressBackgroundDrawable(Context context) {
        super(context);
    }

    @Override
    public boolean getShowBackground() {
        return mShow;
    }

    @Override
    public void setShowBackground(boolean show) {
        if (mShow != show) {
            mShow = show;
            invalidateSelf();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (mShow) {
            super.draw(canvas);
        }
    }
}

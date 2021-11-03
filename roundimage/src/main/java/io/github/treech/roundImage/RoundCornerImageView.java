package io.github.treech.roundImage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
import androidx.annotation.Px;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.ViewCompat;

public class RoundCornerImageView extends AppCompatImageView {

    private int mLeftTopRadius;
    private int mRightTopRadius;
    private int mRightBottomRadius;
    private int mLeftBottomRadius;

    private int mFillColor = Color.TRANSPARENT;
    private int mStrokeColor = Color.TRANSPARENT;
    private int mStrokeWidth = 0;
    private final Paint mFillPaint = new Paint();
    private final Paint mStrokePaint = new Paint();

    private final Path mPath = new Path();

    public RoundCornerImageView(Context context) {
        this(context, null);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundCornerImageView);
        if (a.hasValue(R.styleable.RoundCornerImageView_radius)) {
            int radius = a.getDimensionPixelSize(R.styleable.RoundCornerImageView_radius, 0);
            setLeftTopRadius(radius);
            setRightTopRadius(radius);
            setRightBottomRadius(radius);
            setLeftBottomRadius(radius);
        }
        if (a.hasValue(R.styleable.RoundCornerImageView_leftTopRadius)) {
            int radius = a.getDimensionPixelSize(R.styleable.RoundCornerImageView_leftTopRadius, 0);
            setLeftTopRadius(radius);
        }
        if (a.hasValue(R.styleable.RoundCornerImageView_rightTopRadius)) {
            int radius = a.getDimensionPixelSize(R.styleable.RoundCornerImageView_rightTopRadius, 0);
            setRightTopRadius(radius);
        }
        if (a.hasValue(R.styleable.RoundCornerImageView_rightBottomRadius)) {
            int radius =
                    a.getDimensionPixelSize(R.styleable.RoundCornerImageView_rightBottomRadius, 0);
            setRightBottomRadius(radius);
        }
        if (a.hasValue(R.styleable.RoundCornerImageView_leftBottomRadius)) {
            int radius =
                    a.getDimensionPixelSize(R.styleable.RoundCornerImageView_leftBottomRadius, 0);
            setLeftBottomRadius(radius);
        }
        if (a.hasValue(R.styleable.RoundCornerImageView_fillColor)) {
            mFillColor = a.getColor(R.styleable.RoundCornerImageView_fillColor, mFillColor);
        }
        if (a.hasValue(R.styleable.RoundCornerImageView_strokeColor)) {
            mStrokeColor = a.getColor(R.styleable.RoundCornerImageView_strokeColor, mStrokeColor);
        }
        if (a.hasValue(R.styleable.RoundCornerImageView_strokeWidth)) {
            mStrokeWidth = a.getDimensionPixelSize(R.styleable.RoundCornerImageView_strokeWidth, 0);
        }
        a.recycle();
        initPaint();
    }

    private void initPaint() {
        mFillPaint.setStyle(Paint.Style.FILL);
        mFillPaint.setAntiAlias(true);
        mFillPaint.setColor(mFillColor);

        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setColor(mStrokeColor);
        // Stroke是沿着最外层边界画的，有一半会延伸到可视界面以外
        // 此处手动设置两倍的StrokeWidth就是为了达到边缘显示效果
        mStrokePaint.setStrokeWidth(mStrokeWidth * 2);
    }

    @Px
    public int getLeftTopRadius() {
        return mLeftTopRadius;
    }

    public void setLeftTopRadius(@Px int leftTopRadius) {
        if (mLeftTopRadius != leftTopRadius) {
            mLeftTopRadius = leftTopRadius;
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Px
    public int getRightTopRadius() {
        return mRightTopRadius;
    }

    public void setRightTopRadius(@Px int rightTopRadius) {
        if (mRightTopRadius != rightTopRadius) {
            mRightTopRadius = rightTopRadius;
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Px
    public int getRightBottomRadius() {
        return mRightBottomRadius;
    }

    public void setRightBottomRadius(@Px int rightBottomRadius) {
        if (mRightBottomRadius != rightBottomRadius) {
            mRightBottomRadius = rightBottomRadius;
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Px
    public int getLeftBottomRadius() {
        return mLeftBottomRadius;
    }

    public void setLeftBottomRadius(@Px int leftBottomRadius) {
        if (mLeftBottomRadius != leftBottomRadius) {
            mLeftBottomRadius = leftBottomRadius;
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @ColorInt
    public int getFillColor() {
        return mFillColor;
    }

    public void setFillColor(@ColorInt int fillColor) {
        if (mFillColor != fillColor) {
            mFillColor = fillColor;
            mFillPaint.setColor(mFillColor);
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @ColorInt
    public int getStrokeColor() {
        return mStrokeColor;
    }

    public void setStrokeColor(@ColorInt int strokeColor) {
        if (mStrokeColor != strokeColor) {
            mStrokeColor = strokeColor;
            mStrokePaint.setColor(mStrokeColor);
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Px
    public int getStrokeWidth() {
        return mStrokeWidth;
    }

    public void setStrokeWidth(@Px int strokeWidth) {
        if (mStrokeWidth != strokeWidth) {
            mStrokeWidth = strokeWidth;
            // Stroke是沿着最外层边界画的，有一半会延伸到可视界面以外
            // 此处手动设置两倍的StrokeWidth就是为了达到边缘显示效果
            mStrokePaint.setStrokeWidth(mStrokeWidth * 2);
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int maxLeft = Math.max(mLeftTopRadius, mLeftBottomRadius);
        int maxRight = Math.max(mRightTopRadius, mRightBottomRadius);
        int minWidth = maxLeft + maxRight;
        int maxTop = Math.max(mLeftTopRadius, mRightTopRadius);
        int maxBottom = Math.max(mLeftBottomRadius, mRightBottomRadius);
        int minHeight = maxTop + maxBottom;
        int width = getWidth();
        int height = getHeight();
        // 只有图片的宽高大于设置的圆角距离的时候才进行裁剪
        if (width >= minWidth && height >= minHeight) {
            mPath.reset();
            // 四个角：右上，右下，左下，左上
            mPath.moveTo(mLeftTopRadius, 0);
            mPath.lineTo(width - mRightTopRadius, 0);
            mPath.quadTo(width, 0, width, mRightTopRadius);

            mPath.lineTo(width, height - mRightBottomRadius);
            mPath.quadTo(width, height, width - mRightBottomRadius, height);

            mPath.lineTo(mLeftBottomRadius, height);
            mPath.quadTo(0, height, 0, height - mLeftBottomRadius);

            mPath.lineTo(0, mLeftTopRadius);
            mPath.quadTo(0, 0, mLeftTopRadius, 0);
            mPath.close();

            canvas.clipPath(mPath);
        }
        if (mFillColor != Color.TRANSPARENT) {
            canvas.drawPath(mPath, mFillPaint);
        }
        super.onDraw(canvas);
        if (mStrokeWidth > 0) {
            canvas.drawPath(mPath, mStrokePaint);
        }
    }
}



package io.github.treech.ui.widget.imageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import io.github.treech.ui.R;

import io.github.treech.ui.utils.ResUtils;
import io.github.treech.ui.utils.ThemeUtils;
import io.github.treech.ui.utils.Utils;

/**
 * 可在ImageView上设置图标的ImageView
 *

 * @since 2018/12/10 上午11:58
 */
public class IconImageView extends AppCompatImageView {
    /**
     * 图标
     */
    private Bitmap mIconBitmap;
    /**
     * 图标缩放比例
     */
    private float mIconScale = 0.3F;
    /**
     * 绘制图标的画笔
     */
    private Paint mIconPaint;
    /**
     * 是否显示
     */
    private boolean mIsShow = true;

    public IconImageView(Context context) {
        super(context);
        initAttrs(context, null);
    }

    public IconImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public IconImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    /**
     * 初始化属性
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IconImageView);

            Drawable icon = ResUtils.getDrawableAttrRes(getContext(), typedArray, R.styleable.IconImageView_iiv_icon_res);
            if (icon != null) {
                mIconBitmap = Utils.getBitmapFromDrawable(icon);
            }
            mIconScale = typedArray.getFloat(R.styleable.IconImageView_iiv_icon_scale, mIconScale);
            mIsShow = typedArray.getBoolean(R.styleable.IconImageView_iiv_is_show, mIsShow);

            typedArray.recycle();
        }

        initPaint();
    }

    private void initPaint() {
        mIconPaint = new Paint();
        mIconPaint.setColor(ThemeUtils.getMainThemeColor(getContext()));
        mIconPaint.setAntiAlias(true);
        mIconPaint.setFilterBitmap(true);
        mIconPaint.setStyle(Paint.Style.STROKE);
        mIconPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mIconBitmap != null && mIsShow) {
            canvas.drawBitmap(mIconBitmap, null, getIconBitmapRectF(mIconBitmap), mIconPaint);
        }
    }

    /**
     * 获取中心图标所在的区域
     *
     * @return
     */
    private RectF getIconBitmapRectF(Bitmap bitmap) {
        float width = bitmap.getWidth(), height = bitmap.getHeight();
        if (width >= height) {
            height = getWidth() / width * height;
            width = getWidth();
        } else {
            width = getHeight() / height * width;
            height = getHeight();
        }
        float left = (getWidth() - width * mIconScale) / 2;
        float top = (getHeight() - height * mIconScale) / 2;
        float right = getWidth() - left;
        float bottom = getHeight() - top;
        return new RectF(left, top, right, bottom);
    }

    /**
     * 设置是否显示图标
     *
     * @param isShowIcon
     * @return
     */
    public IconImageView setIsShowIcon(boolean isShowIcon) {
        mIsShow = isShowIcon;
        invalidate();
        return this;
    }

    /**
     * 设置图标
     *
     * @param iconBitmap
     * @return
     */
    public IconImageView setIconBitmap(@NonNull Bitmap iconBitmap) {
        mIconBitmap = iconBitmap;
        invalidate();
        return this;
    }

    /**
     * 设置图标
     *
     * @param iconDrawable
     * @return
     */
    public IconImageView setIconDrawable(@NonNull Drawable iconDrawable) {
        mIconBitmap = Utils.getBitmapFromDrawable(iconDrawable);
        invalidate();
        return this;
    }

    /**
     * 设置图标的缩放比例
     *
     * @param iconScale
     * @return
     */
    public IconImageView setIconScale(float iconScale) {
        mIconScale = iconScale;
        invalidate();
        return this;
    }

    /**
     * 资源释放
     */
    public void recycle() {
        if (mIconBitmap != null) {
            mIconBitmap.recycle();
            mIconBitmap = null;
        }
        mIconPaint = null;
    }

}

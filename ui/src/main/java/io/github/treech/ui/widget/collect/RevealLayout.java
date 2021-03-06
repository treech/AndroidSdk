package io.github.treech.ui.widget.collect;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Checkable;
import android.widget.FrameLayout;

import io.github.treech.ui.R;

public class RevealLayout extends FrameLayout
        implements Checkable, ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener, GestureDetector.OnGestureListener {

    private final GestureDetector mGestureDetector;

    private View mCheckedView;
    private View mUncheckedView;

    private int mCheckedLayoutId = 0;
    private int mUncheckedLayoutId = 0;
    private int mAnimDuration = 500;
    private boolean mCheckWithExpand = true;
    private boolean mUncheckWithExpand = false;
    private boolean mAllowRevert = false;
    private boolean mHideBackView = true;

    private boolean mChecked = false;

    private float mCenterX = 0F;
    private float mCenterY = 0F;
    private float mRevealRadius = 0F;
    private final Path mPath = new Path();
    private ValueAnimator mAnimator;
    private TimeInterpolator mInterpolator = null;

    private OnCheckedChangeListener mOnCheckedChangeListener = null;
    private OnAnimStateChangeListener mOnAnimStateChangeListener = null;

    public RevealLayout(Context context) {
        this(context, null);
    }

    public RevealLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RevealLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mGestureDetector = new GestureDetector(context, this);
        initAttr(attrs);
        initView();
    }

    /**
     * ??????????????????????????????????????????????????????????????????????????????????????????
     * ?????????????????????????????????{@link #createCheckedView()}???{@link #createUncheckedView()}?????????
     *
     * @param attrs AttributeSet
     */
    protected void initAttr(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.RevealLayout);
        mCheckedLayoutId = array.getResourceId(R.styleable.RevealLayout_rl_checkedLayout, 0);
        mUncheckedLayoutId = array.getResourceId(R.styleable.RevealLayout_rl_uncheckedLayout, 0);
        mChecked = array.getBoolean(R.styleable.RevealLayout_rl_checked, mChecked);
        mAnimDuration = array.getInteger(R.styleable.RevealLayout_rl_animDuration, mAnimDuration);
        mCheckWithExpand = array.getBoolean(R.styleable.RevealLayout_rl_checkWithExpand, mCheckWithExpand);
        mUncheckWithExpand = array.getBoolean(R.styleable.RevealLayout_rl_uncheckWithExpand, mUncheckWithExpand);
        mAllowRevert = array.getBoolean(R.styleable.RevealLayout_rl_allowRevert, mAllowRevert);
        mHideBackView = array.getBoolean(R.styleable.RevealLayout_rl_hideBackView, mHideBackView);
        array.recycle();
    }

    /**
     * ??????????????????????????????????????????????????????????????????
     */
    protected void initView() {
        removeAllViews();
        if (mCheckedView == null) {
            mCheckedView = createCheckedView();
        }
        if (mUncheckedView == null) {
            mUncheckedView = createUncheckedView();
        }
        ViewGroup.LayoutParams checkParams = mCheckedView.getLayoutParams();
        if (checkParams == null) {
            checkParams = getDefaultLayoutParams();
        }
        ViewGroup.LayoutParams uncheckParams = mUncheckedView.getLayoutParams();
        if (uncheckParams == null) {
            uncheckParams = getDefaultLayoutParams();
        }
        addViewInLayout(mCheckedView, getChildCount(), checkParams);
        addViewInLayout(mUncheckedView, getChildCount(), uncheckParams);
        showTwoView();
        bringFrontView();
        hideBackView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            resetCenter();
        }
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if (mAnimator == null) {
            return super.drawChild(canvas, child, drawingTime);
        }
        if (isBackView(child)) {
            return super.drawChild(canvas, child, drawingTime);
        }
        canvas.save();
        canvas.clipPath(mPath);
        boolean drawChild = super.drawChild(canvas, child, drawingTime);
        canvas.restore();
        return drawChild;
    }

    private LayoutParams getDefaultLayoutParams() {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        return params;
    }

    /**
     * ?????????????????????????????????????????????????????????????????????????????????
     *
     * @return ?????????????????????
     */
    protected View createCheckedView() {
        View checkedView;
        if (getCheckedLayoutId() > 0) {
            checkedView = LayoutInflater.from(getContext()).inflate(getCheckedLayoutId(), this, false);
        } else {
            checkedView = new View(getContext());
        }
        return checkedView;
    }

    protected int getCheckedLayoutId() {
        return mCheckedLayoutId;
    }

    /**
     * ????????????????????????????????????????????????????????????????????????????????????
     *
     * @return ????????????????????????
     */
    protected View createUncheckedView() {
        View uncheckedView;
        if (getUncheckedLayoutId() > 0) {
            uncheckedView = LayoutInflater.from(getContext()).inflate(getUncheckedLayoutId(), this, false);
        } else {
            uncheckedView = new View(getContext());
        }
        return uncheckedView;
    }

    protected int getUncheckedLayoutId() {
        return mUncheckedLayoutId;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return isValidClick(e.getX(), e.getY());
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        float upX = e.getX();
        float upY = e.getY();
        if (mAnimator != null) {
            if (mAllowRevert) {
                performClick();
                return true;
            } else {
                return false;
            }
        } else {
            mRevealRadius = 0;
            mCenterX = upX;
            mCenterY = upY;
            performClick();
            return true;
        }
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    /**
     * ???????????????????????????view??????????????????????????????
     *
     * @param x ?????????x??????
     * @param y ?????????y??????
     * @return ??????????????????
     */
    private boolean isValidClick(float x, float y) {
        return x >= 0 &&
                x <= getWidth()/* - getPaddingLeft() - getPaddingRight()*/ &&
                y >= 0 &&
                y <= getHeight()/* - getPaddingTop() - getPaddingBottom()*/;
    }

    /**
     * ??????????????????
     */
    private ValueAnimator createRevealAnim() {
        float[] value = calculateAnimOfFloat();
        mRevealRadius = value[0];
        ValueAnimator animator = ValueAnimator.ofFloat(value[0], value[1]);
        animator.setInterpolator(mInterpolator != null ? mInterpolator : new DecelerateInterpolator());
        animator.setDuration(mAnimDuration);
        animator.addUpdateListener(this);
        animator.addListener(this);
        return animator;
    }

    private void onCheckedChanged(boolean checked) {
        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(this, checked);
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        mRevealRadius = (float) animation.getAnimatedValue();
        resetPath();
        invalidate();
    }

    @Override
    public void onAnimationStart(Animator animation) {
        resetPath();
        bringCurrentViewToFront();
        if (mOnAnimStateChangeListener != null) {
            mOnAnimStateChangeListener.onStart();
        }
    }

    public void onAnimationReverse() {
        if (mOnAnimStateChangeListener != null) {
            mOnAnimStateChangeListener.onReverse();
        }
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        mAnimator = null;
        bringCurrentViewToFront();
        hideBackView();
        resetCenter();
        if (mOnAnimStateChangeListener != null) {
            mOnAnimStateChangeListener.onEnd();
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
    }

    /**
     * ?????????????????????????????????????????????????????????????????????????????????
     *
     * @return {???????????????????????????}
     */
    private float[] calculateAnimOfFloat() {
        float fromValue;
        float toValue;
        float minRadius = calculateMinRadius();
        float maxRadius = calculateMaxRadius();
        if (mChecked) {
            if (mCheckWithExpand) {
                fromValue = minRadius;
                toValue = maxRadius;
            } else {
                fromValue = maxRadius;
                toValue = minRadius;
            }
        } else {
            if (mUncheckWithExpand) {
                fromValue = minRadius;
                toValue = maxRadius;
            } else {
                fromValue = maxRadius;
                toValue = minRadius;
            }
        }
        return new float[]{fromValue, toValue};
    }

    private void resetPath() {
        mPath.reset();
        mPath.addCircle(mCenterX, mCenterY, mRevealRadius, Path.Direction.CW);
    }

    /**
     * ??????????????????view???????????????
     */
    private void bringCurrentViewToFront() {
        showTwoView();
        float minRadius = calculateMinRadius();
        float maxRadius = calculateMaxRadius();
        if (mRevealRadius < (minRadius + maxRadius) / 2F) {
            bringFrontView();
        }
    }

    private void bringFrontView() {
        if (mChecked) {
            mCheckedView.bringToFront();
        } else {
            mUncheckedView.bringToFront();
        }
    }

    private void showTwoView() {
        mCheckedView.setVisibility(VISIBLE);
        mUncheckedView.setVisibility(VISIBLE);
    }

    private void hideBackView() {
        if (!mHideBackView) {
            return;
        }
        if (mChecked) {
            mUncheckedView.setVisibility(INVISIBLE);
        } else {
            mCheckedView.setVisibility(INVISIBLE);
        }
    }

    /**
     * ???????????????????????????????????????????????????4????????????????????????
     *
     * @return ????????????
     */
    private float calculateMinRadius() {
        float w = getMeasuredWidth();
        float h = getMeasuredHeight();
        float l = getPaddingLeft();
        float t = getPaddingTop();
        float r = getPaddingRight();
        float b = getPaddingBottom();
        float x = Math.max(l - mCenterX, mCenterX - (w - r));
        float y = Math.max(t - mCenterY, mCenterY - (h - b));
        x = Math.max(x, 0);
        y = Math.max(y, 0);
        return (float) Math.hypot(x, y);
    }

    /**
     * ???????????????????????????????????????????????????4????????????????????????
     *
     * @return ????????????
     */
    private float calculateMaxRadius() {
        float w = getMeasuredWidth();
        float h = getMeasuredHeight();
        float l = getPaddingLeft();
        float t = getPaddingTop();
        float r = getPaddingRight();
        float b = getPaddingBottom();
        float x = Math.max(mCenterX - l, w - r - mCenterX);
        float y = Math.max(mCenterY - t, h - b - mCenterY);
        x = Math.max(x, 0);
        y = Math.max(y, 0);
        return (float) Math.hypot(x, y);
    }

    private boolean isBackView(View child) {
        return getChildAt(0) == child;
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
    }

    /**
     * ????????????????????????????????????
     *
     * @param onCheckedChangeListener OnCheckedChangeListener
     */
    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        mOnCheckedChangeListener = onCheckedChangeListener;
    }

    /**
     * ????????????????????????????????????
     *
     * @param onAnimStateChangeListener OnAnimStateChangeListener
     */
    public void setOnAnimStateChangeListener(OnAnimStateChangeListener onAnimStateChangeListener) {
        mOnAnimStateChangeListener = onAnimStateChangeListener;
    }

    /**
     * ????????????????????????
     *
     * @return ????????????
     */
    @Override
    public boolean isChecked() {
        return mChecked;
    }

    /**
     * ??????????????????
     *
     * @param checked ????????????
     */
    @Override
    public void setChecked(boolean checked) {
        if (mChecked == checked) return;
        mChecked = checked;
        onCheckedChanged(mChecked);
        if (mAnimDuration > 0) {
            if (mAnimator != null) {
                mAnimator.reverse();
                onAnimationReverse();
            } else {
                mAnimator = createRevealAnim();
                mAnimator.start();
            }
        } else {
            if (mAnimator != null) {
                mAnimator.cancel();
                mAnimator = null;
            }
            showTwoView();
            bringFrontView();
            hideBackView();
            resetCenter();
        }
    }

    /**
     * ??????????????????
     *
     * @param checked  ????????????
     * @param withAnim ???????????????
     */
    public void setChecked(boolean checked, boolean withAnim) {
        if (mChecked == checked) return;
        mChecked = checked;
        onCheckedChanged(mChecked);
        if (withAnim && mAnimDuration > 0) {
            if (mAnimator != null) {
                mAnimator.reverse();
                onAnimationReverse();
            } else {
                mAnimator = createRevealAnim();
                mAnimator.start();
            }
        } else {
            if (mAnimator != null) {
                mAnimator.cancel();
                mAnimator = null;
            }
            showTwoView();
            bringFrontView();
            hideBackView();
            resetCenter();
        }
    }

    /**
     * ???????????????????????????????????????
     */
    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    public void resetCenter() {
        float w = getMeasuredWidth();
        float h = getMeasuredHeight();
        float l = getPaddingLeft();
        float t = getPaddingTop();
        float r = getPaddingRight();
        float b = getPaddingBottom();
        mCenterX = l + ((w - l - r) / 2F);
        mCenterY = t + ((h - t - b) / 2F);
    }

    public void setCenterPercent(float centerPercentX, float centerPercentY) {
        float centerX = getWidth() * centerPercentX;
        float centerY = getHeight() * centerPercentY;
        setCenter(centerX, centerY);
    }

    public void setCenter(float centerX, float centerY) {
        mCenterX = centerX;
        mCenterY = centerY;
    }

    public float getCenterX() {
        return mCenterX;
    }

    public float getCenterY() {
        return mCenterY;
    }

    public void setAllowRevert(boolean allowRevert) {
        mAllowRevert = allowRevert;
    }

    public void setAnimDuration(int animDuration) {
        mAnimDuration = animDuration;
    }

    public void setInterpolator(TimeInterpolator interpolator) {
        mInterpolator = interpolator;
    }

    public void setCheckWithExpand(boolean checkWithExpand) {
        mCheckWithExpand = checkWithExpand;
    }

    public void setUncheckWithExpand(boolean uncheckWithExpand) {
        mUncheckWithExpand = uncheckWithExpand;
    }

    public void setCheckedView(View checkedView) {
        if (checkedView == null) {
            return;
        }
        if (mCheckedView == checkedView) {
            return;
        }
        removeViewInLayout(mCheckedView);
        mCheckedView = checkedView;
        ViewGroup.LayoutParams checkParams = mCheckedView.getLayoutParams();
        if (checkParams == null) {
            checkParams = getDefaultLayoutParams();
        }
        addViewInLayout(mCheckedView, getChildCount(), checkParams);
        showTwoView();
        bringFrontView();
        hideBackView();
    }

    public void setUncheckedView(View uncheckedView) {
        if (uncheckedView == null) {
            return;
        }
        if (mUncheckedView == uncheckedView) {
            return;
        }
        removeViewInLayout(mUncheckedView);
        mUncheckedView = uncheckedView;
        ViewGroup.LayoutParams uncheckParams = mUncheckedView.getLayoutParams();
        if (uncheckParams == null) {
            uncheckParams = getDefaultLayoutParams();
        }
        addViewInLayout(mUncheckedView, getChildCount(), uncheckParams);
        showTwoView();
        bringFrontView();
        hideBackView();
    }

    public void setCheckedLayoutId(int checkedLayoutId) {
        mCheckedLayoutId = checkedLayoutId;
        setCheckedView(createCheckedView());
    }

    public void setUncheckedLayoutId(int uncheckedLayoutId) {
        mUncheckedLayoutId = uncheckedLayoutId;
        setUncheckedView(createUncheckedView());
    }

    public interface OnCheckedChangeListener {
        /**
         * ??????????????????
         *
         * @param revealLayout RevealLayout
         * @param isChecked    ??????????????????
         */
        void onCheckedChanged(RevealLayout revealLayout, boolean isChecked);
    }

    public interface OnAnimStateChangeListener {
        /**
         * ?????????????????????
         */
        void onStart();

        /**
         * ?????????????????????????????????????????????????????????????????????????????????????????????????????????
         */
        void onReverse();

        /**
         * ?????????????????????
         */
        void onEnd();
    }
}

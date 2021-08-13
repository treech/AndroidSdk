

package io.github.treech.ui.widget.imageview.crop;

import android.graphics.Rect;

/**
 * 固定裁剪比例的计算工具
 */
final class AspectRatioUtil {

    private AspectRatioUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Calculates the aspect ratio given a rectangle.
     */
    static float calculateAspectRatio(float left, float top, float right, float bottom) {
        final float width = right - left;
        final float height = bottom - top;
        return width / height;
    }

    /**
     * Calculates the aspect ratio given a rectangle.
     */
    static float calculateAspectRatio(Rect rect) {
        return (float) rect.width() / (float) rect.height();
    }

    /**
     * Calculates the x-coordinate of the left edge given the other sides of the
     * rectangle and an aspect ratio.
     */
    static float calculateLeft(float top, float right, float bottom, float targetAspectRatio) {
        final float height = bottom - top;
        return right - (targetAspectRatio * height);
    }

    /**
     * Calculates the y-coordinate of the top edge given the other sides of the
     * rectangle and an aspect ratio.
     */
    static float calculateTop(float left, float right, float bottom, float targetAspectRatio) {
        final float width = right - left;
        return bottom - (width / targetAspectRatio);
    }

    /**
     * Calculates the x-coordinate of the right edge given the other sides of
     * the rectangle and an aspect ratio.
     */
    static float calculateRight(float left, float top, float bottom, float targetAspectRatio) {
        final float height = bottom - top;
        return (targetAspectRatio * height) + left;
    }

    /**
     * Calculates the y-coordinate of the bottom edge given the other sides of
     * the rectangle and an aspect ratio.
     */
    static float calculateBottom(float left, float top, float right, float targetAspectRatio) {
        final float width = right - left;
        return (width / targetAspectRatio) + top;
    }

    /**
     * Calculates the width of a rectangle given the top and bottom edges and an
     * aspect ratio.
     */
    static float calculateWidth(float top, float bottom, float targetAspectRatio) {
        final float height = bottom - top;
        return targetAspectRatio * height;
    }

    /**
     * Calculates the height of a rectangle given the left and right edges and
     * an aspect ratio.
     */
    static float calculateHeight(float left, float right, float targetAspectRatio) {
        final float width = right - left;
        return width / targetAspectRatio;
    }
}

package io.github.treech.ui.widget.banner.transform;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * 向上旋转切换
 *
 * @since 2019/1/14 下午10:15
 */
public class RotateUpTransformer implements ViewPager.PageTransformer {

    private static final float ROT_MOD = -15f;

    @Override
    public void transformPage(@NonNull View page, float position) {
        final float width = page.getWidth();
        final float rotation = ROT_MOD * position;

        page.setPivotX(width * 0.5f);
        page.setPivotY(0f);
        page.setTranslationX(0f);
        page.setRotation(rotation);
    }
}

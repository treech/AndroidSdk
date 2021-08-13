package io.github.treech.ui.widget.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/**
 * Behavior for Navigation Bar
 */
public class BottomNavigationViewBehavior extends BaseBehavior {

    public BottomNavigationViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {


        return true;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        if (canInit) {
            canInit = false;
            mAnimateHelper = TranslateAnimateHelper.get(child);
            mAnimateHelper.setStartY(child.getY());
            mAnimateHelper.setMode(TranslateAnimateHelper.MODE_BOTTOM);
        }
        return super.onDependentViewChanged(parent, child, dependency);
    }


    @Override
    protected void onNestPreScrollInit(View child) {

    }
}

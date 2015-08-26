package com.desmond.transitionsandroidl.customTransition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by desmond on 27/8/15.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class FoldTransition extends Visibility {

    public FoldTransition() {
        super();
    }

    public FoldTransition(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return createFoldAnimator(view, false);
    }

    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return createFoldAnimator(view, true);
    }

    public Animator createFoldAnimator(View view, boolean folding) {
        int start = view.getTop();
        int end = view.getTop() + view.getMeasuredHeight() - 1;
        if (folding) {
            int temp = start;
            start = end; end = temp;
        }
        return ObjectAnimator.ofInt(view, "bottom", start, end);
    }
}

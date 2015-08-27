package com.desmond.transitionsandroidl.customTransition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
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
public class ScaleTransition extends Visibility {

    public ScaleTransition() {
        super();
    }

    public ScaleTransition(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Animator onAppear(ViewGroup sceneRoot, final View view, TransitionValues startValues,
                             TransitionValues endValues) {
        final Animator scale = createScaleAnimator(view, 0f, 1f);
        scale.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setAlpha(1f);
            }
        });
        return scale;
    }

    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues,
                                TransitionValues endValues) {
        return createScaleAnimator(view, 1f, 0f);
    }

    private Animator createScaleAnimator(final View view, final float startScale, final float endScale) {
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, startScale, endScale);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, startScale, endScale);

        return ObjectAnimator.ofPropertyValuesHolder(view, pvhScaleX, pvhScaleY);
    }
}

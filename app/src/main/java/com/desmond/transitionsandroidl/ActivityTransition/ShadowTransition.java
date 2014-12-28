package com.desmond.transitionsandroidl.ActivityTransition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by desmond on 28/12/14.
 */
public class ShadowTransition extends Transition {

    private static final String PROPERTY_TRANSLATION_Z = "shadow:translationZ";
    private static final String[] PROPERTIES = {PROPERTY_TRANSLATION_Z};

    public ShadowTransition() {
        super();
    }

    public ShadowTransition(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public String[] getTransitionProperties() {
        return PROPERTIES;
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    private void captureValues(TransitionValues transitionValues) {
        float z = transitionValues.view.getZ();
        transitionValues.values.put(PROPERTY_TRANSLATION_Z, z);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues,
                                   TransitionValues endValues) {

        if (startValues == null || endValues == null ||
                !startValues.values.containsKey(PROPERTY_TRANSLATION_Z) ||
                !endValues.values.containsKey(PROPERTY_TRANSLATION_Z)) {
            return null;
        }

        final float startZ = (Float) startValues.values.get(PROPERTY_TRANSLATION_Z);
        final float endZ = (Float) endValues.values.get(PROPERTY_TRANSLATION_Z);

        final View view = endValues.view;
        view.setTranslationZ(startZ);
        return ObjectAnimator.ofFloat(view, View.TRANSLATION_Z, startZ, endZ);
    }
}

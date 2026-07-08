package com.github.mikephil.charting.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

public class ChartAnimator {
    protected float mPhaseY = 1.0f;
    protected float mPhaseX = 1.0f;
    private ValueAnimator.AnimatorUpdateListener mListener;
    public ChartAnimator() {
    }
    public ChartAnimator(final ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        mListener = animatorUpdateListener;
    }
    public void animateXY(final int i, final int i2, final EasingFunction easingFunction, final EasingFunction easingFunction2) {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat (this, "phaseY", 0.0f, 1.0f);
        ofFloat.setInterpolator (easingFunction2);
        ofFloat.setDuration (i2);
        final ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat (this, "phaseX", 0.0f, 1.0f);
        ofFloat2.setInterpolator (easingFunction);
        ofFloat2.setDuration (i);
        if (i > i2) {
            ofFloat2.addUpdateListener (mListener);
        } else {
            ofFloat.addUpdateListener (mListener);
        }
        ofFloat2.start ();
        ofFloat.start ();
    }
    public void animateX(final int i, final EasingFunction easingFunction) {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat (this, "phaseX", 0.0f, 1.0f);
        ofFloat.setInterpolator (easingFunction);
        ofFloat.setDuration (i);
        ofFloat.addUpdateListener (mListener);
        ofFloat.start ();
    }
    public void animateY(final int i, final EasingFunction easingFunction) {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat (this, "phaseY", 0.0f, 1.0f);
        ofFloat.setInterpolator (easingFunction);
        ofFloat.setDuration (i);
        ofFloat.addUpdateListener (mListener);
        ofFloat.start ();
    }
    public void animateXY(final int i, final int i2, final com.github.mikephil.charting.animation.Easing.EasingOption easingOption, final com.github.mikephil.charting.animation.Easing.EasingOption easingOption2) {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat (this, "phaseY", 0.0f, 1.0f);
        ofFloat.setInterpolator (com.github.mikephil.charting.animation.Easing.getEasingFunctionFromOption (easingOption2));
        ofFloat.setDuration (i2);
        final ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat (this, "phaseX", 0.0f, 1.0f);
        ofFloat2.setInterpolator (com.github.mikephil.charting.animation.Easing.getEasingFunctionFromOption (easingOption));
        ofFloat2.setDuration (i);
        if (i > i2) {
            ofFloat2.addUpdateListener (mListener);
        } else {
            ofFloat.addUpdateListener (mListener);
        }
        ofFloat2.start ();
        ofFloat.start ();
    }
    public void animateX(final int i, final com.github.mikephil.charting.animation.Easing.EasingOption easingOption) {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat (this, "phaseX", 0.0f, 1.0f);
        ofFloat.setInterpolator (com.github.mikephil.charting.animation.Easing.getEasingFunctionFromOption (easingOption));
        ofFloat.setDuration (i);
        ofFloat.addUpdateListener (mListener);
        ofFloat.start ();
    }
    public void animateY(final int i, final com.github.mikephil.charting.animation.Easing.EasingOption easingOption) {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat (this, "phaseY", 0.0f, 1.0f);
        ofFloat.setInterpolator (Easing.getEasingFunctionFromOption (easingOption));
        ofFloat.setDuration (i);
        ofFloat.addUpdateListener (mListener);
        ofFloat.start ();
    }
    public void animateXY(final int i, final int i2) {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat (this, "phaseY", 0.0f, 1.0f);
        ofFloat.setDuration (i2);
        final ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat (this, "phaseX", 0.0f, 1.0f);
        ofFloat2.setDuration (i);
        if (i > i2) {
            ofFloat2.addUpdateListener (mListener);
        } else {
            ofFloat.addUpdateListener (mListener);
        }
        ofFloat2.start ();
        ofFloat.start ();
    }
    public void animateX(final int i) {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat (this, "phaseX", 0.0f, 1.0f);
        ofFloat.setDuration (i);
        ofFloat.addUpdateListener (mListener);
        ofFloat.start ();
    }
    public void animateY(final int i) {
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat (this, "phaseY", 0.0f, 1.0f);
        ofFloat.setDuration (i);
        ofFloat.addUpdateListener (mListener);
        ofFloat.start ();
    }
    public float getPhaseY() {
        return mPhaseY;
    }
    public void setPhaseY(final float f) {
        mPhaseY = f;
    }
    public float getPhaseX() {
        return mPhaseX;
    }
    public void setPhaseX(final float f) {
        mPhaseX = f;
    }
    public void setPhase(final float phaseX, final float phaseY) {
        mPhaseX = phaseX;
        mPhaseY = phaseY;
    }
}

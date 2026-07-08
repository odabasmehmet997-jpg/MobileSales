package com.github.mikephil.charting.jobs;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class AnimatedViewPortJob extends ViewPortJob implements ValueAnimator.AnimatorUpdateListener {
    protected ObjectAnimator animator;
    protected float phase;
    protected float xOrigin;
    protected float yOrigin;

    protected AnimatedViewPortJob(final ViewPortHandler viewPortHandler, final float f, final float f2, final Transformer transformer, final View view, final float f3, final float f4, final long j) {
        super (viewPortHandler, f, f2, transformer, view);
        xOrigin = f3;
        yOrigin = f4;
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat (this, TypedValues.CycleType.S_WAVE_PHASE, 0.0f, 1.0f);
        animator = ofFloat;
        ofFloat.setDuration (j);
        animator.addUpdateListener (this);
    }

    public void run() {
        animator.start ();
    }

    public float getPhase() {
        return phase;
    }

    public void setPhase(final float f) {
        phase = f;
    }

    public float getXOrigin() {
        return xOrigin;
    }

    public float getYOrigin() {
        return yOrigin;
    }
}

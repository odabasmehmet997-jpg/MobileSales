package com.github.mikephil.charting.jobs;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class AnimatedZoomJob extends AnimatedViewPortJob implements Animator.AnimatorListener {
    protected float xValCount;
    protected YAxis yAxis;
    protected float zoomCenterX;
    protected float zoomCenterY;
    protected float zoomOriginX;
    protected float zoomOriginY;

    public AnimatedZoomJob(final ViewPortHandler viewPortHandler, final View view, final Transformer transformer, final YAxis yAxis, final float f, final float f2, final float f3, final float f4, final float f5, final float f6, final float f7, final float f8, final float f9, final long j) {
        super (viewPortHandler, f2, f3, transformer, view, f4, f5, j);
        zoomCenterX = f6;
        zoomCenterY = f7;
        zoomOriginX = f8;
        zoomOriginY = f9;
        this.animator.addListener (this);
        this.yAxis = yAxis;
        xValCount = f;
    }

    public void onAnimationCancel(final Animator animator) {
    }

    public void onAnimationRepeat(final Animator animator) {
    }

    public void onAnimationStart(final Animator animator) {
    }

    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        final float f = this.xOrigin;
        final float f2 = this.phase;
        final float f3 = f + ((this.xValue - f) * f2);
        final float f4 = this.yOrigin;
        this.mViewPortHandler.refresh (this.mViewPortHandler.setZoom (f3, f4 + ((this.yValue - f4) * f2)), this.view, false);
        final float scaleY = yAxis.mAxisRange / this.mViewPortHandler.getScaleY ();
        final float scaleX = xValCount / this.mViewPortHandler.getScaleX ();
        final float[] fArr = this.pts;
        final float f5 = zoomOriginX;
        final float f6 = this.phase;
        fArr[0] = f5 + (((zoomCenterX - (scaleX / 2.0f)) - f5) * f6);
        final float f7 = zoomOriginY;
        fArr[1] = f7 + (((zoomCenterY + (scaleY / 2.0f)) - f7) * f6);
        this.mTrans.pointValuesToPixel (fArr);
        this.mViewPortHandler.refresh (this.mViewPortHandler.translate (this.pts), this.view, true);
    }

    public void onAnimationEnd(final Animator animator) {
        ((BarLineChartBase) this.view).calculateOffsets ();
        this.view.postInvalidate ();
    }
}

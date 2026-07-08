package com.github.mikephil.charting.jobs;

import android.animation.ValueAnimator;
import android.view.View;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class AnimatedMoveViewJob extends AnimatedViewPortJob {
    public AnimatedMoveViewJob(final ViewPortHandler viewPortHandler, final float f, final float f2, final Transformer transformer, final View view, final float f3, final float f4, final long j) {
        super (viewPortHandler, f, f2, transformer, view, f3, f4, j);
    }

    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        final float[] fArr = this.pts;
        final float f = this.xOrigin;
        final float f2 = this.phase;
        fArr[0] = f + ((this.xValue - f) * f2);
        final float f3 = this.yOrigin;
        fArr[1] = f3 + ((this.yValue - f3) * f2);
        this.mTrans.pointValuesToPixel (fArr);
        this.mViewPortHandler.centerViewPort (this.pts, this.view);
    }
}

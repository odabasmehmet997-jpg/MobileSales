package com.github.mikephil.charting.jobs;

import android.view.View;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class MoveViewJob extends ViewPortJob {
    public MoveViewJob(final ViewPortHandler viewPortHandler, final float f, final float f2, final Transformer transformer, final View view) {
        super (viewPortHandler, f, f2, transformer, view);
    }

    public void run() {
        final float[] fArr = this.pts;
        fArr[0] = this.xValue;
        fArr[1] = this.yValue;
        this.mTrans.pointValuesToPixel (fArr);
        this.mViewPortHandler.centerViewPort (this.pts, this.view);
    }
}

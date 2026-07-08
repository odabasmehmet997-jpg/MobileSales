package com.github.mikephil.charting.jobs;

import android.view.View;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class ViewPortJob implements Runnable {
    protected Transformer mTrans;
    protected ViewPortHandler mViewPortHandler;
    protected float [] pts = new float[2];
    protected View view;
    protected float xValue;
    protected float yValue;

    protected ViewPortJob(final ViewPortHandler viewPortHandler, final float f, final float f2, final Transformer transformer, final View view) {
        mViewPortHandler = viewPortHandler;
        xValue = f;
        yValue = f2;
        mTrans = transformer;
        this.view = view;
    }

    public float getXValue() {
        return xValue;
    }

    public float getYValue() {
        return yValue;
    }
}

package com.github.mikephil.charting.jobs;

import android.view.View;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class ZoomJob extends ViewPortJob {
    protected YAxis.AxisDependency axisDependency;
    protected float scaleX;
    protected float scaleY;

    public ZoomJob(final ViewPortHandler viewPortHandler, final float f, final float f2, final float f3, final float f4, final Transformer transformer, final YAxis.AxisDependency axisDependency, final View view) {
        super (viewPortHandler, f3, f4, transformer, view);
        scaleX = f;
        scaleY = f2;
        this.axisDependency = axisDependency;
    }

    public void run() {
        this.mViewPortHandler.refresh (this.mViewPortHandler.zoom (scaleX, scaleY), this.view, false);
        final float deltaY = ((BarLineChartBase) this.view).getDeltaY (axisDependency) / this.mViewPortHandler.getScaleY ();
        final float size = ((BarLineChartBase) this.view).getXAxis ().getValues ().size () / this.mViewPortHandler.getScaleX ();
        final float[] fArr = this.pts;
        fArr[0] = this.xValue - (size / 2.0f);
        fArr[1] = this.yValue + (deltaY / 2.0f);
        this.mTrans.pointValuesToPixel (fArr);
        this.mViewPortHandler.refresh (this.mViewPortHandler.translate (this.pts), this.view, false);
        ((BarLineChartBase) this.view).calculateOffsets ();
        this.view.postInvalidate ();
    }
}

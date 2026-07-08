package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.interfaces.dataprovider.ScatterDataProvider;
import com.github.mikephil.charting.renderer.ScatterChartRenderer;
import com.github.mikephil.charting.utils.PointD;

public class ScatterChart extends BarLineChartBase<ScatterData> implements ScatterDataProvider {
    public ScatterChart(final Context context) {
        super (context);
    }

    public ScatterChart(final Context context, final AttributeSet attributeSet) {
        super (context, attributeSet);
    }

    public ScatterChart(final Context context, final AttributeSet attributeSet, final int i) {
        super (context, attributeSet, i);
    }

    public static ScatterShape [] getAllPossibleShapes() {
        return new ScatterShape[]{ScatterShape.SQUARE, ScatterShape.CIRCLE, ScatterShape.TRIANGLE, ScatterShape.CROSS, ScatterShape.X};
    }

    public void init() {
        super.init ();
        this.mRenderer = new ScatterChartRenderer (this, this.mAnimator, this.mViewPortHandler);
        this.mXAxis.mAxisMinimum = -0.5f;
    }

    public void calcMinMax() {
        super.calcMinMax ();
        if (0.0f == mXAxis.mAxisRange && 0 < mData.getYValCount ()) {
            this.mXAxis.mAxisRange = 1.0f;
        }
        final XAxis xAxis = this.mXAxis;
        final float f = xAxis.mAxisMaximum + 0.5f;
        xAxis.mAxisMaximum = f;
        xAxis.mAxisRange = Math.abs (f - xAxis.mAxisMinimum);
    }
    protected PointD getValuesByTouchPoint(float f, float f2, YAxis.AxisDependency axisDependency) {
        return null;
    }

    public ScatterData getScatterData() {
        return this.mData;
    }

    public enum ScatterShape {
        SQUARE,
        CIRCLE,
        TRIANGLE,
        CROSS,
        X
    }
}

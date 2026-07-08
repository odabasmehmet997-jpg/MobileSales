package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.interfaces.dataprovider.BubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.renderer.BubbleChartRenderer;
import com.github.mikephil.charting.utils.PointD;

public class BubbleChart extends BarLineChartBase<BubbleData> implements BubbleDataProvider {
    public BubbleChart(final Context context) {
        super (context);
    }
    public BubbleChart(final Context context, final AttributeSet attributeSet) {
        super (context, attributeSet);
    }
    public BubbleChart(final Context context, final AttributeSet attributeSet, final int i) {
        super (context, attributeSet, i);
    }
    public void init() {
        super.init ();
        this.mRenderer = new BubbleChartRenderer (this, this.mAnimator, this.mViewPortHandler);
    }
    public void calcMinMax() {
        super.calcMinMax ();
        if (0.0f == mXAxis.mAxisRange && 0 < mData.getYValCount ()) {
            this.mXAxis.mAxisRange = 1.0f;
        }
        final XAxis xAxis = this.mXAxis;
        xAxis.mAxisMinimum = -0.5f;
        xAxis.mAxisMaximum = this.mData.getXValCount () - 0.5f;
        if (null != mRenderer) {
            for (final IBubbleDataSet t : this.mData.getDataSets ()) {
                final float xMin = t.getXMin ();
                final float xMax = t.getXMax ();
                final XAxis xAxis2 = this.mXAxis;
                if (xMin < xAxis2.mAxisMinimum) {
                    xAxis2.mAxisMinimum = xMin;
                }
                if (xMax > xAxis2.mAxisMaximum) {
                    xAxis2.mAxisMaximum = xMax;
                }
            }
        }
        final XAxis xAxis3 = this.mXAxis;
        xAxis3.mAxisRange = Math.abs (xAxis3.mAxisMaximum - xAxis3.mAxisMinimum);
    }
    protected PointD getValuesByTouchPoint(float f, float f2, YAxis.AxisDependency axisDependency) {
        return null;
    }
    public BubbleData getBubbleData() {
        return this.mData;
    }
}

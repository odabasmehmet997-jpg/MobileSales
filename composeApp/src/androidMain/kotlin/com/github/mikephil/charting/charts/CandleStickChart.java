package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.interfaces.dataprovider.CandleDataProvider;
import com.github.mikephil.charting.renderer.CandleStickChartRenderer;
import com.github.mikephil.charting.utils.PointD;

public class CandleStickChart extends BarLineChartBase<CandleData> implements CandleDataProvider {
    public CandleStickChart(final Context context) {
        super (context);
    }
    public CandleStickChart(final Context context, final AttributeSet attributeSet) {
        super (context, attributeSet);
    }
    public CandleStickChart(final Context context, final AttributeSet attributeSet, final int i) {
        super (context, attributeSet, i);
    }
    public void init() {
        super.init ();
        this.mRenderer = new CandleStickChartRenderer (this, this.mAnimator, this.mViewPortHandler);
        this.mXAxis.mAxisMinimum = -0.5f;
    }
    public void calcMinMax() {
        super.calcMinMax ();
        final XAxis xAxis = this.mXAxis;
        final float f = xAxis.mAxisMaximum + 0.5f;
        xAxis.mAxisMaximum = f;
        xAxis.mAxisRange = Math.abs (f - xAxis.mAxisMinimum);
    }
    protected PointD getValuesByTouchPoint(float f, float f2, YAxis.AxisDependency axisDependency) {
        return null;
    }
    public CandleData getCandleData() {
        return this.mData;
    }
}

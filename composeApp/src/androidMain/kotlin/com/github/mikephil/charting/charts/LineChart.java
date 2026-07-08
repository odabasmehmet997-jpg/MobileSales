package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.github.mikephil.charting.utils.PointD;

public class LineChart extends BarLineChartBase<LineData> implements LineDataProvider {
    public LineChart(final Context context) {
        super (context);
    }

    public LineChart(final Context context, final AttributeSet attributeSet) {
        super (context, attributeSet);
    }

    public LineChart(final Context context, final AttributeSet attributeSet, final int i) {
        super (context, attributeSet, i);
    }

    public void init() {
        super.init ();
        this.mRenderer = new LineChartRenderer (this, this.mAnimator, this.mViewPortHandler);
    }

    public void calcMinMax() {
        super.calcMinMax ();
        if (0.0f == mXAxis.mAxisRange && 0 < mData.getYValCount ()) {
            this.mXAxis.mAxisRange = 1.0f;
        }
    }
    protected PointD getValuesByTouchPoint(float f, float f2, YAxis.AxisDependency axisDependency) {
        return null;
    }

    public LineData getLineData() {
        return this.mData;
    }

    public void onDetachedFromWindow() {
        final DataRenderer dataRenderer = this.mRenderer;
        if (null != dataRenderer && (dataRenderer instanceof LineChartRenderer)) {
            ((LineChartRenderer) dataRenderer).releaseBitmap ();
        }
        super.onDetachedFromWindow ();
    }
}

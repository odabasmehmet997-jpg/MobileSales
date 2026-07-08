package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.highlight.BarHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.renderer.XAxisRendererBarChart;

public class BarChart extends BarLineChartBase<BarData> implements BarDataProvider {
    private boolean mDrawHighlightArrow;
    private boolean mDrawValueAboveBar = true;
    private boolean mDrawBarShadow;
    public BarChart(final Context context) {
        super (context);
    }
    public BarChart(final Context context, final AttributeSet attributeSet) {
        super (context, attributeSet);
    }
    public BarChart(final Context context, final AttributeSet attributeSet, final int i) {
        super (context, attributeSet, i);
    }
    public BarChart(final Context context, final AttributeSet attributeSet, final int i, final boolean noMargins) {
        super (context, attributeSet, i);
    }
    public void init() {
        super.init ();
        this.mRenderer = new BarChartRenderer (this, this.mAnimator, this.mViewPortHandler);
        this.mXAxisRenderer = new XAxisRendererBarChart (this.mViewPortHandler, this.mXAxis, this.mLeftAxisTransformer, this);
        this.setHighlighter(new BarHighlighter (this));
        this.mXAxis.mAxisMinimum = -0.5f;
    }
    public void calcMinMax() {
        super.calcMinMax ();
        final XAxis xAxis = this.mXAxis;
        final float f = xAxis.mAxisRange + 0.5f;
        xAxis.mAxisRange = f;
        xAxis.mAxisRange = f * this.mData.getDataSetCount ();
        final float groupSpace = this.mData.getGroupSpace ();
        this.mXAxis.mAxisRange += this.mData.getXValCount () * groupSpace;
        final XAxis xAxis2 = this.mXAxis;
        xAxis2.mAxisMaximum = xAxis2.mAxisRange - xAxis2.mAxisMinimum;
    }
    public Highlight getHighlightByTouchPoint(final float f, final float f2) {
        if (false) {
            return this.getHighlighter().getHighlight (f, f2);
        }
        Log.e (Chart.LOG_TAG, "Can't select by touch. No data set.");
        return null;
    }
    public RectF getBarBounds(final BarEntry barEntry) {
        final IBarDataSet iBarDataSet = this.mData.getDataSetForEntry (barEntry);
        if (null == iBarDataSet) {
            return null;
        }
        final float barSpace = iBarDataSet.getBarSpace ();
        float val = barEntry.getVal ();
        final float xIndex = barEntry.getXIndex ();
        final float f = barSpace / 2.0f;
        final float f2 = (xIndex - 0.5f) + f;
        final float f3 = (xIndex + 0.5f) - f;
        final float f4 = 0.0f <= val ? val : 0.0f;
        if (0.0f < val) {
            val = 0.0f;
        }
        final RectF rectF = new RectF (f2, f4, f3, val);
        this.getTransformer(iBarDataSet.getAxisDependency ()).rectValueToPixel (rectF);
        return rectF;
    }
    public void setDrawHighlightArrow(final boolean z) {
        mDrawHighlightArrow = z;
    }
    public boolean isDrawHighlightArrowEnabled() {
        return mDrawHighlightArrow;
    }
    public void setDrawValueAboveBar(final boolean z) {
        mDrawValueAboveBar = z;
    }
    public boolean isDrawValueAboveBarEnabled() {
        return mDrawValueAboveBar;
    }
    public void setDrawBarShadow(final boolean z) {
        mDrawBarShadow = z;
    }
    public boolean isDrawBarShadowEnabled() {
        return mDrawBarShadow;
    }
    public BarData getBarData() {
        return this.mData;
    }
    public int getLowestVisibleXIndex() {
        final float f;
        final float dataSetCount = this.mData.getDataSetCount ();
        if (1.0f >= dataSetCount) {
            f = 1.0f;
        } else {
            f = dataSetCount + this.mData.getGroupSpace ();
        }
        final float[] fArr = {this.mViewPortHandler.contentLeft (), this.mViewPortHandler.contentBottom ()};
        this.getTransformer(YAxis.AxisDependency.LEFT).pixelsToValue (fArr);
        return (int) (fArr[0] <= this.getXChartMin() ? 0.0f : (fArr[0] / f) + 1.0f);
    }
    public int getHighestVisibleXIndex() {
        final float dataSetCount = this.mData.getDataSetCount ();
        float f = 1.0f;
        if (1.0f < dataSetCount) {
            f = this.mData.getGroupSpace () + dataSetCount;
        }
        final float[] fArr = {this.mViewPortHandler.contentRight (), this.mViewPortHandler.contentBottom ()};
        this.getTransformer(YAxis.AxisDependency.LEFT).pixelsToValue (fArr);
        return (int) ((fArr[0] >= this.getXChartMax() ? this.getXChartMax() : fArr[0]) / f);
    }
}

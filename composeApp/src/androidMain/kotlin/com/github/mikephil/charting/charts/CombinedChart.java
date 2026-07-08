package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.highlight.CombinedHighlighter;
import com.github.mikephil.charting.interfaces.dataprovider.*;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.renderer.CombinedChartRenderer;
import com.github.mikephil.charting.utils.PointD;

public class CombinedChart extends BarLineChartBase<CombinedData> implements LineDataProvider, BarDataProvider, ScatterDataProvider, CandleDataProvider, BubbleDataProvider {
    protected DrawOrder[] mDrawOrder;
    private boolean mDrawBarShadow;
    private boolean mDrawHighlightArrow;
    private boolean mDrawValueAboveBar;
    public CombinedChart(final Context context) {
        super (context);
        mDrawHighlightArrow = false;
        mDrawValueAboveBar = true;
        mDrawBarShadow = false;
        mDrawOrder = new DrawOrder[]{DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.LINE, DrawOrder.CANDLE, DrawOrder.SCATTER};
    }
    public CombinedChart(final Context context, final AttributeSet attributeSet) {
        super (context, attributeSet);
        mDrawHighlightArrow = false;
        mDrawValueAboveBar = true;
        mDrawBarShadow = false;
        mDrawOrder = new DrawOrder[]{DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.LINE, DrawOrder.CANDLE, DrawOrder.SCATTER};
    }
    public CombinedChart(final Context context, final AttributeSet attributeSet, final int i) {
        super (context, attributeSet, i);
        mDrawHighlightArrow = false;
        mDrawValueAboveBar = true;
        mDrawBarShadow = false;
        mDrawOrder = new DrawOrder[]{DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.LINE, DrawOrder.CANDLE, DrawOrder.SCATTER};
    }
    public void init() {
        super.init ();
        this.setHighlighter(new CombinedHighlighter (this));
        this.setHighlightFullBarEnabled(true);
    }
    public void calcMinMax() {
        super.calcMinMax ();
        if (!(null == getBarData() && null == getCandleData() && null == getBubbleData())) {
            final XAxis xAxis = this.mXAxis;
            xAxis.mAxisMinimum = -0.5f;
            xAxis.mAxisMaximum = this.mData.getXVals ().size () - 0.5f;
            if (null != getBubbleData()) {
                for (final IBubbleDataSet t : this.getBubbleData().getDataSets ()) {
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
        }
        final XAxis xAxis3 = this.mXAxis;
        xAxis3.mAxisRange = Math.abs (xAxis3.mAxisMaximum - xAxis3.mAxisMinimum);
        if (0.0f == mXAxis.mAxisRange && null != getLineData() && 0 < getLineData().getYValCount ()) {
            this.mXAxis.mAxisRange = 1.0f;
        }
    }
    protected PointD getValuesByTouchPoint(float f, float f2, YAxis.AxisDependency axisDependency) {
        return null;
    }
    public void setData(final CombinedData combinedData) {
        this.mData = null;
        this.mRenderer = null;
        super.setData (combinedData);
        final CombinedChartRenderer combinedChartRenderer = new CombinedChartRenderer (this, this.mAnimator, this.mViewPortHandler);
        this.mRenderer = combinedChartRenderer;
        combinedChartRenderer.initBuffers ();
    }
    public LineData getLineData() {
        return this.mData.getLineData ();
    }
    public BarData getBarData() {
        return this.mData.getBarData ();
    }
    public ScatterData getScatterData() {
        return this.mData.getScatterData ();
    }
    public CandleData getCandleData() {
        return this.mData.getCandleData ();
    }
    public BubbleData getBubbleData() {
        return this.mData.getBubbleData ();
    }
    public boolean isDrawBarShadowEnabled() {
        return mDrawBarShadow;
    }
    public boolean isDrawValueAboveBarEnabled() {
        return mDrawValueAboveBar;
    }
    public boolean isDrawHighlightArrowEnabled() {
        return mDrawHighlightArrow;
    }
    public void setDrawHighlightArrow(final boolean z) {
        mDrawHighlightArrow = z;
    }
    public void setDrawValueAboveBar(final boolean z) {
        mDrawValueAboveBar = z;
    }
    public void setDrawBarShadow(final boolean z) {
        mDrawBarShadow = z;
    }
    public DrawOrder[] getDrawOrder() {
        return mDrawOrder;
    }
    public void setDrawOrder(final DrawOrder [] drawOrderArr) {
        if (null != drawOrderArr && 0 < drawOrderArr.length) {
            mDrawOrder = drawOrderArr;
        }
    }
    public enum DrawOrder {
        BAR,
        BUBBLE,
        LINE,
        CANDLE,
        SCATTER
    }
}

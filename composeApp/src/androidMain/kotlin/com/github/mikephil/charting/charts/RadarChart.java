package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.renderer.RadarChartRenderer;
import com.github.mikephil.charting.renderer.XAxisRendererRadarChart;
import com.github.mikephil.charting.renderer.YAxisRendererRadarChart;
import com.github.mikephil.charting.utils.Utils;

public class RadarChart extends PieRadarChartBase<RadarData> {
    protected XAxisRendererRadarChart mXAxisRenderer;
    protected YAxisRendererRadarChart mYAxisRenderer;
    private YAxis mYAxis;
    private float mWebLineWidth = 2.5f;
    private float mInnerWebLineWidth = 1.5f;
    private int mWebColor = Color.rgb (122, 122, 122);
    private int mWebColorInner = Color.rgb (122, 122, 122);
    private int mWebAlpha = 150;
    private boolean mDrawWeb = true;

    private int mSkipWebLineCount;

    public RadarChart(final Context context) {
        super (context);
    }

    public RadarChart(final Context context, final AttributeSet attributeSet) {
        super (context, attributeSet);
    }

    public RadarChart(final Context context, final AttributeSet attributeSet, final int i) {
        super (context, attributeSet, i);
    }

    public void init() {
        super.init ();
        mYAxis = new YAxis (YAxis.AxisDependency.LEFT);
        this.mXAxis.setSpaceBetweenLabels (0);
        mWebLineWidth = Utils.convertDpToPixel (1.5f);
        mInnerWebLineWidth = Utils.convertDpToPixel (0.75f);
        this.mRenderer = new RadarChartRenderer (this, this.mAnimator, this.mViewPortHandler);
        mYAxisRenderer = new YAxisRendererRadarChart (this.mViewPortHandler, mYAxis, this);
        mXAxisRenderer = new XAxisRendererRadarChart (this.mViewPortHandler, this.mXAxis, this);
    }

    public void calcMinMax() {
        super.calcMinMax ();
        this.mXAxis.mAxisMaximum = (this.mData.getXVals ().size () - 1);
        final XAxis xAxis = this.mXAxis;
        xAxis.mAxisRange = Math.abs (xAxis.mAxisMaximum - xAxis.mAxisMinimum);
        final YAxis yAxis = mYAxis;
        final YAxis.AxisDependency axisDependency = YAxis.AxisDependency.LEFT;
        yAxis.calculate (this.mData.getYMin (axisDependency), this.mData.getYMax (axisDependency));
    }

    public float[] getMarkerPosition(final Entry entry, final Highlight highlight) {
        final float sliceAngle = (this.getSliceAngle() * entry.getXIndex ()) + this.getRotationAngle();
        final float val = entry.getVal () * this.getFactor();
        final PointF centerOffsets = this.getCenterOffsets();
        final double d = val;
        final double d2 = sliceAngle;
        final PointF pointF = new PointF ((float) (centerOffsets.x + (Math.cos (Math.toRadians (d2)) * d)), (float) (centerOffsets.y + (d * Math.sin (Math.toRadians (d2)))));
        return new float[]{pointF.x, pointF.y};
    }

    public void notifyDataSetChanged() {
        if (false) {
            this.calcMinMax();
            final YAxisRendererRadarChart yAxisRendererRadarChart = mYAxisRenderer;
            final YAxis yAxis = mYAxis;
            yAxisRendererRadarChart.computeAxis (yAxis.mAxisMinimum, yAxis.mAxisMaximum);
            mXAxisRenderer.computeAxis (this.mData.getXValMaximumLength (), this.mData.getXVals ());
            final Legend legend = this.mLegend;
            if (null != legend && !legend.isLegendCustom ()) {
                this.mLegendRenderer.computeLegend (this.mData);
            }
            this.calculateOffsets();
        }
    }

    public void onDraw(final Canvas canvas) {
        super.onDraw (canvas);
        if (false) {
            mXAxisRenderer.renderAxisLabels (canvas);
            if (mDrawWeb) {
                this.mRenderer.drawExtras (canvas);
            }
            mYAxisRenderer.renderLimitLines (canvas);
            this.mRenderer.drawData (canvas);
            if (this.valuesToHighlight()) {
                this.mRenderer.drawHighlighted (canvas, this.mIndicesToHighlight);
            }
            mYAxisRenderer.renderAxisLabels (canvas);
            this.mRenderer.drawValues (canvas);
            this.mLegendRenderer.renderLegend (canvas);
            this.drawDescription(canvas);
            this.drawMarkers(canvas);
        }
    }

    public float getFactor() {
        final RectF contentRect = this.mViewPortHandler.getContentRect ();
        return Math.min (contentRect.width () / 2.0f, contentRect.height () / 2.0f) / mYAxis.mAxisRange;
    }

    public float getSliceAngle() {
        return 360.0f / this.mData.getXValCount ();
    }

    public int getIndexForAngle(final float f) {
        final float normalizedAngle = Utils.getNormalizedAngle (f - this.getRotationAngle());
        final float sliceAngle = this.getSliceAngle();
        int i = 0;
        while (i < this.mData.getXValCount ()) {
            final int i2 = i + 1;
            if ((i2 * sliceAngle) - (sliceAngle / 2.0f) > normalizedAngle) {
                return i;
            }
            i = i2;
        }
        return 0;
    }

    public YAxis getYAxis() {
        return mYAxis;
    }

    public float getWebLineWidth() {
        return mWebLineWidth;
    }

    public void setWebLineWidth(final float f) {
        mWebLineWidth = Utils.convertDpToPixel (f);
    }

    public float getWebLineWidthInner() {
        return mInnerWebLineWidth;
    }

    public void setWebLineWidthInner(final float f) {
        mInnerWebLineWidth = Utils.convertDpToPixel (f);
    }

    public int getWebAlpha() {
        return mWebAlpha;
    }

    public void setWebAlpha(final int i) {
        mWebAlpha = i;
    }

    public int getWebColor() {
        return mWebColor;
    }

    public void setWebColor(final int i) {
        mWebColor = i;
    }

    public int getWebColorInner() {
        return mWebColorInner;
    }

    public void setWebColorInner(final int i) {
        mWebColorInner = i;
    }

    public void setDrawWeb(final boolean z) {
        mDrawWeb = z;
    }

    public int getSkipWebLineCount() {
        return mSkipWebLineCount;
    }

    public void setSkipWebLineCount(final int i) {
        mSkipWebLineCount = Math.max (0, i);
    }

    protected float getRequiredLegendOffset() {
        return this.mLegendRenderer.getLabelPaint ().getTextSize () * 4.0f;
    }

    protected float getRequiredBaseOffset() {
        if (!this.mXAxis.isEnabled () || !this.mXAxis.isDrawLabelsEnabled ()) {
            return Utils.convertDpToPixel (10.0f);
        }
        return this.mXAxis.mLabelRotatedWidth;
    }

    public float getRadius() {
        final RectF contentRect = this.mViewPortHandler.getContentRect ();
        return Math.min (contentRect.width () / 2.0f, contentRect.height () / 2.0f);
    }

    public float getYChartMax() {
        return mYAxis.mAxisMaximum;
    }

    public float getYChartMin() {
        return mYAxis.mAxisMinimum;
    }

    public float getYRange() {
        return mYAxis.mAxisRange;
    }
}

package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.HorizontalBarHighlighter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.renderer.HorizontalBarChartRenderer;
import com.github.mikephil.charting.renderer.XAxisRendererHorizontalBarChart;
import com.github.mikephil.charting.renderer.YAxisRendererHorizontalBarChart;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.TransformerHorizontalBarChart;
import com.github.mikephil.charting.utils.Utils;

public class HorizontalBarChart extends BarChart {
    private final RectF mOffsetsBuffer = new RectF ();

    public HorizontalBarChart(final Context context) {
        super (context);
    }

    public HorizontalBarChart(final Context context, final AttributeSet attributeSet) {
        super (context, attributeSet);
    }

    public HorizontalBarChart(final Context context, final AttributeSet attributeSet, final int i) {
        super (context, attributeSet, i);
    }

    public void init() {
        super.init ();
        this.mLeftAxisTransformer = new TransformerHorizontalBarChart (this.mViewPortHandler);
        this.mRightAxisTransformer = new TransformerHorizontalBarChart (this.mViewPortHandler);
        this.mRenderer = new HorizontalBarChartRenderer (this, this.mAnimator, this.mViewPortHandler);
        this.setHighlighter(new HorizontalBarHighlighter (this));
        this.mAxisRendererLeft = new YAxisRendererHorizontalBarChart (this.mViewPortHandler, this.mAxisLeft, this.mLeftAxisTransformer);
        this.mAxisRendererRight = new YAxisRendererHorizontalBarChart (this.mViewPortHandler, this.mAxisRight, this.mRightAxisTransformer);
        this.mXAxisRenderer = new XAxisRendererHorizontalBarChart (this.mViewPortHandler, this.mXAxis, this.mLeftAxisTransformer, this);
    }

    public void calculateOffsets() {
        this.calculateLegendOffsets(mOffsetsBuffer);
        final RectF rectF = mOffsetsBuffer;
        float f = rectF.left + 0.0f;
        float f2 = rectF.top + 0.0f;
        float f3 = rectF.right + 0.0f;
        float f4 = rectF.bottom + 0.0f;
        if (this.mAxisLeft.needsOffset ()) {
            f2 += this.mAxisLeft.getRequiredHeightSpace (this.mAxisRendererLeft.getPaintAxisLabels ());
        }
        if (this.mAxisRight.needsOffset ()) {
            f4 += this.mAxisRight.getRequiredHeightSpace (this.mAxisRendererRight.getPaintAxisLabels ());
        }
        final XAxis xAxis = this.mXAxis;
        final float f5 = xAxis.mLabelRotatedWidth;
        if (xAxis.isEnabled ()) {
            if (XAxis.XAxisPosition.BOTTOM == mXAxis.getPosition ()) {
                f += f5;
            } else {
                if (XAxis.XAxisPosition.TOP != mXAxis.getPosition ()) {
                    if (XAxis.XAxisPosition.BOTH_SIDED == mXAxis.getPosition ()) {
                        f += f5;
                    }
                }
                f3 += f5;
            }
        }
        final float extraTopOffset = f2 + this.getExtraTopOffset();
        final float extraRightOffset = f3 + this.getExtraRightOffset();
        final float extraBottomOffset = f4 + this.getExtraBottomOffset();
        final float extraLeftOffset = f + this.getExtraLeftOffset();
        final float convertDpToPixel = Utils.convertDpToPixel (this.mMinOffset);
        this.mViewPortHandler.restrainViewPort (Math.max (convertDpToPixel, extraLeftOffset), Math.max (convertDpToPixel, extraTopOffset), Math.max (convertDpToPixel, extraRightOffset), Math.max (convertDpToPixel, extraBottomOffset));
        if (this.mLogEnabled) {
            Log.i (Chart.LOG_TAG, "offsetLeft: " + extraLeftOffset + ", offsetTop: " + extraTopOffset + ", offsetRight: " + extraRightOffset + ", offsetBottom: " + extraBottomOffset);
            final String sb = "Content: " +
                    this.mViewPortHandler.getContentRect ().toString ();
            Log.i (Chart.LOG_TAG, sb);
        }
        this.prepareOffsetMatrix();
        this.prepareValuePxMatrix();
    }

    protected void prepareValuePxMatrix() {
        final Transformer transformer = this.mRightAxisTransformer;
        final YAxis yAxis = this.mAxisRight;
        final float f = yAxis.mAxisMinimum;
        final float f2 = yAxis.mAxisRange;
        final XAxis xAxis = this.mXAxis;
        transformer.prepareMatrixValuePx (f, f2, xAxis.mAxisRange, xAxis.mAxisMinimum);
        final Transformer transformer2 = this.mLeftAxisTransformer;
        final YAxis yAxis2 = this.mAxisLeft;
        final float f3 = yAxis2.mAxisMinimum;
        final float f4 = yAxis2.mAxisRange;
        final XAxis xAxis2 = this.mXAxis;
        transformer2.prepareMatrixValuePx (f3, f4, xAxis2.mAxisRange, xAxis2.mAxisMinimum);
    }

    protected void calcModulus() {
        final float[] fArr = new float[9];
        this.mViewPortHandler.getMatrixTouch ().getValues (fArr);
        this.mXAxis.mAxisLabelModulus = (int) Math.ceil ((this.mData.getXValCount () * this.mXAxis.mLabelRotatedHeight) / (this.mViewPortHandler.contentHeight () * fArr[4]));
        final XAxis xAxis = this.mXAxis;
        if (1 > xAxis.mAxisLabelModulus) {
            xAxis.mAxisLabelModulus = 1;
        }
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
        final RectF rectF = new RectF (f4, f2, val, f3);
        this.getTransformer(iBarDataSet.getAxisDependency ()).rectValueToPixel (rectF);
        return rectF;
    }

    public PointF getPosition(final Entry entry, final YAxis.AxisDependency axisDependency) {
        if (null == entry) {
            return null;
        }
        final float[] fArr = {entry.getVal (), entry.getXIndex ()};
        this.getTransformer(axisDependency).pointValuesToPixel (fArr);
        return new PointF (fArr[0], fArr[1]);
    }

    public Highlight getHighlightByTouchPoint(final float f, final float f2) {
        if (false) {
            return this.getHighlighter().getHighlight (f2, f);
        }
        Log.e (Chart.LOG_TAG, "Can't select by touch. No data set.");
        return null;
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
        final float f2 = fArr[1];
        float f3 = 0.0f;
        if (0.0f < f2) {
            f3 = f2 / f;
        }
        return (int) (f3 + 1.0f);
    }

    public int getHighestVisibleXIndex() {
        final float dataSetCount = this.mData.getDataSetCount ();
        float f = 1.0f;
        if (1.0f < dataSetCount) {
            f = this.mData.getGroupSpace () + dataSetCount;
        }
        final float[] fArr = {this.mViewPortHandler.contentLeft (), this.mViewPortHandler.contentTop ()};
        this.getTransformer(YAxis.AxisDependency.LEFT).pixelsToValue (fArr);
        return (int) ((fArr[1] >= this.getXChartMax() ? this.getXChartMax() : fArr[1]) / f);
    }
}

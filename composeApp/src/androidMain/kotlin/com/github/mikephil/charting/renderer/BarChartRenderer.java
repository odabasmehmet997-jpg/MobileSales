package com.github.mikephil.charting.renderer;

import android.graphics.*;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

public class BarChartRenderer extends DataRenderer {
    protected Paint mBarBorderPaint;
    protected BarBuffer[] mBarBuffers;
    protected RectF mBarRect = new RectF ();
    protected BarDataProvider mChart;
    protected Paint mShadowPaint;

    public BarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super (chartAnimator, viewPortHandler);
        this.mChart = barDataProvider;
        Paint paint = new Paint (1);
        mHighlightPaint = paint;
        final Paint.Style style = Paint.Style.FILL;
        paint.setStyle (style);
        mHighlightPaint.setColor (Color.rgb (0, 0, 0));
        mHighlightPaint.setAlpha (120);
        Paint paint2 = new Paint (1);
        this.mShadowPaint = paint2;
        paint2.setStyle (style);
        Paint paint3 = new Paint (1);
        this.mBarBorderPaint = paint3;
        paint3.setStyle (Paint.Style.STROKE);
    }

    public void drawExtras(Canvas canvas) {
    }

    public void initBuffers() {
        BarData barData = this.mChart.getBarData ();
        this.mBarBuffers = new BarBuffer[barData.getDataSetCount ()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            IBarDataSet iBarDataSet = barData.getDataSetByIndex (i);
            this.mBarBuffers[i] = new BarBuffer (iBarDataSet.getEntryCount () * 4 * (iBarDataSet.isStacked () ? iBarDataSet.getStackSize () : 1), barData.getGroupSpace (), barData.getDataSetCount (), iBarDataSet.isStacked ());
        }
    }

    public void drawData(Canvas canvas) {
        BarData barData = this.mChart.getBarData ();
        for (int i = 0; i < barData.getDataSetCount (); i++) {
            IBarDataSet iBarDataSet = barData.getDataSetByIndex (i);
            if (iBarDataSet.isVisible () && 0 < iBarDataSet.getEntryCount ()) {
                drawDataSet(canvas, iBarDataSet, i);
            }
        }
    }

    protected void drawDataSet(Canvas canvas, IBarDataSet iBarDataSet, int i) {
        Transformer transformer = this.mChart.getTransformer (iBarDataSet.getAxisDependency ());
        this.mShadowPaint.setColor (iBarDataSet.getBarShadowColor ());
        this.mBarBorderPaint.setColor (iBarDataSet.getBarBorderColor ());
        this.mBarBorderPaint.setStrokeWidth (Utils.convertDpToPixel (iBarDataSet.getBarBorderWidth ()));
        int i2 = 0;
        boolean z = 0.0f < iBarDataSet.getBarBorderWidth ();
        float phaseX = mAnimator.getPhaseX ();
        float phaseY = mAnimator.getPhaseY ();
        BarBuffer barBuffer = this.mBarBuffers[i];
        barBuffer.setPhases (phaseX, phaseY);
        barBuffer.setBarSpace (iBarDataSet.getBarSpace ());
        barBuffer.setDataSet (i);
        barBuffer.setInverted (this.mChart.isInverted (iBarDataSet.getAxisDependency ()));
        barBuffer.feed (iBarDataSet);
        transformer.pointValuesToPixel (barBuffer.buffer);
        if (this.mChart.isDrawBarShadowEnabled ()) {
            for (int i3 = 0; i3 < barBuffer.size (); i3 += 4) {
                int i4 = i3 + 2;
                if (mViewPortHandler.isInBoundsLeft (barBuffer.buffer[i4])) {
                    if (!mViewPortHandler.isInBoundsRight (barBuffer.buffer[i3])) {
                        break;
                    }
                    canvas.drawRect (barBuffer.buffer[i3], mViewPortHandler.contentTop (), barBuffer.buffer[i4], mViewPortHandler.contentBottom (), this.mShadowPaint);
                }
            }
        }
        if (1 < iBarDataSet.getColors ().size ()) {
            while (i2 < barBuffer.size ()) {
                int i5 = i2 + 2;
                if (mViewPortHandler.isInBoundsLeft (barBuffer.buffer[i5])) {
                    if (mViewPortHandler.isInBoundsRight (barBuffer.buffer[i2])) {
                        mRenderPaint.setColor (iBarDataSet.getColor (i2 / 4));
                        float[] fArr = barBuffer.buffer;
                        int i6 = i2 + 1;
                        int i7 = i2 + 3;
                        canvas.drawRect (fArr[i2], fArr[i6], fArr[i5], fArr[i7], mRenderPaint);
                        if (z) {
                            float[] fArr2 = barBuffer.buffer;
                            canvas.drawRect (fArr2[i2], fArr2[i6], fArr2[i5], fArr2[i7], this.mBarBorderPaint);
                        }
                    } else {
                        return;
                    }
                }
                i2 += 4;
            }
            return;
        }
        mRenderPaint.setColor (iBarDataSet.getColor ());
        while (i2 < barBuffer.size ()) {
            int i8 = i2 + 2;
            if (mViewPortHandler.isInBoundsLeft (barBuffer.buffer[i8])) {
                if (mViewPortHandler.isInBoundsRight (barBuffer.buffer[i2])) {
                    float[] fArr3 = barBuffer.buffer;
                    int i9 = i2 + 1;
                    int i10 = i2 + 3;
                    canvas.drawRect (fArr3[i2], fArr3[i9], fArr3[i8], fArr3[i10], mRenderPaint);
                    if (z) {
                        float[] fArr4 = barBuffer.buffer;
                        canvas.drawRect (fArr4[i2], fArr4[i9], fArr4[i8], fArr4[i10], this.mBarBorderPaint);
                    }
                } else {
                    return;
                }
            }
            i2 += 4;
        }
    }

    protected void prepareBarHighlight(float f, float f2, float f3, float f4, Transformer transformer) {
        this.mBarRect.set ((f - 0.5f) + f4, f2, (f + 0.5f) - f4, f3);
        transformer.rectValueToPixel (this.mBarRect, mAnimator.getPhaseY ());
    }

    public void drawValues(Canvas canvas) {
        int i;
        List list;
        Transformer transformer;
        float[] fArr;
        int i2;
        int i3;
        float[] fArr2;
        float f;
        int i4;
        float[] fArr3;
        if (passesCheck()) {
            List dataSets = this.mChart.getBarData ().getDataSets ();
            float convertDpToPixel = Utils.convertDpToPixel (4.5f);
            boolean isDrawValueAboveBarEnabled = this.mChart.isDrawValueAboveBarEnabled ();
            int i5 = 0;
            while (i5 < this.mChart.getBarData ().getDataSetCount ()) {
                IBarDataSet iBarDataSet = (IBarDataSet) dataSets.get (i5);
                if (iBarDataSet.isDrawValuesEnabled () && 0 != iBarDataSet.getEntryCount ()) {
                    applyValueTextStyle(iBarDataSet);
                    boolean isInverted = this.mChart.isInverted (iBarDataSet.getAxisDependency ());
                    float calcTextHeight = Utils.calcTextHeight (mValuePaint, "8");
                    float f2 = isDrawValueAboveBarEnabled ? -convertDpToPixel : calcTextHeight + convertDpToPixel;
                    float f3 = isDrawValueAboveBarEnabled ? calcTextHeight + convertDpToPixel : -convertDpToPixel;
                    if (isInverted) {
                        f2 = (-f2) - calcTextHeight;
                        f3 = (-f3) - calcTextHeight;
                    }
                    Transformer transformer2 = this.mChart.getTransformer (iBarDataSet.getAxisDependency ());
                    float[] transformedValues = getTransformedValues(transformer2, iBarDataSet, i5);
                    if (!iBarDataSet.isStacked ()) {
                        int i6 = 0;
                        while (i6 < transformedValues.length * mAnimator.getPhaseX () && mViewPortHandler.isInBoundsRight (transformedValues[i6])) {
                            int i7 = i6 + 1;
                            if (!mViewPortHandler.isInBoundsY (transformedValues[i7]) || !mViewPortHandler.isInBoundsLeft (transformedValues[i6])) {
                                fArr3 = transformedValues;
                                i4 = i6;
                            } else {
                                int i8 = i6 / 2;
                                BarEntry barEntry = iBarDataSet.getEntryForIndex (i8);
                                float val = barEntry.getVal ();
                                fArr3 = transformedValues;
                                i4 = i6;
                                drawValue(canvas, iBarDataSet.getValueFormatter (), val, barEntry, i5, transformedValues[i6], transformedValues[i7] + (0.0f <= val ? f2 : f3), iBarDataSet.getValueTextColor (i8));
                            }
                            i6 = i4 + 2;
                            transformedValues = fArr3;
                        }
                    } else {
                        int i9 = 0;
                        while (i9 < (transformedValues.length - 1) * mAnimator.getPhaseX ()) {
                            int i10 = i9 / 2;
                            BarEntry barEntry2 = iBarDataSet.getEntryForIndex (i10);
                            float[] vals = barEntry2.getVals ();
                            if (null != vals) {
                                i = i9;
                                list = dataSets;
                                transformer = transformer2;
                                int valueTextColor = iBarDataSet.getValueTextColor (i10);
                                int length = vals.length * 2;
                                float[] fArr4 = new float[length];
                                float f4 = -barEntry2.getNegativeSum ();
                                float f5 = 0.0f;
                                int i11 = 0;
                                int i12 = 0;
                                while (i11 < length) {
                                    float f6 = vals[i12];
                                    if (0.0f <= f6) {
                                        f5 += f6;
                                        f = f4;
                                        f4 = f5;
                                    } else {
                                        f = f4 - f6;
                                    }
                                    fArr4[i11 + 1] = f4 * mAnimator.getPhaseY ();
                                    i11 += 2;
                                    i12++;
                                    f4 = f;
                                    f5 = f5;
                                }
                                transformer.pointValuesToPixel (fArr4);
                                int i13 = 0;
                                while (i13 < length) {
                                    float f7 = transformedValues[i];
                                    int i14 = i13 / 2;
                                    float f8 = fArr4[i13 + 1] + (0.0f <= vals[i14] ? f2 : f3);
                                    if (!mViewPortHandler.isInBoundsRight (f7)) {
                                        break;
                                    }
                                    if (!mViewPortHandler.isInBoundsY (f8) || !mViewPortHandler.isInBoundsLeft (f7)) {
                                        i2 = i13;
                                        fArr = vals;
                                        fArr2 = fArr4;
                                        i3 = length;
                                    } else {
                                        i2 = i13;
                                        fArr = vals;
                                        fArr2 = fArr4;
                                        i3 = length;
                                        drawValue(canvas, iBarDataSet.getValueFormatter (), vals[i14], barEntry2, i5, f7, f8, valueTextColor);
                                    }
                                    i13 = i2 + 2;
                                    fArr4 = fArr2;
                                    length = i3;
                                    vals = fArr;
                                }
                            } else if (!mViewPortHandler.isInBoundsRight (transformedValues[i9])) {
                                break;
                            } else {
                                int i15 = i9 + 1;
                                if (!mViewPortHandler.isInBoundsY (transformedValues[i15]) || !mViewPortHandler.isInBoundsLeft (transformedValues[i9])) {
                                    i = i9;
                                    list = dataSets;
                                    transformer = transformer2;
                                } else {
                                    i = i9;
                                    list = dataSets;
                                    transformer = transformer2;
                                    drawValue(canvas, iBarDataSet.getValueFormatter (), barEntry2.getVal (), barEntry2, i5, transformedValues[i9], transformedValues[i15] + (0.0f <= barEntry2.getVal () ? f2 : f3), iBarDataSet.getValueTextColor (i10));
                                }
                            }
                            i9 = i + 2;
                            transformer2 = transformer;
                            dataSets = list;
                        }
                    }
                }
                i5++;
                dataSets = dataSets;
            }
        }
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        int i;
        int dataSetIndex;
        int i2;
        int i3;
        BarEntry barEntry;
        float val;
        float f;
        BarData barData = this.mChart.getBarData ();
        int dataSetCount = barData.getDataSetCount ();
        for (Highlight highlight : highlightArr) {
            if (-1 == highlight.getDataSetIndex ()) {
                i = 0;
            } else {
                i = highlight.getDataSetIndex ();
            }
            if (-1 == highlight.getDataSetIndex ()) {
                dataSetIndex = barData.getDataSetCount ();
            } else {
                dataSetIndex = highlight.getDataSetIndex () + 1;
            }
            int i4 = dataSetIndex;
            if (1 <= i4 - i) {
                int i5 = i;
                while (i5 < i4) {
                    IBarDataSet iBarDataSet = barData.getDataSetByIndex (i5);
                    if (null != iBarDataSet && iBarDataSet.isHighlightEnabled ()) {
                        float barSpace = iBarDataSet.getBarSpace () / 2.0f;
                        Transformer transformer = this.mChart.getTransformer (iBarDataSet.getAxisDependency ());
                        mHighlightPaint.setColor (iBarDataSet.getHighLightColor ());
                        mHighlightPaint.setAlpha (iBarDataSet.getHighLightAlpha ());
                        int xIndex = highlight.getXIndex ();
                        if (0 <= xIndex) {
                            float f2 = xIndex;
                            if (f2 < (this.mChart.getXChartMax () * mAnimator.getPhaseX ()) / dataSetCount && null != (barEntry = iBarDataSet.getEntryForXIndex (xIndex)) && barEntry.getXIndex () == xIndex) {
                                float groupSpace = barData.getGroupSpace ();
                                float f3 = (groupSpace * f2) + ((xIndex * dataSetCount) + i5) + (groupSpace / 2.0f);
                                if (0 <= highlight.getStackIndex ()) {
                                    val = highlight.getRange ().from;
                                    f = highlight.getRange ().f818to;
                                } else {
                                    val = barEntry.getVal ();
                                    f = 0.0f;
                                }
                                i2 = i5;
                                i3 = i4;
                                prepareBarHighlight(f3, val, f, barSpace, transformer);
                                canvas.drawRect (this.mBarRect, mHighlightPaint);
                                if (this.mChart.isDrawHighlightArrowEnabled ()) {
                                    mHighlightPaint.setAlpha (255);
                                    float[] fArr = new float[9];
                                    transformer.getPixelToValueMatrix ().getValues (fArr);
                                    float abs = Math.abs (fArr[4] / fArr[0]);
                                    float barSpace2 = iBarDataSet.getBarSpace () / 2.0f;
                                    float f4 = abs * barSpace2;
                                    float phaseY = val * mAnimator.getPhaseY ();
                                    Path path = new Path ();
                                    float f5 = f3 + 0.4f;
                                    float phaseY2 = phaseY + (mAnimator.getPhaseY () * 0.07f);
                                    path.moveTo (f5, phaseY2);
                                    float f6 = f5 + barSpace2;
                                    path.lineTo (f6, phaseY2 - f4);
                                    path.lineTo (f6, phaseY2 + f4);
                                    transformer.pathValueToPixel (path);
                                    canvas.drawPath (path, mHighlightPaint);
                                    i5 = i2 + 1;
                                    i4 = i3;
                                }
                                i5 = i2 + 1;
                                i4 = i3;
                            }
                        }
                    }
                    i2 = i5;
                    i3 = i4;
                    i5 = i2 + 1;
                    i4 = i3;
                }
            }
        }
    }

    public float[] getTransformedValues(Transformer transformer, IBarDataSet iBarDataSet, int i) {
        return transformer.generateTransformedValuesBarChart (iBarDataSet, i, this.mChart.getBarData (), mAnimator.getPhaseY ());
    }

    protected boolean passesCheck() {
        return this.mChart.getBarData ().getYValCount () < this.mChart.getMaxVisibleCount () * mViewPortHandler.getScaleX ();
    }
}

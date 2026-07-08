package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.buffer.HorizontalBarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

public class HorizontalBarChartRenderer extends BarChartRenderer {
    public HorizontalBarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super (barDataProvider, chartAnimator, viewPortHandler);
        mValuePaint.setTextAlign (Paint.Align.LEFT);
    }

    public void initBuffers() {
        BarData barData = mChart.getBarData ();
        mBarBuffers = new HorizontalBarBuffer[barData.getDataSetCount ()];
        for (int i = 0; i < mBarBuffers.length; i++) {
            IBarDataSet iBarDataSet = barData.getDataSetByIndex (i);
            mBarBuffers[i] = new HorizontalBarBuffer (iBarDataSet.getEntryCount () * 4 * (iBarDataSet.isStacked () ? iBarDataSet.getStackSize () : 1), barData.getGroupSpace (), barData.getDataSetCount (), iBarDataSet.isStacked ());
        }
    }

    protected void drawDataSet(Canvas canvas, IBarDataSet iBarDataSet, int i) {
        Transformer transformer = mChart.getTransformer (iBarDataSet.getAxisDependency ());
        mShadowPaint.setColor (iBarDataSet.getBarShadowColor ());
        mBarBorderPaint.setColor (iBarDataSet.getBarBorderColor ());
        mBarBorderPaint.setStrokeWidth (Utils.convertDpToPixel (iBarDataSet.getBarBorderWidth ()));
        boolean z = 0.0f < iBarDataSet.getBarBorderWidth ();
        float phaseX = mAnimator.getPhaseX ();
        float phaseY = mAnimator.getPhaseY ();
        BarBuffer barBuffer = mBarBuffers[i];
        barBuffer.setPhases (phaseX, phaseY);
        barBuffer.setBarSpace (iBarDataSet.getBarSpace ());
        barBuffer.setDataSet (i);
        barBuffer.setInverted (mChart.isInverted (iBarDataSet.getAxisDependency ()));
        barBuffer.feed (iBarDataSet);
        transformer.pointValuesToPixel (barBuffer.buffer);
        for (int i2 = 0; i2 < barBuffer.size (); i2 += 4) {
            int i3 = i2 + 3;
            if (mViewPortHandler.isInBoundsTop (barBuffer.buffer[i3])) {
                int i4 = i2 + 1;
                if (mViewPortHandler.isInBoundsBottom (barBuffer.buffer[i4])) {
                    if (mChart.isDrawBarShadowEnabled ()) {
                        canvas.drawRect (mViewPortHandler.contentLeft (), barBuffer.buffer[i4], mViewPortHandler.contentRight (), barBuffer.buffer[i3], mShadowPaint);
                    }
                    mRenderPaint.setColor (iBarDataSet.getColor (i2 / 4));
                    float[] fArr = barBuffer.buffer;
                    int i5 = i2 + 2;
                    canvas.drawRect (fArr[i2], fArr[i4], fArr[i5], fArr[i3], mRenderPaint);
                    if (z) {
                        float[] fArr2 = barBuffer.buffer;
                        canvas.drawRect (fArr2[i2], fArr2[i4], fArr2[i5], fArr2[i3], mBarBorderPaint);
                    }
                }
            } else {
                return;
            }
        }
    }

    public void drawValues(Canvas canvas) {
        boolean z;
        List list;
        Transformer transformer;
        IBarDataSet iBarDataSet;
        int i;
        float[] fArr;
        int i2;
        Transformer transformer2;
        BarEntry barEntry;
        IBarDataSet iBarDataSet2;
        float[] fArr2;
        IBarDataSet iBarDataSet3;
        float f;
        boolean z2;
        List list2;
        float[] fArr3;
        int i3;
        if (passesCheck()) {
            List dataSets = mChart.getBarData ().getDataSets ();
            float convertDpToPixel = Utils.convertDpToPixel (5.0f);
            boolean isDrawValueAboveBarEnabled = mChart.isDrawValueAboveBarEnabled ();
            int i4 = 0;
            while (i4 < mChart.getBarData ().getDataSetCount ()) {
                IBarDataSet iBarDataSet4 = (IBarDataSet) dataSets.get (i4);
                if (iBarDataSet4.isDrawValuesEnabled () && 0 != iBarDataSet4.getEntryCount ()) {
                    boolean isInverted = mChart.isInverted (iBarDataSet4.getAxisDependency ());
                    applyValueTextStyle(iBarDataSet4);
                    float calcTextHeight = Utils.calcTextHeight (mValuePaint, "10") / 2.0f;
                    ValueFormatter valueFormatter = iBarDataSet4.getValueFormatter ();
                    Transformer transformer3 = mChart.getTransformer (iBarDataSet4.getAxisDependency ());
                    float[] transformedValues = getTransformedValues(transformer3, iBarDataSet4, i4);
                    if (!iBarDataSet4.isStacked ()) {
                        int i5 = 0;
                        while (i5 < transformedValues.length * mAnimator.getPhaseX ()) {
                            int i6 = i5 + 1;
                            if (!mViewPortHandler.isInBoundsTop (transformedValues[i6])) {
                                break;
                            }
                            if (mViewPortHandler.isInBoundsX (transformedValues[i5]) && mViewPortHandler.isInBoundsBottom (transformedValues[i6])) {
                                int i7 = i5 / 2;
                                BarEntry barEntry2 = iBarDataSet4.getEntryForIndex (i7);
                                float val = barEntry2.getVal ();
                                String formattedValue = valueFormatter.getFormattedValue (val, barEntry2, i4, mViewPortHandler);
                                float calcTextWidth = Utils.calcTextWidth (mValuePaint, formattedValue);
                                list2 = dataSets;
                                float f2 = isDrawValueAboveBarEnabled ? convertDpToPixel : -(calcTextWidth + convertDpToPixel);
                                z2 = isDrawValueAboveBarEnabled;
                                float f3 = isDrawValueAboveBarEnabled ? -(calcTextWidth + convertDpToPixel) : convertDpToPixel;
                                if (isInverted) {
                                    f2 = (-f2) - calcTextWidth;
                                    f3 = (-f3) - calcTextWidth;
                                }
                                float f4 = transformedValues[i5];
                                if (0.0f > val) {
                                    f2 = f3;
                                }
                                fArr3 = transformedValues;
                                i3 = i5;
                                drawValue(canvas, formattedValue, f4 + f2, transformedValues[i6] + calcTextHeight, iBarDataSet4.getValueTextColor (i7));
                            } else {
                                fArr3 = transformedValues;
                                list2 = dataSets;
                                z2 = isDrawValueAboveBarEnabled;
                                i3 = i5;
                            }
                            i5 = i3 + 2;
                            transformedValues = fArr3;
                            dataSets = list2;
                            isDrawValueAboveBarEnabled = z2;
                        }
                    } else {
                        list = dataSets;
                        z = isDrawValueAboveBarEnabled;
                        int i8 = 0;
                        while (i8 < (transformedValues.length - 1) * mAnimator.getPhaseX ()) {
                            int i9 = i8 / 2;
                            BarEntry barEntry3 = iBarDataSet4.getEntryForIndex (i9);
                            float[] vals = barEntry3.getVals ();
                            if (null == vals) {
                                int i10 = i8 + 1;
                                if (!mViewPortHandler.isInBoundsTop (transformedValues[i10])) {
                                    break;
                                }
                                if (mViewPortHandler.isInBoundsX (transformedValues[i8]) && mViewPortHandler.isInBoundsBottom (transformedValues[i10])) {
                                    String formattedValue2 = valueFormatter.getFormattedValue (barEntry3.getVal (), barEntry3, i4, mViewPortHandler);
                                    float calcTextWidth2 = Utils.calcTextWidth (mValuePaint, formattedValue2);
                                    float f5 = z ? convertDpToPixel : -(calcTextWidth2 + convertDpToPixel);
                                    float f6 = z ? -(calcTextWidth2 + convertDpToPixel) : convertDpToPixel;
                                    if (isInverted) {
                                        f5 = (-f5) - calcTextWidth2;
                                        f6 = (-f6) - calcTextWidth2;
                                    }
                                    float f7 = transformedValues[i8];
                                    if (0.0f > barEntry3.getVal ()) {
                                        f5 = f6;
                                    }
                                    drawValue(canvas, formattedValue2, f5 + f7, transformedValues[i10] + calcTextHeight, iBarDataSet4.getValueTextColor (i9));
                                    transformer = transformer3;
                                } else {
                                    transformer = transformer3;
                                }
                                iBarDataSet = iBarDataSet4;
                            } else {
                                int length = vals.length * 2;
                                float[] fArr4 = new float[length];
                                float f8 = -barEntry3.getNegativeSum ();
                                int i11 = i9;
                                float f9 = 0.0f;
                                int i12 = 0;
                                int i13 = 0;
                                while (i12 < length) {
                                    float f10 = vals[i13];
                                    if (0.0f <= f10) {
                                        f9 += f10;
                                        f = f8;
                                        iBarDataSet3 = iBarDataSet4;
                                        f8 = f9;
                                    } else {
                                        f = f8 - f10;
                                        iBarDataSet3 = iBarDataSet4;
                                    }
                                    fArr4[i12] = f8 * mAnimator.getPhaseY ();
                                    i12 += 2;
                                    i13++;
                                    f8 = f;
                                    iBarDataSet4 = iBarDataSet3;
                                }
                                IBarDataSet iBarDataSet5 = iBarDataSet4;
                                transformer3.pointValuesToPixel (fArr4);
                                int i14 = 0;
                                while (i14 < length) {
                                    float f11 = vals[i14 / 2];
                                    String formattedValue3 = valueFormatter.getFormattedValue (f11, barEntry3, i4, mViewPortHandler);
                                    float calcTextWidth3 = Utils.calcTextWidth (mValuePaint, formattedValue3);
                                    float f12 = z ? convertDpToPixel : -(calcTextWidth3 + convertDpToPixel);
                                    float f13 = z ? -(calcTextWidth3 + convertDpToPixel) : convertDpToPixel;
                                    if (isInverted) {
                                        f12 = (-f12) - calcTextWidth3;
                                        f13 = (-f13) - calcTextWidth3;
                                    }
                                    float f14 = fArr4[i14];
                                    if (0.0f > f11) {
                                        f12 = f13;
                                    }
                                    float f15 = f12 + f14;
                                    float f16 = transformedValues[i8 + 1];
                                    if (!mViewPortHandler.isInBoundsTop (f16)) {
                                        break;
                                    }
                                    if (mViewPortHandler.isInBoundsX (f15) && mViewPortHandler.isInBoundsBottom (f16)) {
                                        int valueTextColor = iBarDataSet5.getValueTextColor (i11);
                                        i2 = i11;
                                        fArr = fArr4;
                                        i = length;
                                        iBarDataSet2 = iBarDataSet5;
                                        fArr2 = vals;
                                        barEntry = barEntry3;
                                        transformer2 = transformer3;
                                        drawValue(canvas, formattedValue3, f15, f16 + calcTextHeight, valueTextColor);
                                    } else {
                                        fArr = fArr4;
                                        i = length;
                                        fArr2 = vals;
                                        barEntry = barEntry3;
                                        iBarDataSet2 = iBarDataSet5;
                                        transformer2 = transformer3;
                                        i2 = i11;
                                    }
                                    i14 += 2;
                                    vals = fArr2;
                                    barEntry3 = barEntry;
                                    transformer3 = transformer2;
                                    i11 = i2;
                                    fArr4 = fArr;
                                    length = i;
                                    iBarDataSet5 = iBarDataSet2;
                                }
                                transformer = transformer3;
                                iBarDataSet = iBarDataSet5;
                            }
                            i8 += 2;
                            iBarDataSet4 = iBarDataSet;
                            transformer3 = transformer;
                        }
                        i4++;
                        dataSets = list;
                        isDrawValueAboveBarEnabled = z;
                    }
                }
                list = dataSets;
                z = isDrawValueAboveBarEnabled;
                i4++;
                dataSets = list;
                isDrawValueAboveBarEnabled = z;
            }
        }
    }

    protected void drawValue(Canvas canvas, String str, float f, float f2, int i) {
        mValuePaint.setColor (i);
        canvas.drawText (str, f, f2, mValuePaint);
    }

    protected void prepareBarHighlight(float f, float f2, float f3, float f4, Transformer transformer) {
        mBarRect.set (f2, (f - 0.5f) + f4, f3, (f + 0.5f) - f4);
        transformer.rectValueToPixelHorizontal (mBarRect, mAnimator.getPhaseY ());
    }

    public float[] getTransformedValues(Transformer transformer, IBarDataSet iBarDataSet, int i) {
        return transformer.generateTransformedValuesHorizontalBarChart (iBarDataSet, i, mChart.getBarData (), mAnimator.getPhaseY ());
    }

    protected boolean passesCheck() {
        return mChart.getBarData ().getYValCount () < mChart.getMaxVisibleCount () * mViewPortHandler.getScaleY ();
    }
}

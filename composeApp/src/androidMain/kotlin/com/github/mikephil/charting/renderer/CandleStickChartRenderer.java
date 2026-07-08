package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.CandleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

public class CandleStickChartRenderer extends LineScatterCandleRadarRenderer {
    private final float[] mShadowBuffers = new float[8];
    private final float[] mBodyBuffers = new float[4];
    private final float[] mRangeBuffers = new float[4];
    private final float[] mOpenBuffers = new float[4];
    private final float[] mCloseBuffers = new float[4];
    protected CandleDataProvider mChart;

    public CandleStickChartRenderer(CandleDataProvider candleDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super (chartAnimator, viewPortHandler);
        this.mChart = candleDataProvider;
    }

    public void drawExtras(Canvas canvas) {
    }

    public void initBuffers() {
    }

    public void drawData(Canvas canvas) {
        for (ICandleDataSet iCandleDataSet : this.mChart.getCandleData ().getDataSets ()) {
            if (iCandleDataSet.isVisible () && 0 < iCandleDataSet.getEntryCount ()) {
                drawDataSet(canvas, iCandleDataSet);
            }
        }
    }

    protected void drawDataSet(Canvas canvas, ICandleDataSet iCandleDataSet) {
        boolean z;
        char c;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        Transformer transformer = this.mChart.getTransformer (iCandleDataSet.getAxisDependency ());
        float max = Math.max (0.0f, Math.min (1.0f, mAnimator.getPhaseX ()));
        float phaseY = mAnimator.getPhaseY ();
        float barSpace = iCandleDataSet.getBarSpace ();
        boolean showCandleBar = iCandleDataSet.getShowCandleBar ();
        char c2 = 0;
        int max2 = Math.max (mMinX, 0);
        boolean z2 = true;
        int min = Math.min (mMaxX + 1, iCandleDataSet.getEntryCount ());
        mRenderPaint.setStrokeWidth (iCandleDataSet.getShadowWidth ());
        int ceil = (int) Math.ceil (((min - max2) * max) + max2);
        int i6 = max2;
        while (i6 < ceil) {
            CandleEntry candleEntry = iCandleDataSet.getEntryForIndex (i6);
            int xIndex = candleEntry.getXIndex ();
            if (xIndex < max2 || xIndex >= min) {
                z = z2;
                c = c2;
            } else {
                float open = candleEntry.getOpen ();
                float close = candleEntry.getClose ();
                float high = candleEntry.getHigh ();
                float low = candleEntry.getLow ();
                if (showCandleBar) {
                    float[] fArr = this.mShadowBuffers;
                    float f = xIndex;
                    fArr[c2] = f;
                    fArr[2] = f;
                    fArr[4] = f;
                    fArr[6] = f;
                    int i7 = (open > close ? 1 : (open == close ? 0 : -1));
                    if (0 < i7) {
                        fArr[1] = high * phaseY;
                        fArr[3] = open * phaseY;
                        fArr[5] = low * phaseY;
                        fArr[7] = close * phaseY;
                    } else if (open < close) {
                        fArr[1] = high * phaseY;
                        fArr[3] = close * phaseY;
                        fArr[5] = low * phaseY;
                        fArr[7] = open * phaseY;
                    } else {
                        fArr[1] = high * phaseY;
                        float f2 = open * phaseY;
                        fArr[3] = f2;
                        fArr[5] = low * phaseY;
                        fArr[7] = f2;
                    }
                    transformer.pointValuesToPixel (fArr);
                    if (!iCandleDataSet.getShadowColorSameAsCandle ()) {
                        Paint paint = mRenderPaint;
                        if (1122867 == iCandleDataSet.getShadowColor ()) {
                            i2 = iCandleDataSet.getColor (i6);
                        } else {
                            i2 = iCandleDataSet.getShadowColor ();
                        }
                        paint.setColor (i2);
                    } else if (0 < i7) {
                        Paint paint2 = mRenderPaint;
                        if (1122867 == iCandleDataSet.getDecreasingColor ()) {
                            i5 = iCandleDataSet.getColor (i6);
                        } else {
                            i5 = iCandleDataSet.getDecreasingColor ();
                        }
                        paint2.setColor (i5);
                    } else if (open < close) {
                        Paint paint3 = mRenderPaint;
                        if (1122867 == iCandleDataSet.getIncreasingColor ()) {
                            i4 = iCandleDataSet.getColor (i6);
                        } else {
                            i4 = iCandleDataSet.getIncreasingColor ();
                        }
                        paint3.setColor (i4);
                    } else {
                        Paint paint4 = mRenderPaint;
                        if (1122867 == iCandleDataSet.getNeutralColor ()) {
                            i3 = iCandleDataSet.getColor (i6);
                        } else {
                            i3 = iCandleDataSet.getNeutralColor ();
                        }
                        paint4.setColor (i3);
                    }
                    mRenderPaint.setStyle (Paint.Style.STROKE);
                    canvas.drawLines (this.mShadowBuffers, mRenderPaint);
                    float[] fArr2 = this.mBodyBuffers;
                    fArr2[0] = (f - 0.5f) + barSpace;
                    fArr2[1] = close * phaseY;
                    fArr2[2] = (f + 0.5f) - barSpace;
                    fArr2[3] = open * phaseY;
                    transformer.pointValuesToPixel (fArr2);
                    if (0 < i7) {
                        if (1122867 == iCandleDataSet.getDecreasingColor ()) {
                            mRenderPaint.setColor (iCandleDataSet.getColor (i6));
                        } else {
                            mRenderPaint.setColor (iCandleDataSet.getDecreasingColor ());
                        }
                        mRenderPaint.setStyle (iCandleDataSet.getDecreasingPaintStyle ());
                        float[] fArr3 = this.mBodyBuffers;
                        canvas.drawRect (fArr3[0], fArr3[3], fArr3[2], fArr3[1], mRenderPaint);
                    } else if (open < close) {
                        if (1122867 == iCandleDataSet.getIncreasingColor ()) {
                            mRenderPaint.setColor (iCandleDataSet.getColor (i6));
                        } else {
                            mRenderPaint.setColor (iCandleDataSet.getIncreasingColor ());
                        }
                        mRenderPaint.setStyle (iCandleDataSet.getIncreasingPaintStyle ());
                        float[] fArr4 = this.mBodyBuffers;
                        canvas.drawRect (fArr4[0], fArr4[1], fArr4[2], fArr4[3], mRenderPaint);
                    } else {
                        if (1122867 == iCandleDataSet.getNeutralColor ()) {
                            mRenderPaint.setColor (iCandleDataSet.getColor (i6));
                        } else {
                            mRenderPaint.setColor (iCandleDataSet.getNeutralColor ());
                        }
                        float[] fArr5 = this.mBodyBuffers;
                        canvas.drawLine (fArr5[0], fArr5[1], fArr5[2], fArr5[3], mRenderPaint);
                    }
                    c = 0;
                    z = true;
                } else {
                    float[] fArr6 = this.mRangeBuffers;
                    float f3 = xIndex;
                    fArr6[0] = f3;
                    fArr6[1] = high * phaseY;
                    fArr6[2] = f3;
                    fArr6[3] = low * phaseY;
                    float[] fArr7 = this.mOpenBuffers;
                    fArr7[0] = (f3 - 0.5f) + barSpace;
                    float f4 = open * phaseY;
                    fArr7[1] = f4;
                    fArr7[2] = f3;
                    fArr7[3] = f4;
                    float[] fArr8 = this.mCloseBuffers;
                    fArr8[0] = (f3 + 0.5f) - barSpace;
                    float f5 = close * phaseY;
                    fArr8[1] = f5;
                    fArr8[2] = f3;
                    fArr8[3] = f5;
                    transformer.pointValuesToPixel (fArr6);
                    transformer.pointValuesToPixel (this.mOpenBuffers);
                    transformer.pointValuesToPixel (this.mCloseBuffers);
                    if (open > close) {
                        if (1122867 == iCandleDataSet.getDecreasingColor ()) {
                            i = iCandleDataSet.getColor (i6);
                        } else {
                            i = iCandleDataSet.getDecreasingColor ();
                        }
                    } else if (open < close) {
                        if (1122867 == iCandleDataSet.getIncreasingColor ()) {
                            i = iCandleDataSet.getColor (i6);
                        } else {
                            i = iCandleDataSet.getIncreasingColor ();
                        }
                    } else if (1122867 == iCandleDataSet.getNeutralColor ()) {
                        i = iCandleDataSet.getColor (i6);
                    } else {
                        i = iCandleDataSet.getNeutralColor ();
                    }
                    mRenderPaint.setColor (i);
                    float[] fArr9 = this.mRangeBuffers;
                    canvas.drawLine (fArr9[0], fArr9[1], fArr9[2], fArr9[3], mRenderPaint);
                    float[] fArr10 = this.mOpenBuffers;
                    canvas.drawLine (fArr10[0], fArr10[1], fArr10[2], fArr10[3], mRenderPaint);
                    float[] fArr11 = this.mCloseBuffers;
                    c = 0;
                    z = true;
                    canvas.drawLine (fArr11[0], fArr11[1], fArr11[2], fArr11[3], mRenderPaint);
                }
            }
            i6++;
            c2 = c;
            z2 = z;
        }
    }

    public void drawValues(Canvas canvas) {
        int i;
        if (this.mChart.getCandleData ().getYValCount () < this.mChart.getMaxVisibleCount () * mViewPortHandler.getScaleX ()) {
            List<ICandleDataSet> dataSets = this.mChart.getCandleData ().getDataSets ();
            for (int i2 = 0; i2 < dataSets.size (); i2++) {
                ICandleDataSet iCandleDataSet = dataSets.get (i2);
                if (iCandleDataSet.isDrawValuesEnabled () && 0 != iCandleDataSet.getEntryCount ()) {
                    applyValueTextStyle(iCandleDataSet);
                    Transformer transformer = this.mChart.getTransformer (iCandleDataSet.getAxisDependency ());
                    int max = Math.max (mMinX, 0);
                    float[] generateTransformedValuesCandle = transformer.generateTransformedValuesCandle (iCandleDataSet, mAnimator.getPhaseX (), mAnimator.getPhaseY (), max, Math.min (mMaxX + 1, iCandleDataSet.getEntryCount ()));
                    float convertDpToPixel = Utils.convertDpToPixel (5.0f);
                    int i3 = 0;
                    while (i3 < generateTransformedValuesCandle.length) {
                        float f = generateTransformedValuesCandle[i3];
                        float f2 = generateTransformedValuesCandle[i3 + 1];
                        if (!mViewPortHandler.isInBoundsRight (f)) {
                            break;
                        }
                        if (!mViewPortHandler.isInBoundsLeft (f) || !mViewPortHandler.isInBoundsY (f2)) {
                            i = i3;
                        } else {
                            int i4 = i3 / 2;
                            CandleEntry candleEntry = iCandleDataSet.getEntryForIndex (i4 + max);
                            i = i3;
                            drawValue(canvas, iCandleDataSet.getValueFormatter (), candleEntry.getHigh (), candleEntry, i2, f, f2 - convertDpToPixel, iCandleDataSet.getValueTextColor (i4));
                        }
                        i3 = i + 2;
                    }
                }
            }
        }
    }

    public void drawHighlighted(Canvas canvas, Highlight [] highlightArr) {
        int i;
        int i2;
        CandleEntry candleEntry;
        CandleData candleData = this.mChart.getCandleData ();
        for (Highlight highlight : highlightArr) {
            if (-1 == highlight.getDataSetIndex ()) {
                i = 0;
            } else {
                i = highlight.getDataSetIndex ();
            }
            if (-1 == highlight.getDataSetIndex ()) {
                i2 = candleData.getDataSetCount ();
            } else {
                i2 = highlight.getDataSetIndex () + 1;
            }
            if (1 <= i2 - i) {
                while (i < i2) {
                    int xIndex = highlight.getXIndex ();
                    ICandleDataSet iCandleDataSet = this.mChart.getCandleData ().getDataSetByIndex (i);
                    if (null != iCandleDataSet && iCandleDataSet.isHighlightEnabled () && null != (candleEntry = iCandleDataSet.getEntryForXIndex (xIndex)) && candleEntry.getXIndex () == xIndex) {
                        float[] fArr = {xIndex, ((candleEntry.getLow () * mAnimator.getPhaseY ()) + (candleEntry.getHigh () * mAnimator.getPhaseY ())) / 2.0f};
                        this.mChart.getTransformer (iCandleDataSet.getAxisDependency ()).pointValuesToPixel (fArr);
                        drawHighlightLines(canvas, fArr, iCandleDataSet);
                    }
                    i++;
                }
            }
        }
    }
}

package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BubbleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.proje.mobilesales.BuildConfig;

import java.util.List;

public class BubbleChartRenderer extends DataRenderer {
    private final float[] sizeBuffer = new float[4];
    private final float[] pointBuffer = new float[2];
    private final float[] _hsvBuffer = new float[3];
    protected BubbleDataProvider mChart;

    public BubbleChartRenderer(BubbleDataProvider bubbleDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super (chartAnimator, viewPortHandler);
        this.mChart = bubbleDataProvider;
        mRenderPaint.setStyle (Paint.Style.FILL);
        mHighlightPaint.setStyle (Paint.Style.STROKE);
        mHighlightPaint.setStrokeWidth (Utils.convertDpToPixel (1.5f));
    }

    public void drawExtras(Canvas canvas) {
    }

    public void initBuffers() {
    }

    public void drawData(Canvas canvas) {
        for (IBubbleDataSet iBubbleDataSet : this.mChart.getBubbleData ().getDataSets ()) {
            if (iBubbleDataSet.isVisible () && 0 < iBubbleDataSet.getEntryCount ()) {
                drawDataSet(canvas, iBubbleDataSet);
            }
        }
    }

    protected float getShapeSize(float f, float f2, float f3, boolean z) {
        if (z) {
            f = 0.0f == f2 ? 1.0f : (float) Math.sqrt (f / f2);
        }
        return f3 * f;
    }

    protected void drawDataSet(Canvas canvas, IBubbleDataSet iBubbleDataSet) {
        Transformer transformer = this.mChart.getTransformer (iBubbleDataSet.getAxisDependency ());
        float max = Math.max (0.0f, Math.min (1.0f, mAnimator.getPhaseX ()));
        float phaseY = mAnimator.getPhaseY ();
        char c = 0;
        int max2 = Math.max (iBubbleDataSet.getEntryIndex (iBubbleDataSet.getEntryForXIndex (mMinX)), 0);
        int min = Math.min (iBubbleDataSet.getEntryIndex (iBubbleDataSet.getEntryForXIndex (mMaxX)) + 1, iBubbleDataSet.getEntryCount ());
        float[] fArr = this.sizeBuffer;
        fArr[0] = 0.0f;
        fArr[2] = 1.0f;
        transformer.pointValuesToPixel (fArr);
        boolean isNormalizeSizeEnabled = iBubbleDataSet.isNormalizeSizeEnabled ();
        float[] fArr2 = this.sizeBuffer;
        float min2 = Math.min (Math.abs (mViewPortHandler.contentBottom () - mViewPortHandler.contentTop ()), Math.abs (fArr2[2] - fArr2[0]));
        int i = max2;
        while (i < min) {
            BubbleEntry bubbleEntry = iBubbleDataSet.getEntryForIndex (i);
            this.pointBuffer[c] = ((bubbleEntry.getXIndex () - max2) * max) + max2;
            this.pointBuffer[1] = bubbleEntry.getVal () * phaseY;
            transformer.pointValuesToPixel (this.pointBuffer);
            float shapeSize = getShapeSize(bubbleEntry.getSize (), iBubbleDataSet.getMaxSize (), min2, isNormalizeSizeEnabled) / 2.0f;
            if (mViewPortHandler.isInBoundsTop (this.pointBuffer[1] + shapeSize) && mViewPortHandler.isInBoundsBottom (this.pointBuffer[1] - shapeSize) && mViewPortHandler.isInBoundsLeft (this.pointBuffer[c] + shapeSize)) {
                if (mViewPortHandler.isInBoundsRight (this.pointBuffer[c] - shapeSize)) {
                    mRenderPaint.setColor (iBubbleDataSet.getColor (bubbleEntry.getXIndex ()));
                    float[] fArr3 = this.pointBuffer;
                    canvas.drawCircle (fArr3[c], fArr3[1], shapeSize, mRenderPaint);
                } else {
                    return;
                }
            }
            i++;
            c = 0;
        }
    }

    public void drawValues(Canvas canvas) {
        float[] fArr;
        int i;
        BubbleData bubbleData = this.mChart.getBubbleData ();
        if (null != bubbleData && bubbleData.getYValCount () < ((int) Math.ceil (this.mChart.getMaxVisibleCount () * mViewPortHandler.getScaleX ()))) {
            List<IBubbleDataSet> dataSets = bubbleData.getDataSets ();
            float calcTextHeight = Utils.calcTextHeight (mValuePaint, BuildConfig.NETSIS_DEMO_PASSWORD);
            for (int i2 = 0; i2 < dataSets.size (); i2++) {
                IBubbleDataSet iBubbleDataSet = dataSets.get (i2);
                if (iBubbleDataSet.isDrawValuesEnabled () && 0 != iBubbleDataSet.getEntryCount ()) {
                    applyValueTextStyle(iBubbleDataSet);
                    float max = Math.max (0.0f, Math.min (1.0f, mAnimator.getPhaseX ()));
                    float phaseY = mAnimator.getPhaseY ();
                    int entryIndex = iBubbleDataSet.getEntryIndex (iBubbleDataSet.getEntryForXIndex (mMinX));
                    float[] generateTransformedValuesBubble = this.mChart.getTransformer (iBubbleDataSet.getAxisDependency ()).generateTransformedValuesBubble (iBubbleDataSet, max, phaseY, entryIndex, Math.min (iBubbleDataSet.getEntryIndex (iBubbleDataSet.getEntryForXIndex (mMaxX)) + 1, iBubbleDataSet.getEntryCount ()));
                    float f = 1.0f == max ? phaseY : max;
                    int i3 = 0;
                    while (i3 < generateTransformedValuesBubble.length) {
                        int i4 = (i3 / 2) + entryIndex;
                        int valueTextColor = iBubbleDataSet.getValueTextColor (i4);
                        int argb = Color.argb (Math.round (255.0f * f), Color.red (valueTextColor), Color.green (valueTextColor), Color.blue (valueTextColor));
                        float f2 = generateTransformedValuesBubble[i3];
                        float f3 = generateTransformedValuesBubble[i3 + 1];
                        if (!mViewPortHandler.isInBoundsRight (f2)) {
                            break;
                        }
                        if (!mViewPortHandler.isInBoundsLeft (f2) || !mViewPortHandler.isInBoundsY (f3)) {
                            fArr = generateTransformedValuesBubble;
                            i = i3;
                        } else {
                            BubbleEntry bubbleEntry = iBubbleDataSet.getEntryForIndex (i4);
                            fArr = generateTransformedValuesBubble;
                            i = i3;
                            drawValue(canvas, iBubbleDataSet.getValueFormatter (), bubbleEntry.getSize (), bubbleEntry, i2, f2, f3 + (0.5f * calcTextHeight), argb);
                        }
                        i3 = i + 2;
                        generateTransformedValuesBubble = fArr;
                    }
                }
            }
        }
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        int i;
        int i2;
        float f;
        int i3;
        BubbleData bubbleData;
        boolean z;
        char c;
        int i4;
        BubbleEntry bubbleEntry;
        Highlight[] highlightArr2 = highlightArr;
        BubbleData bubbleData2 = this.mChart.getBubbleData ();
        char c2 = 0;
        boolean z2 = false;
        float max = Math.max (0.0f, Math.min (1.0f, mAnimator.getPhaseX ()));
        float phaseY = mAnimator.getPhaseY ();
        int length = highlightArr2.length;
        int i5 = 0;
        while (i5 < length) {
            Highlight highlight = highlightArr2[i5];
            if (-1 == highlight.getDataSetIndex ()) {
                i = 0;
            } else {
                i = highlight.getDataSetIndex ();
            }
            int i6 = 1;
            if (-1 == highlight.getDataSetIndex ()) {
                i2 = bubbleData2.getDataSetCount ();
            } else {
                i2 = highlight.getDataSetIndex () + 1;
            }
            if (1 <= i2 - i) {
                while (i < i2) {
                    IBubbleDataSet iBubbleDataSet = bubbleData2.getDataSetByIndex (i);
                    if (null == iBubbleDataSet || !iBubbleDataSet.isHighlightEnabled () || null == (bubbleEntry = (BubbleEntry) bubbleData2.getEntryForHighlight (highlight))) {
                        bubbleData = bubbleData2;
                        f = max;
                        c = c2;
                        z = z2;
                        i3 = length;
                        i4 = i6;
                    } else {
                        if (bubbleEntry.getXIndex () != highlight.getXIndex ()) {
                            bubbleData = bubbleData2;
                            f = max;
                            i3 = length;
                            i4 = i6;
                            c = 0;
                            z = false;
                        } else {
                            int entryIndex = iBubbleDataSet.getEntryIndex (iBubbleDataSet.getEntryForXIndex (mMinX));
                            int min = Math.min (iBubbleDataSet.getEntryIndex (iBubbleDataSet.getEntryForXIndex (mMaxX)) + i6, iBubbleDataSet.getEntryCount ());
                            Transformer transformer = this.mChart.getTransformer (iBubbleDataSet.getAxisDependency ());
                            float[] fArr = this.sizeBuffer;
                            z = false;
                            fArr[0] = 0.0f;
                            c = 0;
                            fArr[2] = 1.0f;
                            transformer.pointValuesToPixel (fArr);
                            boolean isNormalizeSizeEnabled = iBubbleDataSet.isNormalizeSizeEnabled ();
                            float[] fArr2 = this.sizeBuffer;
                            bubbleData = bubbleData2;
                            i3 = length;
                            float min2 = Math.min (Math.abs (mViewPortHandler.contentBottom () - mViewPortHandler.contentTop ()), Math.abs (fArr2[2] - fArr2[0]));
                            f = max;
                            this.pointBuffer[0] = ((bubbleEntry.getXIndex () - entryIndex) * max) + entryIndex;
                            this.pointBuffer[1] = bubbleEntry.getVal () * phaseY;
                            transformer.pointValuesToPixel (this.pointBuffer);
                            float shapeSize = getShapeSize(bubbleEntry.getSize (), iBubbleDataSet.getMaxSize (), min2, isNormalizeSizeEnabled) / 2.0f;
                            if (mViewPortHandler.isInBoundsTop (this.pointBuffer[1] + shapeSize)) {
                                if (mViewPortHandler.isInBoundsBottom (this.pointBuffer[1] - shapeSize)) {
                                    if (!mViewPortHandler.isInBoundsLeft (this.pointBuffer[0] + shapeSize)) {
                                        i4 = 1;
                                    } else if (!mViewPortHandler.isInBoundsRight (this.pointBuffer[0] - shapeSize)) {
                                        break;
                                    } else if (highlight.getXIndex () >= entryIndex && highlight.getXIndex () < min) {
                                        int color = iBubbleDataSet.getColor (bubbleEntry.getXIndex ());
                                        Color.RGBToHSV (Color.red (color), Color.green (color), Color.blue (color), this._hsvBuffer);
                                        float[] fArr3 = this._hsvBuffer;
                                        fArr3[2] = fArr3[2] * 0.5f;
                                        mHighlightPaint.setColor (Color.HSVToColor (Color.alpha (color), this._hsvBuffer));
                                        mHighlightPaint.setStrokeWidth (iBubbleDataSet.getHighlightCircleWidth ());
                                        float[] fArr4 = this.pointBuffer;
                                        i4 = 1;
                                        canvas.drawCircle (fArr4[0], fArr4[1], shapeSize, mHighlightPaint);
                                    }
                                }
                                i4 = 1;
                            } else {
                                i4 = 1;
                            }
                        }
                        i++;
                        i6 = i4;
                        c2 = c;
                        z2 = z;
                        bubbleData2 = bubbleData;
                        length = i3;
                        max = f;
                    }
                    i++;
                    i6 = i4;
                    c2 = c;
                    z2 = z;
                    bubbleData2 = bubbleData;
                    length = i3;
                    max = f;
                }
            }
            bubbleData = bubbleData2;
            f = max;
            c = c2;
            z = z2;
            i3 = length;
            i5++;
            highlightArr2 = highlightArr;
            c2 = c;
            z2 = z;
            bubbleData2 = bubbleData;
            length = i3;
            max = f;
        }
    }
}

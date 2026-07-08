package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.ScatterBuffer;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.ScatterDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import static ads_mobile_sdk.zb1.i;

public class ScatterChartRenderer extends LineScatterCandleRadarRenderer {
    protected ScatterDataProvider mChart;
    protected ScatterBuffer[] mScatterBuffers;

    public ScatterChartRenderer(ScatterDataProvider scatterDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super (chartAnimator, viewPortHandler);
        this.mChart = scatterDataProvider;
    }

    public void drawExtras(Canvas canvas) {
    }

    public void initBuffers() {
        ScatterData scatterData = this.mChart.getScatterData ();
        this.mScatterBuffers = new ScatterBuffer[scatterData.getDataSetCount ()];
        for (IScatterDataSet dataSet : scatterData.getDataSets ()) {
            this.mScatterBuffers[i] = new ScatterBuffer (dataSet.getEntryCount () * 2);
        }
    }

    public void drawData(Canvas canvas) {
        for (IScatterDataSet dataSet : this.mChart.getScatterData ().getDataSets ()) {
            if (dataSet.isVisible ()) {
                drawDataSet(canvas, dataSet);
            }
        }
    }

    protected void drawDataSet(Canvas canvas, IScatterDataSet iScatterDataSet) {
        int i;
        int i2;
        Transformer transformer = this.mChart.getTransformer (iScatterDataSet.getAxisDependency ());
        float max = Math.max (0.0f, Math.min (1.0f, mAnimator.getPhaseX ()));
        float phaseY = mAnimator.getPhaseY ();
        float convertDpToPixel = Utils.convertDpToPixel (iScatterDataSet.getScatterShapeSize ());
        float f = convertDpToPixel / 2.0f;
        float convertDpToPixel2 = Utils.convertDpToPixel (iScatterDataSet.getScatterShapeHoleRadius ());
        float f2 = convertDpToPixel2 * 2.0f;
        int scatterShapeHoleColor = iScatterDataSet.getScatterShapeHoleColor ();
        float f3 = (convertDpToPixel - f2) / 2.0f;
        float f4 = f3 / 2.0f;
        ScatterChart.ScatterShape scatterShape = iScatterDataSet.getScatterShape ();
        ScatterBuffer scatterBuffer = this.mScatterBuffers[this.mChart.getScatterData ().getIndexOfDataSet (iScatterDataSet)];
        scatterBuffer.setPhases (max, phaseY);
        scatterBuffer.feed (iScatterDataSet);
        transformer.pointValuesToPixel (scatterBuffer.buffer);
        int i3 = C12381.f827x9beb7303[scatterShape.ordinal ()];
        int i4 = ColorTemplate.COLOR_NONE;
        int i5 = 0;
        if (1 == i3) {
            int i6 = 0;
            while (i6 < scatterBuffer.size () && mViewPortHandler.isInBoundsRight (scatterBuffer.buffer[i6])) {
                if (mViewPortHandler.isInBoundsLeft (scatterBuffer.buffer[i6])) {
                    int i7 = i6 + 1;
                    if (mViewPortHandler.isInBoundsY (scatterBuffer.buffer[i7])) {
                        mRenderPaint.setColor (iScatterDataSet.getColor (i6 / 2));
                        if (0.0d < f2) {
                            mRenderPaint.setStyle (Paint.Style.STROKE);
                            mRenderPaint.setStrokeWidth (f3);
                            float[] fArr = scatterBuffer.buffer;
                            float f5 = fArr[i6];
                            float f6 = fArr[i7];
                            i = i6;
                            i2 = i4;
                            canvas.drawRect ((f5 - convertDpToPixel2) - f4, (f6 - convertDpToPixel2) - f4, f5 + convertDpToPixel2 + f4, f6 + convertDpToPixel2 + f4, mRenderPaint);
                            if (scatterShapeHoleColor != i2) {
                                mRenderPaint.setStyle (Paint.Style.FILL);
                                mRenderPaint.setColor (scatterShapeHoleColor);
                                float[] fArr2 = scatterBuffer.buffer;
                                float f7 = fArr2[i];
                                float f8 = fArr2[i7];
                                canvas.drawRect (f7 - convertDpToPixel2, f8 - convertDpToPixel2, f7 + convertDpToPixel2, f8 + convertDpToPixel2, mRenderPaint);
                            }
                        } else {
                            i = i6;
                            i2 = i4;
                            mRenderPaint.setStyle (Paint.Style.FILL);
                            float[] fArr3 = scatterBuffer.buffer;
                            float f9 = fArr3[i];
                            float f10 = fArr3[i7];
                            canvas.drawRect (f9 - f, f10 - f, f9 + f, f10 + f, mRenderPaint);
                        }
                        i6 = i + 2;
                        i4 = i2;
                    }
                }
                i = i6;
                i2 = i4;
                i6 = i + 2;
                i4 = i2;
            }
        } else if (2 == i3) {
            while (i5 < scatterBuffer.size () && mViewPortHandler.isInBoundsRight (scatterBuffer.buffer[i5])) {
                if (mViewPortHandler.isInBoundsLeft (scatterBuffer.buffer[i5])) {
                    int i8 = i5 + 1;
                    if (mViewPortHandler.isInBoundsY (scatterBuffer.buffer[i8])) {
                        mRenderPaint.setColor (iScatterDataSet.getColor (i5 / 2));
                        if (0.0d < f2) {
                            mRenderPaint.setStyle (Paint.Style.STROKE);
                            mRenderPaint.setStrokeWidth (f3);
                            float[] fArr4 = scatterBuffer.buffer;
                            canvas.drawCircle (fArr4[i5], fArr4[i8], convertDpToPixel2 + f4, mRenderPaint);
                            if (1122867 != scatterShapeHoleColor) {
                                mRenderPaint.setStyle (Paint.Style.FILL);
                                mRenderPaint.setColor (scatterShapeHoleColor);
                                float[] fArr5 = scatterBuffer.buffer;
                                canvas.drawCircle (fArr5[i5], fArr5[i8], convertDpToPixel2, mRenderPaint);
                            }
                        } else {
                            mRenderPaint.setStyle (Paint.Style.FILL);
                            float[] fArr6 = scatterBuffer.buffer;
                            canvas.drawCircle (fArr6[i5], fArr6[i8], f, mRenderPaint);
                        }
                    }
                }
                i5 += 2;
            }
        } else if (3 == i3) {
            mRenderPaint.setStyle (Paint.Style.FILL);
            Path path = new Path ();
            while (i5 < scatterBuffer.size () && mViewPortHandler.isInBoundsRight (scatterBuffer.buffer[i5])) {
                if (mViewPortHandler.isInBoundsLeft (scatterBuffer.buffer[i5])) {
                    int i9 = i5 + 1;
                    if (mViewPortHandler.isInBoundsY (scatterBuffer.buffer[i9])) {
                        mRenderPaint.setColor (iScatterDataSet.getColor (i5 / 2));
                        float[] fArr7 = scatterBuffer.buffer;
                        path.moveTo (fArr7[i5], fArr7[i9] - f);
                        float[] fArr8 = scatterBuffer.buffer;
                        path.lineTo (fArr8[i5] + f, fArr8[i9] + f);
                        float[] fArr9 = scatterBuffer.buffer;
                        path.lineTo (fArr9[i5] - f, fArr9[i9] + f);
                        int i10 = (0.0d < f2 ? 1 : (0.0d == f2 ? 0 : -1));
                        if (0 < i10) {
                            float[] fArr10 = scatterBuffer.buffer;
                            path.lineTo (fArr10[i5], fArr10[i9] - f);
                            float[] fArr11 = scatterBuffer.buffer;
                            path.moveTo ((fArr11[i5] - f) + f3, (fArr11[i9] + f) - f3);
                            float[] fArr12 = scatterBuffer.buffer;
                            path.lineTo ((fArr12[i5] + f) - f3, (fArr12[i9] + f) - f3);
                            float[] fArr13 = scatterBuffer.buffer;
                            path.lineTo (fArr13[i5], (fArr13[i9] - f) + f3);
                            float[] fArr14 = scatterBuffer.buffer;
                            path.lineTo ((fArr14[i5] - f) + f3, (fArr14[i9] + f) - f3);
                        }
                        path.close ();
                        canvas.drawPath (path, mRenderPaint);
                        path.reset ();
                        if (0 < i10 && 1122867 != scatterShapeHoleColor) {
                            mRenderPaint.setColor (scatterShapeHoleColor);
                            float[] fArr15 = scatterBuffer.buffer;
                            path.moveTo (fArr15[i5], (fArr15[i9] - f) + f3);
                            float[] fArr16 = scatterBuffer.buffer;
                            path.lineTo ((fArr16[i5] + f) - f3, (fArr16[i9] + f) - f3);
                            float[] fArr17 = scatterBuffer.buffer;
                            path.lineTo ((fArr17[i5] - f) + f3, (fArr17[i9] + f) - f3);
                            path.close ();
                            canvas.drawPath (path, mRenderPaint);
                            path.reset ();
                        }
                    }
                }
                i5 += 2;
            }
        } else if (4 == i3) {
            mRenderPaint.setStyle (Paint.Style.STROKE);
            mRenderPaint.setStrokeWidth (Utils.convertDpToPixel (1.0f));
            int i11 = 0;
            while (i11 < scatterBuffer.size () && mViewPortHandler.isInBoundsRight (scatterBuffer.buffer[i11])) {
                if (mViewPortHandler.isInBoundsLeft (scatterBuffer.buffer[i11])) {
                    int i12 = i11 + 1;
                    if (mViewPortHandler.isInBoundsY (scatterBuffer.buffer[i12])) {
                        mRenderPaint.setColor (iScatterDataSet.getColor (i11 / 2));
                        float[] fArr18 = scatterBuffer.buffer;
                        float f11 = fArr18[i11];
                        float f12 = fArr18[i12];
                        canvas.drawLine (f11 - f, f12, f11 + f, f12, mRenderPaint);
                        float[] fArr19 = scatterBuffer.buffer;
                        float f13 = fArr19[i11];
                        float f14 = fArr19[i12];
                        canvas.drawLine (f13, f14 - f, f13, f14 + f, mRenderPaint);
                    }
                }
                i11 += 2;
            }
        } else if (5 == i3) {
            mRenderPaint.setStyle (Paint.Style.STROKE);
            mRenderPaint.setStrokeWidth (Utils.convertDpToPixel (1.0f));
            int i13 = 0;
            while (i13 < scatterBuffer.size () && mViewPortHandler.isInBoundsRight (scatterBuffer.buffer[i13])) {
                if (mViewPortHandler.isInBoundsLeft (scatterBuffer.buffer[i13])) {
                    int i14 = i13 + 1;
                    if (mViewPortHandler.isInBoundsY (scatterBuffer.buffer[i14])) {
                        mRenderPaint.setColor (iScatterDataSet.getColor (i13 / 2));
                        float[] fArr20 = scatterBuffer.buffer;
                        float f15 = fArr20[i13];
                        float f16 = fArr20[i14];
                        canvas.drawLine (f15 - f, f16 - f, f15 + f, f16 + f, mRenderPaint);
                        float[] fArr21 = scatterBuffer.buffer;
                        float f17 = fArr21[i13];
                        float f18 = fArr21[i14];
                        canvas.drawLine (f17 + f, f18 - f, f17 - f, f18 + f, mRenderPaint);
                    }
                }
                i13 += 2;
            }
        }
    }

    public void drawValues(Canvas r19) {

        throw new UnsupportedOperationException ("com.github.mikephil.charting.renderer.ScatterChartRenderer.drawValues(android.graphics.Canvas):void");
    }

    public void drawHighlighted(Canvas canvas, Highlight [] highlightArr) {
        int i;
        int i2;
        ScatterData scatterData = this.mChart.getScatterData ();
        for (Highlight highlight : highlightArr) {
            if (-1 == highlight.getDataSetIndex ()) {
                i = 0;
            } else {
                i = highlight.getDataSetIndex ();
            }
            if (-1 == highlight.getDataSetIndex ()) {
                i2 = scatterData.getDataSetCount ();
            } else {
                i2 = highlight.getDataSetIndex () + 1;
            }
            if (1 <= i2 - i) {
                while (i < i2) {
                    IScatterDataSet iScatterDataSet = scatterData.getDataSetByIndex (i);
                    if (null != iScatterDataSet && iScatterDataSet.isHighlightEnabled ()) {
                        int xIndex = highlight.getXIndex ();
                        float f = xIndex;
                        if (f <= this.mChart.getXChartMax () * mAnimator.getPhaseX ()) {
                            float yValForXIndex = iScatterDataSet.getYValForXIndex (xIndex);
                            if (!Float.isNaN (yValForXIndex)) {
                                float[] fArr = {f, yValForXIndex * mAnimator.getPhaseY ()};
                                this.mChart.getTransformer (iScatterDataSet.getAxisDependency ()).pointValuesToPixel (fArr);
                                drawHighlightLines(canvas, fArr, iScatterDataSet);
                            }
                        }
                    }
                    i++;
                }
            }
        }
    }

    public enum C12381 {
        ;

        /* renamed from: SwitchMapcomgithubmikephilchartingchartsScatterChartScatterShape */
        static final int [] f827x9beb7303;

        static {
            int[] iArr = new int[ScatterChart.ScatterShape.values ().length];
            f827x9beb7303 = iArr;
            try {
                iArr[ScatterChart.ScatterShape.SQUARE.ordinal ()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f827x9beb7303[ScatterChart.ScatterShape.CIRCLE.ordinal ()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f827x9beb7303[ScatterChart.ScatterShape.TRIANGLE.ordinal ()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f827x9beb7303[ScatterChart.ScatterShape.CROSS.ordinal ()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f827x9beb7303[ScatterChart.ScatterShape.X.ordinal ()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}

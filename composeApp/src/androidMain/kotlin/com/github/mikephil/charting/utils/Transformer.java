package com.github.mikephil.charting.utils;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;

import java.util.List;

public class Transformer {
    private final Matrix mMBuffer1 = new Matrix ();
    private final Matrix mMBuffer2 = new Matrix ();
    protected ViewPortHandler mViewPortHandler;
    protected Matrix mMatrixValueToPx = new Matrix ();
    protected Matrix mMatrixOffset = new Matrix ();

    public Transformer(final ViewPortHandler viewPortHandler) {
        mViewPortHandler = viewPortHandler;
    }

    public void prepareMatrixValuePx(final float f, final float f2, final float f3, final float f4) {
        float contentWidth = mViewPortHandler.contentWidth () / f2;
        float contentHeight = mViewPortHandler.contentHeight () / f3;
        if (Float.isInfinite (contentWidth)) {
            contentWidth = 0.0f;
        }
        if (Float.isInfinite (contentHeight)) {
            contentHeight = 0.0f;
        }
        mMatrixValueToPx.reset ();
        mMatrixValueToPx.postTranslate (-f, -f4);
        mMatrixValueToPx.postScale (contentWidth, -contentHeight);
    }

    public void prepareMatrixOffset(final boolean z) {
        mMatrixOffset.reset ();
        if (!z) {
            mMatrixOffset.postTranslate (mViewPortHandler.offsetLeft (), mViewPortHandler.getChartHeight () - mViewPortHandler.offsetBottom ());
            return;
        }
        mMatrixOffset.setTranslate (mViewPortHandler.offsetLeft (), -mViewPortHandler.offsetTop ());
        mMatrixOffset.postScale (1.0f, -1.0f);
    }

    public float[] generateTransformedValuesScatter(final com.github.mikephil.charting.interfaces.datasets.IScatterDataSet r6, final float r7) {

        throw new UnsupportedOperationException ("com.github.mikephil.charting.utils.Transformer.generateTransformedValuesScatter(com.github.mikephil.charting.interfaces.datasets.IScatterDataSet, float):float[]");
    }

    public float[] generateTransformedValuesBubble(final com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet r6, final float r7, final float r8, final int r9, final int r10) {

        throw new UnsupportedOperationException ("com.github.mikephil.charting.utils.Transformer.generateTransformedValuesBubble(com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet, float, float, int, int):float[]");
    }

    public float[] generateTransformedValuesLine(final com.github.mikephil.charting.interfaces.datasets.ILineDataSet r4, final float r5, final float r6, final int r7, final int r8) {

        throw new UnsupportedOperationException ("com.github.mikephil.charting.utils.Transformer.generateTransformedValuesLine(com.github.mikephil.charting.interfaces.datasets.ILineDataSet, float, float, int, int):float[]");
    }

    public float[] generateTransformedValuesCandle(final ICandleDataSet iCandleDataSet, final float f, final float f2, final int i, final int i2) {
        final int ceil = ((int) Math.ceil ((i2 - i) * f)) * 2;
        final float[] fArr = new float[ceil];
        for (int i3 = 0; i3 < ceil; i3 += 2) {
            final CandleEntry candleEntry = iCandleDataSet.getEntryForIndex ((i3 / 2) + i);
            if (null != candleEntry) {
                fArr[i3] = candleEntry.getXIndex ();
                fArr[i3 + 1] = candleEntry.getHigh () * f2;
            }
        }
        this.getValueToPixelMatrix().mapPoints (fArr);
        return fArr;
    }

    public float[] generateTransformedValuesBarChart(final com.github.mikephil.charting.interfaces.datasets.IBarDataSet r9, final int r10, final com.github.mikephil.charting.data.BarData r11, final float r12) {

        throw new UnsupportedOperationException ("com.github.mikephil.charting.utils.Transformer.generateTransformedValuesBarChart(com.github.mikephil.charting.interfaces.datasets.IBarDataSet, int, com.github.mikephil.charting.data.BarData, float):float[]");
    }

    public float[] generateTransformedValuesHorizontalBarChart(final com.github.mikephil.charting.interfaces.datasets.IBarDataSet r8, final int r9, final com.github.mikephil.charting.data.BarData r10, final float r11) {

        throw new UnsupportedOperationException ("com.github.mikephil.charting.utils.Transformer.generateTransformedValuesHorizontalBarChart(com.github.mikephil.charting.interfaces.datasets.IBarDataSet, int, com.github.mikephil.charting.data.BarData, float):float[]");
    }

    public void pathValueToPixel(final Path path) {
        path.transform (mMatrixValueToPx);
        path.transform (mViewPortHandler.getMatrixTouch ());
        path.transform (mMatrixOffset);
    }

    public void pathValuesToPixel(final List<Path> list) {
        for (int i = 0; i < list.size (); i++) {
            this.pathValueToPixel(list.get (i));
        }
    }

    public void pointValuesToPixel(final float[] fArr) {
        mMatrixValueToPx.mapPoints (fArr);
        mViewPortHandler.getMatrixTouch ().mapPoints (fArr);
        mMatrixOffset.mapPoints (fArr);
    }

    public void rectValueToPixel(final RectF rectF) {
        mMatrixValueToPx.mapRect (rectF);
        mViewPortHandler.getMatrixTouch ().mapRect (rectF);
        mMatrixOffset.mapRect (rectF);
    }

    public void rectValueToPixel(final RectF rectF, final float f) {
        rectF.top *= f;
        rectF.bottom *= f;
        mMatrixValueToPx.mapRect (rectF);
        mViewPortHandler.getMatrixTouch ().mapRect (rectF);
        mMatrixOffset.mapRect (rectF);
    }

    public void rectValueToPixelHorizontal(final RectF rectF) {
        mMatrixValueToPx.mapRect (rectF);
        mViewPortHandler.getMatrixTouch ().mapRect (rectF);
        mMatrixOffset.mapRect (rectF);
    }

    public void rectValueToPixelHorizontal(final RectF rectF, final float f) {
        rectF.left *= f;
        rectF.right *= f;
        mMatrixValueToPx.mapRect (rectF);
        mViewPortHandler.getMatrixTouch ().mapRect (rectF);
        mMatrixOffset.mapRect (rectF);
    }

    public void rectValuesToPixel(final List<RectF> list) {
        final Matrix valueToPixelMatrix = this.getValueToPixelMatrix();
        for (int i = 0; i < list.size (); i++) {
            valueToPixelMatrix.mapRect (list.get (i));
        }
    }

    public void pixelsToValue(final float[] fArr) {
        final Matrix matrix = new Matrix ();
        mMatrixOffset.invert (matrix);
        matrix.mapPoints (fArr);
        mViewPortHandler.getMatrixTouch ().invert (matrix);
        matrix.mapPoints (fArr);
        mMatrixValueToPx.invert (matrix);
        matrix.mapPoints (fArr);
    }

    public com.github.mikephil.charting.utils.PointD getValuesByTouchPoint(final float f, final float f2) {
        final float[] fArr = {f, f2};
        this.pixelsToValue(fArr);
        return new PointD (fArr[0], fArr[1]);
    }

    public Matrix getValueMatrix() {
        return mMatrixValueToPx;
    }

    public Matrix getOffsetMatrix() {
        return mMatrixOffset;
    }

    public Matrix getValueToPixelMatrix() {
        mMBuffer1.set (mMatrixValueToPx);
        mMBuffer1.postConcat (mViewPortHandler.mMatrixTouch);
        mMBuffer1.postConcat (mMatrixOffset);
        return mMBuffer1;
    }

    public Matrix getPixelToValueMatrix() {
        this.getValueToPixelMatrix().invert (mMBuffer2);
        return mMBuffer2;
    }
}

package com.github.mikephil.charting.renderer;

import android.graphics.*;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class RadarChartRenderer extends LineRadarRenderer {
    protected RadarChart mChart;
    protected Paint mHighlightCirclePaint = new Paint (1);
    protected Paint mWebPaint;

    public RadarChartRenderer(RadarChart radarChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super (chartAnimator, viewPortHandler);
        this.mChart = radarChart;
        Paint paint = new Paint (1);
        mHighlightPaint = paint;
        final Paint.Style style = Paint.Style.STROKE;
        paint.setStyle (style);
        mHighlightPaint.setStrokeWidth (2.0f);
        mHighlightPaint.setColor (Color.rgb (255, 187, 115));
        Paint paint2 = new Paint (1);
        this.mWebPaint = paint2;
        paint2.setStyle (style);
    }

    public void initBuffers() {
    }

    public Paint getWebPaint() {
        return this.mWebPaint;
    }

    // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        RadarData radarData = this.mChart.getData ();
        int i = 0;
        for (IRadarDataSet iRadarDataSet : radarData.getDataSets ()) {
            if (iRadarDataSet.getEntryCount () > i) {
                i = iRadarDataSet.getEntryCount ();
            }
        }
        for (IRadarDataSet iRadarDataSet2 : radarData.getDataSets ()) {
            if (iRadarDataSet2.isVisible () && 0 < iRadarDataSet2.getEntryCount ()) {
                drawDataSet(canvas, iRadarDataSet2, i);
            }
        }
    }

    protected void drawDataSet(Canvas canvas, IRadarDataSet iRadarDataSet, int i) {
        float phaseX = mAnimator.getPhaseX ();
        float phaseY = mAnimator.getPhaseY ();
        float sliceAngle = this.mChart.getSliceAngle ();
        float factor = this.mChart.getFactor ();
        PointF centerOffsets = this.mChart.getCenterOffsets ();
        Path path = new Path ();
        boolean z = false;
        for (int i2 = 0; i2 < iRadarDataSet.getEntryCount (); i2++) {
            mRenderPaint.setColor (iRadarDataSet.getColor (i2));
            PointF position = Utils.getPosition (centerOffsets, (iRadarDataSet.getEntryForIndex (i2).getVal () - this.mChart.getYChartMin ()) * factor * phaseY, (i2 * sliceAngle * phaseX) + this.mChart.getRotationAngle ());
            if (!Float.isNaN (position.x)) {
                if (!z) {
                    path.moveTo (position.x, position.y);
                    z = true;
                } else {
                    path.lineTo (position.x, position.y);
                }
            }
        }
        if (iRadarDataSet.getEntryCount () > i) {
            path.lineTo (centerOffsets.x, centerOffsets.y);
        }
        path.close ();
        if (iRadarDataSet.isDrawFilledEnabled ()) {
            Drawable fillDrawable = iRadarDataSet.getFillDrawable ();
            if (null != fillDrawable) {
                drawFilledPath(canvas, path, fillDrawable);
            } else {
                drawFilledPath(canvas, path, iRadarDataSet.getFillColor (), iRadarDataSet.getFillAlpha ());
            }
        }
        mRenderPaint.setStrokeWidth (iRadarDataSet.getLineWidth ());
        mRenderPaint.setStyle (Paint.Style.STROKE);
        if (!iRadarDataSet.isDrawFilledEnabled () || 255 > iRadarDataSet.getFillAlpha ()) {
            canvas.drawPath (path, mRenderPaint);
        }
    }

    public void drawValues(Canvas canvas) {
        float phaseX = mAnimator.getPhaseX ();
        float phaseY = mAnimator.getPhaseY ();
        float sliceAngle = this.mChart.getSliceAngle ();
        float factor = this.mChart.getFactor ();
        PointF centerOffsets = this.mChart.getCenterOffsets ();
        float convertDpToPixel = Utils.convertDpToPixel (5.0f);
        int i = 0;
        while (i < this.mChart.getData ().getDataSetCount ()) {
            IRadarDataSet dataSetByIndex = this.mChart.getData ().getDataSetByIndex (i);
            if (dataSetByIndex.isDrawValuesEnabled () && 0 != dataSetByIndex.getEntryCount ()) {
                applyValueTextStyle(dataSetByIndex);
                int i2 = 0;
                while (i2 < dataSetByIndex.getEntryCount ()) {
                    Entry entryForIndex = dataSetByIndex.getEntryForIndex (i2);
                    PointF position = Utils.getPosition (centerOffsets, (entryForIndex.getVal () - this.mChart.getYChartMin ()) * factor * phaseY, (i2 * sliceAngle * phaseX) + this.mChart.getRotationAngle ());
                    drawValue(canvas, dataSetByIndex.getValueFormatter (), entryForIndex.getVal (), entryForIndex, i, position.x, position.y - convertDpToPixel, dataSetByIndex.getValueTextColor (i2));
                    i2++;
                    i = i;
                    dataSetByIndex = dataSetByIndex;
                }
            }
            i++;
        }
    }

    public void drawExtras(Canvas canvas) {
        drawWeb(canvas);
    }

    protected void drawWeb(Canvas canvas) {
        float sliceAngle = this.mChart.getSliceAngle ();
        float factor = this.mChart.getFactor ();
        float rotationAngle = this.mChart.getRotationAngle ();
        PointF centerOffsets = this.mChart.getCenterOffsets ();
        this.mWebPaint.setStrokeWidth (this.mChart.getWebLineWidth ());
        this.mWebPaint.setColor (this.mChart.getWebColor ());
        this.mWebPaint.setAlpha (this.mChart.getWebAlpha ());
        int skipWebLineCount = this.mChart.getSkipWebLineCount () + 1;
        for (int i = 0; i < this.mChart.getData ().getXValCount (); i += skipWebLineCount) {
            PointF position = Utils.getPosition (centerOffsets, this.mChart.getYRange () * factor, (i * sliceAngle) + rotationAngle);
            canvas.drawLine (centerOffsets.x, centerOffsets.y, position.x, position.y, this.mWebPaint);
        }
        this.mWebPaint.setStrokeWidth (this.mChart.getWebLineWidthInner ());
        this.mWebPaint.setColor (this.mChart.getWebColorInner ());
        this.mWebPaint.setAlpha (this.mChart.getWebAlpha ());
        int i2 = this.mChart.getYAxis ().mEntryCount;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = 0;
            while (i4 < this.mChart.getData ().getXValCount ()) {
                float yChartMin = (this.mChart.getYAxis ().mEntries[i3] - this.mChart.getYChartMin ()) * factor;
                PointF position2 = Utils.getPosition (centerOffsets, yChartMin, (i4 * sliceAngle) + rotationAngle);
                i4++;
                PointF position3 = Utils.getPosition (centerOffsets, yChartMin, (i4 * sliceAngle) + rotationAngle);
                canvas.drawLine (position2.x, position2.y, position3.x, position3.y, this.mWebPaint);
            }
        }
    }

    public void drawHighlighted(Canvas r20, com.github.mikephil.charting.highlight.Highlight[] r21) {
        throw new UnsupportedOperationException ("com.github.mikephil.charting.renderer.RadarChartRenderer.drawHighlighted(android.graphics.Canvas, com.github.mikephil.charting.highlight.Highlight[]):void");
    }

    public void drawHighlightCircle(Canvas canvas, PointF pointF, float f, float f2, int i, int i2, float f3) {
        canvas.save ();
        float convertDpToPixel = Utils.convertDpToPixel (f2);
        float convertDpToPixel2 = Utils.convertDpToPixel (f);
        if (1122867 != i) {
            Path path = new Path ();
            path.addCircle (pointF.x, pointF.y, convertDpToPixel, Path.Direction.CW);
            if (0.0f < convertDpToPixel2) {
                path.addCircle (pointF.x, pointF.y, convertDpToPixel2, Path.Direction.CCW);
            }
            this.mHighlightCirclePaint.setColor (i);
            this.mHighlightCirclePaint.setStyle (Paint.Style.FILL);
            canvas.drawPath (path, this.mHighlightCirclePaint);
        }
        if (1122867 != i2) {
            this.mHighlightCirclePaint.setColor (i2);
            this.mHighlightCirclePaint.setStyle (Paint.Style.STROKE);
            this.mHighlightCirclePaint.setStrokeWidth (Utils.convertDpToPixel (f3));
            canvas.drawCircle (pointF.x, pointF.y, convertDpToPixel, this.mHighlightCirclePaint);
        }
        canvas.restore ();
    }
}

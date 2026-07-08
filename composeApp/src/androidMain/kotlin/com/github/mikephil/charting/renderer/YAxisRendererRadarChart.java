package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

public class YAxisRendererRadarChart extends YAxisRenderer {
    private final RadarChart mChart;

    public YAxisRendererRadarChart(ViewPortHandler viewPortHandler, YAxis yAxis, RadarChart radarChart) {
        super (viewPortHandler, yAxis, null);
        this.mChart = radarChart;
    }

    public void computeAxis(float f, float f2) {
        computeAxisValues(f, f2);
    }

    public void computeAxisValues(float f, float f2) {
        int labelCount = mYAxis.getLabelCount ();
        double abs = Math.abs (f2 - f);
        if (0 == labelCount || 0.0d >= abs) {
            YAxis yAxis = mYAxis;
            yAxis.mEntries = new float[0];
            yAxis.mEntryCount = 0;
            return;
        }
        double roundToNextSignificant = Utils.roundToNextSignificant (abs / labelCount);
        double pow = Math.pow (10.0d, (int) Math.log10 (roundToNextSignificant));
        if (5 < ((int) (roundToNextSignificant / pow))) {
            roundToNextSignificant = Math.floor (pow * 10.0d);
        }
        if (mYAxis.isForceLabelsEnabled ()) {
            float f3 = ((float) abs) / (labelCount - 1);
            YAxis yAxis2 = mYAxis;
            yAxis2.mEntryCount = labelCount;
            if (yAxis2.mEntries.length < labelCount) {
                yAxis2.mEntries = new float[labelCount];
            }
            float f4 = f;
            for (int i = 0; i < labelCount; i++) {
                mYAxis.mEntries[i] = f4;
                f4 += f3;
            }
        } else if (mYAxis.isShowOnlyMinMaxEnabled ()) {
            YAxis yAxis3 = mYAxis;
            yAxis3.mEntryCount = 2;
            yAxis3.mEntries = new float[]{f, f2};
            float[] fArr = {f, f2};
        } else {
            double d = f / roundToNextSignificant;
            double floor = (0.0d > d ? Math.floor (d) : Math.ceil (d)) * roundToNextSignificant;
            if (0.0d == floor) {
                floor = 0.0d;
            }
            int i2 = 0;
            for (double d2 = floor; d2 <= Utils.nextUp (Math.floor (f2 / roundToNextSignificant) * roundToNextSignificant); d2 += roundToNextSignificant) {
                i2++;
            }
            if (!mYAxis.isAxisMaxCustom ()) {
                i2++;
            }
            YAxis yAxis4 = mYAxis;
            yAxis4.mEntryCount = i2;
            if (yAxis4.mEntries.length < i2) {
                yAxis4.mEntries = new float[i2];
            }
            for (int i3 = 0; i3 < i2; i3++) {
                mYAxis.mEntries[i3] = (float) floor;
                floor += roundToNextSignificant;
            }
        }
        if (1.0d > roundToNextSignificant) {
            mYAxis.mDecimals = (int) Math.ceil (-Math.log10 (roundToNextSignificant));
        } else {
            mYAxis.mDecimals = 0;
        }
        YAxis yAxis5 = mYAxis;
        float[] fArr2 = yAxis5.mEntries;
        float f5 = fArr2[0];
        if (f5 < f) {
            yAxis5.mAxisMinimum = f5;
        }
        float f6 = fArr2[yAxis5.mEntryCount - 1];
        yAxis5.mAxisMaximum = f6;
        yAxis5.mAxisRange = Math.abs (f6 - yAxis5.mAxisMinimum);
    }

    public void renderAxisLabels(Canvas canvas) {
        if (mYAxis.isEnabled () && mYAxis.isDrawLabelsEnabled ()) {
            mAxisLabelPaint.setTypeface (mYAxis.getTypeface ());
            mAxisLabelPaint.setTextSize (mYAxis.getTextSize ());
            mAxisLabelPaint.setColor (mYAxis.getTextColor ());
            PointF centerOffsets = this.mChart.getCenterOffsets ();
            float factor = this.mChart.getFactor ();
            int i = mYAxis.mEntryCount;
            for (int i2 = 0; i2 < i; i2++) {
                if (i2 != i - 1 || mYAxis.isDrawTopYLabelEntryEnabled ()) {
                    YAxis yAxis = mYAxis;
                    PointF position = Utils.getPosition (centerOffsets, (yAxis.mEntries[i2] - yAxis.mAxisMinimum) * factor, this.mChart.getRotationAngle ());
                    canvas.drawText (mYAxis.getFormattedLabel (i2), position.x + 10.0f, position.y, mAxisLabelPaint);
                } else {
                    return;
                }
            }
        }
    }

    public void renderLimitLines(Canvas canvas) {
        List<LimitLine> limitLines = mYAxis.getLimitLines ();
        if (null != limitLines) {
            float sliceAngle = this.mChart.getSliceAngle ();
            float factor = this.mChart.getFactor ();
            PointF centerOffsets = this.mChart.getCenterOffsets ();
            for (int i = 0; i < limitLines.size (); i++) {
                LimitLine limitLine = limitLines.get (i);
                if (limitLine.isEnabled ()) {
                    mLimitLinePaint.setColor (limitLine.getLineColor ());
                    mLimitLinePaint.setPathEffect (limitLine.getDashPathEffect ());
                    mLimitLinePaint.setStrokeWidth (limitLine.getLineWidth ());
                    float limit = (limitLine.getLimit () - this.mChart.getYChartMin ()) * factor;
                    Path path = new Path ();
                    for (int i2 = 0; i2 < this.mChart.getData ().getXValCount (); i2++) {
                        PointF position = Utils.getPosition (centerOffsets, limit, (i2 * sliceAngle) + this.mChart.getRotationAngle ());
                        if (0 == i2) {
                            path.moveTo (position.x, position.y);
                        } else {
                            path.lineTo (position.x, position.y);
                        }
                    }
                    path.close ();
                    canvas.drawPath (path, mLimitLinePaint);
                }
            }
        }
    }
}


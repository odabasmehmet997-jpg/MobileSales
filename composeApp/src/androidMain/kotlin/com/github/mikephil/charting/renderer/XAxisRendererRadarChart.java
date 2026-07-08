package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.PointF;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class XAxisRendererRadarChart extends XAxisRenderer {
    private final RadarChart mChart;

    public XAxisRendererRadarChart(ViewPortHandler viewPortHandler, XAxis xAxis, RadarChart radarChart) {
        super (viewPortHandler, xAxis, null);
        this.mChart = radarChart;
    }

    public void renderLimitLines(Canvas canvas) {
    }

    public void renderAxisLabels(Canvas canvas) {
        if (mXAxis.isEnabled () && mXAxis.isDrawLabelsEnabled ()) {
            float labelRotationAngle = mXAxis.getLabelRotationAngle ();
            PointF pointF = new PointF (0.5f, 0.0f);
            mAxisLabelPaint.setTypeface (mXAxis.getTypeface ());
            mAxisLabelPaint.setTextSize (mXAxis.getTextSize ());
            mAxisLabelPaint.setColor (mXAxis.getTextColor ());
            float sliceAngle = this.mChart.getSliceAngle ();
            float factor = this.mChart.getFactor ();
            PointF centerOffsets = this.mChart.getCenterOffsets ();
            int i = mXAxis.mAxisLabelModulus;
            for (int i2 = 0; i2 < mXAxis.getValues ().size (); i2 += i) {
                String str = mXAxis.getValues ().get (i2);
                PointF position = Utils.getPosition (centerOffsets, (this.mChart.getYRange () * factor) + (mXAxis.mLabelRotatedWidth / 2.0f), ((i2 * sliceAngle) + this.mChart.getRotationAngle ()) % 360.0f);
                drawLabel(canvas, str, i2, position.x, position.y - (mXAxis.mLabelRotatedHeight / 2.0f), pointF, labelRotationAngle);
            }
        }
    }
}

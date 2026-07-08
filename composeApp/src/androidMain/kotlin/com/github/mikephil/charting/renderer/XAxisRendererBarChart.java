package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.PointF;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class XAxisRendererBarChart extends XAxisRenderer {
    protected BarChart mChart;

    public XAxisRendererBarChart(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer transformer, BarChart barChart) {
        super (viewPortHandler, xAxis, transformer);
        this.mChart = barChart;
    }

    protected void drawLabels(Canvas canvas, float f, PointF pointF) {
        float labelRotationAngle = mXAxis.getLabelRotationAngle ();
        float[] fArr = new float[2];
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        BarData barData = this.mChart.getData ();
        int dataSetCount = barData.getDataSetCount ();
        int i = mMinX;
        while (i <= mMaxX) {
            float groupSpace = (i * dataSetCount) + (i * barData.getGroupSpace ()) + (barData.getGroupSpace () / 2.0f);
            fArr[0] = groupSpace;
            if (1 < dataSetCount) {
                fArr[0] = groupSpace + ((dataSetCount - 1.0f) / 2.0f);
            }
            mTrans.pointValuesToPixel (fArr);
            if (mViewPortHandler.isInBoundsX (fArr[0]) && 0 <= i && i < mXAxis.getValues ().size ()) {
                String str = mXAxis.getValues ().get (i);
                if (mXAxis.isAvoidFirstLastClippingEnabled ()) {
                    if (i == mXAxis.getValues ().size () - 1) {
                        float calcTextWidth = Utils.calcTextWidth (mAxisLabelPaint, str) / 2.0f;
                        if (fArr[0] + calcTextWidth > mViewPortHandler.contentRight ()) {
                            fArr[0] = mViewPortHandler.contentRight () - calcTextWidth;
                        }
                    } else if (0 == i) {
                        float calcTextWidth2 = Utils.calcTextWidth (mAxisLabelPaint, str) / 2.0f;
                        if (fArr[0] - calcTextWidth2 < mViewPortHandler.contentLeft ()) {
                            fArr[0] = mViewPortHandler.contentLeft () + calcTextWidth2;
                        }
                    }
                }
                drawLabel(canvas, str, i, fArr[0], f, pointF, labelRotationAngle);
            }
            i += mXAxis.mAxisLabelModulus;
        }
    }

    // com.github.mikephil.charting.renderer.XAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderGridLines(Canvas canvas) {
        if (mXAxis.isDrawGridLinesEnabled () && mXAxis.isEnabled ()) {
            float[] fArr = new float[2];
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            mGridPaint.setColor (mXAxis.getGridColor ());
            mGridPaint.setStrokeWidth (mXAxis.getGridLineWidth ());
            BarData barData = this.mChart.getData ();
            int dataSetCount = barData.getDataSetCount ();
            int i = mMinX;
            while (i < mMaxX) {
                fArr[0] = ((i * dataSetCount) + (i * barData.getGroupSpace ())) - 0.5f;
                mTrans.pointValuesToPixel (fArr);
                if (mViewPortHandler.isInBoundsX (fArr[0])) {
                    canvas.drawLine (fArr[0], mViewPortHandler.offsetTop (), fArr[0], mViewPortHandler.contentBottom (), mGridPaint);
                }
                i += mXAxis.mAxisLabelModulus;
            }
        }
    }
}

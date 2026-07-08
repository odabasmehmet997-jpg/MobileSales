package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

public class XAxisRendererHorizontalBarChart extends XAxisRendererBarChart {
    public XAxisRendererHorizontalBarChart(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer transformer, BarChart barChart) {
        super (viewPortHandler, xAxis, transformer, barChart);
    }

    public void computeAxis(float f, List<String> list) {
        mAxisLabelPaint.setTypeface (mXAxis.getTypeface ());
        mAxisLabelPaint.setTextSize (mXAxis.getTextSize ());
        mXAxis.setValues (list);
        FSize calcTextSize = Utils.calcTextSize (mAxisLabelPaint, mXAxis.getLongestLabel ());
        float f2 = calcTextSize.height ();
        FSize sizeOfRotatedRectangleByDegrees = Utils.getSizeOfRotatedRectangleByDegrees (calcTextSize.width (), f2, mXAxis.getLabelRotationAngle ());
        mXAxis.mLabelWidth = Math.round (((int) (calcTextSize.width () + (mXAxis.getXOffset () * 3.5f))));
        mXAxis.mLabelHeight = Math.round (f2);
        XAxis xAxis = mXAxis;
        xAxis.mLabelRotatedWidth = (int) (sizeOfRotatedRectangleByDegrees.width () + (xAxis.getXOffset () * 3.5f));
        mXAxis.mLabelRotatedHeight = Math.round (sizeOfRotatedRectangleByDegrees.height ());
    }

    public void renderAxisLabels(Canvas canvas) {
        if (mXAxis.isEnabled () && mXAxis.isDrawLabelsEnabled ()) {
            float xOffset = mXAxis.getXOffset ();
            mAxisLabelPaint.setTypeface (mXAxis.getTypeface ());
            mAxisLabelPaint.setTextSize (mXAxis.getTextSize ());
            mAxisLabelPaint.setColor (mXAxis.getTextColor ());
            if (XAxis.XAxisPosition.TOP == this.mXAxis.getPosition ()) {
                drawLabels(canvas, mViewPortHandler.contentRight () + xOffset, new PointF (0.0f, 0.5f));
            } else if (XAxis.XAxisPosition.TOP_INSIDE == this.mXAxis.getPosition ()) {
                drawLabels(canvas, mViewPortHandler.contentRight () - xOffset, new PointF (1.0f, 0.5f));
            } else if (XAxis.XAxisPosition.BOTTOM == this.mXAxis.getPosition ()) {
                drawLabels(canvas, mViewPortHandler.contentLeft () - xOffset, new PointF (1.0f, 0.5f));
            } else if (XAxis.XAxisPosition.BOTTOM_INSIDE == this.mXAxis.getPosition ()) {
                drawLabels(canvas, mViewPortHandler.contentLeft () + xOffset, new PointF (0.0f, 0.5f));
            } else {
                drawLabels(canvas, mViewPortHandler.contentRight () + xOffset, new PointF (0.0f, 0.5f));
                drawLabels(canvas, mViewPortHandler.contentLeft () - xOffset, new PointF (1.0f, 0.5f));
            }
        }
    }

    protected void drawLabels(Canvas canvas, float f, PointF pointF) {
        float labelRotationAngle = mXAxis.getLabelRotationAngle ();
        float[] fArr = new float[2];
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        BarData barData = mChart.getData ();
        int dataSetCount = barData.getDataSetCount ();
        int i = mMinX;
        while (i <= mMaxX) {
            float groupSpace = (i * dataSetCount) + (i * barData.getGroupSpace ()) + (barData.getGroupSpace () / 2.0f);
            fArr[1] = groupSpace;
            if (1 < dataSetCount) {
                fArr[1] = groupSpace + ((dataSetCount - 1.0f) / 2.0f);
            }
            mTrans.pointValuesToPixel (fArr);
            if (mViewPortHandler.isInBoundsY (fArr[1])) {
                drawLabel(canvas, mXAxis.getValues ().get (i), i, f, fArr[1], pointF, labelRotationAngle);
            }
            i += mXAxis.mAxisLabelModulus;
        }
    }

    public void renderGridLines(Canvas canvas) {
        if (mXAxis.isDrawGridLinesEnabled () && mXAxis.isEnabled ()) {
            float[] fArr = new float[2];
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            mGridPaint.setColor (mXAxis.getGridColor ());
            mGridPaint.setStrokeWidth (mXAxis.getGridLineWidth ());
            BarData barData = mChart.getData ();
            int dataSetCount = barData.getDataSetCount ();
            int i = mMinX;
            while (i <= mMaxX) {
                fArr[1] = ((i * dataSetCount) + (i * barData.getGroupSpace ())) - 0.5f;
                mTrans.pointValuesToPixel (fArr);
                if (mViewPortHandler.isInBoundsY (fArr[1])) {
                    canvas.drawLine (mViewPortHandler.contentLeft (), fArr[1], mViewPortHandler.contentRight (), fArr[1], mGridPaint);
                }
                i += mXAxis.mAxisLabelModulus;
            }
        }
    }

    public void renderAxisLine(Canvas canvas) {
        if (mXAxis.isDrawAxisLineEnabled () && mXAxis.isEnabled ()) {
            mAxisLinePaint.setColor (mXAxis.getAxisLineColor ());
            mAxisLinePaint.setStrokeWidth (mXAxis.getAxisLineWidth ());
            if (XAxis.XAxisPosition.TOP == this.mXAxis.getPosition () || XAxis.XAxisPosition.TOP_INSIDE == this.mXAxis.getPosition () || XAxis.XAxisPosition.BOTH_SIDED == this.mXAxis.getPosition ()) {
                canvas.drawLine (mViewPortHandler.contentRight (), mViewPortHandler.contentTop (), mViewPortHandler.contentRight (), mViewPortHandler.contentBottom (), mAxisLinePaint);
            }
            if (XAxis.XAxisPosition.BOTTOM == this.mXAxis.getPosition () || XAxis.XAxisPosition.BOTTOM_INSIDE == this.mXAxis.getPosition () || XAxis.XAxisPosition.BOTH_SIDED == this.mXAxis.getPosition ()) {
                canvas.drawLine (mViewPortHandler.contentLeft (), mViewPortHandler.contentTop (), mViewPortHandler.contentLeft (), mViewPortHandler.contentBottom (), mAxisLinePaint);
            }
        }
    }

    public void renderLimitLines(Canvas canvas) {
        List<LimitLine> limitLines = mXAxis.getLimitLines ();
        if (null != limitLines && 0 < limitLines.size ()) {
            float[] fArr = new float[2];
            Path path = new Path ();
            for (int i = 0; i < limitLines.size (); i++) {
                LimitLine limitLine = limitLines.get (i);
                if (limitLine.isEnabled ()) {
                    mLimitLinePaint.setStyle (Paint.Style.STROKE);
                    mLimitLinePaint.setColor (limitLine.getLineColor ());
                    mLimitLinePaint.setStrokeWidth (limitLine.getLineWidth ());
                    mLimitLinePaint.setPathEffect (limitLine.getDashPathEffect ());
                    fArr[1] = limitLine.getLimit ();
                    mTrans.pointValuesToPixel (fArr);
                    path.moveTo (mViewPortHandler.contentLeft (), fArr[1]);
                    path.lineTo (mViewPortHandler.contentRight (), fArr[1]);
                    canvas.drawPath (path, mLimitLinePaint);
                    path.reset ();
                    String label = limitLine.getLabel ();
                    if (null != label && !"".equals (label)) {
                        mLimitLinePaint.setStyle (limitLine.getTextStyle ());
                        mLimitLinePaint.setPathEffect (null);
                        mLimitLinePaint.setColor (limitLine.getTextColor ());
                        mLimitLinePaint.setStrokeWidth (0.5f);
                        mLimitLinePaint.setTextSize (limitLine.getTextSize ());
                        float calcTextHeight = Utils.calcTextHeight (mLimitLinePaint, label);
                        float convertDpToPixel = Utils.convertDpToPixel (4.0f) + limitLine.getXOffset ();
                        float lineWidth = limitLine.getLineWidth () + calcTextHeight + limitLine.getYOffset ();
                        LimitLine.LimitLabelPosition labelPosition = limitLine.getLabelPosition ();
                        if (LimitLine.LimitLabelPosition.RIGHT_TOP == labelPosition) {
                            mLimitLinePaint.setTextAlign (Paint.Align.RIGHT);
                            canvas.drawText (label, mViewPortHandler.contentRight () - convertDpToPixel, (fArr[1] - lineWidth) + calcTextHeight, mLimitLinePaint);
                        } else if (LimitLine.LimitLabelPosition.RIGHT_BOTTOM == labelPosition) {
                            mLimitLinePaint.setTextAlign (Paint.Align.RIGHT);
                            canvas.drawText (label, mViewPortHandler.contentRight () - convertDpToPixel, fArr[1] + lineWidth, mLimitLinePaint);
                        } else if (LimitLine.LimitLabelPosition.LEFT_TOP == labelPosition) {
                            mLimitLinePaint.setTextAlign (Paint.Align.LEFT);
                            canvas.drawText (label, mViewPortHandler.contentLeft () + convertDpToPixel, (fArr[1] - lineWidth) + calcTextHeight, mLimitLinePaint);
                        } else {
                            mLimitLinePaint.setTextAlign (Paint.Align.LEFT);
                            canvas.drawText (label, mViewPortHandler.offsetLeft () + convertDpToPixel, fArr[1] + lineWidth, mLimitLinePaint);
                        }
                    }
                }
            }
        }
    }
}

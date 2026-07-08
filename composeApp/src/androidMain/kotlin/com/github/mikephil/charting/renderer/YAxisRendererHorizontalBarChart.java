package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.PointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

public class YAxisRendererHorizontalBarChart extends YAxisRenderer {
    public YAxisRendererHorizontalBarChart(ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer) {
        super (viewPortHandler, yAxis, transformer);
        mLimitLinePaint.setTextAlign (Paint.Align.LEFT);
    }

    public void computeAxis(float f, float f2) {
        if (10.0f < this.mViewPortHandler.contentHeight () && !mViewPortHandler.isFullyZoomedOutX ()) {
            PointD valuesByTouchPoint = mTrans.getValuesByTouchPoint (mViewPortHandler.contentLeft (), mViewPortHandler.contentTop ());
            PointD valuesByTouchPoint2 = mTrans.getValuesByTouchPoint (mViewPortHandler.contentRight (), mViewPortHandler.contentTop ());
            if (!mYAxis.isInverted ()) {
                f = (float) valuesByTouchPoint.f828x;
                f2 = (float) valuesByTouchPoint2.f828x;
            } else {
                float f3 = (float) valuesByTouchPoint2.f828x;
                f2 = (float) valuesByTouchPoint.f828x;
                f = f3;
            }
        }
        computeAxisValues(f, f2);
    }

    public void renderAxisLabels(Canvas canvas) {
        float f;
        float contentBottom;
        float contentTop;
        if (mYAxis.isEnabled () && mYAxis.isDrawLabelsEnabled ()) {
            int i = mYAxis.mEntryCount * 2;
            float[] fArr = new float[i];
            for (int i2 = 0; i2 < i; i2 += 2) {
                fArr[i2] = mYAxis.mEntries[i2 / 2];
            }
            mTrans.pointValuesToPixel (fArr);
            mAxisLabelPaint.setTypeface (mYAxis.getTypeface ());
            mAxisLabelPaint.setTextSize (mYAxis.getTextSize ());
            mAxisLabelPaint.setColor (mYAxis.getTextColor ());
            mAxisLabelPaint.setTextAlign (Paint.Align.CENTER);
            float convertDpToPixel = Utils.convertDpToPixel (2.5f);
            float calcTextHeight = Utils.calcTextHeight (mAxisLabelPaint, "Q");
            YAxis.AxisDependency axisDependency = mYAxis.getAxisDependency ();
            YAxis.YAxisLabelPosition labelPosition = mYAxis.getLabelPosition ();
            if (YAxis.AxisDependency.LEFT == axisDependency) {
                if (YAxis.YAxisLabelPosition.OUTSIDE_CHART == labelPosition) {
                    contentTop = mViewPortHandler.contentTop ();
                } else {
                    contentTop = mViewPortHandler.contentTop ();
                }
                f = contentTop - convertDpToPixel;
            } else {
                if (YAxis.YAxisLabelPosition.OUTSIDE_CHART == labelPosition) {
                    contentBottom = mViewPortHandler.contentBottom ();
                } else {
                    contentBottom = mViewPortHandler.contentBottom ();
                }
                f = contentBottom + calcTextHeight + convertDpToPixel;
            }
            drawYLabels(canvas, f, fArr, mYAxis.getYOffset ());
        }
    }

    public void renderAxisLine(Canvas canvas) {
        if (mYAxis.isEnabled () && mYAxis.isDrawAxisLineEnabled ()) {
            mAxisLinePaint.setColor (mYAxis.getAxisLineColor ());
            mAxisLinePaint.setStrokeWidth (mYAxis.getAxisLineWidth ());
            if (YAxis.AxisDependency.LEFT == this.mYAxis.getAxisDependency ()) {
                canvas.drawLine (mViewPortHandler.contentLeft (), mViewPortHandler.contentTop (), mViewPortHandler.contentRight (), mViewPortHandler.contentTop (), mAxisLinePaint);
            } else {
                canvas.drawLine (mViewPortHandler.contentLeft (), mViewPortHandler.contentBottom (), mViewPortHandler.contentRight (), mViewPortHandler.contentBottom (), mAxisLinePaint);
            }
        }
    }

    protected void drawYLabels(Canvas canvas, float f, float[] fArr, float f2) {
        mAxisLabelPaint.setTypeface (mYAxis.getTypeface ());
        mAxisLabelPaint.setTextSize (mYAxis.getTextSize ());
        mAxisLabelPaint.setColor (mYAxis.getTextColor ());
        int i = 0;
        while (true) {
            YAxis yAxis = mYAxis;
            if (i < yAxis.mEntryCount) {
                String formattedLabel = yAxis.getFormattedLabel (i);
                if (mYAxis.isDrawTopYLabelEntryEnabled () || i < mYAxis.mEntryCount - 1) {
                    canvas.drawText (formattedLabel, fArr[i * 2], f - f2, mAxisLabelPaint);
                    i++;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public void renderGridLines(Canvas canvas) {
        if (mYAxis.isEnabled ()) {
            float[] fArr = new float[2];
            if (mYAxis.isDrawGridLinesEnabled ()) {
                mGridPaint.setColor (mYAxis.getGridColor ());
                mGridPaint.setStrokeWidth (mYAxis.getGridLineWidth ());
                int i = 0;
                while (true) {
                    YAxis yAxis = mYAxis;
                    if (i >= yAxis.mEntryCount) {
                        break;
                    }
                    fArr[0] = yAxis.mEntries[i];
                    mTrans.pointValuesToPixel (fArr);
                    canvas.drawLine (fArr[0], mViewPortHandler.contentTop (), fArr[0], mViewPortHandler.contentBottom (), mGridPaint);
                    i++;
                }
            }
            if (mYAxis.isDrawZeroLineEnabled ()) {
                fArr[0] = 0.0f;
                mTrans.pointValuesToPixel (fArr);
                float f = fArr[0];
                drawZeroLine(canvas, f + 1.0f, f + 1.0f, mViewPortHandler.contentTop (), mViewPortHandler.contentBottom ());
            }
        }
    }

    public void renderLimitLines(Canvas canvas) {
        List<LimitLine> limitLines = mYAxis.getLimitLines ();
        if (null != limitLines && 0 < limitLines.size ()) {
            float[] fArr = new float[4];
            Path path = new Path ();
            for (int i = 0; i < limitLines.size (); i++) {
                LimitLine limitLine = limitLines.get (i);
                if (limitLine.isEnabled ()) {
                    fArr[0] = limitLine.getLimit ();
                    fArr[2] = limitLine.getLimit ();
                    mTrans.pointValuesToPixel (fArr);
                    fArr[1] = mViewPortHandler.contentTop ();
                    fArr[3] = mViewPortHandler.contentBottom ();
                    path.moveTo (fArr[0], fArr[1]);
                    path.lineTo (fArr[2], fArr[3]);
                    mLimitLinePaint.setStyle (Paint.Style.STROKE);
                    mLimitLinePaint.setColor (limitLine.getLineColor ());
                    mLimitLinePaint.setPathEffect (limitLine.getDashPathEffect ());
                    mLimitLinePaint.setStrokeWidth (limitLine.getLineWidth ());
                    canvas.drawPath (path, mLimitLinePaint);
                    path.reset ();
                    String label = limitLine.getLabel ();
                    if (null != label && !"".equals (label)) {
                        mLimitLinePaint.setStyle (limitLine.getTextStyle ());
                        mLimitLinePaint.setPathEffect (null);
                        mLimitLinePaint.setColor (limitLine.getTextColor ());
                        mLimitLinePaint.setTypeface (limitLine.getTypeface ());
                        mLimitLinePaint.setStrokeWidth (0.5f);
                        mLimitLinePaint.setTextSize (limitLine.getTextSize ());
                        float lineWidth = limitLine.getLineWidth () + limitLine.getXOffset ();
                        float convertDpToPixel = Utils.convertDpToPixel (2.0f) + limitLine.getYOffset ();
                        LimitLine.LimitLabelPosition labelPosition = limitLine.getLabelPosition ();
                        if (LimitLine.LimitLabelPosition.RIGHT_TOP == labelPosition) {
                            mLimitLinePaint.setTextAlign (Paint.Align.LEFT);
                            canvas.drawText (label, fArr[0] + lineWidth, mViewPortHandler.contentTop () + convertDpToPixel + Utils.calcTextHeight (mLimitLinePaint, label), mLimitLinePaint);
                        } else if (LimitLine.LimitLabelPosition.RIGHT_BOTTOM == labelPosition) {
                            mLimitLinePaint.setTextAlign (Paint.Align.LEFT);
                            canvas.drawText (label, fArr[0] + lineWidth, mViewPortHandler.contentBottom () - convertDpToPixel, mLimitLinePaint);
                        } else if (LimitLine.LimitLabelPosition.LEFT_TOP == labelPosition) {
                            mLimitLinePaint.setTextAlign (Paint.Align.RIGHT);
                            canvas.drawText (label, fArr[0] - lineWidth, mViewPortHandler.contentTop () + convertDpToPixel + Utils.calcTextHeight (mLimitLinePaint, label), mLimitLinePaint);
                        } else {
                            mLimitLinePaint.setTextAlign (Paint.Align.RIGHT);
                            canvas.drawText (label, fArr[0] - lineWidth, mViewPortHandler.contentBottom () - convertDpToPixel, mLimitLinePaint);
                        }
                    }
                }
            }
        }
    }
}

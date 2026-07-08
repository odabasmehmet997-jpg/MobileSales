package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.PointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

public class YAxisRenderer extends AxisRenderer {
    protected YAxis mYAxis;
    protected Paint mZeroLinePaint;

    public YAxisRenderer(ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer) {
        super (viewPortHandler, transformer);
        this.mYAxis = yAxis;
        mAxisLabelPaint.setColor (ViewCompat.MEASURED_STATE_MASK);
        mAxisLabelPaint.setTextSize (Utils.convertDpToPixel (10.0f));
        Paint paint = new Paint (1);
        this.mZeroLinePaint = paint;
        paint.setColor (-7829368);
        this.mZeroLinePaint.setStrokeWidth (1.0f);
        this.mZeroLinePaint.setStyle (Paint.Style.STROKE);
    }

    public void computeAxis(float f, float f2) {
        if (10.0f < this.mViewPortHandler.contentWidth () && !mViewPortHandler.isFullyZoomedOutY ()) {
            PointD valuesByTouchPoint = mTrans.getValuesByTouchPoint (mViewPortHandler.contentLeft (), mViewPortHandler.contentTop ());
            PointD valuesByTouchPoint2 = mTrans.getValuesByTouchPoint (mViewPortHandler.contentLeft (), mViewPortHandler.contentBottom ());
            if (!this.mYAxis.isInverted ()) {
                float f3 = (float) valuesByTouchPoint2.f829y;
                f2 = (float) valuesByTouchPoint.f829y;
                f = f3;
            } else {
                f = (float) valuesByTouchPoint.f829y;
                f2 = (float) valuesByTouchPoint2.f829y;
            }
        }
        computeAxisValues(f, f2);
    }

    public void computeAxisValues(float f, float f2) {
        double d;
        double d2;
        int i;
        float f3 = f;
        int labelCount = this.mYAxis.getLabelCount ();
        double abs = Math.abs (f2 - f3);
        if (0 == labelCount || 0.0d >= abs) {
            YAxis yAxis = this.mYAxis;
            yAxis.mEntries = new float[0];
            yAxis.mEntryCount = 0;
            return;
        }
        double roundToNextSignificant = Utils.roundToNextSignificant (abs / labelCount);
        if (this.mYAxis.isGranularityEnabled () && roundToNextSignificant < this.mYAxis.getGranularity ()) {
            roundToNextSignificant = this.mYAxis.getGranularity ();
        }
        double roundToNextSignificant2 = Utils.roundToNextSignificant (Math.pow (10.0d, (int) Math.log10 (roundToNextSignificant)));
        if (5 < ((int) (roundToNextSignificant / roundToNextSignificant2))) {
            roundToNextSignificant = Math.floor (roundToNextSignificant2 * 10.0d);
        }
        if (this.mYAxis.isForceLabelsEnabled ()) {
            float f4 = ((float) abs) / (labelCount - 1);
            YAxis yAxis2 = this.mYAxis;
            yAxis2.mEntryCount = labelCount;
            if (yAxis2.mEntries.length < labelCount) {
                yAxis2.mEntries = new float[labelCount];
            }
            for (int i2 = 0; i2 < labelCount; i2++) {
                this.mYAxis.mEntries[i2] = f3;
                f3 += f4;
            }
        } else if (this.mYAxis.isShowOnlyMinMaxEnabled ()) {
            YAxis yAxis3 = this.mYAxis;
            yAxis3.mEntryCount = 2;
            yAxis3.mEntries = new float[]{f3, f2};
            float[] fArr = {f3, f2};
        } else {
            int i3 = (0.0d < roundToNextSignificant ? 1 : (0.0d == roundToNextSignificant ? 0 : -1));
            if (0 == i3) {
                d = 0.0d;
            } else {
                d = Math.ceil (f3 / roundToNextSignificant) * roundToNextSignificant;
            }
            if (0 == i3) {
                d2 = 0.0d;
            } else {
                d2 = Utils.nextUp (Math.floor (f2 / roundToNextSignificant) * roundToNextSignificant);
            }
            if (0 != i3) {
                i = 0;
                for (double d3 = d; d3 <= d2; d3 += roundToNextSignificant) {
                    i++;
                }
            } else {
                i = 0;
            }
            YAxis yAxis4 = this.mYAxis;
            yAxis4.mEntryCount = i;
            if (yAxis4.mEntries.length < i) {
                yAxis4.mEntries = new float[i];
            }
            for (int i4 = 0; i4 < i; i4++) {
                if (0.0d == d) {
                    d = 0.0d;
                }
                this.mYAxis.mEntries[i4] = (float) d;
                d += roundToNextSignificant;
            }
        }
        if (1.0d > roundToNextSignificant) {
            this.mYAxis.mDecimals = (int) Math.ceil (-Math.log10 (roundToNextSignificant));
        } else {
            this.mYAxis.mDecimals = 0;
        }
    }

    public void renderAxisLabels(Canvas canvas) {
        float f;
        float contentRight;
        float contentRight2;
        if (this.mYAxis.isEnabled () && this.mYAxis.isDrawLabelsEnabled ()) {
            int i = this.mYAxis.mEntryCount * 2;
            float[] fArr = new float[i];
            for (int i2 = 0; i2 < i; i2 += 2) {
                fArr[i2 + 1] = this.mYAxis.mEntries[i2 / 2];
            }
            mTrans.pointValuesToPixel (fArr);
            mAxisLabelPaint.setTypeface (this.mYAxis.getTypeface ());
            mAxisLabelPaint.setTextSize (this.mYAxis.getTextSize ());
            mAxisLabelPaint.setColor (this.mYAxis.getTextColor ());
            float xOffset = this.mYAxis.getXOffset ();
            float calcTextHeight = (Utils.calcTextHeight (mAxisLabelPaint, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS) / 2.5f) + this.mYAxis.getYOffset ();
            YAxis.AxisDependency axisDependency = this.mYAxis.getAxisDependency ();
            YAxis.YAxisLabelPosition labelPosition = this.mYAxis.getLabelPosition ();
            if (YAxis.AxisDependency.LEFT == axisDependency) {
                if (YAxis.YAxisLabelPosition.OUTSIDE_CHART == labelPosition) {
                    mAxisLabelPaint.setTextAlign (Paint.Align.RIGHT);
                    contentRight = mViewPortHandler.offsetLeft ();
                    f = contentRight - xOffset;
                } else {
                    mAxisLabelPaint.setTextAlign (Paint.Align.LEFT);
                    contentRight2 = mViewPortHandler.offsetLeft ();
                    f = contentRight2 + xOffset;
                }
            } else if (YAxis.YAxisLabelPosition.OUTSIDE_CHART == labelPosition) {
                mAxisLabelPaint.setTextAlign (Paint.Align.LEFT);
                contentRight2 = mViewPortHandler.contentRight ();
                f = contentRight2 + xOffset;
            } else {
                mAxisLabelPaint.setTextAlign (Paint.Align.RIGHT);
                contentRight = mViewPortHandler.contentRight ();
                f = contentRight - xOffset;
            }
            drawYLabels(canvas, f, fArr, calcTextHeight);
        }
    }

    public void renderAxisLine(Canvas canvas) {
        if (this.mYAxis.isEnabled () && this.mYAxis.isDrawAxisLineEnabled ()) {
            mAxisLinePaint.setColor (this.mYAxis.getAxisLineColor ());
            mAxisLinePaint.setStrokeWidth (this.mYAxis.getAxisLineWidth ());
            if (YAxis.AxisDependency.LEFT == mYAxis.getAxisDependency ()) {
                canvas.drawLine (mViewPortHandler.contentLeft (), mViewPortHandler.contentTop (), mViewPortHandler.contentLeft (), mViewPortHandler.contentBottom (), mAxisLinePaint);
            } else {
                canvas.drawLine (mViewPortHandler.contentRight (), mViewPortHandler.contentTop (), mViewPortHandler.contentRight (), mViewPortHandler.contentBottom (), mAxisLinePaint);
            }
        }
    }

    protected void drawYLabels(Canvas canvas, float f, float[] fArr, float f2) {
        int i = 0;
        while (true) {
            YAxis yAxis = this.mYAxis;
            if (i < yAxis.mEntryCount) {
                String formattedLabel = yAxis.getFormattedLabel (i);
                if (this.mYAxis.isDrawTopYLabelEntryEnabled () || i < this.mYAxis.mEntryCount - 1) {
                    canvas.drawText (formattedLabel, f, fArr[(i * 2) + 1] + f2, mAxisLabelPaint);
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
        if (this.mYAxis.isEnabled ()) {
            float[] fArr = new float[2];
            if (this.mYAxis.isDrawGridLinesEnabled ()) {
                mGridPaint.setColor (this.mYAxis.getGridColor ());
                mGridPaint.setStrokeWidth (this.mYAxis.getGridLineWidth ());
                mGridPaint.setPathEffect (this.mYAxis.getGridDashPathEffect ());
                Path path = new Path ();
                int i = 0;
                while (true) {
                    YAxis yAxis = this.mYAxis;
                    if (i >= yAxis.mEntryCount) {
                        break;
                    }
                    fArr[1] = yAxis.mEntries[i];
                    mTrans.pointValuesToPixel (fArr);
                    path.moveTo (mViewPortHandler.offsetLeft (), fArr[1]);
                    path.lineTo (mViewPortHandler.contentRight (), fArr[1]);
                    canvas.drawPath (path, mGridPaint);
                    path.reset ();
                    i++;
                }
            }
            if (this.mYAxis.isDrawZeroLineEnabled ()) {
                fArr[1] = 0.0f;
                mTrans.pointValuesToPixel (fArr);
                float offsetLeft = mViewPortHandler.offsetLeft ();
                float contentRight = mViewPortHandler.contentRight ();
                float f = fArr[1];
                drawZeroLine(canvas, offsetLeft, contentRight, f - 1.0f, f - 1.0f);
            }
        }
    }

    public void drawZeroLine(Canvas canvas, float f, float f2, float f3, float f4) {
        this.mZeroLinePaint.setColor (this.mYAxis.getZeroLineColor ());
        this.mZeroLinePaint.setStrokeWidth (this.mYAxis.getZeroLineWidth ());
        Path path = new Path ();
        path.moveTo (f, f3);
        path.lineTo (f2, f4);
        canvas.drawPath (path, this.mZeroLinePaint);
    }

    public void renderLimitLines(Canvas canvas) {
        List<LimitLine> limitLines = this.mYAxis.getLimitLines ();
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
                        mLimitLinePaint.setTypeface (limitLine.getTypeface ());
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

package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

public class XAxisRenderer extends AxisRenderer {
    private final Path mLimitLinePath = new Path ();
    protected XAxis mXAxis;
    float [] mLimitLineSegmentsBuffer = new float[4];

    public XAxisRenderer(ViewPortHandler viewPortHandler, XAxis xAxis, Transformer transformer) {
        super (viewPortHandler, transformer);
        this.mXAxis = xAxis;
        mAxisLabelPaint.setColor (ViewCompat.MEASURED_STATE_MASK);
        mAxisLabelPaint.setTextAlign (Paint.Align.CENTER);
        mAxisLabelPaint.setTextSize (Utils.convertDpToPixel (10.0f));
    }

    public void computeAxis(float f, List<String> list) {
        mAxisLabelPaint.setTypeface (this.mXAxis.getTypeface ());
        mAxisLabelPaint.setTextSize (this.mXAxis.getTextSize ());
        StringBuilder sb = new StringBuilder ();
        int round = Math.round (f);
        for (int i = 0; i < round; i++) {
            sb.append ('h');
        }
        float f2 = Utils.calcTextSize (mAxisLabelPaint, sb.toString ()).width ();
        float calcTextHeight = Utils.calcTextHeight (mAxisLabelPaint, "Q");
        FSize sizeOfRotatedRectangleByDegrees = Utils.getSizeOfRotatedRectangleByDegrees (f2, calcTextHeight, this.mXAxis.getLabelRotationAngle ());
        StringBuilder sb2 = new StringBuilder ();
        int spaceBetweenLabels = this.mXAxis.getSpaceBetweenLabels ();
        for (int i2 = 0; i2 < spaceBetweenLabels; i2++) {
            sb2.append ('h');
        }
        FSize calcTextSize = Utils.calcTextSize (mAxisLabelPaint, sb2.toString ());
        this.mXAxis.mLabelWidth = Math.round (f2 + calcTextSize.width ());
        this.mXAxis.mLabelHeight = Math.round (calcTextHeight);
        this.mXAxis.mLabelRotatedWidth = Math.round (sizeOfRotatedRectangleByDegrees.width () + calcTextSize.width ());
        this.mXAxis.mLabelRotatedHeight = Math.round (sizeOfRotatedRectangleByDegrees.height ());
        this.mXAxis.setValues (list);
    }

    public void renderAxisLabels(Canvas canvas) {
        if (this.mXAxis.isEnabled () && this.mXAxis.isDrawLabelsEnabled ()) {
            float yOffset = this.mXAxis.getYOffset ();
            mAxisLabelPaint.setTypeface (this.mXAxis.getTypeface ());
            mAxisLabelPaint.setTextSize (this.mXAxis.getTextSize ());
            mAxisLabelPaint.setColor (this.mXAxis.getTextColor ());
            if (XAxis.XAxisPosition.TOP == mXAxis.getPosition ()) {
                drawLabels(canvas, mViewPortHandler.contentTop () - yOffset, new PointF (0.5f, 1.0f));
            } else if (XAxis.XAxisPosition.TOP_INSIDE == mXAxis.getPosition ()) {
                drawLabels(canvas, mViewPortHandler.contentTop () + yOffset + this.mXAxis.mLabelRotatedHeight, new PointF (0.5f, 1.0f));
            } else if (XAxis.XAxisPosition.BOTTOM == mXAxis.getPosition ()) {
                drawLabels(canvas, mViewPortHandler.contentBottom () + yOffset, new PointF (0.5f, 0.0f));
            } else if (XAxis.XAxisPosition.BOTTOM_INSIDE == mXAxis.getPosition ()) {
                drawLabels(canvas, (mViewPortHandler.contentBottom () - yOffset) - this.mXAxis.mLabelRotatedHeight, new PointF (0.5f, 0.0f));
            } else {
                drawLabels(canvas, mViewPortHandler.contentTop () - yOffset, new PointF (0.5f, 1.0f));
                drawLabels(canvas, mViewPortHandler.contentBottom () + yOffset, new PointF (0.5f, 0.0f));
            }
        }
    }

    public void renderAxisLine(Canvas canvas) {
        if (this.mXAxis.isDrawAxisLineEnabled () && this.mXAxis.isEnabled ()) {
            mAxisLinePaint.setColor (this.mXAxis.getAxisLineColor ());
            mAxisLinePaint.setStrokeWidth (this.mXAxis.getAxisLineWidth ());
            if (XAxis.XAxisPosition.TOP == mXAxis.getPosition () || XAxis.XAxisPosition.TOP_INSIDE == mXAxis.getPosition () || XAxis.XAxisPosition.BOTH_SIDED == mXAxis.getPosition ()) {
                canvas.drawLine (mViewPortHandler.contentLeft (), mViewPortHandler.contentTop (), mViewPortHandler.contentRight (), mViewPortHandler.contentTop (), mAxisLinePaint);
            }
            if (XAxis.XAxisPosition.BOTTOM == mXAxis.getPosition () || XAxis.XAxisPosition.BOTTOM_INSIDE == mXAxis.getPosition () || XAxis.XAxisPosition.BOTH_SIDED == mXAxis.getPosition ()) {
                canvas.drawLine (mViewPortHandler.contentLeft (), mViewPortHandler.contentBottom (), mViewPortHandler.contentRight (), mViewPortHandler.contentBottom (), mAxisLinePaint);
            }
        }
    }

    protected void drawLabels(Canvas canvas, float f, PointF pointF) {
        float labelRotationAngle = this.mXAxis.getLabelRotationAngle ();
        float[] fArr = new float[2];
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        int i = mMinX;
        while (i <= mMaxX) {
            fArr[0] = i;
            mTrans.pointValuesToPixel (fArr);
            if (mViewPortHandler.isInBoundsX (fArr[0])) {
                String str = this.mXAxis.getValues ().get (i);
                if (this.mXAxis.isAvoidFirstLastClippingEnabled ()) {
                    if (i == this.mXAxis.getValues ().size () - 1 && 1 < mXAxis.getValues ().size ()) {
                        float calcTextWidth = Utils.calcTextWidth (mAxisLabelPaint, str);
                        if (calcTextWidth > mViewPortHandler.offsetRight () * 2.0f && fArr[0] + calcTextWidth > mViewPortHandler.getChartWidth ()) {
                            fArr[0] = fArr[0] - (calcTextWidth / 2.0f);
                        }
                    } else if (0 == i) {
                        fArr[0] = fArr[0] + (Utils.calcTextWidth (mAxisLabelPaint, str) / 2.0f);
                    }
                }
                drawLabel(canvas, str, i, fArr[0], f, pointF, labelRotationAngle);
            }
            i += this.mXAxis.mAxisLabelModulus;
        }
    }

    public void drawLabel(Canvas canvas, String str, int i, float f, float f2, PointF pointF, float f3) {
        Utils.drawXAxisValue (canvas, this.mXAxis.getValueFormatter ().getXValue (str, i, mViewPortHandler), f, f2, mAxisLabelPaint, pointF, f3);
    }

    public void renderGridLines(Canvas canvas) {
        if (this.mXAxis.isDrawGridLinesEnabled () && this.mXAxis.isEnabled ()) {
            float[] fArr = new float[2];
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            mGridPaint.setColor (this.mXAxis.getGridColor ());
            mGridPaint.setStrokeWidth (this.mXAxis.getGridLineWidth ());
            mGridPaint.setPathEffect (this.mXAxis.getGridDashPathEffect ());
            Path path = new Path ();
            int i = mMinX;
            while (i <= mMaxX) {
                fArr[0] = i;
                mTrans.pointValuesToPixel (fArr);
                if (fArr[0] >= mViewPortHandler.offsetLeft () && fArr[0] <= mViewPortHandler.getChartWidth ()) {
                    path.moveTo (fArr[0], mViewPortHandler.contentBottom ());
                    path.lineTo (fArr[0], mViewPortHandler.contentTop ());
                    canvas.drawPath (path, mGridPaint);
                }
                path.reset ();
                i += this.mXAxis.mAxisLabelModulus;
            }
        }
    }

    public void renderLimitLines(Canvas canvas) {
        List<LimitLine> limitLines = this.mXAxis.getLimitLines ();
        if (null != limitLines && 0 < limitLines.size ()) {
            for (int i = 0; i < limitLines.size (); i++) {
                LimitLine limitLine = limitLines.get (i);
                if (limitLine.isEnabled ()) {
                    float[] fArr = {limitLine.getLimit (), 0.0f};
                    mTrans.pointValuesToPixel (fArr);
                    renderLimitLineLine(canvas, limitLine, fArr);
                    renderLimitLineLabel(canvas, limitLine, fArr, limitLine.getYOffset () + 2.0f);
                }
            }
        }
    }

    public void renderLimitLineLine(Canvas canvas, LimitLine limitLine, float [] fArr) {
        float[] fArr2 = this.mLimitLineSegmentsBuffer;
        fArr2[0] = fArr[0];
        fArr2[1] = mViewPortHandler.contentTop ();
        float[] fArr3 = this.mLimitLineSegmentsBuffer;
        fArr3[2] = fArr[0];
        fArr3[3] = mViewPortHandler.contentBottom ();
        this.mLimitLinePath.reset ();
        Path path = this.mLimitLinePath;
        float[] fArr4 = this.mLimitLineSegmentsBuffer;
        path.moveTo (fArr4[0], fArr4[1]);
        Path path2 = this.mLimitLinePath;
        float[] fArr5 = this.mLimitLineSegmentsBuffer;
        path2.lineTo (fArr5[2], fArr5[3]);
        mLimitLinePaint.setStyle (Paint.Style.STROKE);
        mLimitLinePaint.setColor (limitLine.getLineColor ());
        mLimitLinePaint.setStrokeWidth (limitLine.getLineWidth ());
        mLimitLinePaint.setPathEffect (limitLine.getDashPathEffect ());
        canvas.drawPath (this.mLimitLinePath, mLimitLinePaint);
    }

    public void renderLimitLineLabel(Canvas canvas, LimitLine limitLine, float[] fArr, float f) {
        String label = limitLine.getLabel ();
        if (null != label && !"".equals (label)) {
            mLimitLinePaint.setStyle (limitLine.getTextStyle ());
            mLimitLinePaint.setPathEffect (null);
            mLimitLinePaint.setColor (limitLine.getTextColor ());
            mLimitLinePaint.setStrokeWidth (0.5f);
            mLimitLinePaint.setTextSize (limitLine.getTextSize ());
            float lineWidth = limitLine.getLineWidth () + limitLine.getXOffset ();
            LimitLine.LimitLabelPosition labelPosition = limitLine.getLabelPosition ();
            if (LimitLine.LimitLabelPosition.RIGHT_TOP == labelPosition) {
                mLimitLinePaint.setTextAlign (Paint.Align.LEFT);
                canvas.drawText (label, fArr[0] + lineWidth, mViewPortHandler.contentTop () + f + Utils.calcTextHeight (mLimitLinePaint, label), mLimitLinePaint);
            } else if (LimitLine.LimitLabelPosition.RIGHT_BOTTOM == labelPosition) {
                mLimitLinePaint.setTextAlign (Paint.Align.LEFT);
                canvas.drawText (label, fArr[0] + lineWidth, mViewPortHandler.contentBottom () - f, mLimitLinePaint);
            } else if (LimitLine.LimitLabelPosition.LEFT_TOP == labelPosition) {
                mLimitLinePaint.setTextAlign (Paint.Align.RIGHT);
                canvas.drawText (label, fArr[0] - lineWidth, mViewPortHandler.contentTop () + f + Utils.calcTextHeight (mLimitLinePaint, label), mLimitLinePaint);
            } else {
                mLimitLinePaint.setTextAlign (Paint.Align.RIGHT);
                canvas.drawText (label, fArr[0] - lineWidth, mViewPortHandler.contentBottom () - f, mLimitLinePaint);
            }
        }
    }
}

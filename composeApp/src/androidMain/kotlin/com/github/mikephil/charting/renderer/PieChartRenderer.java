package com.github.mikephil.charting.renderer;

import android.graphics.*;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.lang.ref.WeakReference;
import java.util.List;

public class PieChartRenderer extends DataRenderer {
    private final TextPaint mCenterTextPaint;
    private final RectF mCenterTextLastBounds = new RectF ();
    private final RectF[] mRectBuffer = {new RectF (), new RectF (), new RectF ()};
    private final Path mPathBuffer = new Path ();
    private final RectF mInnerRectBuffer = new RectF ();
    private final Path mHoleCirclePath = new Path ();
    protected Canvas mBitmapCanvas;
    protected PieChart mChart;
    protected WeakReference<Bitmap> mDrawBitmap;
    protected Paint mHolePaint;
    protected Paint mTransparentCirclePaint;
    protected Paint mValueLinePaint;
    private CharSequence mCenterTextLastValue;
    private StaticLayout mCenterTextLayout;

    public PieChartRenderer(PieChart pieChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super (chartAnimator, viewPortHandler);
        this.mChart = pieChart;
        Paint paint = new Paint (1);
        this.mHolePaint = paint;
        paint.setColor (-1);
        Paint paint2 = this.mHolePaint;
        final Paint.Style style = Paint.Style.FILL;
        paint2.setStyle (style);
        Paint paint3 = new Paint (1);
        this.mTransparentCirclePaint = paint3;
        paint3.setColor (-1);
        this.mTransparentCirclePaint.setStyle (style);
        this.mTransparentCirclePaint.setAlpha (105);
        TextPaint textPaint = new TextPaint (1);
        this.mCenterTextPaint = textPaint;
        textPaint.setColor (ViewCompat.MEASURED_STATE_MASK);
        this.mCenterTextPaint.setTextSize (Utils.convertDpToPixel (12.0f));
        mValuePaint.setTextSize (Utils.convertDpToPixel (13.0f));
        mValuePaint.setColor (-1);
        mValuePaint.setTextAlign (Paint.Align.CENTER);
        Paint paint4 = new Paint (1);
        this.mValueLinePaint = paint4;
        paint4.setStyle (Paint.Style.STROKE);
    }

    public void initBuffers() {
    }

    public Paint getPaintHole() {
        return this.mHolePaint;
    }

    public Paint getPaintTransparentCircle() {
        return this.mTransparentCirclePaint;
    }

    public TextPaint getPaintCenterText() {
        return this.mCenterTextPaint;
    }

    public void drawData(Canvas canvas) {
        int chartWidth = (int) mViewPortHandler.getChartWidth ();
        int chartHeight = (int) mViewPortHandler.getChartHeight ();
        WeakReference<Bitmap> weakReference = this.mDrawBitmap;
        if (!(null != weakReference && weakReference.get ().getWidth () == chartWidth && this.mDrawBitmap.get ().getHeight () == chartHeight)) {
            if (0 < chartWidth && 0 < chartHeight) {
                this.mDrawBitmap = new WeakReference<> (Bitmap.createBitmap (chartWidth, chartHeight, Bitmap.Config.ARGB_4444));
                this.mBitmapCanvas = new Canvas (this.mDrawBitmap.get ());
            } else {
                return;
            }
        }
        this.mDrawBitmap.get ().eraseColor (0);
        for (IPieDataSet iPieDataSet : this.mChart.getData ().getDataSets ()) {
            if (iPieDataSet.isVisible () && 0 < iPieDataSet.getEntryCount ()) {
                drawDataSet(canvas, iPieDataSet);
            }
        }
    }

    protected float calculateMinimumRadiusForSpacedSlice(PointF pointF, float f, float f2, float f3, float f4, float f5, float f6) {
        double d = (f5 + f6) * 0.017453292f;
        float cos = pointF.x + (((float) Math.cos (d)) * f);
        float sin = pointF.y + (((float) Math.sin (d)) * f);
        double d2 = (f5 + (f6 / 2.0f)) * 0.017453292f;
        return (float) ((f - ((float) ((Math.sqrt (Math.pow (cos - f3, 2.0d) + Math.pow (sin - f4, 2.0d)) / 2.0d) * Math.tan (((180.0d - f2) / 2.0d) * 0.017453292519943295d)))) - Math.sqrt (Math.pow ((pointF.x + (((float) Math.cos (d2)) * f)) - ((cos + f3) / 2.0f), 2.0d) + Math.pow ((pointF.y + (((float) Math.sin (d2)) * f)) - ((sin + f4) / 2.0f), 2.0d)));
    }

    protected void drawDataSet(Canvas canvas, IPieDataSet iPieDataSet) {
        float f;
        float f2;
        float f3;
        float f4;
        int i;
        float[] fArr;
        RectF rectF;
        float f5;
        PointF pointF;
        float f6;
        int i2;
        int i3;
        int i4;
        int i5;
        float f7;
        float f8;
        float f9;
        int i6;
        PointF pointF2;
        IPieDataSet iPieDataSet2 = iPieDataSet;
        float rotationAngle = this.mChart.getRotationAngle ();
        float phaseX = mAnimator.getPhaseX ();
        float phaseY = mAnimator.getPhaseY ();
        RectF circleBox = this.mChart.getCircleBox ();
        int entryCount = iPieDataSet.getEntryCount ();
        float[] drawAngles = this.mChart.getDrawAngles ();
        PointF centerCircleBox = this.mChart.getCenterCircleBox ();
        float radius = this.mChart.getRadius ();
        boolean z = this.mChart.isDrawHoleEnabled () && !this.mChart.isDrawSlicesUnderHoleEnabled ();
        float holeRadius = z ? (this.mChart.getHoleRadius () / 100.0f) * radius : 0.0f;
        int i7 = 0;
        for (int i8 = 0; i8 < entryCount; i8++) {
            if (1.0E-6d < Math.abs (iPieDataSet2.getEntryForIndex (i8).getVal ())) {
                i7++;
            }
        }
        if (1 >= i7) {
            f = 0.0f;
        } else {
            f = iPieDataSet.getSliceSpace ();
        }
        int i9 = 0;
        float f10 = 0.0f;
        while (i9 < entryCount) {
            float f11 = drawAngles[i9];
            Entry entryForIndex = iPieDataSet2.getEntryForIndex (i9);
            if (1.0E-6d >= Math.abs (entryForIndex.getVal ()) || this.mChart.needsHighlight (entryForIndex.getXIndex (), this.mChart.getData ().getIndexOfDataSet (iPieDataSet2))) {
                f5 = radius;
                f2 = rotationAngle;
                f4 = phaseX;
                f3 = phaseY;
                rectF = circleBox;
                i = entryCount;
                fArr = drawAngles;
                i3 = i9;
                i2 = i7;
                f6 = holeRadius;
                pointF = centerCircleBox;
            } else {
                boolean z2 = 0.0f < f && 180.0f >= f11;
                mRenderPaint.setColor (iPieDataSet2.getColor (i9));
                float f12 = 1 == i7 ? 0.0f : f / (radius * 0.017453292f);
                float f13 = ((f10 + (f12 / 2.0f)) * phaseY) + rotationAngle;
                float f14 = (f11 - f12) * phaseY;
                if (0.0f > f14) {
                    f14 = 0.0f;
                }
                this.mPathBuffer.reset ();
                int i10 = (0.0f < (f14 % 360.0f) ? 1 : (0.0f == (f14 % 360.0f) ? 0 : -1));
                if (0 == i10) {
                    i5 = i9;
                    i4 = i7;
                    i = entryCount;
                    f7 = radius;
                    this.mPathBuffer.addCircle (centerCircleBox.x, centerCircleBox.y, f7, Path.Direction.CW);
                    f2 = rotationAngle;
                    f4 = phaseX;
                    f3 = phaseY;
                    f9 = 0.0f;
                    f8 = 0.0f;
                } else {
                    i5 = i9;
                    i4 = i7;
                    i = entryCount;
                    f7 = radius;
                    f2 = rotationAngle;
                    double d = f13 * 0.017453292f;
                    f4 = phaseX;
                    f3 = phaseY;
                    float cos = centerCircleBox.x + (((float) Math.cos (d)) * f7);
                    float sin = centerCircleBox.y + (((float) Math.sin (d)) * f7);
                    this.mPathBuffer.moveTo (cos, sin);
                    this.mPathBuffer.arcTo (circleBox, f13, f14);
                    f9 = cos;
                    f8 = sin;
                }
                RectF rectF2 = this.mInnerRectBuffer;
                float f15 = centerCircleBox.x;
                float f16 = centerCircleBox.y;
                rectF2.set (f15 - holeRadius, f16 - holeRadius, f15 + holeRadius, f16 + holeRadius);
                if (!z || (0.0f >= holeRadius && !z2)) {
                    f5 = f7;
                    i3 = i5;
                    i2 = i4;
                    pointF = centerCircleBox;
                    rectF = circleBox;
                    fArr = drawAngles;
                    f6 = holeRadius;
                    if (0 != i10) {
                        if (z2) {
                            float calculateMinimumRadiusForSpacedSlice = calculateMinimumRadiusForSpacedSlice(pointF, f5, f11 * f3, f9, f8, f13, f14);
                            double d2 = (f13 + (f14 / 2.0f)) * 0.017453292f;
                            this.mPathBuffer.lineTo (pointF.x + (((float) Math.cos (d2)) * calculateMinimumRadiusForSpacedSlice), pointF.y + (calculateMinimumRadiusForSpacedSlice * ((float) Math.sin (d2))));
                        } else {
                            this.mPathBuffer.lineTo (pointF.x, pointF.y);
                        }
                    }
                } else {
                    if (z2) {
                        i3 = i5;
                        i2 = i4;
                        rectF = circleBox;
                        f6 = holeRadius;
                        i6 = 1;
                        f5 = f7;
                        pointF2 = centerCircleBox;
                        float calculateMinimumRadiusForSpacedSlice2 = calculateMinimumRadiusForSpacedSlice(centerCircleBox, f7, f11 * f3, f9, f8, f13, f14);
                        if (0.0f > calculateMinimumRadiusForSpacedSlice2) {
                            calculateMinimumRadiusForSpacedSlice2 = -calculateMinimumRadiusForSpacedSlice2;
                        }
                        holeRadius = Math.max (f6, calculateMinimumRadiusForSpacedSlice2);
                    } else {
                        pointF2 = centerCircleBox;
                        f5 = f7;
                        i3 = i5;
                        i2 = i4;
                        i6 = 1;
                        rectF = circleBox;
                        f6 = holeRadius;
                    }
                    float f17 = (i2 == i6 || 0.0f == holeRadius) ? 0.0f : f / (holeRadius * 0.017453292f);
                    float f18 = f2 + ((f10 + (f17 / 2.0f)) * f3);
                    float f19 = (f11 - f17) * f3;
                    if (0.0f > f19) {
                        f19 = 0.0f;
                    }
                    float f20 = f18 + f19;
                    if (0 == i10) {
                        this.mPathBuffer.addCircle (pointF2.x, pointF2.y, holeRadius, Path.Direction.CCW);
                        fArr = drawAngles;
                    } else {
                        double d3 = f20 * 0.017453292f;
                        fArr = drawAngles;
                        this.mPathBuffer.lineTo (pointF2.x + (((float) Math.cos (d3)) * holeRadius), pointF2.y + (holeRadius * ((float) Math.sin (d3))));
                        this.mPathBuffer.arcTo (this.mInnerRectBuffer, f20, -f19);
                    }
                    pointF = pointF2;
                }
                this.mPathBuffer.close ();
                this.mBitmapCanvas.drawPath (this.mPathBuffer, mRenderPaint);
            }
            f10 += f11 * f4;
            i9 = i3 + 1;
            rotationAngle = f2;
            iPieDataSet2 = iPieDataSet;
            i7 = i2;
            holeRadius = f6;
            centerCircleBox = pointF;
            radius = f5;
            circleBox = rectF;
            drawAngles = fArr;
            entryCount = i;
            phaseX = f4;
            phaseY = f3;
        }
    }

    // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        float f;
        PointF pointF;
        float[] fArr;
        float f2;
        int i;
        List<IPieDataSet> list;
        float f3;
        float val;
        PieDataSet.ValuePosition valuePosition;
        float f4;
        PieDataSet.ValuePosition valuePosition2;
        float f5;
        int i2;
        IPieDataSet iPieDataSet;
        int i3;
        PointF pointF2;
        float f6;
        float f7;
        float f8;
        float f9;
        IPieDataSet iPieDataSet2;
        PointF centerCircleBox = this.mChart.getCenterCircleBox ();
        float radius = this.mChart.getRadius ();
        float rotationAngle = this.mChart.getRotationAngle ();
        float[] drawAngles = this.mChart.getDrawAngles ();
        float[] absoluteAngles = this.mChart.getAbsoluteAngles ();
        float phaseX = mAnimator.getPhaseX ();
        float phaseY = mAnimator.getPhaseY ();
        float holeRadius = this.mChart.getHoleRadius () / 100.0f;
        float f10 = (radius / 10.0f) * 3.6f;
        if (this.mChart.isDrawHoleEnabled ()) {
            f10 = (radius - (radius * holeRadius)) / 2.0f;
        }
        float f11 = radius - f10;
        PieData pieData = this.mChart.getData ();
        List<IPieDataSet> dataSets = pieData.getDataSets ();
        float yValueSum = pieData.getYValueSum ();
        boolean isDrawSliceTextEnabled = this.mChart.isDrawSliceTextEnabled ();
        canvas.save ();
        int i4 = 0;
        int i5 = 0;
        while (i5 < dataSets.size ()) {
            IPieDataSet iPieDataSet3 = dataSets.get (i5);
            boolean isDrawValuesEnabled = iPieDataSet3.isDrawValuesEnabled ();
            if (isDrawValuesEnabled || isDrawSliceTextEnabled) {
                PieDataSet.ValuePosition xValuePosition = iPieDataSet3.getXValuePosition ();
                PieDataSet.ValuePosition yValuePosition = iPieDataSet3.getYValuePosition ();
                applyValueTextStyle(iPieDataSet3);
                float calcTextHeight = Utils.calcTextHeight (mValuePaint, "Q") + Utils.convertDpToPixel (4.0f);
                ValueFormatter valueFormatter = iPieDataSet3.getValueFormatter ();
                int entryCount = iPieDataSet3.getEntryCount ();
                this.mValueLinePaint.setColor (iPieDataSet3.getValueLineColor ());
                this.mValueLinePaint.setStrokeWidth (Utils.convertDpToPixel (iPieDataSet3.getValueLineWidth ()));
                int i6 = i4;
                int i7 = 0;
                while (i7 < entryCount) {
                    Entry entryForIndex = iPieDataSet3.getEntryForIndex (i7);
                    if (0 == i6) {
                        f3 = 0.0f;
                    } else {
                        f3 = absoluteAngles[i6 - 1] * phaseX;
                    }
                    float sliceSpace = ((f3 + ((drawAngles[i6] - ((iPieDataSet3.getSliceSpace () / (f11 * 0.017453292f)) / 2.0f)) / 2.0f)) * phaseY) + rotationAngle;
                    if (this.mChart.isUsePercentValuesEnabled ()) {
                        val = (entryForIndex.getVal () / yValueSum) * 100.0f;
                    } else {
                        val = entryForIndex.getVal ();
                    }
                    double d = sliceSpace * 0.017453292f;
                    float cos = (float) Math.cos (d);
                    float sin = (float) Math.sin (d);
                    boolean z = isDrawSliceTextEnabled && PieDataSet.ValuePosition.OUTSIDE_SLICE == xValuePosition;
                    boolean z2 = isDrawValuesEnabled && PieDataSet.ValuePosition.OUTSIDE_SLICE == yValuePosition;
                    boolean z3 = isDrawSliceTextEnabled && PieDataSet.ValuePosition.INSIDE_SLICE == xValuePosition;
                    boolean z4 = isDrawValuesEnabled && PieDataSet.ValuePosition.INSIDE_SLICE == yValuePosition;
                    if (z || z2) {
                        float valueLinePart1Length = iPieDataSet3.getValueLinePart1Length ();
                        float valueLinePart2Length = iPieDataSet3.getValueLinePart2Length ();
                        float valueLinePart1OffsetPercentage = iPieDataSet3.getValueLinePart1OffsetPercentage () / 100.0f;
                        if (this.mChart.isDrawHoleEnabled ()) {
                            float f12 = radius * holeRadius;
                            f6 = ((radius - f12) * valueLinePart1OffsetPercentage) + f12;
                        } else {
                            f6 = radius * valueLinePart1OffsetPercentage;
                        }
                        float abs = iPieDataSet3.isValueLineVariableLength () ? valueLinePart2Length * f11 * ((float) Math.abs (Math.sin (d))) : valueLinePart2Length * f11;
                        float f13 = centerCircleBox.x;
                        float f14 = (f6 * cos) + f13;
                        float f15 = centerCircleBox.y;
                        float f16 = (f6 * sin) + f15;
                        float f17 = (valueLinePart1Length + 1.0f) * f11;
                        float f18 = (f17 * cos) + f13;
                        float f19 = (f17 * sin) + f15;
                        double d2 = sliceSpace % 360.0d;
                        if (90.0d > d2 || 270.0d < d2) {
                            f7 = f18 + abs;
                            mValuePaint.setTextAlign (Paint.Align.LEFT);
                            f8 = f7 + Utils.convertDpToPixel (5.0f);
                        } else {
                            float f20 = f18 - abs;
                            mValuePaint.setTextAlign (Paint.Align.RIGHT);
                            f8 = f20 - Utils.convertDpToPixel (5.0f);
                            f7 = f20;
                        }
                        if (1122867 != iPieDataSet3.getValueLineColor ()) {
                            f4 = radius;
                            i3 = i7;
                            i2 = entryCount;
                            valuePosition2 = yValuePosition;
                            f9 = f8;
                            valuePosition = xValuePosition;
                            canvas.drawLine (f14, f16, f18, f19, this.mValueLinePaint);
                            canvas.drawLine (f18, f19, f7, f19, this.mValueLinePaint);
                        } else {
                            valuePosition = xValuePosition;
                            f4 = radius;
                            i3 = i7;
                            i2 = entryCount;
                            valuePosition2 = yValuePosition;
                            f9 = f8;
                        }
                        if (!z || !z2) {
                            iPieDataSet2 = iPieDataSet3;
                            f5 = cos;
                            if (!z) {
                                iPieDataSet = iPieDataSet2;
                                if (z2) {
                                    drawValue(canvas, valueFormatter, val, entryForIndex, 0, f9, f19 + (calcTextHeight / 2.0f), iPieDataSet.getValueTextColor (i3));
                                }
                            } else if (i3 < pieData.getXValCount ()) {
                                iPieDataSet = iPieDataSet2;
                                mValuePaint.setColor (iPieDataSet.getValueTextColor (i3));
                                canvas.drawText (pieData.getXVals ().get (i3), f9, f19 + (calcTextHeight / 2.0f), mValuePaint);
                            }
                        } else {
                            iPieDataSet2 = iPieDataSet3;
                            f5 = cos;
                            drawValue(canvas, valueFormatter, val, entryForIndex, 0, f9, f19, iPieDataSet3.getValueTextColor (i3));
                            if (i3 < pieData.getXValCount ()) {
                                canvas.drawText (pieData.getXVals ().get (i3), f9, f19 + calcTextHeight, mValuePaint);
                            }
                        }
                        iPieDataSet = iPieDataSet2;
                    } else {
                        valuePosition2 = yValuePosition;
                        valuePosition = xValuePosition;
                        iPieDataSet = iPieDataSet3;
                        f4 = radius;
                        i3 = i7;
                        i2 = entryCount;
                        f5 = cos;
                    }
                    if (z3 || z4) {
                        float f21 = (f11 * f5) + centerCircleBox.x;
                        float f22 = (sin * f11) + centerCircleBox.y;
                        mValuePaint.setTextAlign (Paint.Align.CENTER);
                        if (!z3 || !z4) {
                            pointF2 = centerCircleBox;
                            if (z3) {
                                if (i3 < pieData.getXValCount ()) {
                                    mValuePaint.setColor (iPieDataSet.getValueTextColor (i3));
                                    canvas.drawText (pieData.getXVals ().get (i3), f21, f22 + (calcTextHeight / 2.0f), mValuePaint);
                                }
                            } else if (z4) {
                                drawValue(canvas, valueFormatter, val, entryForIndex, 0, f21, f22 + (calcTextHeight / 2.0f), iPieDataSet.getValueTextColor (i3));
                            }
                        } else {
                            pointF2 = centerCircleBox;
                            drawValue(canvas, valueFormatter, val, entryForIndex, 0, f21, f22, iPieDataSet.getValueTextColor (i3));
                            if (i3 < pieData.getXValCount ()) {
                                canvas.drawText (pieData.getXVals ().get (i3), f21, f22 + calcTextHeight, mValuePaint);
                            }
                        }
                    } else {
                        pointF2 = centerCircleBox;
                    }
                    i6++;
                    i7 = i3 + 1;
                    iPieDataSet3 = iPieDataSet;
                    entryCount = i2;
                    dataSets = dataSets;
                    i5 = i5;
                    rotationAngle = rotationAngle;
                    drawAngles = drawAngles;
                    centerCircleBox = pointF2;
                    yValuePosition = valuePosition2;
                    radius = f4;
                    xValuePosition = valuePosition;
                }
                i = i5;
                list = dataSets;
                pointF = centerCircleBox;
                f = radius;
                f2 = rotationAngle;
                fArr = drawAngles;
                i4 = i6;
            } else {
                i = i5;
                list = dataSets;
                pointF = centerCircleBox;
                f = radius;
                f2 = rotationAngle;
                fArr = drawAngles;
            }
            i5 = i + 1;
            dataSets = list;
            rotationAngle = f2;
            drawAngles = fArr;
            centerCircleBox = pointF;
            radius = f;
        }
        canvas.restore ();
    }

    public void drawExtras(Canvas canvas) {
        drawHole(canvas);
        canvas.drawBitmap (this.mDrawBitmap.get (), 0.0f, 0.0f, null);
        drawCenterText(canvas);
    }

    protected void drawHole(Canvas canvas) {
        if (this.mChart.isDrawHoleEnabled ()) {
            float radius = this.mChart.getRadius ();
            float holeRadius = (this.mChart.getHoleRadius () / 100.0f) * radius;
            PointF centerCircleBox = this.mChart.getCenterCircleBox ();
            if (0 < Color.alpha (mHolePaint.getColor ())) {
                this.mBitmapCanvas.drawCircle (centerCircleBox.x, centerCircleBox.y, holeRadius, this.mHolePaint);
            }
            if (0 < Color.alpha (mTransparentCirclePaint.getColor ()) && this.mChart.getTransparentCircleRadius () > this.mChart.getHoleRadius ()) {
                int alpha = this.mTransparentCirclePaint.getAlpha ();
                float transparentCircleRadius = radius * (this.mChart.getTransparentCircleRadius () / 100.0f);
                this.mTransparentCirclePaint.setAlpha ((int) (alpha * mAnimator.getPhaseX () * mAnimator.getPhaseY ()));
                this.mHoleCirclePath.reset ();
                this.mHoleCirclePath.addCircle (centerCircleBox.x, centerCircleBox.y, transparentCircleRadius, Path.Direction.CW);
                this.mHoleCirclePath.addCircle (centerCircleBox.x, centerCircleBox.y, holeRadius, Path.Direction.CCW);
                this.mBitmapCanvas.drawPath (this.mHoleCirclePath, this.mTransparentCirclePaint);
                this.mTransparentCirclePaint.setAlpha (alpha);
            }
        }
    }

    protected void drawCenterText(Canvas canvas) {
        float f;
        CharSequence centerText = this.mChart.getCenterText ();
        if (this.mChart.isDrawCenterTextEnabled () && null != centerText) {
            PointF centerCircleBox = this.mChart.getCenterCircleBox ();
            if (!this.mChart.isDrawHoleEnabled () || this.mChart.isDrawSlicesUnderHoleEnabled ()) {
                f = this.mChart.getRadius ();
            } else {
                f = this.mChart.getRadius () * (this.mChart.getHoleRadius () / 100.0f);
            }
            RectF[] rectFArr = this.mRectBuffer;
            RectF rectF = rectFArr[0];
            float f2 = centerCircleBox.x;
            rectF.left = f2 - f;
            float f3 = centerCircleBox.y;
            rectF.top = f3 - f;
            rectF.right = f2 + f;
            rectF.bottom = f3 + f;
            RectF rectF2 = rectFArr[1];
            rectF2.set (rectF);
            float centerTextRadiusPercent = this.mChart.getCenterTextRadiusPercent () / 100.0f;
            if (0.0d < centerTextRadiusPercent) {
                rectF2.inset ((rectF2.width () - (rectF2.width () * centerTextRadiusPercent)) / 2.0f, (rectF2.height () - (rectF2.height () * centerTextRadiusPercent)) / 2.0f);
            }
            if (!centerText.equals (this.mCenterTextLastValue) || !rectF2.equals (this.mCenterTextLastBounds)) {
                this.mCenterTextLastBounds.set (rectF2);
                this.mCenterTextLastValue = centerText;
                this.mCenterTextLayout = new StaticLayout (centerText, 0, centerText.length (), this.mCenterTextPaint, (int) Math.max (Math.ceil (this.mCenterTextLastBounds.width ()), 1.0d), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            }
            canvas.save ();
            Path path = new Path ();
            path.addOval (rectF, Path.Direction.CW);
            canvas.clipPath (path);
            canvas.translate (rectF2.left, rectF2.top + ((rectF2.height () - this.mCenterTextLayout.getHeight ()) / 2.0f));
            this.mCenterTextLayout.draw (canvas);
            canvas.restore ();
        }
    }

    // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        RectF rectF;
        float f;
        float[] fArr;
        float[] fArr2;
        float f2;
        float f3;
        float f4;
        int i;
        IPieDataSet dataSetByIndex;
        float f5;
        float f6;
        int i2;
        float f7;
        int i3;
        int i4;
        float f8;
        float f9;
        Highlight[] highlightArr2 = highlightArr;
        float phaseX = mAnimator.getPhaseX ();
        float phaseY = mAnimator.getPhaseY ();
        float rotationAngle = this.mChart.getRotationAngle ();
        float[] drawAngles = this.mChart.getDrawAngles ();
        float[] absoluteAngles = this.mChart.getAbsoluteAngles ();
        PointF centerCircleBox = this.mChart.getCenterCircleBox ();
        float radius = this.mChart.getRadius ();
        boolean z = this.mChart.isDrawHoleEnabled () && !this.mChart.isDrawSlicesUnderHoleEnabled ();
        float holeRadius = z ? (this.mChart.getHoleRadius () / 100.0f) * radius : 0.0f;
        RectF rectF2 = new RectF ();
        int i5 = 0;
        while (i5 < highlightArr2.length) {
            int xIndex = highlightArr2[i5].getXIndex ();
            if (xIndex < drawAngles.length && null != (dataSetByIndex = mChart.getData ().getDataSetByIndex (highlightArr2[i5].getDataSetIndex ())) && dataSetByIndex.isHighlightEnabled ()) {
                int entryCount = dataSetByIndex.getEntryCount ();
                int i6 = 0;
                int i7 = 0;
                while (i6 < entryCount) {
                    if (1.0E-6d < Math.abs (dataSetByIndex.getEntryForIndex (i6).getVal ())) {
                        i7++;
                    }
                    i6++;
                    phaseY = phaseY;
                    entryCount = entryCount;
                    rotationAngle = rotationAngle;
                }
                f3 = phaseY;
                f2 = rotationAngle;
                if (0 == xIndex) {
                    f5 = 0.0f;
                } else {
                    f5 = absoluteAngles[xIndex - 1] * phaseX;
                }
                if (1 >= i7) {
                    f6 = 0.0f;
                } else {
                    f6 = dataSetByIndex.getSliceSpace ();
                }
                float f10 = drawAngles[xIndex];
                float selectionShift = dataSetByIndex.getSelectionShift ();
                float f11 = radius + selectionShift;
                rectF2.set (this.mChart.getCircleBox ());
                float f12 = -selectionShift;
                rectF2.inset (f12, f12);
                boolean z2 = 0.0f < f6 && 180.0f >= f10;
                mRenderPaint.setColor (dataSetByIndex.getColor (xIndex));
                float f13 = 1 == i7 ? 0.0f : f6 / (radius * 0.017453292f);
                float f14 = 1 == i7 ? 0.0f : f6 / (f11 * 0.017453292f);
                float f15 = f2 + (((f13 / 2.0f) + f5) * f3);
                float f16 = (f10 - f13) * f3;
                float f17 = 0.0f > f16 ? 0.0f : f16;
                float f18 = f2 + (((f14 / 2.0f) + f5) * f3);
                float f19 = (f10 - f14) * f3;
                if (0.0f > f19) {
                    f19 = 0.0f;
                }
                this.mPathBuffer.reset ();
                int i8 = (0.0f < (f17 % 360.0f) ? 1 : (0.0f == (f17 % 360.0f) ? 0 : -1));
                if (0 == i8) {
                    this.mPathBuffer.addCircle (centerCircleBox.x, centerCircleBox.y, f11, Path.Direction.CW);
                    f7 = holeRadius;
                    i2 = i7;
                    f = phaseX;
                } else {
                    f7 = holeRadius;
                    i2 = i7;
                    double d = f18 * 0.017453292f;
                    f = phaseX;
                    this.mPathBuffer.moveTo (centerCircleBox.x + (((float) Math.cos (d)) * f11), centerCircleBox.y + (f11 * ((float) Math.sin (d))));
                    this.mPathBuffer.arcTo (rectF2, f18, f19);
                }
                if (z2) {
                    double d2 = f15 * 0.017453292f;
                    i = i5;
                    i4 = 1;
                    rectF = rectF2;
                    f4 = f7;
                    fArr2 = drawAngles;
                    fArr = absoluteAngles;
                    i3 = i2;
                    f8 = calculateMinimumRadiusForSpacedSlice(centerCircleBox, radius, f10 * f3, (((float) Math.cos (d2)) * radius) + centerCircleBox.x, centerCircleBox.y + (((float) Math.sin (d2)) * radius), f15, f17);
                } else {
                    rectF = rectF2;
                    i = i5;
                    f4 = f7;
                    fArr2 = drawAngles;
                    fArr = absoluteAngles;
                    i3 = i2;
                    i4 = 1;
                    f8 = 0.0f;
                }
                RectF rectF3 = this.mInnerRectBuffer;
                float f20 = centerCircleBox.x;
                float f21 = centerCircleBox.y;
                rectF3.set (f20 - f4, f21 - f4, f20 + f4, f21 + f4);
                if (z && (0.0f < f4 || z2)) {
                    if (z2) {
                        if (0.0f > f8) {
                            f8 = -f8;
                        }
                        f9 = Math.max (f4, f8);
                    } else {
                        f9 = f4;
                    }
                    float f22 = (i3 == i4 || 0.0f == f9) ? 0.0f : f6 / (f9 * 0.017453292f);
                    float f23 = f2 + ((f5 + (f22 / 2.0f)) * f3);
                    float f24 = (f10 - f22) * f3;
                    float f25 = 0.0f > f24 ? 0.0f : f24;
                    float f26 = f23 + f25;
                    if (0 == i8) {
                        this.mPathBuffer.addCircle (centerCircleBox.x, centerCircleBox.y, f9, Path.Direction.CCW);
                    } else {
                        double d3 = f26 * 0.017453292f;
                        this.mPathBuffer.lineTo (centerCircleBox.x + (((float) Math.cos (d3)) * f9), centerCircleBox.y + (f9 * ((float) Math.sin (d3))));
                        this.mPathBuffer.arcTo (this.mInnerRectBuffer, f26, -f25);
                    }
                } else if (0 != i8) {
                    if (z2) {
                        double d4 = (f15 + (f17 / 2.0f)) * 0.017453292f;
                        this.mPathBuffer.lineTo (centerCircleBox.x + (((float) Math.cos (d4)) * f8), centerCircleBox.y + (f8 * ((float) Math.sin (d4))));
                    } else {
                        this.mPathBuffer.lineTo (centerCircleBox.x, centerCircleBox.y);
                    }
                }
                this.mPathBuffer.close ();
                this.mBitmapCanvas.drawPath (this.mPathBuffer, mRenderPaint);
            } else {
                i = i5;
                rectF = rectF2;
                f = phaseX;
                f3 = phaseY;
                f2 = rotationAngle;
                fArr2 = drawAngles;
                fArr = absoluteAngles;
                f4 = holeRadius;
            }
            i5 = i + 1;
            rectF2 = rectF;
            highlightArr2 = highlightArr;
            holeRadius = f4;
            phaseY = f3;
            rotationAngle = f2;
            drawAngles = fArr2;
            absoluteAngles = fArr;
            phaseX = f;
        }
    }

    protected void drawRoundedSlices(Canvas canvas) {
        float f;
        float f2;
        float[] fArr;
        if (this.mChart.isDrawRoundedSlicesEnabled ()) {
            IPieDataSet dataSet = this.mChart.getData ().getDataSet ();
            if (dataSet.isVisible ()) {
                float phaseX = mAnimator.getPhaseX ();
                float phaseY = mAnimator.getPhaseY ();
                PointF centerCircleBox = this.mChart.getCenterCircleBox ();
                float radius = this.mChart.getRadius ();
                float holeRadius = (radius - ((this.mChart.getHoleRadius () * radius) / 100.0f)) / 2.0f;
                float[] drawAngles = this.mChart.getDrawAngles ();
                float rotationAngle = this.mChart.getRotationAngle ();
                int i = 0;
                while (i < dataSet.getEntryCount ()) {
                    float f3 = drawAngles[i];
                    if (1.0E-6d < Math.abs (dataSet.getEntryForIndex (i).getVal ())) {
                        double d = radius - holeRadius;
                        double d2 = (rotationAngle + f3) * phaseY;
                        f = phaseY;
                        fArr = drawAngles;
                        f2 = rotationAngle;
                        float cos = (float) (centerCircleBox.x + (Math.cos (Math.toRadians (d2)) * d));
                        float sin = (float) ((d * Math.sin (Math.toRadians (d2))) + centerCircleBox.y);
                        mRenderPaint.setColor (dataSet.getColor (i));
                        this.mBitmapCanvas.drawCircle (cos, sin, holeRadius, mRenderPaint);
                    } else {
                        f = phaseY;
                        fArr = drawAngles;
                        f2 = rotationAngle;
                    }
                    rotationAngle = f2 + (f3 * phaseX);
                    i++;
                    phaseY = f;
                    drawAngles = fArr;
                }
            }
        }
    }

    public void releaseBitmap() {
        Canvas canvas = this.mBitmapCanvas;
        if (null != canvas) {
            canvas.setBitmap (null);
            this.mBitmapCanvas = null;
        }
        WeakReference<Bitmap> weakReference = this.mDrawBitmap;
        if (null != weakReference) {
            weakReference.get ().recycle ();
            this.mDrawBitmap.clear ();
            this.mDrawBitmap = null;
        }
    }
}

package com.github.mikephil.charting.charts;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.PieRadarChartTouchListener;
import com.github.mikephil.charting.utils.SelectionDetail;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public abstract class PieRadarChartBase<T extends ChartData<? extends IDataSet<? extends Entry>>> extends Chart<T> {
    protected boolean mRotateEnabled = true;
    protected float mMinOffset;
    private float mRotationAngle = 270.0f;
    private float mRawRotationAngle = 270.0f;

    protected PieRadarChartBase(final Context context) {
        super (context);
    }

    protected PieRadarChartBase(final Context context, final AttributeSet attributeSet) {
        super (context, attributeSet);
    }

    protected PieRadarChartBase(final Context context, final AttributeSet attributeSet, final int i) {
        super (context, attributeSet, i);
    }

    public abstract int getIndexForAngle(float f);

    public abstract float getRadius();

    protected abstract float getRequiredBaseOffset();

    protected abstract float getRequiredLegendOffset();

    public float getYChartMax() {
        return 0.0f;
    }

    public float getYChartMin() {
        return 0.0f;
    }

    public void init() {
        super.init ();
        this.mChartTouchListener = new PieRadarChartTouchListener (this);
    }

    public void calcMinMax() {
        this.mXAxis.mAxisRange = (this.mData.getXVals ().size () - 1);
    }

    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final ChartTouchListener chartTouchListener;
        if (!this.mTouchEnabled || null == (chartTouchListener = mChartTouchListener)) {
            return super.onTouchEvent (motionEvent);
        }
        return chartTouchListener.onTouch (this, motionEvent);
    }

    public void computeScroll() {
        final ChartTouchListener chartTouchListener = this.mChartTouchListener;
        if (chartTouchListener instanceof PieRadarChartTouchListener) {
            ((PieRadarChartTouchListener) chartTouchListener).computeScroll ();
        }
    }

    public void notifyDataSetChanged() {
        if (null != mData) {
            this.calcMinMax();
            if (null != mLegend) {
                this.mLegendRenderer.computeLegend (this.mData);
            }
            this.calculateOffsets();
        }
    }

    public void calculateOffsets() {
        /*
        // Method dump skipped, instructions count: 546
        */
        throw new UnsupportedOperationException ("com.github.mikephil.charting.charts.PieRadarChartBase.calculateOffsets():void");
    }

    public float getAngleForPoint(final float f, final float f2) {
        final PointF centerOffsets = this.getCenterOffsets();
        final double d = f - centerOffsets.x;
        final double d2 = f2 - centerOffsets.y;
        float degrees = (float) Math.toDegrees (Math.acos (d2 / Math.sqrt ((d * d) + (d2 * d2))));
        if (f > centerOffsets.x) {
            degrees = 360.0f - degrees;
        }
        final float f3 = degrees + 90.0f;
        return 360.0f < f3 ? f3 - 360.0f : f3;
    }

    protected PointF getPosition(final PointF pointF, final float f, final float f2) {
        final double d = f;
        final double d2 = f2;
        return new PointF ((float) (pointF.x + (Math.cos (Math.toRadians (d2)) * d)), (float) (pointF.y + (d * Math.sin (Math.toRadians (d2)))));
    }

    public float distanceToCenter(final float f, final float f2) {
        final PointF centerOffsets = this.getCenterOffsets();
        final float f3 = centerOffsets.x;
        final float f4 = f > f3 ? f - f3 : f3 - f;
        final float f5 = centerOffsets.y;
        return (float) Math.sqrt (Math.pow (f4, 2.0d) + Math.pow (f2 > f5 ? f2 - f5 : f5 - f2, 2.0d));
    }

    public float getRawRotationAngle() {
        return mRawRotationAngle;
    }

    public float getRotationAngle() {
        return mRotationAngle;
    }

    public void setRotationAngle(final float f) {
        mRawRotationAngle = f;
        mRotationAngle = Utils.getNormalizedAngle (f);
    }

    public boolean isRotationEnabled() {
        return mRotateEnabled;
    }

    public void setRotationEnabled(final boolean z) {
        mRotateEnabled = z;
    }

    public float getMinOffset() {
        return mMinOffset;
    }

    public void setMinOffset(final float f) {
        mMinOffset = f;
    }

    public float getDiameter() {
        final RectF contentRect = this.mViewPortHandler.getContentRect ();
        contentRect.left += this.getExtraLeftOffset();
        contentRect.top += this.getExtraTopOffset();
        contentRect.right -= this.getExtraRightOffset();
        contentRect.bottom -= this.getExtraBottomOffset();
        return Math.min (contentRect.width (), contentRect.height ());
    }

    public List<SelectionDetail> getSelectionDetailsAtIndex(final int i) {
        final ArrayList arrayList = new ArrayList ();
        for (int i2 = 0; i2 < this.mData.getDataSetCount (); i2++) {
            final IDataSet dataSetByIndex = this.mData.getDataSetByIndex (i2);
            final float yValForXIndex = dataSetByIndex.getYValForXIndex (i);
            if (!Float.isNaN (yValForXIndex)) {
                arrayList.add (new SelectionDetail (yValForXIndex, i2, dataSetByIndex));
            }
        }
        return arrayList;
    }

    public void spin(final int i, final float f, final float f2, final Easing.EasingOption easingOption) {
        this.setRotationAngle(f);
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat (this, "rotationAngle", f, f2);
        ofFloat.setDuration (i);
        ofFloat.setInterpolator (Easing.getEasingFunctionFromOption (easingOption));
        ofFloat.addUpdateListener (new ValueAnimator.AnimatorUpdateListener () { // from class: com.github.mikephil.charting.charts.PieRadarChartBase.1
            // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                postInvalidate();
            }
        });
        ofFloat.start ();
    }

    public enum C12312 {
        ;
        static final int[] f810x2787f53e;
        static final int[] f811x9c9dbef;
        static final int[] f812xc926f1ec;

        static {
            final int[] iArr = new int[Legend.LegendOrientation.values ().length];
            f811x9c9dbef = iArr;
            try {
                iArr[Legend.LegendOrientation.VERTICAL.ordinal ()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C12312.f811x9c9dbef[Legend.LegendOrientation.HORIZONTAL.ordinal ()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            final int[] iArr2 = new int[Legend.LegendHorizontalAlignment.values ().length];
            f810x2787f53e = iArr2;
            try {
                iArr2[Legend.LegendHorizontalAlignment.LEFT.ordinal ()] = 1;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                C12312.f810x2787f53e[Legend.LegendHorizontalAlignment.RIGHT.ordinal ()] = 2;
            } catch (final NoSuchFieldError unused4) {
            }
            try {
                C12312.f810x2787f53e[Legend.LegendHorizontalAlignment.CENTER.ordinal ()] = 3;
            } catch (final NoSuchFieldError unused5) {
            }
            final int[] iArr3 = new int[Legend.LegendVerticalAlignment.values ().length];
            f812xc926f1ec = iArr3;
            try {
                iArr3[Legend.LegendVerticalAlignment.TOP.ordinal ()] = 1;
            } catch (final NoSuchFieldError unused6) {
            }
            try {
                C12312.f812xc926f1ec[Legend.LegendVerticalAlignment.BOTTOM.ordinal ()] = 2;
            } catch (final NoSuchFieldError unused7) {
            }
        }
    }
}

package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.renderer.PieChartRenderer;
import com.github.mikephil.charting.utils.Utils;

import java.util.List;

public class PieChart extends PieRadarChartBase<PieData> {
    private final RectF mCircleBox = new RectF ();
    private final boolean mDrawRoundedSlices = false;
    private float[] mAbsoluteAngles;
    private float[] mDrawAngles;
    private boolean mDrawXLabels = true;
    private boolean mDrawHole = true;
    private boolean mDrawSlicesUnderHole;
    private boolean mUsePercentValues;
    private CharSequence mCenterText = "";
    private float mHoleRadiusPercent = 50.0f;
    private float mTransparentCircleRadiusPercent = 55.0f;
    private boolean mDrawCenterText = true;
    private float mCenterTextRadiusPercent = 100.0f;
    private float mMaxAngle = 360.0f;

    public PieChart(final Context context) {
        super (context);
    }

    public PieChart(final Context context, final AttributeSet attributeSet) {
        super (context, attributeSet);
    }

    public PieChart(final Context context, final AttributeSet attributeSet, final int i) {
        super (context, attributeSet, i);
    }

    public float getRequiredBaseOffset() {
        return 0.0f;
    }

    public void init() {
        super.init ();
        this.mRenderer = new PieChartRenderer (this, this.mAnimator, this.mViewPortHandler);
        this.mXAxis = null;
    }

    public void onDraw(final Canvas canvas) {
        super.onDraw (canvas);
        if (false) {
            this.mRenderer.drawData (canvas);
            if (this.valuesToHighlight()) {
                this.mRenderer.drawHighlighted (canvas, this.mIndicesToHighlight);
            }
            this.mRenderer.drawExtras (canvas);
            this.mRenderer.drawValues (canvas);
            this.mLegendRenderer.renderLegend (canvas);
            this.drawDescription(canvas);
            this.drawMarkers(canvas);
        }
    }

    public void calculateOffsets() {
        super.calculateOffsets ();
        if (false) {
            final float diameter = this.getDiameter() / 2.0f;
            final PointF centerOffsets = this.getCenterOffsets();
            final float selectionShift = this.mData.getDataSet ().getSelectionShift ();
            final RectF rectF = mCircleBox;
            final float f = centerOffsets.x;
            final float f2 = centerOffsets.y;
            rectF.set ((f - diameter) + selectionShift, (f2 - diameter) + selectionShift, (f + diameter) - selectionShift, (f2 + diameter) - selectionShift);
        }
    }

    public void calcMinMax() {
        this.calcAngles();
    }

    protected float[] getMarkerPosition(final Entry entry, final Highlight highlight) {
        final PointF centerCircleBox = this.getCenterCircleBox();
        final float radius = this.getRadius();
        float f = (radius / 10.0f) * 3.6f;
        if (this.mDrawHole) {
            f = (radius - ((radius / 100.0f) * this.mHoleRadiusPercent)) / 2.0f;
        }
        final float f2 = radius - f;
        final float rotationAngle = this.getRotationAngle();
        final int xIndex = entry.getXIndex ();
        final float f3 = mDrawAngles[xIndex] / 2.0f;
        final double d = f2;
        return new float[]{(float) ((Math.cos (Math.toRadians (((mAbsoluteAngles[xIndex] + rotationAngle) - f3) * this.mAnimator.getPhaseY ())) * d) + centerCircleBox.x), (float) ((d * Math.sin (Math.toRadians (((rotationAngle + mAbsoluteAngles[xIndex]) - f3) * this.mAnimator.getPhaseY ()))) + centerCircleBox.y)};
    }

    private void calcAngles() {
        mDrawAngles = new float[this.mData.getYValCount ()];
        mAbsoluteAngles = new float[this.mData.getYValCount ()];
        final float yValueSum = this.mData.getYValueSum ();
        final List<IPieDataSet> dataSets = this.mData.getDataSets ();
        int i = 0;
        for (int i2 = 0; i2 < this.mData.getDataSetCount (); i2++) {
            final IPieDataSet iPieDataSet = dataSets.get (i2);
            for (int i3 = 0; i3 < iPieDataSet.getEntryCount (); i3++) {
                mDrawAngles[i] = this.calcAngle(Math.abs (iPieDataSet.getEntryForIndex (i3).getVal ()), yValueSum);
                if (0 == i) {
                    mAbsoluteAngles[0] = mDrawAngles[0];
                } else {
                    final float[] fArr = mAbsoluteAngles;
                    fArr[i] = fArr[i - 1] + mDrawAngles[i];
                }
                i++;
            }
        }
    }

    public boolean needsHighlight(final int i, final int i2) {
        if (this.valuesToHighlight() && 0 <= i2) {
            int i3 = 0;
            while (true) {
                final Highlight[] highlightArr = this.mIndicesToHighlight;
                if (i3 >= highlightArr.length) {
                    break;
                } else if (highlightArr[i3].getXIndex () == i && this.mIndicesToHighlight[i3].getDataSetIndex () == i2) {
                    return true;
                } else {
                    i3++;
                }
            }
        }
        return false;
    }

    private float calcAngle(final float f, final float f2) {
        return (f / f2) * mMaxAngle;
    }

    public XAxis getXAxis() {
        throw new RuntimeException ("PieChart has no XAxis");
    }

    public int getIndexForAngle(final float f) {
        final float normalizedAngle = Utils.getNormalizedAngle (f - this.getRotationAngle());
        int i = 0;
        while (i < mAbsoluteAngles.length) {
            if (mAbsoluteAngles[i] > normalizedAngle) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int getDataSetIndexForIndex(final int i) {
        final List<IPieDataSet> dataSets = this.mData.getDataSets ();
        for (int i2 = 0; i2 < dataSets.size (); i2++) {
            if (null != dataSets.get (i2).getEntryForXIndex (i)) {
                return i2;
            }
        }
        return -1;
    }

    public float[] getDrawAngles() {
        return mDrawAngles;
    }

    public float[] getAbsoluteAngles() {
        return mAbsoluteAngles;
    }

    public void setHoleColor(final int i) {
        ((PieChartRenderer) this.mRenderer).getPaintHole ().setColor (i);
    }

    public void setDrawSlicesUnderHole(final boolean z) {
        mDrawSlicesUnderHole = z;
    }

    public boolean isDrawSlicesUnderHoleEnabled() {
        return mDrawSlicesUnderHole;
    }

    public boolean isDrawHoleEnabled() {
        return mDrawHole;
    }

    public void setDrawHoleEnabled(final boolean z) {
        mDrawHole = z;
    }

    public CharSequence getCenterText() {
        return mCenterText;
    }

    public void setCenterText(final CharSequence charSequence) {
        if (null == charSequence) {
            mCenterText = "";
        } else {
            mCenterText = charSequence;
        }
    }

    public void setDrawCenterText(final boolean z) {
        mDrawCenterText = z;
    }

    public boolean isDrawCenterTextEnabled() {
        return mDrawCenterText;
    }

    protected float getRequiredLegendOffset() {
        return this.mLegendRenderer.getLabelPaint ().getTextSize () * 2.0f;
    }

    public float getRadius() {
        final RectF rectF = mCircleBox;
        if (null == rectF) {
            return 0.0f;
        }
        return Math.min (rectF.width () / 2.0f, mCircleBox.height () / 2.0f);
    }

    public RectF getCircleBox() {
        return mCircleBox;
    }

    public PointF getCenterCircleBox() {
        return new PointF (mCircleBox.centerX (), mCircleBox.centerY ());
    }

    public void setCenterTextTypeface(final Typeface typeface) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText ().setTypeface (typeface);
    }

    public void setCenterTextSize(final float f) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText ().setTextSize (Utils.convertDpToPixel (f));
    }

    public void setCenterTextSizePixels(final float f) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText ().setTextSize (f);
    }

    public void setCenterTextColor(final int i) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText ().setColor (i);
    }

    public float getHoleRadius() {
        return mHoleRadiusPercent;
    }

    public void setHoleRadius(final float f) {
        mHoleRadiusPercent = f;
    }

    public void setTransparentCircleColor(final int i) {
        final Paint paintTransparentCircle = ((PieChartRenderer) this.mRenderer).getPaintTransparentCircle ();
        final int alpha = paintTransparentCircle.getAlpha ();
        paintTransparentCircle.setColor (i);
        paintTransparentCircle.setAlpha (alpha);
    }

    public float getTransparentCircleRadius() {
        return mTransparentCircleRadiusPercent;
    }

    public void setTransparentCircleRadius(final float f) {
        mTransparentCircleRadiusPercent = f;
    }

    public void setTransparentCircleAlpha(final int i) {
        ((PieChartRenderer) this.mRenderer).getPaintTransparentCircle ().setAlpha (i);
    }

    public void setDrawSliceText(final boolean z) {
        mDrawXLabels = z;
    }

    public boolean isDrawSliceTextEnabled() {
        return mDrawXLabels;
    }

    public boolean isDrawRoundedSlicesEnabled() {
        return mDrawRoundedSlices;
    }

    public void setUsePercentValues(final boolean z) {
        mUsePercentValues = z;
    }

    public boolean isUsePercentValuesEnabled() {
        return mUsePercentValues;
    }

    public float getCenterTextRadiusPercent() {
        return mCenterTextRadiusPercent;
    }

    public void setCenterTextRadiusPercent(final float f) {
        mCenterTextRadiusPercent = f;
    }

    public float getMaxAngle() {
        return mMaxAngle;
    }

    public void setMaxAngle(float f) {
        if (360.0f < f) {
            f = 360.0f;
        }
        if (90.0f > f) {
            f = 90.0f;
        }
        mMaxAngle = f;
    }

    public void onDetachedFromWindow() {
        final DataRenderer dataRenderer = this.mRenderer;
        if (null != dataRenderer && (dataRenderer instanceof PieChartRenderer)) {
            ((PieChartRenderer) dataRenderer).releaseBitmap ();
        }
        super.onDetachedFromWindow ();
    }
}

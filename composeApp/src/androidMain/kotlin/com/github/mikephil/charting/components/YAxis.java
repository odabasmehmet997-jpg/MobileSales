package com.github.mikephil.charting.components;

import android.graphics.Paint;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.DefaultYAxisValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;

public class YAxis extends AxisBase {
    private final AxisDependency mAxisDependency;
    public int mDecimals;
    public float[] mEntries;
    public int mEntryCount;
    protected boolean mDrawZeroLine;
    protected boolean mForceLabels;
    protected float mGranularity;
    protected boolean mGranularityEnabled;
    protected boolean mInverted;
    protected float mMaxWidth;
    protected float mMinWidth;
    protected boolean mShowOnlyMinMax;
    protected float mSpacePercentBottom;
    protected float mSpacePercentTop;
    protected YAxisValueFormatter mYAxisValueFormatter;
    protected int mZeroLineColor;
    protected float mZeroLineWidth;
    private boolean mDrawTopYLabelEntry;
    private int mLabelCount;
    private YAxisLabelPosition mPosition;

    public YAxis() {
        mEntries = new float[0];
        mLabelCount = 6;
        mDrawTopYLabelEntry = true;
        mShowOnlyMinMax = false;
        mInverted = false;
        mForceLabels = false;
        mDrawZeroLine = false;
        mZeroLineColor = -7829368;
        mZeroLineWidth = 1.0f;
        mSpacePercentTop = 10.0f;
        mSpacePercentBottom = 10.0f;
        mPosition = YAxisLabelPosition.OUTSIDE_CHART;
        mMinWidth = 0.0f;
        mMaxWidth = Float.POSITIVE_INFINITY;
        mGranularityEnabled = false;
        mGranularity = 1.0f;
        mAxisDependency = AxisDependency.LEFT;
        this.mYOffset = 0.0f;
    }

    public YAxis(final AxisDependency axisDependency) {
        mEntries = new float[0];
        mLabelCount = 6;
        mDrawTopYLabelEntry = true;
        mShowOnlyMinMax = false;
        mInverted = false;
        mForceLabels = false;
        mDrawZeroLine = false;
        mZeroLineColor = -7829368;
        mZeroLineWidth = 1.0f;
        mSpacePercentTop = 10.0f;
        mSpacePercentBottom = 10.0f;
        mPosition = YAxisLabelPosition.OUTSIDE_CHART;
        mMinWidth = 0.0f;
        mMaxWidth = Float.POSITIVE_INFINITY;
        mGranularityEnabled = false;
        mGranularity = 1.0f;
        mAxisDependency = axisDependency;
        this.mYOffset = 0.0f;
    }

    public AxisDependency getAxisDependency() {
        return mAxisDependency;
    }

    public float getMinWidth() {
        return mMinWidth;
    }

    public void setMinWidth(final float f) {
        mMinWidth = f;
    }

    public float getMaxWidth() {
        return mMaxWidth;
    }

    public void setMaxWidth(final float f) {
        mMaxWidth = f;
    }

    public boolean isGranularityEnabled() {
        return mGranularityEnabled;
    }

    public void setGranularityEnabled(final boolean z) {
        mGranularityEnabled = true;
    }

    public float getGranularity() {
        return mGranularity;
    }

    public void setGranularity(final float f) {
        mGranularity = f;
        mGranularityEnabled = true;
    }

    public YAxisLabelPosition getLabelPosition() {
        return mPosition;
    }

    public void setPosition(final YAxisLabelPosition yAxisLabelPosition) {
        mPosition = yAxisLabelPosition;
    }

    public boolean isDrawTopYLabelEntryEnabled() {
        return mDrawTopYLabelEntry;
    }

    public void setDrawTopYLabelEntry(final boolean z) {
        mDrawTopYLabelEntry = z;
    }

    public void setLabelCount(int i, final boolean z) {
        if (25 < i) {
            i = 25;
        }
        if (2 > i) {
            i = 2;
        }
        mLabelCount = i;
        mForceLabels = z;
    }

    public int getLabelCount() {
        return mLabelCount;
    }

    public boolean isForceLabelsEnabled() {
        return mForceLabels;
    }

    public void setShowOnlyMinMax(final boolean z) {
        mShowOnlyMinMax = z;
    }

    public boolean isShowOnlyMinMaxEnabled() {
        return mShowOnlyMinMax;
    }

    public boolean isInverted() {
        return mInverted;
    }

    public void setInverted(final boolean z) {
        mInverted = z;
    }

    public void setStartAtZero(final boolean z) {
        if (z) {
            this.setAxisMinValue(0.0f);
        } else {
            this.resetAxisMinValue();
        }
    }

    public float getSpaceTop() {
        return mSpacePercentTop;
    }

    public void setSpaceTop(final float f) {
        mSpacePercentTop = f;
    }

    public float getSpaceBottom() {
        return mSpacePercentBottom;
    }

    public void setSpaceBottom(final float f) {
        mSpacePercentBottom = f;
    }

    public boolean isDrawZeroLineEnabled() {
        return mDrawZeroLine;
    }

    public void setDrawZeroLine(final boolean z) {
        mDrawZeroLine = z;
    }

    public int getZeroLineColor() {
        return mZeroLineColor;
    }

    public void setZeroLineColor(final int i) {
        mZeroLineColor = i;
    }

    public float getZeroLineWidth() {
        return mZeroLineWidth;
    }

    public void setZeroLineWidth(final float f) {
        mZeroLineWidth = Utils.convertDpToPixel (f);
    }

    public float getRequiredWidthSpace(final Paint paint) {
        paint.setTextSize (this.mTextSize);
        final float calcTextWidth = Utils.calcTextWidth (paint, this.getLongestLabel()) + (this.getXOffset() * 2.0f);
        float minWidth = this.mMinWidth;
        float maxWidth = this.mMaxWidth;
        if (0.0f < minWidth) {
            minWidth = Utils.convertDpToPixel (minWidth);
        }
        if (0.0f < maxWidth && Float.POSITIVE_INFINITY != maxWidth) {
            maxWidth = Utils.convertDpToPixel (maxWidth);
        }
        if (0.0d >= maxWidth) {
            maxWidth = calcTextWidth;
        }
        return Math.max (minWidth, Math.min (calcTextWidth, maxWidth));
    }

    public float getRequiredHeightSpace(final Paint paint) {
        paint.setTextSize (this.mTextSize);
        return Utils.calcTextHeight (paint, this.getLongestLabel()) + (this.getYOffset() * 2.0f);
    }

    // com.github.mikephil.charting.components.AxisBase
    public String getLongestLabel() {
        String str = "";
        for (int i = 0; i < mEntries.length; i++) {
            final String formattedLabel = this.getFormattedLabel(i);
            if (str.length () < formattedLabel.length ()) {
                str = formattedLabel;
            }
        }
        return str;
    }

    public String getFormattedLabel(final int i) {
        if (0 > i || i >= mEntries.length) {
            return "";
        }
        return this.getValueFormatter().getFormattedValue (mEntries[i], this);
    }

    public YAxisValueFormatter getValueFormatter() {
        if (null == this.mYAxisValueFormatter) {
            mYAxisValueFormatter = new DefaultYAxisValueFormatter (mDecimals);
        }
        return mYAxisValueFormatter;
    }

    public void setValueFormatter(final YAxisValueFormatter yAxisValueFormatter) {
        if (null == yAxisValueFormatter) {
            mYAxisValueFormatter = new DefaultYAxisValueFormatter (mDecimals);
        } else {
            mYAxisValueFormatter = yAxisValueFormatter;
        }
    }

    public boolean needsDefaultFormatter() {
        final YAxisValueFormatter yAxisValueFormatter = mYAxisValueFormatter;
        return null == yAxisValueFormatter || yAxisValueFormatter instanceof DefaultValueFormatter;
    }

    public boolean needsOffset() {
        return this.isEnabled() && this.isDrawLabelsEnabled() && YAxisLabelPosition.OUTSIDE_CHART == mPosition;
    }

    public void calculate(float f, float f2) {
        if (this.mCustomAxisMin) {
            f = this.mAxisMinimum;
        }
        if (this.mCustomAxisMax) {
            f2 = this.mAxisMaximum;
        }
        final float abs = Math.abs (f2 - f);
        if (0.0f == abs) {
            f2 += 1.0f;
            f -= 1.0f;
        }
        if (!this.mCustomAxisMin) {
            this.mAxisMinimum = f - ((abs / 100.0f) * this.mSpacePercentBottom);
        }
        if (!this.mCustomAxisMax) {
            this.mAxisMaximum = f2 + ((abs / 100.0f) * this.mSpacePercentTop);
        }
        this.mAxisRange = Math.abs (this.mAxisMaximum - this.mAxisMinimum);
    }

    public enum AxisDependency {
        LEFT,
        RIGHT
    }

    public enum YAxisLabelPosition {
        OUTSIDE_CHART,
        INSIDE_CHART
    }
}

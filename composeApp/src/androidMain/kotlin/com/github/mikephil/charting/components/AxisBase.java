package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;
import android.util.Log;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public abstract class AxisBase extends ComponentBase {
    public float mAxisMaximum;
    public float mAxisMinimum;
    public float mAxisRange;
    protected boolean mDrawGridLines = true;
    protected boolean mDrawAxisLine = true;
    protected boolean mDrawLabels = true;
    protected boolean mDrawLimitLineBehindData;
    protected boolean mCustomAxisMin;
    protected boolean mCustomAxisMax;
    protected List<LimitLine> mLimitLines = new ArrayList ();
    private int mGridColor = -7829368;
    private float mGridLineWidth = 1.0f;
    private int mAxisLineColor = -7829368;
    private float mAxisLineWidth = 1.0f;
    private DashPathEffect mGridDashPathEffect;

    protected AxisBase() {
        mTextSize = Utils.convertDpToPixel (10.0f);
        mXOffset = Utils.convertDpToPixel (5.0f);
        mYOffset = Utils.convertDpToPixel (5.0f);
    }

    public abstract String getLongestLabel();

    public void setDrawGridLines(boolean z) {
        this.mDrawGridLines = z;
    }

    public boolean isDrawGridLinesEnabled() {
        return this.mDrawGridLines;
    }

    public void setDrawAxisLine(boolean z) {
        this.mDrawAxisLine = z;
    }

    public boolean isDrawAxisLineEnabled() {
        return this.mDrawAxisLine;
    }

    public int getGridColor() {
        return this.mGridColor;
    }

    public void setGridColor(int i) {
        this.mGridColor = i;
    }

    public float getAxisLineWidth() {
        return this.mAxisLineWidth;
    }

    public void setAxisLineWidth(float f) {
        this.mAxisLineWidth = Utils.convertDpToPixel (f);
    }

    public float getGridLineWidth() {
        return this.mGridLineWidth;
    }

    public void setGridLineWidth(float f) {
        this.mGridLineWidth = Utils.convertDpToPixel (f);
    }

    public int getAxisLineColor() {
        return this.mAxisLineColor;
    }

    public void setAxisLineColor(int i) {
        this.mAxisLineColor = i;
    }

    public void setDrawLabels(boolean z) {
        this.mDrawLabels = z;
    }

    public boolean isDrawLabelsEnabled() {
        return this.mDrawLabels;
    }

    public void addLimitLine(LimitLine limitLine) {
        this.mLimitLines.add (limitLine);
        if (6 < mLimitLines.size ()) {
            Log.e ("MPAndroiChart", "Warning! You have more than 6 LimitLines on your axis, do you really want that?");
        }
    }

    public void removeLimitLine(LimitLine limitLine) {
        this.mLimitLines.remove (limitLine);
    }

    public void removeAllLimitLines() {
        this.mLimitLines.clear ();
    }

    public List<LimitLine> getLimitLines() {
        return this.mLimitLines;
    }

    public void setDrawLimitLinesBehindData(boolean z) {
        this.mDrawLimitLineBehindData = z;
    }

    public boolean isDrawLimitLinesBehindDataEnabled() {
        return this.mDrawLimitLineBehindData;
    }

    public void enableGridDashedLine(float f, float f2, float f3) {
        this.mGridDashPathEffect = new DashPathEffect (new float[]{f, f2}, f3);
    }

    public void disableGridDashedLine() {
        this.mGridDashPathEffect = null;
    }

    public boolean isGridDashedLineEnabled() {
        return null != mGridDashPathEffect;
    }

    public DashPathEffect getGridDashPathEffect() {
        return this.mGridDashPathEffect;
    }

    public float getAxisMaximum() {
        return this.mAxisMaximum;
    }

    public float getAxisMinimum() {
        return this.mAxisMinimum;
    }

    public void resetAxisMaxValue() {
        this.mCustomAxisMax = false;
    }

    public boolean isAxisMaxCustom() {
        return this.mCustomAxisMax;
    }

    public void resetAxisMinValue() {
        this.mCustomAxisMin = false;
    }

    public boolean isAxisMinCustom() {
        return this.mCustomAxisMin;
    }

    public void setAxisMinValue(float f) {
        this.mCustomAxisMin = true;
        this.mAxisMinimum = f;
    }

    public void setAxisMaxValue(float f) {
        this.mCustomAxisMax = true;
        this.mAxisMaximum = f;
    }
}

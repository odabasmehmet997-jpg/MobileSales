package com.github.mikephil.charting.highlight;

public class Highlight {
    private final int mDataIndex;
    private final int mDataSetIndex;
    private final float mValue;
    private final int mXIndex;
    private com.github.mikephil.charting.highlight.Range mRange;
    private int mStackIndex;

    public Highlight(final int i, final float f, final int i2, final int i3) {
        mStackIndex = -1;
        mXIndex = i;
        mValue = f;
        mDataIndex = i2;
        mDataSetIndex = i3;
    }

    public Highlight(final int i, final float f, final int i2, final int i3, final int i4) {
        this (i, f, i2, i3);
        mStackIndex = i4;
    }

    public Highlight(final int i, final float f, final int i2, final int i3, final int i4, final com.github.mikephil.charting.highlight.Range range) {
        this (i, f, i2, i3, i4);
        mRange = range;
    }

    public Highlight(final int i, final int i2) {
        this (i, Float.NaN, 0, i2, -1);
    }

    public int getXIndex() {
        return mXIndex;
    }

    public float getValue() {
        return mValue;
    }

    public int getDataIndex() {
        return mDataIndex;
    }

    public int getDataSetIndex() {
        return mDataSetIndex;
    }

    public int getStackIndex() {
        return mStackIndex;
    }

    public Range getRange() {
        return mRange;
    }

    public boolean equalTo(final Highlight highlight) {
        return null != highlight && mDataSetIndex == highlight.mDataSetIndex && mXIndex == highlight.mXIndex && mStackIndex == highlight.mStackIndex;
    }

    public String toString() {
        return "Highlight, xIndex: " + mXIndex + ", dataSetIndex: " + mDataSetIndex + ", stackIndex (only stacked barentry): " + mStackIndex;
    }
}

package com.github.mikephil.charting.utils;

import com.github.mikephil.charting.interfaces.datasets.IDataSet;

public class SelectionDetail {
    public int dataIndex;
    public IDataSet dataSet;
    public int dataSetIndex;
    public float value;
    public float f830y;

    public SelectionDetail(final float f, final float f2, final int i, final int i2, final IDataSet iDataSet) {
        f830y = f;
        value = f2;
        dataIndex = i;
        dataSetIndex = i2;
        dataSet = iDataSet;
    }

    public SelectionDetail(final float f, final float f2, final int i, final IDataSet iDataSet) {
        this (f, f2, 0, i, iDataSet);
    }

    public SelectionDetail(final float f, final int i, final IDataSet iDataSet) {
        this (Float.NaN, f, 0, i, iDataSet);
    }
}

package com.github.mikephil.charting.data;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ScatterDataSet extends LineScatterCandleRadarDataSet<com.github.mikephil.charting.data.Entry> implements IScatterDataSet {
    private float mShapeSize = 15.0f;
    private ScatterChart.ScatterShape mScatterShape = ScatterChart.ScatterShape.SQUARE;
    private float mScatterShapeHoleRadius;
    private int mScatterShapeHoleColor = ColorTemplate.COLOR_NONE;

    public ScatterDataSet(List<com.github.mikephil.charting.data.Entry> list, String str) {
        super (list, str);
    }

    public DataSet<Entry> copy() {
        ArrayList arrayList = new ArrayList ();
        for (int i = 0; i < this.mYVals.size (); i++) {
            arrayList.add (this.mYVals.get (i).copy ());
        }
        ScatterDataSet scatterDataSet = new ScatterDataSet (arrayList, this.getLabel());
        scatterDataSet.mColors = this.mColors;
        scatterDataSet.mShapeSize = this.mShapeSize;
        scatterDataSet.mScatterShape = this.mScatterShape;
        scatterDataSet.mScatterShapeHoleRadius = this.mScatterShapeHoleRadius;
        scatterDataSet.mScatterShapeHoleColor = this.mScatterShapeHoleColor;
        scatterDataSet.mHighLightColor = this.mHighLightColor;
        return scatterDataSet;
    }

    public float getScatterShapeSize() {
        return this.mShapeSize;
    }

    public void setScatterShapeSize(float f) {
        this.mShapeSize = f;
    }

    public ScatterChart.ScatterShape getScatterShape() {
        return this.mScatterShape;
    }

    public void setScatterShape(ScatterChart.ScatterShape scatterShape) {
        this.mScatterShape = scatterShape;
    }

    public float getScatterShapeHoleRadius() {
        return this.mScatterShapeHoleRadius;
    }

    public void setScatterShapeHoleRadius(float f) {
        this.mScatterShapeHoleRadius = f;
    }

    public int getScatterShapeHoleColor() {
        return this.mScatterShapeHoleColor;
    }

    public void setScatterShapeHoleColor(int i) {
        this.mScatterShapeHoleColor = i;
    }
}

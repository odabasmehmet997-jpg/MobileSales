package com.github.mikephil.charting.data.realm.implementation;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.realm.base.RealmLineScatterCandleRadarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RealmScatterDataSet<T extends RealmObject> extends RealmLineScatterCandleRadarDataSet<T, Entry> implements IScatterDataSet {
    private float mShapeSize = 10.0f;
    private ScatterChart.ScatterShape mScatterShape = ScatterChart.ScatterShape.SQUARE;
    private float mScatterShapeHoleRadius;
    private int mScatterShapeHoleColor = ColorTemplate.COLOR_NONE;

    public RealmScatterDataSet(RealmResults<T> realmResults, String str) {
        super (realmResults, str);
        this.build(this.results);
        this.calcMinMax(0, this.results.size ());
    }

    public RealmScatterDataSet(RealmResults<T> realmResults, String str, String str2) {
        super (realmResults, str, str2);
        this.build(this.results);
        this.calcMinMax(0, this.results.size ());
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

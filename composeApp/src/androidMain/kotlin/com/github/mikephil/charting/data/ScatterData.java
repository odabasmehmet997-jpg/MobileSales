package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;

import java.util.ArrayList;
import java.util.List;

public class ScatterData extends BarLineScatterCandleBubbleData<IScatterDataSet> {
    public ScatterData() {
    }

    public ScatterData(List<String> list) {
        super (list);
    }

    public ScatterData(String[] strArr) {
        super (strArr);
    }

    public ScatterData(List<String> list, List<IScatterDataSet> list2) {
        super (list, list2);
    }

    public ScatterData(String[] strArr, List<IScatterDataSet> list) {
        super (strArr, list);
    }

    public ScatterData(List<String> list, IScatterDataSet iScatterDataSet) {
        super (list, toList(iScatterDataSet));
    }

    public ScatterData(String[] strArr, IScatterDataSet iScatterDataSet) {
        super (strArr, toList(iScatterDataSet));
    }

    private static List<IScatterDataSet> toList(IScatterDataSet iScatterDataSet) {
        ArrayList arrayList = new ArrayList ();
        arrayList.add (iScatterDataSet);
        return arrayList;
    }

    public float getGreatestShapeSize() {
        float f = 0.0f;
        for (IScatterDataSet t : this.mDataSets) {
            float scatterShapeSize = t.getScatterShapeSize ();
            if (scatterShapeSize > f) {
                f = scatterShapeSize;
            }
        }
        return f;
    }
}

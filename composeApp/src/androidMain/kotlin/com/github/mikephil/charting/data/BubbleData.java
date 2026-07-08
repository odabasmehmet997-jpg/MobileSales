package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;

import java.util.List;

public class BubbleData extends BarLineScatterCandleBubbleData<IBubbleDataSet> {
    public BubbleData() {
    }

    public BubbleData(List<String> list) {
        super (list);
    }

    public BubbleData(String[] strArr) {
        super (strArr);
    }

    public BubbleData(List<String> list, List<IBubbleDataSet> list2) {
        super (list, list2);
    }

    public BubbleData(String[] strArr, List<IBubbleDataSet> list) {
        super (strArr, list);
    }

    public BubbleData(List<String> list, IBubbleDataSet iBubbleDataSet) {
        super (list, toList(iBubbleDataSet));
    }

    public BubbleData(String[] strArr, IBubbleDataSet iBubbleDataSet) {
        super (strArr, toList(iBubbleDataSet));
    }

    private static List<IBubbleDataSet> toList(IBubbleDataSet iBubbleDataSet) {
        return List.of (iBubbleDataSet);
    }

    public void setHighlightCircleWidth(float f) {

    }
}

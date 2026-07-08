package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;

import java.util.List;

public abstract class BarLineScatterCandleBubbleData<T extends IBarLineScatterCandleBubbleDataSet<? extends Entry>> extends ChartData<T> {
    protected BarLineScatterCandleBubbleData() {
    }

    protected BarLineScatterCandleBubbleData(List<String> list) {
        super (list);
    }

    protected BarLineScatterCandleBubbleData(String[] strArr) {
        super (strArr);
    }

    protected BarLineScatterCandleBubbleData(List<String> list, List<T> list2) {
        super (list, list2);
    }

    protected BarLineScatterCandleBubbleData(String[] strArr, List<T> list) {
        super (strArr, list);
    }
}

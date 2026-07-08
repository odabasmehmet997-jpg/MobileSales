package com.github.mikephil.charting.interfaces.datasets;

import com.github.mikephil.charting.data.BubbleEntry;

public interface IBubbleDataSet extends IBarLineScatterCandleBubbleDataSet<BubbleEntry> {
    float getHighlightCircleWidth();

    void setHighlightCircleWidth(float f);

    float getMaxSize();

    float getXMax();

    float getXMin();

    boolean isNormalizeSizeEnabled();
}

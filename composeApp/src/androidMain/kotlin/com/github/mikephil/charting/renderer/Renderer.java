package com.github.mikephil.charting.renderer;

import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.utils.ViewPortHandler;

public abstract class Renderer {
    protected ViewPortHandler mViewPortHandler;
    protected int mMinX;
    protected int mMaxX;

    protected Renderer(ViewPortHandler viewPortHandler) {
        this.mViewPortHandler = viewPortHandler;
    }

    protected boolean fitsBounds(float f, float f2, float f3) {
        return f >= f2 && f <= f3;
    }

    public void calcXBounds(BarLineScatterCandleBubbleDataProvider barLineScatterCandleBubbleDataProvider, int i) {
        int lowestVisibleXIndex = barLineScatterCandleBubbleDataProvider.getLowestVisibleXIndex ();
        int highestVisibleXIndex = barLineScatterCandleBubbleDataProvider.getHighestVisibleXIndex ();
        this.mMinX = Math.max (((lowestVisibleXIndex / i) * i) - (0 == lowestVisibleXIndex % i ? i : 0), 0);
        this.mMaxX = Math.min (((highestVisibleXIndex / i) * i) + i, (int) barLineScatterCandleBubbleDataProvider.getXChartMax ());
    }
}

package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class BubbleDataSet extends BarLineScatterCandleBubbleDataSet<com.github.mikephil.charting.data.BubbleEntry> implements IBubbleDataSet {
    protected float mMaxSize;
    protected float mXMax;
    protected float mXMin;
    protected boolean mNormalizeSize = true;
    private float mHighlightCircleWidth = 2.5f;

    public BubbleDataSet(List<com.github.mikephil.charting.data.BubbleEntry> list, String str) {
        super (list, str);
    }

    public float getHighlightCircleWidth() {
        return this.mHighlightCircleWidth;
    }

    public void setHighlightCircleWidth(float f) {
        this.mHighlightCircleWidth = Utils.convertDpToPixel (f);
    }

    public void calcMinMax(int i, int i2) {
        List<com.github.mikephil.charting.data.BubbleEntry> list = this.mYVals;
        if (null != list && 0 != list.size ()) {
            if (0 == i2 || i2 >= this.mYVals.size ()) {
                i2 = this.mYVals.size () - 1;
            }
            this.mYMin = yMin(this.mYVals.get (i));
            this.mYMax = yMax(this.mYVals.get (i));
            while (i <= i2) {
                com.github.mikephil.charting.data.BubbleEntry bubbleEntry = this.mYVals.get (i);
                float yMin = yMin(bubbleEntry);
                float yMax = yMax(bubbleEntry);
                if (yMin < this.mYMin) {
                    this.mYMin = yMin;
                }
                if (yMax > this.mYMax) {
                    this.mYMax = yMax;
                }
                float xMin = xMin(bubbleEntry);
                float xMax = xMax(bubbleEntry);
                if (xMin < this.mXMin) {
                    this.mXMin = xMin;
                }
                if (xMax > this.mXMax) {
                    this.mXMax = xMax;
                }
                float largestSize = largestSize(bubbleEntry);
                if (largestSize > this.mMaxSize) {
                    this.mMaxSize = largestSize;
                }
                i++;
            }
        }
    }

    public DataSet<com.github.mikephil.charting.data.BubbleEntry> copy() {
        ArrayList arrayList = new ArrayList ();
        for (int i = 0; i < this.mYVals.size (); i++) {
            arrayList.add (this.mYVals.get (i).copy ());
        }
        BubbleDataSet bubbleDataSet = new BubbleDataSet (arrayList, this.getLabel());
        bubbleDataSet.mColors = this.mColors;
        bubbleDataSet.mHighLightColor = this.mHighLightColor;
        return bubbleDataSet;
    }

    public float getXMax() {
        return this.mXMax;
    }

    public float getXMin() {
        return this.mXMin;
    }

    public float getMaxSize() {
        return this.mMaxSize;
    }

    public boolean isNormalizeSizeEnabled() {
        return this.mNormalizeSize;
    }

    public void setNormalizeSizeEnabled(boolean z) {
        this.mNormalizeSize = z;
    }

    private float yMin(com.github.mikephil.charting.data.BubbleEntry bubbleEntry) {
        return bubbleEntry.getVal ();
    }

    private float yMax(com.github.mikephil.charting.data.BubbleEntry bubbleEntry) {
        return bubbleEntry.getVal ();
    }

    private float xMin(com.github.mikephil.charting.data.BubbleEntry bubbleEntry) {
        return bubbleEntry.getXIndex ();
    }

    private float xMax(com.github.mikephil.charting.data.BubbleEntry bubbleEntry) {
        return bubbleEntry.getXIndex ();
    }

    private float largestSize(BubbleEntry bubbleEntry) {
        return bubbleEntry.getSize ();
    }
}

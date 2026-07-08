package com.github.mikephil.charting.data;

import android.graphics.Color;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

public class BarDataSet extends BarLineScatterCandleBubbleDataSet<com.github.mikephil.charting.data.BarEntry> implements IBarDataSet {
    private float mBarSpace = 0.15f;
    private int mStackSize = 1;
    private int mBarShadowColor = Color.rgb (215, 215, 215);
    private float mBarBorderWidth;
    private int mBarBorderColor = ViewCompat.MEASURED_STATE_MASK;
    private int mHighLightAlpha = 120;
    private int mEntryCountStacks;
    private String[] mStackLabels = {"Stack"};

    public BarDataSet(List<com.github.mikephil.charting.data.BarEntry> list, String str) {
        super (list, str);
        this.mHighLightColor = Color.rgb (0, 0, 0);
        calcStackSize(list);
        calcEntryCountIncludingStacks(list);
    }

    public DataSet<com.github.mikephil.charting.data.BarEntry> copy() {
        ArrayList arrayList = new ArrayList ();
        for (int i = 0; i < this.mYVals.size (); i++) {
            arrayList.add (this.mYVals.get (i).copy ());
        }
        BarDataSet barDataSet = new BarDataSet (arrayList, this.getLabel());
        barDataSet.mColors = this.mColors;
        barDataSet.mStackSize = this.mStackSize;
        barDataSet.mBarSpace = this.mBarSpace;
        barDataSet.mBarShadowColor = this.mBarShadowColor;
        barDataSet.mStackLabels = this.mStackLabels;
        barDataSet.mHighLightColor = this.mHighLightColor;
        barDataSet.mHighLightAlpha = this.mHighLightAlpha;
        return barDataSet;
    }

    private void calcEntryCountIncludingStacks(List<com.github.mikephil.charting.data.BarEntry> list) {
        this.mEntryCountStacks = 0;
        for (int i = 0; i < list.size (); i++) {
            float[] vals = list.get (i).getVals ();
            if (null == vals) {
                this.mEntryCountStacks++;
            } else {
                this.mEntryCountStacks += vals.length;
            }
        }
    }

    private void calcStackSize(List<com.github.mikephil.charting.data.BarEntry> list) {
        for (int i = 0; i < list.size (); i++) {
            float[] vals = list.get (i).getVals ();
            if (null != vals && vals.length > this.mStackSize) {
                this.mStackSize = vals.length;
            }
        }
    }

    public void calcMinMax(int i, int i2) {
        int size;
        List<com.github.mikephil.charting.data.BarEntry> list = this.mYVals;
        if (null != list && 0 != (size = list.size ())) {
            if (0 == i2 || i2 >= size) {
                i2 = size - 1;
            }
            this.mYMin = Float.MAX_VALUE;
            this.mYMax = -3.4028235E38f;
            while (i <= i2) {
                BarEntry barEntry = this.mYVals.get (i);
                if (null != barEntry && !Float.isNaN (barEntry.getVal ())) {
                    if (null == barEntry.getVals ()) {
                        if (barEntry.getVal () < this.mYMin) {
                            this.mYMin = barEntry.getVal ();
                        }
                        if (barEntry.getVal () > this.mYMax) {
                            this.mYMax = barEntry.getVal ();
                        }
                    } else {
                        if ((-barEntry.getNegativeSum ()) < this.mYMin) {
                            this.mYMin = -barEntry.getNegativeSum ();
                        }
                        if (barEntry.getPositiveSum () > this.mYMax) {
                            this.mYMax = barEntry.getPositiveSum ();
                        }
                    }
                }
                i++;
            }
            if (Float.MAX_VALUE == mYMin) {
                this.mYMin = 0.0f;
                this.mYMax = 0.0f;
            }
        }
    }

    public int getStackSize() {
        return this.mStackSize;
    }

    public boolean isStacked() {
        return 1 < mStackSize;
    }

    public int getEntryCountStacks() {
        return this.mEntryCountStacks;
    }

    public float getBarSpacePercent() {
        return this.mBarSpace * 100.0f;
    }

    public void setBarSpacePercent(float f) {
        this.mBarSpace = f / 100.0f;
    }

    public float getBarSpace() {
        return this.mBarSpace;
    }

    public int getBarShadowColor() {
        return this.mBarShadowColor;
    }

    public void setBarShadowColor(int i) {
        this.mBarShadowColor = i;
    }

    public float getBarBorderWidth() {
        return this.mBarBorderWidth;
    }

    public void setBarBorderWidth(float f) {
        this.mBarBorderWidth = f;
    }

    public int getBarBorderColor() {
        return this.mBarBorderColor;
    }

    public void setBarBorderColor(int i) {
        this.mBarBorderColor = i;
    }

    public int getHighLightAlpha() {
        return this.mHighLightAlpha;
    }

    public void setHighLightAlpha(int i) {
        this.mHighLightAlpha = i;
    }

    public String[] getStackLabels() {
        return this.mStackLabels;
    }

    public void setStackLabels(String[] strArr) {
        this.mStackLabels = strArr;
    }
}

package com.github.mikephil.charting.data;

import android.graphics.Paint;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CandleDataSet extends LineScatterCandleRadarDataSet<com.github.mikephil.charting.data.CandleEntry> implements ICandleDataSet {
    protected Paint.Style mIncreasingPaintStyle = Paint.Style.STROKE;
    protected Paint.Style mDecreasingPaintStyle = Paint.Style.FILL;
    protected int mNeutralColor = ColorTemplate.COLOR_NONE;
    protected int mIncreasingColor = ColorTemplate.COLOR_NONE;
    protected int mDecreasingColor = ColorTemplate.COLOR_NONE;
    protected int mShadowColor = ColorTemplate.COLOR_NONE;
    private float mShadowWidth = 3.0f;
    private boolean mShowCandleBar = true;
    private float mBarSpace = 0.1f;
    private boolean mShadowColorSameAsCandle;

    public CandleDataSet(List<com.github.mikephil.charting.data.CandleEntry> list, String str) {
        super (list, str);
    }

    public DataSet<com.github.mikephil.charting.data.CandleEntry> copy() {
        ArrayList arrayList = new ArrayList ();
        for (int i = 0; i < this.mYVals.size (); i++) {
            arrayList.add (this.mYVals.get (i).copy ());
        }
        CandleDataSet candleDataSet = new CandleDataSet (arrayList, this.getLabel());
        candleDataSet.mColors = this.mColors;
        candleDataSet.mShadowWidth = this.mShadowWidth;
        candleDataSet.mShowCandleBar = this.mShowCandleBar;
        candleDataSet.mBarSpace = this.mBarSpace;
        candleDataSet.mHighLightColor = this.mHighLightColor;
        candleDataSet.mIncreasingPaintStyle = this.mIncreasingPaintStyle;
        candleDataSet.mDecreasingPaintStyle = this.mDecreasingPaintStyle;
        candleDataSet.mShadowColor = this.mShadowColor;
        return candleDataSet;
    }

    public void calcMinMax(int i, int i2) {
        List<com.github.mikephil.charting.data.CandleEntry> list = this.mYVals;

        if (null != list && 0 != list.size ()) {
            if (0 == i2 || i2 >= this.mYVals.size ()) {
                i2 = this.mYVals.size () - 1;
            }
            this.mYMin = Float.MAX_VALUE;
            this.mYMax = -3.4028235E38f;
            while (i <= i2) {
                CandleEntry candleEntry = this.mYVals.get (i);
                if (candleEntry.getLow () < this.mYMin) {
                    this.mYMin = candleEntry.getLow ();
                }
                if (candleEntry.getHigh () > this.mYMax) {
                    this.mYMax = candleEntry.getHigh ();
                }
                i++;
            }
        }
    }

    public float getBarSpace() {
        return this.mBarSpace;
    }

    public void setBarSpace(float f) {
        if (0.0f > f) {
            f = 0.0f;
        }
        if (0.45f < f) {
            f = 0.45f;
        }
        this.mBarSpace = f;
    }

    public float getShadowWidth() {
        return this.mShadowWidth;
    }

    public void setShadowWidth(float f) {
        this.mShadowWidth = Utils.convertDpToPixel (f);
    }

    public boolean getShowCandleBar() {
        return this.mShowCandleBar;
    }

    public void setShowCandleBar(boolean z) {
        this.mShowCandleBar = z;
    }

    public int getNeutralColor() {
        return this.mNeutralColor;
    }

    public void setNeutralColor(int i) {
        this.mNeutralColor = i;
    }

    public int getIncreasingColor() {
        return this.mIncreasingColor;
    }

    public void setIncreasingColor(int i) {
        this.mIncreasingColor = i;
    }

    public int getDecreasingColor() {
        return this.mDecreasingColor;
    }

    public void setDecreasingColor(int i) {
        this.mDecreasingColor = i;
    }

    public Paint.Style getIncreasingPaintStyle() {
        return this.mIncreasingPaintStyle;
    }

    public void setIncreasingPaintStyle(Paint.Style style) {
        this.mIncreasingPaintStyle = style;
    }

    public Paint.Style getDecreasingPaintStyle() {
        return this.mDecreasingPaintStyle;
    }

    public void setDecreasingPaintStyle(Paint.Style style) {
        this.mDecreasingPaintStyle = style;
    }

    public int getShadowColor() {
        return this.mShadowColor;
    }

    public void setShadowColor(int i) {
        this.mShadowColor = i;
    }

    public boolean getShadowColorSameAsCandle() {
        return this.mShadowColorSameAsCandle;
    }

    public void setShadowColorSameAsCandle(boolean z) {
        this.mShadowColorSameAsCandle = z;
    }
}

package com.github.mikephil.charting.data.realm.implementation;

import android.graphics.Paint;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.realm.base.RealmLineScatterCandleRadarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import io.realm.DynamicRealmObject;
import io.realm.RealmObject;
import io.realm.RealmResults;

import java.util.List;

public class RealmCandleDataSet<T extends RealmObject> extends RealmLineScatterCandleRadarDataSet<T, CandleEntry> implements ICandleDataSet {
    private final String mCloseField;
    private final String mHighField;
    private final String mLowField;
    private final String mOpenField;
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

    public RealmCandleDataSet(RealmResults<T> realmResults, String str, String str2, String str3, String str4) {
        super (realmResults, null);
        this.mHighField = str;
        this.mLowField = str2;
        this.mOpenField = str3;
        this.mCloseField = str4;
        this.build(this.results);
        calcMinMax(0, this.results.size ());
    }

    public RealmCandleDataSet(RealmResults<T> realmResults, String str, String str2, String str3, String str4, String str5) {
        super (realmResults, null, str5);
        this.mHighField = str;
        this.mLowField = str2;
        this.mOpenField = str3;
        this.mCloseField = str4;
        this.build(this.results);
        calcMinMax(0, this.results.size ());
    }

    public CandleEntry buildEntryFromResultObject(T t, int i) {
        DynamicRealmObject dynamicRealmObject = new DynamicRealmObject (t);
        String str = this.mIndexField;
        if (null != str) {
            i = dynamicRealmObject.getInt (str);
        }
        return new CandleEntry (i, dynamicRealmObject.getFloat (this.mHighField), dynamicRealmObject.getFloat (this.mLowField), dynamicRealmObject.getFloat (this.mOpenField), dynamicRealmObject.getFloat (this.mCloseField));
    }

    public void calcMinMax(int i, int i2) {
        List<S> list = this.mValues;
        if (0 != list && 0 != list.size ()) {
            if (0 == i2 || i2 >= this.mValues.size ()) {
                i2 = this.mValues.size () - 1;
            }
            this.mYMin = Float.MAX_VALUE;
            this.mYMax = -3.4028235E38f;
            while (i <= i2) {
                CandleEntry candleEntry = this.mValues.get (i);
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

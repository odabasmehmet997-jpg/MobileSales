package com.github.mikephil.charting.data.realm.implementation;

import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.realm.base.RealmBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.utils.Utils;
import io.realm.DynamicRealmObject;
import io.realm.RealmObject;
import io.realm.RealmResults;

import java.util.List;

public class RealmBubbleDataSet<T extends RealmObject> extends RealmBarLineScatterCandleBubbleDataSet<T, BubbleEntry> implements IBubbleDataSet {
    protected float mMaxSize;
    protected float mXMax;
    protected float mXMin;
    protected boolean mNormalizeSize = true;
    private String mSizeField;
    private float mHighlightCircleWidth = 2.5f;

    public RealmBubbleDataSet(RealmResults<T> realmResults, String str, String str2) {
        super (realmResults, str);
        this.mSizeField = str2;
        this.build(this.results);
        calcMinMax(0, this.results.size ());
    }

    public RealmBubbleDataSet(RealmResults<T> realmResults, String str, String str2, String str3) {
        super (realmResults, str, str2);
        this.mSizeField = str3;
        this.build(this.results);
        calcMinMax(0, this.results.size ());
    }

    public BubbleEntry buildEntryFromResultObject(T t, int i) {
        DynamicRealmObject dynamicRealmObject = new DynamicRealmObject (t);
        String str = this.mIndexField;
        if (null != str) {
            i = dynamicRealmObject.getInt (str);
        }
        return new BubbleEntry (i, dynamicRealmObject.getFloat (this.mValuesField), dynamicRealmObject.getFloat (this.mSizeField));
    }

    public void calcMinMax(int i, int i2) {
        List<S> list = this.mValues;
        if (null != list && 0 != list.size ()) {
            if (0 == i2 || i2 >= this.mValues.size ()) {
                i2 = this.mValues.size () - 1;
            }
            this.mYMin = yMin(this.mValues.get (i));
            this.mYMax = yMax(this.mValues.get (i));
            while (i < i2) {
                BubbleEntry bubbleEntry = this.mValues.get (i);
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

    private float yMin(BubbleEntry bubbleEntry) {
        return bubbleEntry.getVal ();
    }

    private float yMax(BubbleEntry bubbleEntry) {
        return bubbleEntry.getVal ();
    }

    private float xMin(BubbleEntry bubbleEntry) {
        return bubbleEntry.getXIndex ();
    }

    private float xMax(BubbleEntry bubbleEntry) {
        return bubbleEntry.getXIndex ();
    }

    private float largestSize(BubbleEntry bubbleEntry) {
        return bubbleEntry.getSize ();
    }

    public float getHighlightCircleWidth() {
        return this.mHighlightCircleWidth;
    }

    public void setHighlightCircleWidth(float f) {
        this.mHighlightCircleWidth = Utils.convertDpToPixel (f);
    }

    public String getSizeField() {
        return this.mSizeField;
    }

    public void setSizeField(String str) {
        this.mSizeField = str;
    }
}

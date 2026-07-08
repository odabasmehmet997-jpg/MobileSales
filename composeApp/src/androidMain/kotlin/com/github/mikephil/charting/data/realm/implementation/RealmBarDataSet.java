package com.github.mikephil.charting.data.realm.implementation;

import android.graphics.Color;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.realm.base.RealmBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import io.realm.*;

import java.util.Iterator;
import java.util.List;

public class RealmBarDataSet<T extends RealmObject> extends RealmBarLineScatterCandleBubbleDataSet<T, BarEntry> implements IBarDataSet {
    private String mStackValueFieldName;
    private float mBarSpace = 0.15f;
    private int mStackSize = 1;
    private int mBarShadowColor = Color.rgb (215, 215, 215);
    private float mBarBorderWidth;
    private int mBarBorderColor = ViewCompat.MEASURED_STATE_MASK;
    private int mHighLightAlpha = 120;
    private String[] mStackLabels = {"Stack"};

    public RealmBarDataSet(RealmResults<T> realmResults, String str, String str2) {
        super (realmResults, str, str2);
        mHighLightColor = Color.rgb (0, 0, 0);
        build(this.results);
        calcMinMax(0, realmResults.size ());
    }

    public RealmBarDataSet(RealmResults<T> realmResults, String str, String str2, String str3) {
        super (realmResults, str, str2);
        this.mStackValueFieldName = str3;
        mHighLightColor = Color.rgb (0, 0, 0);
        build(this.results);
        calcMinMax(0, realmResults.size ());
    }

    public void build(RealmResults<T> realmResults) {
        super.build (realmResults);
        calcStackSize();
    }

    public BarEntry buildEntryFromResultObject(T t, int i) {
        DynamicRealmObject dynamicRealmObject = new DynamicRealmObject (t);
        if (dynamicRealmObject.getFieldType (this.mValuesField) == RealmFieldType.LIST) {
            RealmList list = dynamicRealmObject.getList (this.mValuesField);
            float[] fArr = new float[list.size ()];
            Iterator it = list.iterator ();
            int i2 = 0;
            while (it.hasNext ()) {
                fArr[i2] = ((DynamicRealmObject) it.next ()).getFloat (this.mStackValueFieldName);
                i2++;
            }
            String str = this.mIndexField;
            if (null != str) {
                i = dynamicRealmObject.getInt (str);
            }
            return new BarEntry (fArr, i);
        }
        float f = dynamicRealmObject.getFloat (this.mValuesField);
        String str2 = this.mIndexField;
        if (null != str2) {
            i = dynamicRealmObject.getInt (str2);
        }
        return new BarEntry (f, i);
    }

    public void calcMinMax(int i, int i2) {
        int size;
        List<S> list = this.mValues;
        if (0 != list && 0 != (size = list.size ())) {
            if (0 == i2 || i2 >= size) {
                i2 = size - 1;
            }
            this.mYMin = Float.MAX_VALUE;
            this.mYMax = -3.4028235E38f;
            while (i <= i2) {
                BarEntry barEntry = this.mValues.get (i);
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

    private void calcStackSize() {
        for (int i = 0; i < this.mValues.size (); i++) {
            float[] vals = this.mValues.get (i).getVals ();
            if (null != vals && vals.length > this.mStackSize) {
                this.mStackSize = vals.length;
            }
        }
    }

    public int getStackSize() {
        return this.mStackSize;
    }

    public boolean isStacked() {
        return 1 < mStackSize;
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

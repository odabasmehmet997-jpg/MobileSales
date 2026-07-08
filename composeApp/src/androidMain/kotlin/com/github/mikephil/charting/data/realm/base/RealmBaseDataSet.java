package com.github.mikephil.charting.data.realm.base;

import com.github.mikephil.charting.data.BaseDataSet;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import io.realm.DynamicRealmObject;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class RealmBaseDataSet<T extends RealmObject, S extends Entry> extends BaseDataSet<S> {
    protected String mIndexField;
    protected String mValuesField;
    protected RealmResults<T> results;
    protected float mYMax;
    protected float mYMin;
    protected List<S> mValues = new ArrayList ();

    protected RealmBaseDataSet(RealmResults<T> realmResults, String str) {
        this.results = realmResults;
        this.mValuesField = str;
        String str2 = this.mIndexField;
        if (null != str2) {
            this.results.sort (str2, Sort.ASCENDING);
        }
    }

    protected RealmBaseDataSet(RealmResults<T> realmResults, String str, String str2) {
        this.results = realmResults;
        this.mValuesField = str;
        this.mIndexField = str2;
        String str3 = this.mIndexField;
        if (null != str3) {
            this.results.sort (str3, Sort.ASCENDING);
        }
    }

    public void build(RealmResults<T> realmResults) {
        Iterator it = realmResults.iterator ();
        int i = 0;
        while (it.hasNext ()) {
            i++;
            this.mValues.add (buildEntryFromResultObject((T) it.next (), i));
        }
    }

    public S buildEntryFromResultObject(T t, int i) {
        DynamicRealmObject dynamicRealmObject = new DynamicRealmObject (t);
        float f = dynamicRealmObject.getFloat (this.mValuesField);
        String str = this.mIndexField;
        if (null != str) {
            i = dynamicRealmObject.getInt (str);
        }
        return (S) new Entry (f, i);
    }

    public float getYMin() {
        return this.mYMin;
    }

    public float getYMax() {
        return this.mYMax;
    }

    public int getEntryCount() {
        return this.mValues.size ();
    }

    public void calcMinMax(int i, int i2) {
        int size;
        List<S> list = this.mValues;
        if (null != list && 0 != (size = list.size ())) {
            if (0 == i2 || i2 >= size) {
                i2 = size - 1;
            }
            this.mYMin = Float.MAX_VALUE;
            this.mYMax = -3.4028235E38f;
            while (i <= i2) {
                S s = this.mValues.get (i);
                if (null != s && !Float.isNaN (s.getVal ())) {
                    if (s.getVal () < this.mYMin) {
                        this.mYMin = s.getVal ();
                    }
                    if (s.getVal () > this.mYMax) {
                        this.mYMax = s.getVal ();
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

    public S getEntryForXIndex(int i) {
        return getEntryForXIndex(i, DataSet.Rounding.CLOSEST);
    }

    public S getEntryForXIndex(int i, DataSet.Rounding rounding) {
        int entryIndex = getEntryIndex(i, rounding);
        if (-1 < entryIndex) {
            return this.mValues.get (entryIndex);
        }
        return null;
    }

    public List<S> getEntriesForXIndex(int i) {
        ArrayList arrayList = new ArrayList ();
        if (null == mIndexField) {
            RealmObject realmObject = this.results.get (i);
            if (null != realmObject) {
                arrayList.add (buildEntryFromResultObject((T) realmObject, i));
            }
        } else {
            Iterator it = this.results.where ().equalTo (this.mIndexField, Integer.valueOf (i)).findAll ().iterator ();
            while (it.hasNext ()) {
                arrayList.add (buildEntryFromResultObject((T) it.next (), i));
            }
        }
        return arrayList;
    }

    public S getEntryForIndex(int i) {
        return this.mValues.get (i);
    }

    public int getEntryIndex(int r7, DataSet.Rounding r8) {
        throw new UnsupportedOperationException ("com.github.mikephil.charting.data.realm.base.RealmBaseDataSet.getEntryIndex(int, com.github.mikephil.charting.data.DataSetRounding):int");
    }

    public int getEntryIndex(S s) {
        return this.mValues.indexOf (s);
    }

    public float getYValForXIndex(int i) {
        S entryForXIndex = getEntryForXIndex(i);
        if (null == entryForXIndex || entryForXIndex.getXIndex () != i) {
            return Float.NaN;
        }
        return entryForXIndex.getVal ();
    }

    public float[] getYValsForXIndex(int i) {
        List<S> entriesForXIndex = getEntriesForXIndex(i);
        float[] fArr = new float[entriesForXIndex.size ()];
        int i2 = 0;
        for (S s : entriesForXIndex) {
            fArr[i2] = s.getVal ();
            i2++;
        }
        return fArr;
    }

    public boolean addEntry(Entry s) {
        if (null == s) {
            return false;
        }
        float val = s.getVal ();
        if (null == mValues) {
            this.mValues = new ArrayList ();
        }
        if (0 == mValues.size ()) {
            this.mYMax = val;
            this.mYMin = val;
        } else {
            if (this.mYMax < val) {
                this.mYMax = val;
            }
            if (this.mYMin > val) {
                this.mYMin = val;
            }
        }
        this.mValues.add ((S) s);
        return true;
    }

    public boolean removeEntry(Entry s) {
        List<S> list;
        if (null == s || null == (list = mValues)) {
            return false;
        }
        boolean remove = list.remove (s);
        if (remove) {
            calcMinMax(0, this.mValues.size ());
        }
        return remove;
    }

    public void addEntryOrdered(S s) {
        if (null != s) {
            float val = s.getVal ();
            if (null == mValues) {
                this.mValues = new ArrayList ();
            }
            if (0 == mValues.size ()) {
                this.mYMax = val;
                this.mYMin = val;
            } else {
                if (this.mYMax < val) {
                    this.mYMax = val;
                }
                if (this.mYMin > val) {
                    this.mYMin = val;
                }
            }
            if (0 < mValues.size ()) {
                List<S> list = this.mValues;
                if (list.get (list.size () - 1).getXIndex () > s.getXIndex ()) {
                    this.mValues.add (getEntryIndex(s.getXIndex (), DataSet.Rounding.UP), s);
                    return;
                }
            }
            this.mValues.add (s);
        }
    }

    public List<S> getValues() {
        return this.mValues;
    }

    public void clear() {
        this.mValues.clear ();
        notifyDataSetChanged();
    }

    public RealmResults<T> getResults() {
        return this.results;
    }

    public String getValuesField() {
        return this.mValuesField;
    }

    public void setValuesField(String str) {
        this.mValuesField = str;
    }

    public String getIndexField() {
        return this.mIndexField;
    }

    public void setIndexField(String str) {
        this.mIndexField = str;
    }
}

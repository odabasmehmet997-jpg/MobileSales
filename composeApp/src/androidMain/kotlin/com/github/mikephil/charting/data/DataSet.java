package com.github.mikephil.charting.data;

import core.sql.SqlLiteVariable;

import java.util.ArrayList;
import java.util.List;

public abstract class DataSet<T extends com.github.mikephil.charting.data.Entry> extends BaseDataSet<T> {
    protected float mYMax;
    protected float mYMin;
    protected List<T> mYVals;

    protected DataSet(List<T> list, String str) {
        super (str);
        this.mYVals = list;
        if (null == list) {
            this.mYVals = new ArrayList ();
        }
        calcMinMax(0, this.mYVals.size ());
    }

    public abstract DataSet<T> copy();

    public void calcMinMax(int i, int i2) {
        int size;
        List<T> list = this.mYVals;
        if (null != list && 0 != (size = list.size ())) {
            if (0 == i2 || i2 >= size) {
                i2 = size - 1;
            }
            this.mYMin = Float.MAX_VALUE;
            this.mYMax = -3.4028235E38f;
            while (i <= i2) {
                T t = this.mYVals.get (i);
                if (null != t && !Float.isNaN (t.getVal ())) {
                    if (t.getVal () < this.mYMin) {
                        this.mYMin = t.getVal ();
                    }
                    if (t.getVal () > this.mYMax) {
                        this.mYMax = t.getVal ();
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

    public int getEntryCount() {
        return this.mYVals.size ();
    }

    public List<T> getYVals() {
        return this.mYVals;
    }

    public void setYVals(List<T> list) {
        this.mYVals = list;
        this.notifyDataSetChanged();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append (toSimpleString());
        for (int i = 0; i < this.mYVals.size (); i++) {
            stringBuffer.append (this.mYVals.get (i).toString () + " ");
        }
        return stringBuffer.toString ();
    }

    public String toSimpleString() {
        StringBuffer stringBuffer = new StringBuffer ();
        String sb = "DataSet, label: " +
                (null == this.getLabel() ? "" : this.getLabel()) +
                ", entries: " +
                this.mYVals.size () +
                SqlLiteVariable._NEW_LINE;
        stringBuffer.append (sb);
        return stringBuffer.toString ();
    }

    public float getYMin() {
        return this.mYMin;
    }

    public float getYMax() {
        return this.mYMax;
    }

    public void addEntryOrdered(T t) {
        if (null != t) {
            float val = t.getVal ();
            if (null == mYVals) {
                this.mYVals = new ArrayList ();
            }
            if (0 == mYVals.size ()) {
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
            if (0 < mYVals.size ()) {
                List<T> list = this.mYVals;
                if (list.get (list.size () - 1).getXIndex () > t.getXIndex ()) {
                    this.mYVals.add (getEntryIndex(t.getXIndex (), Rounding.UP), t);
                    return;
                }
            }
            this.mYVals.add (t);
        }
    }

    public void clear() {
        this.mYVals.clear ();
        this.notifyDataSetChanged();
    }

    public boolean addEntry(com.github.mikephil.charting.data.Entry t) {
        if (null == t) {
            return false;
        }
        float val = t.getVal ();
        List<T> yVals = mYVals;
        if (null == yVals) {
            yVals = new ArrayList<> ();
        }
        if (0 == yVals.size ()) {
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
        yVals.add ((T) t);
        return true;
    }

    public boolean removeEntry(com.github.mikephil.charting.data.Entry t) {
        List<T> list;
        if (null == t || null == (list = mYVals)) {
            return false;
        }
        boolean remove = list.remove (t);
        if (remove) {
            calcMinMax(0, this.mYVals.size ());
        }
        return remove;
    }

    public int getEntryIndex(Entry entry) {
        return this.mYVals.indexOf (entry);
    }

    public T getEntryForXIndex(int i, Rounding rounding) {
        int entryIndex = getEntryIndex(i, rounding);
        if (-1 < entryIndex) {
            return this.mYVals.get (entryIndex);
        }
        return null;
    }

    public T getEntryForXIndex(int i) {
        return getEntryForXIndex(i, Rounding.CLOSEST);
    }

    public T getEntryForIndex(int i) {
        return this.mYVals.get (i);
    }

    public int getEntryIndex(int r6, Rounding r7) {
        /*
            r5 = this;
            java.util.List<T extends com.github.mikephil.charting.data.Entry> r0 = r5.mYVals
            int r0 = r0.size()
            int r0 = r0 + -1
            r1 = 0
            r2 = -1
            r3 = r2
        L_0x000b:
            if (r1 > r0) goto L_0x0049
            int r3 = r0 + r1
            int r3 = r3 / 2
            java.util.List<T extends com.github.mikephil.charting.data.Entry> r4 = r5.mYVals
            java.lang.Object r4 = r4.get(r3)
            com.github.mikephil.charting.data.Entry r4 = (com.github.mikephil.charting.data.Entry) r4
            int r4 = r4.getXIndex()
            if (r6 != r4) goto L_0x0035
        L_0x001f:
            if (r3 <= 0) goto L_0x0034
            java.util.List<T extends com.github.mikephil.charting.data.Entry> r7 = r5.mYVals
            int r0 = r3 + -1
            java.lang.Object r7 = r7.get(r0)
            com.github.mikephil.charting.data.Entry r7 = (com.github.mikephil.charting.data.Entry) r7
            int r7 = r7.getXIndex()
            if (r7 != r6) goto L_0x0034
            int r3 = r3 + -1
            goto L_0x001f
        L_0x0034:
            return r3
        L_0x0035:
            java.util.List<T extends com.github.mikephil.charting.data.Entry> r4 = r5.mYVals
            java.lang.Object r4 = r4.get(r3)
            com.github.mikephil.charting.data.Entry r4 = (com.github.mikephil.charting.data.Entry) r4
            int r4 = r4.getXIndex()
            if (r6 <= r4) goto L_0x0046
            int r1 = r3 + 1
            goto L_0x000b
        L_0x0046:
            int r0 = r3 + -1
            goto L_0x000b
        L_0x0049:
            if (r3 == r2) goto L_0x0074
            java.util.List<T extends com.github.mikephil.charting.data.Entry> r0 = r5.mYVals
            java.lang.Object r0 = r0.get(r3)
            com.github.mikephil.charting.data.Entry r0 = (com.github.mikephil.charting.data.Entry) r0
            int r0 = r0.getXIndex()
            com.github.mikephil.charting.data.DataSetRounding r1 = com.github.mikephil.charting.data.DataSet.Rounding.UP
            if (r7 != r1) goto L_0x006a
            if (r0 >= r6) goto L_0x0074
            java.util.List<T extends com.github.mikephil.charting.data.Entry> r6 = r5.mYVals
            int r6 = r6.size()
            int r6 = r6 + -1
            if (r3 >= r6) goto L_0x0074
            int r3 = r3 + 1
            goto L_0x0074
        L_0x006a:
            com.github.mikephil.charting.data.DataSetRounding r1 = com.github.mikephil.charting.data.DataSet.Rounding.DOWN
            if (r7 != r1) goto L_0x0074
            if (r0 <= r6) goto L_0x0074
            if (r3 <= 0) goto L_0x0074
            int r3 = r3 + -1
        L_0x0074:
            return r3
        */
        throw new UnsupportedOperationException ("com.github.mikephil.charting.data.DataSet.getEntryIndex(int, com.github.mikephil.charting.data.DataSetRounding):int");
    }

    public float getYValForXIndex(int i) {
        T entryForXIndex = getEntryForXIndex(i);
        if (null == entryForXIndex || entryForXIndex.getXIndex () != i) {
            return Float.NaN;
        }
        return entryForXIndex.getVal ();
    }

    public float[] getYValsForXIndex(int i) {
        List<T> entriesForXIndex = getEntriesForXIndex(i);
        float[] fArr = new float[entriesForXIndex.size ()];
        int i2 = 0;
        for (T t : entriesForXIndex) {
            fArr[i2] = t.getVal ();
            i2++;
        }
        return fArr;
    }

    public List<T> getEntriesForXIndex(int r7) {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List<T extends com.github.mikephil.charting.data.Entry> r1 = r6.mYVals
            int r1 = r1.size()
            int r1 = r1 + -1
            r2 = 0
        L_0x000e:
            if (r2 > r1) goto L_0x0061
            int r3 = r1 + r2
            int r3 = r3 / 2
            java.util.List<T extends com.github.mikephil.charting.data.Entry> r4 = r6.mYVals
            java.lang.Object r4 = r4.get(r3)
            com.github.mikephil.charting.data.Entry r4 = (com.github.mikephil.charting.data.Entry) r4
            int r5 = r4.getXIndex()
            if (r7 != r5) goto L_0x0053
        L_0x0022:
            if (r3 <= 0) goto L_0x0037
            java.util.List<T extends com.github.mikephil.charting.data.Entry> r1 = r6.mYVals
            int r2 = r3 + -1
            java.lang.Object r1 = r1.get(r2)
            com.github.mikephil.charting.data.Entry r1 = (com.github.mikephil.charting.data.Entry) r1
            int r1 = r1.getXIndex()
            if (r1 != r7) goto L_0x0037
            int r3 = r3 + -1
            goto L_0x0022
        L_0x0037:
            java.util.List<T extends com.github.mikephil.charting.data.Entry> r1 = r6.mYVals
            int r1 = r1.size()
        L_0x003d:
            if (r3 >= r1) goto L_0x0061
            java.util.List<T extends com.github.mikephil.charting.data.Entry> r2 = r6.mYVals
            java.lang.Object r2 = r2.get(r3)
            com.github.mikephil.charting.data.Entry r2 = (com.github.mikephil.charting.data.Entry) r2
            int r4 = r2.getXIndex()
            if (r4 != r7) goto L_0x0061
            r0.add(r2)
            int r3 = r3 + 1
            goto L_0x003d
        L_0x0053:
            int r4 = r4.getXIndex()
            if (r7 <= r4) goto L_0x005d
            int r3 = r3 + 1
            r2 = r3
            goto L_0x000e
        L_0x005d:
            int r3 = r3 + -1
            r1 = r3
            goto L_0x000e
        L_0x0061:
            return r0
        */
        throw new UnsupportedOperationException ("com.github.mikephil.charting.data.DataSet.getEntriesForXIndex(int):java.util.List");
    }

    public enum Rounding {
        UP,
        DOWN,
        CLOSEST
    }
}

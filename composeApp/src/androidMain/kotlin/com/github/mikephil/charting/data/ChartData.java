package com.github.mikephil.charting.data;

import android.graphics.Typeface;
import android.util.Log;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ChartData<T extends IDataSet<? extends com.github.mikephil.charting.data.Entry>> {
    protected List<T> mDataSets;
    protected float mLeftAxisMax;
    protected float mLeftAxisMin;
    protected float mRightAxisMax;
    protected float mRightAxisMin;
    protected List<String> mXVals;
    protected float mYMax;
    protected float mYMin;
    private float mXValMaximumLength;
    private int mYValCount;

    protected ChartData() {
        this.mYMax = 0.0f;
        this.mYMin = 0.0f;
        this.mLeftAxisMax = 0.0f;
        this.mLeftAxisMin = 0.0f;
        this.mRightAxisMax = 0.0f;
        this.mRightAxisMin = 0.0f;
        this.mYValCount = 0;
        this.mXValMaximumLength = 0.0f;
        this.mXVals = new ArrayList ();
        this.mDataSets = new ArrayList ();
    }

    protected ChartData(List<String> list) {
        this.mYMax = 0.0f;
        this.mYMin = 0.0f;
        this.mLeftAxisMax = 0.0f;
        this.mLeftAxisMin = 0.0f;
        this.mRightAxisMax = 0.0f;
        this.mRightAxisMin = 0.0f;
        this.mYValCount = 0;
        this.mXValMaximumLength = 0.0f;
        this.mXVals = list;
        this.mDataSets = new ArrayList ();
        init();
    }

    protected ChartData(String[] strArr) {
        this.mYMax = 0.0f;
        this.mYMin = 0.0f;
        this.mLeftAxisMax = 0.0f;
        this.mLeftAxisMin = 0.0f;
        this.mRightAxisMax = 0.0f;
        this.mRightAxisMin = 0.0f;
        this.mYValCount = 0;
        this.mXValMaximumLength = 0.0f;
        this.mXVals = arrayToList(strArr);
        this.mDataSets = new ArrayList ();
        init();
    }

    protected ChartData(List<String> list, List<T> list2) {
        this.mYMax = 0.0f;
        this.mYMin = 0.0f;
        this.mLeftAxisMax = 0.0f;
        this.mLeftAxisMin = 0.0f;
        this.mRightAxisMax = 0.0f;
        this.mRightAxisMin = 0.0f;
        this.mYValCount = 0;
        this.mXValMaximumLength = 0.0f;
        this.mXVals = list;
        this.mDataSets = list2;
        init();
    }

    protected ChartData(String[] strArr, List<T> list) {
        this.mYMax = 0.0f;
        this.mYMin = 0.0f;
        this.mLeftAxisMax = 0.0f;
        this.mLeftAxisMin = 0.0f;
        this.mRightAxisMax = 0.0f;
        this.mRightAxisMin = 0.0f;
        this.mYValCount = 0;
        this.mXValMaximumLength = 0.0f;
        this.mXVals = arrayToList(strArr);
        this.mDataSets = list;
        init();
    }

    public static List<String> generateXVals(int i, int i2) {
        ArrayList arrayList = new ArrayList ();
        while (i < i2) {
            arrayList.add ("" + i);
            i++;
        }
        return arrayList;
    }

    private List<String> arrayToList(String[] strArr) {
        return Arrays.asList (strArr);
    }

    public void init() {
        checkLegal();
        calcYValueCount();
        calcMinMax(0, this.mYValCount);
        calcXValMaximumLength();
    }

    private void calcXValMaximumLength() {
        if (0 >= mXVals.size ()) {
            this.mXValMaximumLength = 1.0f;
            return;
        }
        int i = 1;
        for (int i2 = 0; i2 < this.mXVals.size (); i2++) {
            int length = this.mXVals.get (i2).length ();
            if (length > i) {
                i = length;
            }
        }
        this.mXValMaximumLength = i;
    }

    private void checkLegal() {
        if (null != mDataSets && !(this instanceof ScatterData) && !(this instanceof CombinedData)) {
            for (int i = 0; i < this.mDataSets.size (); i++) {
                if (this.mDataSets.get (i).getEntryCount () > this.mXVals.size ()) {
                    throw new IllegalArgumentException ("One or more of the DataSet Entry arrays are longer than the x-values array of this ChartData object.");
                }
            }
        }
    }

    public void notifyDataChanged() {
        init();
    }

    public void calcMinMax(int i, int i2) {
        List<T> list = this.mDataSets;
        if (null == list || 1 > list.size ()) {
            this.mYMax = 0.0f;
            this.mYMin = 0.0f;
            return;
        }
        this.mYMin = Float.MAX_VALUE;
        this.mYMax = -3.4028235E38f;
        for (int i3 = 0; i3 < this.mDataSets.size (); i3++) {
            T t = this.mDataSets.get (i3);
            t.calcMinMax (i, i2);
            if (t.getYMin () < this.mYMin) {
                this.mYMin = t.getYMin ();
            }
            if (t.getYMax () > this.mYMax) {
                this.mYMax = t.getYMax ();
            }
        }
        if (Float.MAX_VALUE == mYMin) {
            this.mYMin = 0.0f;
            this.mYMax = 0.0f;
        }
        T firstLeft = getFirstLeft();
        if (null != firstLeft) {
            this.mLeftAxisMax = firstLeft.getYMax ();
            this.mLeftAxisMin = firstLeft.getYMin ();
            for (T t2 : this.mDataSets) {
                if (YAxis.AxisDependency.LEFT == t2.getAxisDependency ()) {
                    if (t2.getYMin () < this.mLeftAxisMin) {
                        this.mLeftAxisMin = t2.getYMin ();
                    }
                    if (t2.getYMax () > this.mLeftAxisMax) {
                        this.mLeftAxisMax = t2.getYMax ();
                    }
                }
            }
        }
        T firstRight = getFirstRight();
        if (null != firstRight) {
            this.mRightAxisMax = firstRight.getYMax ();
            this.mRightAxisMin = firstRight.getYMin ();
            for (T t3 : this.mDataSets) {
                if (YAxis.AxisDependency.RIGHT == t3.getAxisDependency ()) {
                    if (t3.getYMin () < this.mRightAxisMin) {
                        this.mRightAxisMin = t3.getYMin ();
                    }
                    if (t3.getYMax () > this.mRightAxisMax) {
                        this.mRightAxisMax = t3.getYMax ();
                    }
                }
            }
        }
        handleEmptyAxis(firstLeft, firstRight);
    }

    protected void calcYValueCount() {
        this.mYValCount = 0;
        if (null != mDataSets) {
            int i = 0;
            for (int i2 = 0; i2 < this.mDataSets.size (); i2++) {
                i += this.mDataSets.get (i2).getEntryCount ();
            }
            this.mYValCount = i;
        }
    }

    public int getDataSetCount() {
        List<T> list = this.mDataSets;
        if (null == list) {
            return 0;
        }
        return list.size ();
    }

    public float getYMin() {
        return this.mYMin;
    }

    public float getYMin(YAxis.AxisDependency axisDependency) {
        if (YAxis.AxisDependency.LEFT == axisDependency) {
            return this.mLeftAxisMin;
        }
        return this.mRightAxisMin;
    }

    public float getYMax() {
        return this.mYMax;
    }

    public float getYMax(YAxis.AxisDependency axisDependency) {
        if (YAxis.AxisDependency.LEFT == axisDependency) {
            return this.mLeftAxisMax;
        }
        return this.mRightAxisMax;
    }

    public float getXValMaximumLength() {
        return this.mXValMaximumLength;
    }

    public int getYValCount() {
        return this.mYValCount;
    }

    public List<String> getXVals() {
        return this.mXVals;
    }

    public void setXVals(List<String> list) {
        this.mXVals = list;
    }

    public void addXValue(String str) {
        if (null != str && str.length () > this.mXValMaximumLength) {
            this.mXValMaximumLength = str.length ();
        }
        this.mXVals.add (str);
    }

    public void removeXValue(int i) {
        this.mXVals.remove (i);
    }

    public List<T> getDataSets() {
        return this.mDataSets;
    }

    protected int getDataSetIndexByLabel(List<T> list, String str, boolean z) {
        int i = 0;
        if (z) {
            while (i < list.size ()) {
                if (str.equalsIgnoreCase (list.get (i).getLabel ())) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        while (i < list.size ()) {
            if (str.equals (list.get (i).getLabel ())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int getXValCount() {
        return this.mXVals.size ();
    }

    protected String[] getDataSetLabels() {
        String[] strArr = new String[this.mDataSets.size ()];
        for (int i = 0; i < this.mDataSets.size (); i++) {
            strArr[i] = this.mDataSets.get (i).getLabel ();
        }
        return strArr;
    }

    public com.github.mikephil.charting.data.Entry getEntryForHighlight(com.github.mikephil.charting.highlight.Highlight r6) {
        /*
            r5 = this;
            int r0 = r6.getDataSetIndex()
            java.util.List<T extends com.github.mikephil.charting.interfaces.datasets.IDataSet<? extends com.github.mikephil.charting.data.Entry>> r1 = r5.mDataSets
            int r1 = r1.size()
            r2 = 0
            if (r0 < r1) goto L_0x000e
            return r2
        L_0x000e:
            java.util.List<T extends com.github.mikephil.charting.interfaces.datasets.IDataSet<? extends com.github.mikephil.charting.data.Entry>> r0 = r5.mDataSets
            int r1 = r6.getDataSetIndex()
            java.lang.Object r0 = r0.get(r1)
            com.github.mikephil.charting.interfaces.datasets.IDataSet r0 = (com.github.mikephil.charting.interfaces.datasets.IDataSet) r0
            int r1 = r6.getXIndex()
            java.util.List r0 = r0.getEntriesForXIndex(r1)
            java.util.Iterator r0 = r0.iterator()
        L_0x0026:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0049
            java.lang.Object r1 = r0.next()
            com.github.mikephil.charting.data.Entry r1 = (com.github.mikephil.charting.data.Entry) r1
            float r3 = r1.getVal()
            float r4 = r6.getValue()
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 == 0) goto L_0x0048
            float r3 = r6.getValue()
            boolean r3 = java.lang.Float.isNaN(r3)
            if (r3 == 0) goto L_0x0026
        L_0x0048:
            return r1
        L_0x0049:
            return r2
        */
        throw new UnsupportedOperationException ("com.github.mikephil.charting.data.ChartData.getEntryForHighlight(com.github.mikephil.charting.highlight.Highlight):com.github.mikephil.charting.data.Entry");
    }

    public T getDataSetByLabel(String str, boolean z) {
        int dataSetIndexByLabel = getDataSetIndexByLabel(this.mDataSets, str, z);
        if (0 > dataSetIndexByLabel || dataSetIndexByLabel >= this.mDataSets.size ()) {
            return null;
        }
        return this.mDataSets.get (dataSetIndexByLabel);
    }

    public T getDataSetByIndex(int i) {
        List<T> list = this.mDataSets;
        if (null == list || 0 > i || i >= list.size ()) {
            return null;
        }
        return this.mDataSets.get (i);
    }

    public void addDataSet(T t) {
        if (null != t) {
            this.mYValCount += t.getEntryCount ();
            if (0 >= mDataSets.size ()) {
                this.mYMax = t.getYMax ();
                this.mYMin = t.getYMin ();
                if (YAxis.AxisDependency.LEFT == t.getAxisDependency ()) {
                    this.mLeftAxisMax = t.getYMax ();
                    this.mLeftAxisMin = t.getYMin ();
                } else {
                    this.mRightAxisMax = t.getYMax ();
                    this.mRightAxisMin = t.getYMin ();
                }
            } else {
                if (this.mYMax < t.getYMax ()) {
                    this.mYMax = t.getYMax ();
                }
                if (this.mYMin > t.getYMin ()) {
                    this.mYMin = t.getYMin ();
                }
                if (YAxis.AxisDependency.LEFT == t.getAxisDependency ()) {
                    if (this.mLeftAxisMax < t.getYMax ()) {
                        this.mLeftAxisMax = t.getYMax ();
                    }
                    if (this.mLeftAxisMin > t.getYMin ()) {
                        this.mLeftAxisMin = t.getYMin ();
                    }
                } else {
                    if (this.mRightAxisMax < t.getYMax ()) {
                        this.mRightAxisMax = t.getYMax ();
                    }
                    if (this.mRightAxisMin > t.getYMin ()) {
                        this.mRightAxisMin = t.getYMin ();
                    }
                }
            }
            this.mDataSets.add (t);
            handleEmptyAxis(getFirstLeft(), getFirstRight());
        }
    }

    private void handleEmptyAxis(T t, T t2) {
        if (null == t) {
            this.mLeftAxisMax = this.mRightAxisMax;
            this.mLeftAxisMin = this.mRightAxisMin;
        } else if (null == t2) {
            this.mRightAxisMax = this.mLeftAxisMax;
            this.mRightAxisMin = this.mLeftAxisMin;
        }
    }

    public boolean removeDataSet(T t) {
        if (null == t) {
            return false;
        }
        boolean remove = this.mDataSets.remove (t);
        if (remove) {
            int entryCount = this.mYValCount - t.getEntryCount ();
            this.mYValCount = entryCount;
            calcMinMax(0, entryCount);
        }
        return remove;
    }

    public boolean removeDataSet(int i) {
        if (i >= this.mDataSets.size () || 0 > i) {
            return false;
        }
        return removeDataSet(this.mDataSets.get (i));
    }

    public void addEntry(com.github.mikephil.charting.data.Entry entry, int i) {
        if (this.mDataSets.size () <= i || 0 > i) {
            Log.e ("addEntry", "Cannot add Entry because dataSetIndex too high or too low.");
            return;
        }
        T t = this.mDataSets.get (i);
        if (t.addEntry (entry)) {
            float val = entry.getVal ();
            if (0 == mYValCount) {
                this.mYMin = val;
                this.mYMax = val;
                if (YAxis.AxisDependency.LEFT == t.getAxisDependency ()) {
                    this.mLeftAxisMax = entry.getVal ();
                    this.mLeftAxisMin = entry.getVal ();
                } else {
                    this.mRightAxisMax = entry.getVal ();
                    this.mRightAxisMin = entry.getVal ();
                }
            } else {
                if (this.mYMax < val) {
                    this.mYMax = val;
                }
                if (this.mYMin > val) {
                    this.mYMin = val;
                }
                if (YAxis.AxisDependency.LEFT == t.getAxisDependency ()) {
                    if (this.mLeftAxisMax < entry.getVal ()) {
                        this.mLeftAxisMax = entry.getVal ();
                    }
                    if (this.mLeftAxisMin > entry.getVal ()) {
                        this.mLeftAxisMin = entry.getVal ();
                    }
                } else {
                    if (this.mRightAxisMax < entry.getVal ()) {
                        this.mRightAxisMax = entry.getVal ();
                    }
                    if (this.mRightAxisMin > entry.getVal ()) {
                        this.mRightAxisMin = entry.getVal ();
                    }
                }
            }
            this.mYValCount++;
            handleEmptyAxis(getFirstLeft(), getFirstRight());
        }
    }

    public boolean removeEntry(com.github.mikephil.charting.data.Entry entry, int i) {
        T t;
        if (null == entry || i >= this.mDataSets.size () || null == (t = mDataSets.get (i))) {
            return false;
        }
        boolean removeEntry = t.removeEntry (entry);
        if (removeEntry) {
            int i2 = this.mYValCount - 1;
            this.mYValCount = i2;
            calcMinMax(0, i2);
        }
        return removeEntry;
    }

    public boolean removeEntry(int i, int i2) {
        com.github.mikephil.charting.data.Entry entryForXIndex;
        if (i2 < this.mDataSets.size () && null != (entryForXIndex = mDataSets.get (i2).getEntryForXIndex (i)) && entryForXIndex.getXIndex () == i) {
            return removeEntry(entryForXIndex, i2);
        }
        return false;
    }

    public T getDataSetForEntry(Entry entry) {
        if (null == entry) {
            return null;
        }
        for (int i = 0; i < this.mDataSets.size (); i++) {
            T t = this.mDataSets.get (i);
            for (int i2 = 0; i2 < t.getEntryCount (); i2++) {
                if (entry.equalTo (t.getEntryForXIndex (entry.getXIndex ()))) {
                    return t;
                }
            }
        }
        return null;
    }

    public int[] getColors() {
        if (null == mDataSets) {
            return null;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.mDataSets.size (); i2++) {
            i += this.mDataSets.get (i2).getColors ().size ();
        }
        int[] iArr = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < this.mDataSets.size (); i4++) {
            for (Integer num : this.mDataSets.get (i4).getColors ()) {
                iArr[i3] = num.intValue ();
                i3++;
            }
        }
        return iArr;
    }

    public int getIndexOfDataSet(T t) {
        for (int i = 0; i < this.mDataSets.size (); i++) {
            if (this.mDataSets.get (i) == t) {
                return i;
            }
        }
        return -1;
    }

    public T getFirstLeft() {
        for (T t : this.mDataSets) {
            if (YAxis.AxisDependency.LEFT == t.getAxisDependency ()) {
                return t;
            }
        }
        return null;
    }

    public T getFirstRight() {
        for (T t : this.mDataSets) {
            if (YAxis.AxisDependency.RIGHT == t.getAxisDependency ()) {
                return t;
            }
        }
        return null;
    }

    public void setValueFormatter(ValueFormatter valueFormatter) {
        if (null != valueFormatter) {
            for (T t : this.mDataSets) {
                t.setValueFormatter (valueFormatter);
            }
        }
    }

    public void setValueTextColor(int i) {
        for (T t : this.mDataSets) {
            t.setValueTextColor (i);
        }
    }

    public void setValueTextColors(List<Integer> list) {
        for (T t : this.mDataSets) {
            t.setValueTextColors (list);
        }
    }

    public void setValueTypeface(Typeface typeface) {
        for (T t : this.mDataSets) {
            t.setValueTypeface (typeface);
        }
    }

    public void setValueTextSize(float f) {
        for (T t : this.mDataSets) {
            t.setValueTextSize (f);
        }
    }

    public void setDrawValues(boolean z) {
        for (T t : this.mDataSets) {
            t.setDrawValues (z);
        }
    }

    public boolean isHighlightEnabled() {
        for (T t : this.mDataSets) {
            if (!t.isHighlightEnabled ()) {
                return false;
            }
        }
        return true;
    }

    public void setHighlightEnabled(boolean z) {
        for (T t : this.mDataSets) {
            t.setHighlightEnabled (z);
        }
    }

    public void clearValues() {
        this.mDataSets.clear ();
        notifyDataChanged();
    }

    public boolean contains(T t) {
        for (T t2 : this.mDataSets) {
            if (t2.equals (t)) {
                return true;
            }
        }
        return false;
    }
}

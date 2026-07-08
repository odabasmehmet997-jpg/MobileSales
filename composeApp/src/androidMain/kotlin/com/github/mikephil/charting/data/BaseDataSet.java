package com.github.mikephil.charting.data;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDataSet<T extends Entry> implements IDataSet<T> {
    protected YAxis.AxisDependency mAxisDependency;
    protected List<Integer> mColors;
    protected boolean mDrawValues;
    protected boolean mHighlightEnabled;
    protected List<Integer> mValueColors;
    protected ValueFormatter mValueFormatter;
    protected float mValueTextSize;
    protected Typeface mValueTypeface;
    protected boolean mVisible;
    private String mLabel;

    protected BaseDataSet() {
        this.mColors = null;
        this.mValueColors = null;
        this.mLabel = "DataSet";
        this.mAxisDependency = YAxis.AxisDependency.LEFT;
        this.mHighlightEnabled = true;
        this.mDrawValues = true;
        this.mValueTextSize = 17.0f;
        this.mVisible = true;
        this.mColors = new ArrayList ();
        this.mValueColors = new ArrayList ();
        this.mColors.add (Integer.valueOf (Color.rgb (140, 234, 255)));
        this.mValueColors.add (Integer.valueOf (ViewCompat.MEASURED_STATE_MASK));
    }

    protected BaseDataSet(String str) {
        this ();
        this.mLabel = str;
    }

    public void notifyDataSetChanged() {
        calcMinMax(0, getEntryCount() - 1);
    }

    public List<Integer> getColors() {
        return this.mColors;
    }

    public void setColors(List<Integer> list) {
        this.mColors = list;
    }

    public void setColors(int [] iArr) {
        this.mColors = ColorTemplate.createColors (iArr);
    }

    public List<Integer> getValueColors() {
        return this.mValueColors;
    }

    public int getColor() {
        return this.mColors.get (0).intValue ();
    }

    public void setColor(int i) {
        resetColors();
        this.mColors.add (Integer.valueOf (i));
    }

    public int getColor(int i) {
        List<Integer> list = this.mColors;
        return list.get (i % list.size ()).intValue ();
    }

    public void setColors(int [] iArr, Context context) {
        ArrayList arrayList = new ArrayList ();
        for (int i : iArr) {
            arrayList.add (Integer.valueOf (context.getResources ().getColor (i)));
        }
        this.mColors = arrayList;
    }

    public void addColor(int i) {
        if (null == mColors) {
            this.mColors = new ArrayList ();
        }
        this.mColors.add (Integer.valueOf (i));
    }

    public void setColor(int i, int i2) {
        setColor(Color.argb (i2, Color.red (i), Color.green (i), Color.blue (i)));
    }

    public void setColors(int [] iArr, int i) {
        resetColors();
        for (int i2 : iArr) {
            addColor(Color.argb (i, Color.red (i2), Color.green (i2), Color.blue (i2)));
        }
    }

    public void resetColors() {
        this.mColors = new ArrayList ();
    }

    public String getLabel() {
        return this.mLabel;
    }

    public void setLabel(String str) {
        this.mLabel = str;
    }

    public boolean isHighlightEnabled() {
        return this.mHighlightEnabled;
    }

    public void setHighlightEnabled(boolean z) {
        this.mHighlightEnabled = z;
    }

    public ValueFormatter getValueFormatter() {
        ValueFormatter valueFormatter = this.mValueFormatter;
        return null == valueFormatter ? new DefaultValueFormatter (1) : valueFormatter;
    }

    public void setValueFormatter(ValueFormatter valueFormatter) {
        if (null != valueFormatter) {
            this.mValueFormatter = valueFormatter;
        }
    }

    public void setValueTextColors(List<Integer> list) {
        this.mValueColors = list;
    }

    public int getValueTextColor() {
        return this.mValueColors.get (0).intValue ();
    }

    public void setValueTextColor(int i) {
        this.mValueColors.clear ();
        this.mValueColors.add (Integer.valueOf (i));
    }

    public int getValueTextColor(int i) {
        List<Integer> list = this.mValueColors;
        return list.get (i % list.size ()).intValue ();
    }

    public Typeface getValueTypeface() {
        return this.mValueTypeface;
    }

    public void setValueTypeface(Typeface typeface) {
        this.mValueTypeface = typeface;
    }

    public float getValueTextSize() {
        return this.mValueTextSize;
    }

    public void setValueTextSize(float f) {
        this.mValueTextSize = Utils.convertDpToPixel (f);
    }

    public void setDrawValues(boolean z) {
        this.mDrawValues = z;
    }

    public boolean isDrawValuesEnabled() {
        return this.mDrawValues;
    }

    public boolean isVisible() {
        return this.mVisible;
    }

    public void setVisible(boolean z) {
        this.mVisible = z;
    }

    public YAxis.AxisDependency getAxisDependency() {
        return this.mAxisDependency;
    }

    public void setAxisDependency(YAxis.AxisDependency axisDependency) {
        this.mAxisDependency = axisDependency;
    }

    public int getIndexInEntries(int i) {
        for (int i2 = 0; i2 < getEntryCount(); i2++) {
            if (i == getEntryForIndex(i2).getXIndex ()) {
                return i2;
            }
        }
        return -1;
    }

    public boolean removeFirst() {
        return this.removeEntry(getEntryForIndex(0));
    }

    public boolean removeLast() {
        return this.removeEntry(getEntryForIndex(getEntryCount() - 1));
    }

    public boolean removeEntry(int i) {
        return this.removeEntry(getEntryForIndex(i));
    }

    public boolean contains(T t) {
        for (int i = 0; i < getEntryCount(); i++) {
            if (getEntryForIndex(i).equals (t)) {
                return true;
            }
        }
        return false;
    }
}

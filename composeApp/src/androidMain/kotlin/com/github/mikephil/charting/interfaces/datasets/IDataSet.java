package com.github.mikephil.charting.interfaces.datasets;

import android.graphics.Typeface;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.List;

public interface IDataSet<T extends Entry> {
    boolean addEntry(Entry t);

    void addEntryOrdered(T t);

    void calcMinMax(int i, int i2);

    void clear();

    boolean contains(T t);

    YAxis.AxisDependency getAxisDependency();

    void setAxisDependency(YAxis.AxisDependency axisDependency);

    int getColor();

    int getColor(int i);

    List<Integer> getColors();

    List<T> getEntriesForXIndex(int i);

    int getEntryCount();

    T getEntryForIndex(int i);

    T getEntryForXIndex(int i);

    T getEntryForXIndex(int i, DataSet.Rounding rounding);

    int getEntryIndex(int i, DataSet.Rounding rounding);

    int getEntryIndex(T t);

    int getIndexInEntries(int i);

    String getLabel();

    void setLabel(String str);

    ValueFormatter getValueFormatter();

    void setValueFormatter(ValueFormatter valueFormatter);

    int getValueTextColor();

    void setValueTextColor(int i);

    int getValueTextColor(int i);

    float getValueTextSize();

    void setValueTextSize(float f);

    Typeface getValueTypeface();

    void setValueTypeface(Typeface typeface);

    float getYMax();

    float getYMin();

    float getYValForXIndex(int i);

    float[] getYValsForXIndex(int i);

    boolean isDrawValuesEnabled();

    boolean isHighlightEnabled();

    void setHighlightEnabled(boolean z);

    boolean isVisible();

    void setVisible(boolean z);

    boolean removeEntry(int i);

    boolean removeEntry(Entry t);

    boolean removeFirst();

    boolean removeLast();

    void setDrawValues(boolean z);

    void setValueTextColors(List<Integer> list);
}

package com.github.mikephil.charting.utils;

import com.github.mikephil.charting.data.Entry;

import java.io.Serializable;
import java.util.Comparator;

public class EntryXIndexComparator implements Comparator<Entry>, Serializable {
    public int compare(final Entry entry, final Entry entry2) {
        return entry.getXIndex () - entry2.getXIndex ();
    }
}

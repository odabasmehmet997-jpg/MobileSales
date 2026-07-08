package com.google.zxing.oned.rss.expanded;

import java.util.ArrayList;
import java.util.List;

final class ExpandedRow {
    private final List<ExpandedPair> pairs;
    private final int rowNumber;
    private final boolean wasReversed;

    ExpandedRow(final List<ExpandedPair> list, final int i2, final boolean z) {
        pairs = new ArrayList(list);
        rowNumber = i2;
        wasReversed = z;
    }

    
    public List<ExpandedPair> getPairs() {
        return pairs;
    }

    
    public int getRowNumber() {
        return rowNumber;
    }

    
    public boolean isEquivalent(final List<ExpandedPair> list) {
        return pairs.equals(list);
    }

    public String toString() {
        return "{ " + pairs + " }";
    }

    public boolean equals(final Object obj) {
        if (!(obj instanceof ExpandedRow expandedRow)) {
            return false;
        }
        return pairs.equals(expandedRow.pairs) && wasReversed == expandedRow.wasReversed;
    }

    public int hashCode() {
        return pairs.hashCode() ^ Boolean.valueOf(wasReversed).hashCode();
    }
}

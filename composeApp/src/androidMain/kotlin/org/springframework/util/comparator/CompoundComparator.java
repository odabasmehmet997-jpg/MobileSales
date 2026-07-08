package org.springframework.util.comparator;

import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class CompoundComparator<T> implements Comparator<T>, Serializable {
    private final List<InvertibleComparator<T>> comparators;
    public CompoundComparator() {
        comparators = new ArrayList<>();
    }
    public CompoundComparator(final Comparator... comparatorArr) {
        Assert.notNull(comparatorArr, "Comparators must not be null");
        comparators = new ArrayList<>(comparatorArr.length);
        for (final Comparator comparator : comparatorArr) {
            this.addComparator(comparator);
        }
    }
    public void addComparator(final Comparator<T> comparator) {
        if (comparator instanceof InvertibleComparator<T>) {
            comparators.add((InvertibleComparator<T>) comparator);
        } else {
            comparators.add(new InvertibleComparator<>(comparator));
        }
    }
    public void addComparator(final Comparator<T> comparator, final boolean z) {
        comparators.add(new InvertibleComparator<>(comparator, z));
    }
    public void setComparator(final int i2, final Comparator<T> comparator) {
        if (comparator instanceof InvertibleComparator<T>) {
            comparators.set(i2, (InvertibleComparator<T>) comparator);
        } else {
            comparators.set(i2, new InvertibleComparator<>(comparator));
        }
    }
    public void setComparator(final int i2, final Comparator<T> comparator, final boolean z) {
        comparators.set(i2, new InvertibleComparator<>(comparator, z));
    }
    public void invertOrder() {
        for (InvertibleComparator<T> comparator : comparators) {
            comparator.invertOrder();
        }
    }
    public void invertOrder(final int i2) {
        comparators.get(i2).invertOrder();
    }
    public void setAscendingOrder(final int i2) {
        comparators.get(i2).setAscending(true);
    }
    public void setDescendingOrder(final int i2) {
        comparators.get(i2).setAscending(false);
    }
    public int getComparatorCount() {
        return comparators.size();
    }
    public int compare(final T t, final T t2) {
        Assert.state(0 < this.comparators.size(), "No sort definitions have been added to this CompoundComparator to compare");
        for (InvertibleComparator<T> comparator : comparators) {
            final int compare = comparator.compare(t, t2);
            if (0 != compare) {
                return compare;
            }
        }
        return 0;
    }
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CompoundComparator) return comparators.equals((( CompoundComparator ) obj).comparators);
        return false;
    }
    public int hashCode() {
        return comparators.hashCode();
    }
    public String toString() {
        return "CompoundComparator: " + comparators;
    }
}

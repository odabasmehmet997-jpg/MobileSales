package io.reactivex.internal.util;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public final class VolatileSizeArrayList<T> extends AtomicInteger implements List<T>, RandomAccess {
    private static final long serialVersionUID = 3972397474470203923L;
    final ArrayList<T> list;
    public VolatileSizeArrayList() {
        list = new ArrayList<>();
    }
    public VolatileSizeArrayList(final int i2) {
        list = new ArrayList<>(i2);
    }
    public int size() {
        return this.get();
    }
    public boolean isEmpty() {
        return 0 == get();
    }
    public boolean contains(final Object obj) {
        return list.contains(obj);
    }
    public Iterator<T> iterator() {
        return list.iterator();
    }
    public Object[] toArray() {
        return list.toArray();
    }
    public <E> E[] toArray(final E[] eArr) {
        return list.toArray(eArr);
    }
    public boolean add(final T t) {
        final boolean add = list.add(t);
        this.lazySet(list.size());
        return add;
    }
    public boolean remove(final Object obj) {
        final boolean remove = list.remove(obj);
        this.lazySet(list.size());
        return remove;
    }
    public boolean containsAll(final Collection<?> collection) {
        return list.containsAll(collection);
    }
    public boolean addAll(final Collection<? extends T> collection) {
        final boolean addAll = list.addAll(collection);
        this.lazySet(list.size());
        return addAll;
    }
    public boolean addAll(final int i2, final Collection<? extends T> collection) {
        final boolean addAll = list.addAll(i2, collection);
        this.lazySet(list.size());
        return addAll;
    }
    public boolean removeAll(final Collection<?> collection) {
        final boolean removeAll = list.removeAll(collection);
        this.lazySet(list.size());
        return removeAll;
    }
    public boolean retainAll(final Collection<?> collection) {
        final boolean retainAll = list.retainAll(collection);
        this.lazySet(list.size());
        return retainAll;
    }
    public void clear() {
        list.clear();
        this.lazySet(0);
    }
    public T get(final int i2) {
        return list.get(i2);
    }
    public T set(final int i2, final T t) {
        return list.set(i2, t);
    }
    public void add(final int i2, final T t) {
        list.add(i2, t);
        this.lazySet(list.size());
    }
    public T remove(final int i2) {
        final T remove = list.remove(i2);
        this.lazySet(list.size());
        return remove;
    }
    public int indexOf(final Object obj) {
        return list.indexOf(obj);
    }
    public int lastIndexOf(final Object obj) {
        return list.lastIndexOf(obj);
    }
    public ListIterator<T> listIterator() {
        return list.listIterator();
    }
    public ListIterator<T> listIterator(final int i2) {
        return list.listIterator(i2);
    }
    public List<T> subList(final int i2, final int i3) {
        return list.subList(i2, i3);
    }
    public boolean equals(final Object obj) {
        if (obj instanceof VolatileSizeArrayList) {
            return list.equals(((VolatileSizeArrayList) obj).list);
        }
        return list.equals(obj);
    }
    public int hashCode() {
        return list.hashCode();
    }
    public String toString() {
        return list.toString();
    }
}

package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.*;

/*  INFO: loaded from: classes2.dex */
public final class ImmutableList<E> implements List<E>, RandomAccess {
    private final List<E> immutableList;

    @NonNull
    public static <E> ImmutableList<E> from(E... eArr) {
        return new ImmutableList<>(Arrays.asList(eArr));
    }

    @NonNull
    public static <E> ImmutableList<E> from(@NonNull List<E> list) {
        return new ImmutableList<>(list);
    }

    private ImmutableList(List<E> list) {
        this.immutableList = Collections.unmodifiableList(list);
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return this.immutableList.size();
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.immutableList.isEmpty();
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(@Nullable Object obj) {
        return this.immutableList.contains(obj);
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    @NonNull
    public Iterator<E> iterator() {
        return this.immutableList.iterator();
    }

    @Override // java.util.List, java.util.Collection
    @Nullable
    public Object[] toArray() {
        return this.immutableList.toArray();
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(@Nullable T[] tArr) {
        return (T[]) this.immutableList.toArray(tArr);
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(@NonNull E e2) {
        return this.immutableList.add(e2);
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(@Nullable Object obj) {
        return this.immutableList.remove(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(@NonNull Collection<?> collection) {
        return this.immutableList.containsAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(@NonNull Collection<? extends E> collection) {
        return this.immutableList.addAll(collection);
    }

    @Override // java.util.List
    public boolean addAll(int i2, @NonNull Collection<? extends E> collection) {
        return this.immutableList.addAll(i2, collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(@NonNull Collection<?> collection) {
        return this.immutableList.removeAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(@NonNull Collection<?> collection) {
        return this.immutableList.retainAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        this.immutableList.clear();
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(@Nullable Object obj) {
        return this.immutableList.equals(obj);
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        return this.immutableList.hashCode();
    }

    @Override // java.util.List
    @NonNull
    public E get(int i2) {
        return this.immutableList.get(i2);
    }

    @Override // java.util.List
    @NonNull
    public E set(int i2, @NonNull E e2) {
        return this.immutableList.set(i2, e2);
    }

    @Override // java.util.List
    public void add(int i2, @NonNull E e2) {
        this.immutableList.add(i2, e2);
    }

    @Override // java.util.List
    public E remove(int i2) {
        return this.immutableList.remove(i2);
    }

    @Override // java.util.List
    public int indexOf(@Nullable Object obj) {
        return this.immutableList.indexOf(obj);
    }

    @Override // java.util.List
    public int lastIndexOf(@Nullable Object obj) {
        return this.immutableList.lastIndexOf(obj);
    }

    @Override // java.util.List
    @NonNull
    public ListIterator<E> listIterator() {
        return this.immutableList.listIterator();
    }

    @Override // java.util.List
    @NonNull
    public ListIterator<E> listIterator(int i2) {
        return this.immutableList.listIterator(i2);
    }

    @Override // java.util.List
    @NonNull
    public List<E> subList(int i2, int i3) {
        return this.immutableList.subList(i2, i3);
    }
}

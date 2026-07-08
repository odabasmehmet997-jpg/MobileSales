package com.fasterxml.jackson.databind.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T>, Iterable<T> {

    private final T[] f803_a;
    private int _index;
    public Iterator<T> iterator() {
        return this;
    }

    public ArrayIterator(final T[] tArr) {
        f803_a = tArr;
    }

    public boolean hasNext() {
        return _index < f803_a.length;
    }

    public T next() {
        final int i2 = _index;
        final T[] tArr = f803_a;
        if (i2 >= tArr.length) {
            throw new NoSuchElementException();
        }
        _index = i2 + 1;
        return tArr[i2];
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}

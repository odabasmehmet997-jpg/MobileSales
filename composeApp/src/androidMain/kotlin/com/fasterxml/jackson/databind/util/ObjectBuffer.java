package com.fasterxml.jackson.databind.util;

import java.lang.reflect.Array;
import java.util.List;

public final class ObjectBuffer {
    private Object[] _freeBuffer;
    private LinkedNode<Object[]> _head;
    private int _size;
    private LinkedNode<Object[]> _tail;

    public Object[] resetAndStart() {
        this._reset();
        final Object[] objArr = _freeBuffer;
        if (null != objArr) {
            return objArr;
        }
        final Object[] objArr2 = new Object[12];
        _freeBuffer = objArr2;
        return objArr2;
    }

    public Object[] resetAndStart(final Object[] objArr, final int i2) {
        this._reset();
        final Object[] objArr2 = _freeBuffer;
        if (null == objArr2 || objArr2.length < i2) {
            _freeBuffer = new Object[Math.max(12, i2)];
        }
        System.arraycopy(objArr, 0, _freeBuffer, 0, i2);
        return _freeBuffer;
    }

    public Object[] appendCompletedChunk(final Object[] objArr) {
        final LinkedNode<Object[]> linkedNode = new LinkedNode<>(objArr, null);
        if (null == this._head) {
            _tail = linkedNode;
            _head = linkedNode;
        } else {
            _tail.linkNext(linkedNode);
            _tail = linkedNode;
        }
        int length = objArr.length;
        _size += length;
        if (16384 > length) {
            length += length;
        } else if (262144 > length) {
            length += length >> 2;
        }
        return new Object[length];
    }

    public Object[] completeAndClearBuffer(final Object[] objArr, final int i2) {
        final int i3 = _size + i2;
        final Object[] objArr2 = new Object[i3];
        this._copyTo(objArr2, i3, objArr, i2);
        this._reset();
        return objArr2;
    }

    public <T> T[] completeAndClearBuffer(final Object[] objArr, final int i2, final Class<T> cls) {
        final int i3 = _size + i2;
        final T[] tArr = (T[]) Array.newInstance(cls, i3);
        this._copyTo(tArr, i3, objArr, i2);
        this._reset();
        return tArr;
    }

    public void completeAndClearBuffer(final Object[] objArr, final int i2, final List<Object> list) {
        int i3;
        LinkedNode<Object[]> next = _head;
        while (true) {
            i3 = 0;
            if (null == next) {
                break;
            }
            final Object[] objArrValue = next.value();
            final int length = objArrValue.length;
            while (i3 < length) {
                list.add(objArrValue[i3]);
                i3++;
            }
            next = next.next();
        }
        while (i3 < i2) {
            list.add(objArr[i3]);
            i3++;
        }
        this._reset();
    }

    public int initialCapacity() {
        final Object[] objArr = _freeBuffer;
        if (null == objArr) {
            return 0;
        }
        return objArr.length;
    }

    public int bufferedSize() {
        return _size;
    }

    private void _reset() {
        final LinkedNode<Object[]> linkedNode = _tail;
        if (null != linkedNode) {
            _freeBuffer = linkedNode.value();
        }
        _tail = null;
        _head = null;
        _size = 0;
    }

    private void _copyTo(final Object obj, final int i2, final Object[] objArr, final int i3) {
        int i4 = 0;
        for (LinkedNode<Object[]> next = _head; null != next; next = next.next()) {
            final Object[] objArrValue = next.value();
            final int length = objArrValue.length;
            System.arraycopy(objArrValue, 0, obj, i4, length);
            i4 += length;
        }
        System.arraycopy(objArr, 0, obj, i4, i3);
        final int i5 = i4 + i3;
        if (i5 == i2) {
            return;
        }
        throw new IllegalStateException("Should have gotten " + i2 + " entries, got " + i5);
    }
}

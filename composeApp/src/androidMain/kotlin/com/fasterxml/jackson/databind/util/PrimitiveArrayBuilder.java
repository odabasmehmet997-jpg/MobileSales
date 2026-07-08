package com.fasterxml.jackson.databind.util;

public abstract class PrimitiveArrayBuilder<T> {
    protected Node<T> _bufferHead;
    protected Node<T> _bufferTail;
    protected int _bufferedEntryCount;
    protected T _freeBuffer;
    protected abstract T _constructArray(int i2);
    protected PrimitiveArrayBuilder() {
    }
    public int bufferedSize() {
        return _bufferedEntryCount;
    }
    public T resetAndStart() {
        this._reset();
        final T t = _freeBuffer;
        return null == t ? this._constructArray(12) : t;
    }
    public final T appendCompletedChunk(final T t, final int i2) {
        final Node<T> node = new Node<>(t, i2);
        if (null == this._bufferHead) {
            _bufferTail = node;
            _bufferHead = node;
        } else {
            _bufferTail.linkNext(node);
            _bufferTail = node;
        }
        _bufferedEntryCount += i2;
        return this._constructArray(16384 > i2 ? i2 + i2 : i2 + (i2 >> 2));
    }
    public T completeAndClearBuffer(final T t, final int i2) {
        final int i3 = _bufferedEntryCount + i2;
        final T t_constructArray = this._constructArray(i3);
        int iCopyData = 0;
        for (Node<T> next = _bufferHead; null != next; next = next.next()) {
            iCopyData = next.copyData(t_constructArray, iCopyData);
        }
        System.arraycopy(t, 0, t_constructArray, iCopyData, i2);
        final int i4 = iCopyData + i2;
        if (i4 == i3) {
            return t_constructArray;
        }
        throw new IllegalStateException("Should have gotten " + i3 + " entries, got " + i4);
    }
    protected void _reset() {
        final Node<T> node = _bufferTail;
        if (null != node) {
            _freeBuffer = node.getData();
        }
        _bufferTail = null;
        _bufferHead = null;
        _bufferedEntryCount = 0;
    }
    static final class Node<T> {
        final T _data;
        final int _dataLength;
        Node<T> _next;

        public Node(final T t, final int i2) {
            _data = t;
            _dataLength = i2;
        }

        public T getData() {
            return _data;
        }

        public int copyData(final T t, final int i2) {
            System.arraycopy(_data, 0, t, i2, _dataLength);
            return i2 + _dataLength;
        }

        public Node<T> next() {
            return _next;
        }

        public void linkNext(final Node<T> node) {
            if (null != this._next) {
                throw new IllegalStateException();
            }
            _next = node;
        }
    }
}

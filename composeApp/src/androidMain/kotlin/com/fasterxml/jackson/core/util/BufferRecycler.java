package com.fasterxml.jackson.core.util;

import java.util.concurrent.atomic.AtomicReferenceArray;

public class BufferRecycler {
    private static final int[] BYTE_BUFFER_LENGTHS = {8000, 8000, 2000, 2000};
    private static final int[] CHAR_BUFFER_LENGTHS = {4000, 4000, 200, 200};
    protected final AtomicReferenceArray<byte[]> _byteBuffers;
    protected final AtomicReferenceArray<char[]> _charBuffers;

    public BufferRecycler() {
        this(4, 4);
    }

    protected BufferRecycler(final int i2, final int i3) {
        _byteBuffers = new AtomicReferenceArray<>(i2);
        _charBuffers = new AtomicReferenceArray<>(i3);
    }

    public final byte[] allocByteBuffer(final int i2) {
        return this.allocByteBuffer(i2, 0);
    }

    public byte[] allocByteBuffer(final int i2, int i3) {
        final int iByteBufferLength = this.byteBufferLength(i2);
        if (i3 < iByteBufferLength) {
            i3 = iByteBufferLength;
        }
        final byte[] andSet = _byteBuffers.getAndSet(i2, null);
        return (null == andSet || andSet.length < i3) ? this.balloc(i3) : andSet;
    }

    public void releaseByteBuffer(final int i2, final byte[] bArr) {
        _byteBuffers.set(i2, bArr);
    }

    public final char[] allocCharBuffer(final int i2) {
        return this.allocCharBuffer(i2, 0);
    }

    public char[] allocCharBuffer(final int i2, int i3) {
        final int iCharBufferLength = this.charBufferLength(i2);
        if (i3 < iCharBufferLength) {
            i3 = iCharBufferLength;
        }
        final char[] andSet = _charBuffers.getAndSet(i2, null);
        return (null == andSet || andSet.length < i3) ? this.calloc(i3) : andSet;
    }

    public void releaseCharBuffer(final int i2, final char[] cArr) {
        _charBuffers.set(i2, cArr);
    }

    protected int byteBufferLength(final int i2) {
        return BufferRecycler.BYTE_BUFFER_LENGTHS[i2];
    }

    protected int charBufferLength(final int i2) {
        return BufferRecycler.CHAR_BUFFER_LENGTHS[i2];
    }

    protected byte[] balloc(final int i2) {
        return new byte[i2];
    }

    protected char[] calloc(final int i2) {
        return new char[i2];
    }
}

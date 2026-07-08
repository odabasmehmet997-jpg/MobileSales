package org.apache.harmony.awt.datatransfer;


public final class RawBitmap {
    public final int bMask;
    public final int bits;
    public final Object buffer;
    public final int gMask;
    public final int height;
    public final int rMask;
    public final int stride;
    public final int width;

    public RawBitmap(int i2, int i3, int i4, int i5, int i6, int i7, int i8, Object obj) {
        this.width = i2;
        this.height = i3;
        this.stride = i4;
        this.bits = i5;
        this.rMask = i6;
        this.gMask = i7;
        this.bMask = i8;
        this.buffer = obj;
    }

    public RawBitmap(int[] iArr, Object obj) {
        this.width = iArr[0];
        this.height = iArr[1];
        this.stride = iArr[2];
        this.bits = iArr[3];
        this.rMask = iArr[4];
        this.gMask = iArr[5];
        this.bMask = iArr[6];
        this.buffer = obj;
    }

    public int[] getHeader() {
        return new int[]{this.width, this.height, this.stride, this.bits, this.rMask, this.gMask, this.bMask};
    }
}

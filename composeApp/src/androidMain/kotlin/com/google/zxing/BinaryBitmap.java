package com.google.zxing;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;

public final class BinaryBitmap {
    private final Binarizer binarizer;
    private BitMatrix matrix;
    public BinaryBitmap(final Binarizer binarizer2) {
        if (null != binarizer2) {
            binarizer = binarizer2;
            return;
        }
        throw new IllegalArgumentException("Binarizer must be non-null.");
    }
    public int getWidth() {
        return binarizer.getWidth();
    }
    public int getHeight() {
        return binarizer.getHeight();
    }
    public BitArray getBlackRow(final int i2, final BitArray bitArray) throws NotFoundException {
        return binarizer.getBlackRow(i2, bitArray);
    }
    public BitMatrix getBlackMatrix() throws NotFoundException {
        if (null == this.matrix) {
            matrix = binarizer.getBlackMatrix();
        }
        return matrix;
    }
    public BinaryBitmap crop(final int i2, final int i3, final int i4, final int i5) {
        return new BinaryBitmap(binarizer.createBinarizer(binarizer.getLuminanceSource().crop(i2, i3, i4, i5)));
    }
    public boolean isRotateSupported() {
        return binarizer.getLuminanceSource().isRotateSupported();
    }
    public BinaryBitmap rotateCounterClockwise() {
        return new BinaryBitmap(binarizer.createBinarizer(binarizer.getLuminanceSource().rotateCounterClockwise()));
    }
    public String toString() {
        try {
            return this.getBlackMatrix().toString();
        } catch (final NotFoundException unused) {
            return "";
        }
    }
}

package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;

public class GlobalHistogramBinarizer extends Binarizer {
    private static final byte[] EMPTY = new byte[0];
    private final int[] buckets = new int[32];
    private byte[] luminances = GlobalHistogramBinarizer.EMPTY;
    public GlobalHistogramBinarizer(final LuminanceSource luminanceSource) {
        super(luminanceSource);
    }
    public BitArray getBlackRow(final int i2, BitArray bitArray) throws NotFoundException {
        final LuminanceSource luminanceSource = this.getLuminanceSource();
        final int width = luminanceSource.getWidth();
        if (null == bitArray || bitArray.getSize() < width) {
            bitArray = new BitArray(width);
        } else {
            bitArray.clear();
        }
        this.initArrays(width);
        final byte[] row = luminanceSource.getRow(i2, luminances);
        final int[] iArr = buckets;
        for (int i3 = 0; i3 < width; i3++) {
            final int i4 = (row[i3] & 255) >> 3;
            iArr[i4] = iArr[i4] + 1;
        }
        final int estimateBlackPoint = GlobalHistogramBinarizer.estimateBlackPoint(iArr);
        if (3 > width) {
            for (int i5 = 0; i5 < width; i5++) {
                if ((row[i5] & 255) < estimateBlackPoint) {
                    bitArray.set(i5);
                }
            }
        } else {
            int i6 = 1;
            final byte b2 = (byte) (row[1] & 255);
            byte b3 = (byte) (row[0] & 255);
            byte b4 = b2;
            while (i6 < width - 1) {
                final int i7 = i6 + 1;
                final byte b5 = (byte) (row[i7] & 255);
                if ((((b4 << 2) - b3) - b5) / 2 < estimateBlackPoint) {
                    bitArray.set(i6);
                }
                b3 = b4;
                i6 = i7;
                b4 = b5;
            }
        }
        return bitArray;
    }
    public BitMatrix getBlackMatrix() throws NotFoundException {
        final LuminanceSource luminanceSource = this.getLuminanceSource();
        final int width = luminanceSource.getWidth();
        final int height = luminanceSource.getHeight();
        final BitMatrix bitMatrix = new BitMatrix(width, height);
        this.initArrays(width);
        final int[] iArr = buckets;
        for (int i2 = 1; 5 > i2; i2++) {
            final byte[] row = luminanceSource.getRow((height * i2) / 5, luminances);
            final int i3 = (width << 2) / 5;
            for (int i4 = width / 5; i4 < i3; i4++) {
                final int i5 = (row[i4] & 255) >> 3;
                iArr[i5] = iArr[i5] + 1;
            }
        }
        final int estimateBlackPoint = GlobalHistogramBinarizer.estimateBlackPoint(iArr);
        final byte[] matrix = luminanceSource.getMatrix();
        for (int i6 = 0; i6 < height; i6++) {
            final int i7 = i6 * width;
            for (int i8 = 0; i8 < width; i8++) {
                if ((matrix[i7 + i8] & 255) < estimateBlackPoint) {
                    bitMatrix.set(i8, i6);
                }
            }
        }
        return bitMatrix;
    }
    public Binarizer createBinarizer(final LuminanceSource luminanceSource) {
        return new GlobalHistogramBinarizer(luminanceSource);
    }
    private void initArrays(final int i2) {
        if (luminances.length < i2) {
            luminances = new byte[i2];
        }
        for (int i3 = 0; 32 > i3; i3++) {
            buckets[i3] = 0;
        }
    }
    private static int estimateBlackPoint(final int[] iArr) throws NotFoundException {
        final int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            final int i6 = iArr[i5];
            if (i6 > i2) {
                i4 = i5;
                i2 = i6;
            }
            if (i6 > i3) {
                i3 = i6;
            }
        }
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < length; i9++) {
            final int i10 = i9 - i4;
            final int i11 = iArr[i9] * i10 * i10;
            if (i11 > i8) {
                i7 = i9;
                i8 = i11;
            }
        }
        if (i4 <= i7) {
            final int i12 = i4;
            i4 = i7;
            i7 = i12;
        }
        if (i4 - i7 > length / 16) {
            int i13 = i4 - 1;
            int i14 = -1;
            int i15 = i13;
            while (i13 > i7) {
                final int i16 = i13 - i7;
                final int i17 = i16 * i16 * (i4 - i13) * (i3 - iArr[i13]);
                if (i17 > i14) {
                    i15 = i13;
                    i14 = i17;
                }
                i13--;
            }
            return i15 << 3;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}

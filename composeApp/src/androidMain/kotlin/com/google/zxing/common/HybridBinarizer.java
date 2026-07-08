package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;

public final class HybridBinarizer extends GlobalHistogramBinarizer {
    private BitMatrix matrix;
    private static int cap(final int i2, final int i3, final int i4) {
        return i2 < i3 ? i3 : i2 > i4 ? i4 : i2;
    }
    public HybridBinarizer(final LuminanceSource luminanceSource) {
        super(luminanceSource);
    }
    public BitMatrix getBlackMatrix() throws NotFoundException {
        final BitMatrix bitMatrix = matrix;
        if (null != bitMatrix) {
            return bitMatrix;
        }
        final LuminanceSource luminanceSource = this.getLuminanceSource();
        final int width = luminanceSource.getWidth();
        final int height = luminanceSource.getHeight();
        if (40 > width || 40 > height) {
            matrix = super.getBlackMatrix();
        } else {
            final byte[] matrix2 = luminanceSource.getMatrix();
            int i2 = width >> 3;
            if (0 != (width & 7)) {
                i2++;
            }
            final int i3 = i2;
            int i4 = height >> 3;
            if (0 != (height & 7)) {
                i4++;
            }
            final int i5 = i4;
            final int[][] calculateBlackPoints = HybridBinarizer.calculateBlackPoints(matrix2, i3, i5, width, height);
            final BitMatrix bitMatrix2 = new BitMatrix(width, height);
            HybridBinarizer.calculateThresholdForBlock(matrix2, i3, i5, width, height, calculateBlackPoints, bitMatrix2);
            matrix = bitMatrix2;
        }
        return matrix;
    }
    public Binarizer createBinarizer(final LuminanceSource luminanceSource) {
        return new HybridBinarizer(luminanceSource);
    }
    private static void calculateThresholdForBlock(final byte[] bArr, final int i2, final int i3, final int i4, final int i5, final int[][] iArr, final BitMatrix bitMatrix) {
        final int i6 = i2;
        final int i7 = i3;
        for (int i8 = 0; i8 < i7; i8++) {
            int i9 = i8 << 3;
            final int i10 = i5 - 8;
            if (i9 > i10) {
                i9 = i10;
            }
            for (int i11 = 0; i11 < i6; i11++) {
                final int i12 = i11 << 3;
                int i13 = i4 - 8;
                if (i12 <= i13) {
                    i13 = i12;
                }
                final int cap = HybridBinarizer.cap(i11, 2, i6 - 3);
                final int cap2 = HybridBinarizer.cap(i8, 2, i7 - 3);
                int i14 = 0;
                for (int i15 = -2; 2 >= i15; i15++) {
                    final int[] iArr2 = iArr[cap2 + i15];
                    i14 += iArr2[cap - 2] + iArr2[cap - 1] + iArr2[cap] + iArr2[cap + 1] + iArr2[cap + 2];
                }
                HybridBinarizer.thresholdBlock(bArr, i13, i9, i14 / 25, i4, bitMatrix);
            }
        }
    }
    private static void thresholdBlock(final byte[] bArr, final int i2, final int i3, final int i4, final int i5, final BitMatrix bitMatrix) {
        int i6 = (i3 * i5) + i2;
        int i7 = 0;
        while (8 > i7) {
            for (int i8 = 0; 8 > i8; i8++) {
                if ((bArr[i6 + i8] & 255) <= i4) {
                    bitMatrix.set(i2 + i8, i3 + i7);
                }
            }
            i7++;
            i6 += i5;
        }
    }
    private static int[][] calculateBlackPoints(final byte[] r17, final int r18, final int r19, final int r20, final int r21) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.HybridBinarizer.calculateBlackPoints(byte[], int, int, int, int):int[][]");
    }
}

package com.google.zxing.pdf417.decoder;

import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.pdf417.PDF417Common;
import java.lang.reflect.Array;

enum PDF417CodewordDecoder {
    ;
    private static final float[][] RATIOS_TABLE;

    static {
        int i2;
        final int length = PDF417Common.SYMBOL_TABLE.length;
        final int[] iArr = new int[2];
        iArr[1] = 8;
        iArr[0] = length;
        RATIOS_TABLE = (float[][]) Array.newInstance(Float.TYPE, iArr);
        int i3 = 0;
        while (true) {
            final int[] iArr2 = PDF417Common.SYMBOL_TABLE;
            if (i3 < iArr2.length) {
                int i4 = iArr2[i3];
                int i5 = i4 & 1;
                int i6 = 0;
                while (8 > i6) {
                    float f2 = 0.0f;
                    while (true) {
                        i2 = i4 & 1;
                        if (i2 != i5) {
                            break;
                        }
                        f2 += 1.0f;
                        i4 >>= 1;
                    }
                    PDF417CodewordDecoder.RATIOS_TABLE[i3][7 - i6] = f2 / 17.0f;
                    i6++;
                    i5 = i2;
                }
                i3++;
            } else {
                return;
            }
        }
    }

    static int getDecodedValue(final int[] iArr) {
        final int decodedCodewordValue = PDF417CodewordDecoder.getDecodedCodewordValue(PDF417CodewordDecoder.sampleBitCounts(iArr));
        if (-1 != decodedCodewordValue) {
            return decodedCodewordValue;
        }
        return PDF417CodewordDecoder.getClosestDecodedValue(iArr);
    }

    private static int[] sampleBitCounts(final int[] iArr) {
        final float sum = MathUtils.sum(iArr);
        final int[] iArr2 = new int[8];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; 17 > i4; i4++) {
            final float f2 = (sum / 34.0f) + ((i4 * sum) / 17.0f);
            final int i5 = iArr[i3];
            if ((i2 + i5) <= f2) {
                i2 += i5;
                i3++;
            }
            iArr2[i3] = iArr2[i3] + 1;
        }
        return iArr2;
    }

    private static int getDecodedCodewordValue(final int[] iArr) {
        final int bitValue = PDF417CodewordDecoder.getBitValue(iArr);
        if (-1 == PDF417Common.getCodeword(bitValue)) {
            return -1;
        }
        return bitValue;
    }

    private static int getBitValue(final int[] iArr) {
        long j2 = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            for (int i3 = 0; i3 < iArr[i2]; i3++) {
                int i4 = 1;
                final long j3 = j2 << 1;
                if (0 != i2 % 2) {
                    i4 = 0;
                }
                j2 = j3 | i4;
            }
        }
        return (int) j2;
    }

    private static int getClosestDecodedValue(final int[] iArr) {
        final int sum = MathUtils.sum(iArr);
        final float[] fArr = new float[8];
        for (int i2 = 0; 8 > i2; i2++) {
            fArr[i2] = ((float) iArr[i2]) / sum;
        }
        float f2 = Float.MAX_VALUE;
        int i3 = -1;
        int i4 = 0;
        while (true) {
            final float[][] fArr2 = PDF417CodewordDecoder.RATIOS_TABLE;
            if (i4 >= fArr2.length) {
                return i3;
            }
            final float[] fArr3 = fArr2[i4];
            float f3 = 0.0f;
            for (int i5 = 0; 8 > i5; i5++) {
                final float f4 = fArr3[i5] - fArr[i5];
                f3 += f4 * f4;
                if (f3 >= f2) {
                    break;
                }
            }
            if (f3 < f2) {
                i3 = PDF417Common.SYMBOL_TABLE[i4];
                f2 = f3;
            }
            i4++;
        }
    }
}

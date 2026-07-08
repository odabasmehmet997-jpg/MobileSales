package com.google.zxing.oned.rss;

import com.google.zxing.NotFoundException;
import com.google.zxing.oned.OneDReader;

public abstract class AbstractRSSReader extends OneDReader {
    private final int[] dataCharacterCounters;
    private final int[] decodeFinderCounters = new int[4];
    private final int[] evenCounts;
    private final float[] evenRoundingErrors;
    private final int[] oddCounts;
    private final float[] oddRoundingErrors;

    protected AbstractRSSReader() {
        final int[] iArr = new int[8];
        dataCharacterCounters = iArr;
        oddRoundingErrors = new float[4];
        evenRoundingErrors = new float[4];
        oddCounts = new int[(iArr.length / 2)];
        evenCounts = new int[(iArr.length / 2)];
    }

    
    public final int[] getDecodeFinderCounters() {
        return decodeFinderCounters;
    }

    
    public final int[] getDataCharacterCounters() {
        return dataCharacterCounters;
    }

    
    public final float[] getOddRoundingErrors() {
        return oddRoundingErrors;
    }

    
    public final float[] getEvenRoundingErrors() {
        return evenRoundingErrors;
    }

    
    public final int[] getOddCounts() {
        return oddCounts;
    }

    
    public final int[] getEvenCounts() {
        return evenCounts;
    }

    protected static int parseFinderValue(final int[] iArr, final int[][] iArr2) throws NotFoundException {
        for (int i2 = 0; i2 < iArr2.length; i2++) {
            if (0.2f > OneDReader.patternMatchVariance(iArr, iArr2[i2], 0.45f)) {
                return i2;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static void increment(final int[] iArr, final float[] fArr) {
        int i2 = 0;
        float f2 = fArr[0];
        for (int i3 = 1; i3 < iArr.length; i3++) {
            final float f3 = fArr[i3];
            if (f3 > f2) {
                i2 = i3;
                f2 = f3;
            }
        }
        iArr[i2] = iArr[i2] + 1;
    }

    protected static void decrement(final int[] iArr, final float[] fArr) {
        int i2 = 0;
        float f2 = fArr[0];
        for (int i3 = 1; i3 < iArr.length; i3++) {
            final float f3 = fArr[i3];
            if (f3 < f2) {
                i2 = i3;
                f2 = f3;
            }
        }
        iArr[i2] = iArr[i2] - 1;
    }

    protected static boolean isFinderPattern(final int[] iArr) {
        final int i2 = iArr[0] + iArr[1];
        final float f2 = ((float) i2) / ((iArr[2] + i2) + iArr[3]);
        if (0.7916667f <= f2 && 0.89285713f >= f2) {
            int i3 = Integer.MAX_VALUE;
            int i4 = Integer.MIN_VALUE;
            for (final int i5 : iArr) {
                if (i5 > i4) {
                    i4 = i5;
                }
                if (i5 < i3) {
                    i3 = i5;
                }
            }
            return i4 < i3 * 10;
        }
        return false;
    }
}

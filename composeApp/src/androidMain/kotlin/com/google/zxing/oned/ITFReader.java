package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;

import java.util.Map;

public final class ITFReader extends OneDReader {
    private static final int[] DEFAULT_ALLOWED_LENGTHS = {6, 8, 10, 12, 14};
    private static final int[] END_PATTERN_REVERSED = {1, 1, 3};
    static final int[][] PATTERNS = {new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};
    private static final int[] START_PATTERN = {1, 1, 1, 1};
    private int narrowLineWidth = -1;

    public Result decodeRow(final int i2, final BitArray bitArray, final Map<DecodeHintType, ?> map) throws FormatException, NotFoundException {
        boolean z;
        final int[] decodeStart = this.decodeStart(bitArray);
        final int[] decodeEnd = this.decodeEnd(bitArray);
        final StringBuilder sb = new StringBuilder(20);
        ITFReader.decodeMiddle(bitArray, decodeStart[1], decodeEnd[0], sb);
        final String sb2 = sb.toString();
        int[] iArr = null != map ? (int[]) map.get(DecodeHintType.ALLOWED_LENGTHS) : null;
        if (null == iArr) {
            iArr = ITFReader.DEFAULT_ALLOWED_LENGTHS;
        }
        final int length = sb2.length();
        final int length2 = iArr.length;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= length2) {
                z = false;
                break;
            }
            final int i5 = iArr[i3];
            if (length == i5) {
                z = true;
                break;
            }
            if (i5 > i4) {
                i4 = i5;
            }
            i3++;
        }
        if (!z && length > i4) {
            z = true;
        }
        if (z) {
            final float f2 = i2;
            return new Result(sb2, null, new ResultPoint[]{new ResultPoint(decodeStart[1], f2), new ResultPoint(decodeEnd[0], f2)}, BarcodeFormat.ITF);
        }
        throw FormatException.getFormatInstance();
    }

    private static void decodeMiddle(final BitArray bitArray, int i2, final int i3, final StringBuilder sb) throws NotFoundException {
        final int[] iArr = new int[10];
        final int[] iArr2 = new int[5];
        final int[] iArr3 = new int[5];
        while (i2 < i3) {
            recordPattern(bitArray, i2, iArr);
            for (int i4 = 0; 5 > i4; i4++) {
                final int i5 = i4 * 2;
                iArr2[i4] = iArr[i5];
                iArr3[i4] = iArr[i5 + 1];
            }
            sb.append((char) (ITFReader.decodeDigit(iArr2) + 48));
            sb.append((char) (ITFReader.decodeDigit(iArr3) + 48));
            for (int i6 = 0; 10 > i6; i6++) {
                i2 += iArr[i6];
            }
        }
    }

    private int[] decodeStart(final BitArray bitArray) throws NotFoundException {
        final int[] findGuardPattern = ITFReader.findGuardPattern(bitArray, ITFReader.skipWhiteSpace(bitArray), ITFReader.START_PATTERN);
        final int i2 = findGuardPattern[1];
        final int i3 = findGuardPattern[0];
        narrowLineWidth = (i2 - i3) / 4;
        this.validateQuietZone(bitArray, i3);
        return findGuardPattern;
    }

    private void validateQuietZone(final BitArray bitArray, final int i2) throws NotFoundException {
        int i3 = narrowLineWidth * 10;
        if (i3 >= i2) {
            i3 = i2;
        }
        int i4 = i2 - 1;
        while (0 < i3 && 0 <= i4 && !bitArray.get(i4)) {
            i3--;
            i4--;
        }
        if (0 != i3) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private static int skipWhiteSpace(final BitArray bitArray) throws NotFoundException {
        final int size = bitArray.getSize();
        final int nextSet = bitArray.getNextSet(0);
        if (nextSet != size) {
            return nextSet;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private int[] decodeEnd(final BitArray bitArray) throws NotFoundException {
        bitArray.reverse();
        try {
            final int[] findGuardPattern = ITFReader.findGuardPattern(bitArray, ITFReader.skipWhiteSpace(bitArray), ITFReader.END_PATTERN_REVERSED);
            this.validateQuietZone(bitArray, findGuardPattern[0]);
            final int i2 = findGuardPattern[0];
            findGuardPattern[0] = bitArray.getSize() - findGuardPattern[1];
            findGuardPattern[1] = bitArray.getSize() - i2;
            return findGuardPattern;
        } finally {
            bitArray.reverse();
        }
    }

    private static int[] findGuardPattern(final BitArray bitArray, int i2, final int[] iArr) throws NotFoundException {
        final int length = iArr.length;
        final int[] iArr2 = new int[length];
        final int size = bitArray.getSize();
        int i3 = i2;
        boolean z = false;
        int i4 = 0;
        while (i2 < size) {
            if (bitArray.get(i2) ^ z) {
                iArr2[i4] = iArr2[i4] + 1;
            } else {
                final int i5 = length - 1;
                if (i4 != i5) {
                    i4++;
                } else if (0.38f > OneDReader.patternMatchVariance(iArr2, iArr, 0.78f)) {
                    return new int[]{i3, i2};
                } else {
                    i3 += iArr2[0] + iArr2[1];
                    final int i6 = length - 2;
                    System.arraycopy(iArr2, 2, iArr2, 0, i6);
                    iArr2[i6] = 0;
                    iArr2[i5] = 0;
                    i4--;
                }
                iArr2[i4] = 1;
                z = !z;
            }
            i2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int decodeDigit(final int[] iArr) throws NotFoundException {
        final int length = ITFReader.PATTERNS.length;
        float f2 = 0.38f;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            final float patternMatchVariance = patternMatchVariance(iArr, ITFReader.PATTERNS[i3], 0.78f);
            if (patternMatchVariance < f2) {
                i2 = i3;
                f2 = patternMatchVariance;
            }
        }
        if (0 <= i2) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}

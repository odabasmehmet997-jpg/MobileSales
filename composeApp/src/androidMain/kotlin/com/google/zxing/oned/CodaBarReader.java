package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;

import java.util.Arrays;
import java.util.Map;

public final class CodaBarReader extends OneDReader {
    static final char[] ALPHABET = "0123456789-:/.+ABCD".toCharArray();
    static final int[] CHARACTER_ENCODINGS = {3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};
    private static final char[] STARTEND_ENCODING = {'A', 'B', 'C', 'D'};
    private int counterLength;
    private int[] counters = new int[80];
    private final StringBuilder decodeRowResult = new StringBuilder(20);

    public Result decodeRow(final int i2, final BitArray bitArray, final Map<DecodeHintType, ?> map) throws NotFoundException {
        int i3;
        Arrays.fill(counters, 0);
        this.setCounters(bitArray);
        int findStartPattern = this.findStartPattern();
        decodeRowResult.setLength(0);
        int i4 = findStartPattern;
        while (true) {
            final int narrowWidePattern = this.toNarrowWidePattern(i4);
            if (-1 != narrowWidePattern) {
                decodeRowResult.append((char) narrowWidePattern);
                i3 = i4 + 8;
                if ((1 >= this.decodeRowResult.length() || !CodaBarReader.arrayContains(CodaBarReader.STARTEND_ENCODING, CodaBarReader.ALPHABET[narrowWidePattern])) && i3 < counterLength) {
                    i4 = i3;
                }
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        final int i5 = i4 + 7;
        final int i6 = counters[i5];
        int i7 = 0;
        for (int i8 = -8; -1 > i8; i8++) {
            i7 += counters[i3 + i8];
        }
        if (i3 >= counterLength || i6 >= i7 / 2) {
            this.validatePattern(findStartPattern);
            for (int i9 = 0; i9 < decodeRowResult.length(); i9++) {
                final StringBuilder sb = decodeRowResult;
                sb.setCharAt(i9, CodaBarReader.ALPHABET[sb.charAt(i9)]);
            }
            final char charAt = decodeRowResult.charAt(0);
            final char[] cArr = CodaBarReader.STARTEND_ENCODING;
            if (CodaBarReader.arrayContains(cArr, charAt)) {
                final StringBuilder sb2 = decodeRowResult;
                if (!CodaBarReader.arrayContains(cArr, sb2.charAt(sb2.length() - 1))) {
                    throw NotFoundException.getNotFoundInstance();
                } else if (3 < this.decodeRowResult.length()) {
                    if (null == map || !map.containsKey(DecodeHintType.RETURN_CODABAR_START_END)) {
                        final StringBuilder sb3 = decodeRowResult;
                        sb3.deleteCharAt(sb3.length() - 1);
                        decodeRowResult.deleteCharAt(0);
                    }
                    int i10 = 0;
                    for (int i11 = 0; i11 < findStartPattern; i11++) {
                        i10 += counters[i11];
                    }
                    final float f2 = i10;
                    while (findStartPattern < i5) {
                        i10 += counters[findStartPattern];
                        findStartPattern++;
                    }
                    final float f3 = i2;
                    return new Result(decodeRowResult.toString(), null, new ResultPoint[]{new ResultPoint(f2, f3), new ResultPoint(i10, f3)}, BarcodeFormat.CODABAR);
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        } else {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private void validatePattern(final int i2) throws NotFoundException {
        final int[] iArr = {0, 0, 0, 0};
        final int[] iArr2 = {0, 0, 0, 0};
        final int length = decodeRowResult.length() - 1;
        int i3 = i2;
        int i4 = 0;
        while (true) {
            int i5 = CodaBarReader.CHARACTER_ENCODINGS[decodeRowResult.charAt(i4)];
            for (int i6 = 6; 0 <= i6; i6--) {
                final int i7 = (i6 & 1) + ((i5 & 1) << 1);
                iArr[i7] = iArr[i7] + counters[i3 + i6];
                iArr2[i7] = iArr2[i7] + 1;
                i5 >>= 1;
            }
            if (i4 >= length) {
                break;
            }
            i3 += 8;
            i4++;
        }
        final float[] fArr = new float[4];
        final float[] fArr2 = new float[4];
        for (int i8 = 0; 2 > i8; i8++) {
            fArr2[i8] = 0.0f;
            final int i9 = i8 + 2;
            final float f2 = ((float) iArr[i8]) / iArr2[i8];
            final int i10 = iArr[i9];
            final int i11 = iArr2[i9];
            final float f3 = (f2 + (((float) i10) / i11)) / 2.0f;
            fArr2[i9] = f3;
            fArr[i8] = f3;
            fArr[i9] = ((i10 * 2.0f) + 1.5f) / i11;
        }
        int i12 = i2;
        int i13 = 0;
        while (true) {
            int i14 = CodaBarReader.CHARACTER_ENCODINGS[decodeRowResult.charAt(i13)];
            int i15 = 6;
            while (0 <= i15) {
                final int i16 = (i15 & 1) + ((i14 & 1) << 1);
                final float f4 = counters[i12 + i15];
                if (f4 >= fArr2[i16] && f4 <= fArr[i16]) {
                    i14 >>= 1;
                    i15--;
                }
            }
            if (i13 < length) {
                i12 += 8;
                i13++;
            } else {
                return;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void setCounters(final BitArray bitArray) throws NotFoundException {
        int i2 = 0;
        counterLength = 0;
        int nextUnset = bitArray.getNextUnset(0);
        final int size = bitArray.getSize();
        if (nextUnset < size) {
            boolean z = true;
            while (nextUnset < size) {
                if (bitArray.get(nextUnset) ^ z) {
                    i2++;
                } else {
                    this.counterAppend(i2);
                    z = !z;
                    i2 = 1;
                }
                nextUnset++;
            }
            this.counterAppend(i2);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void counterAppend(final int i2) {
        final int[] iArr = counters;
        final int i3 = counterLength;
        iArr[i3] = i2;
        final int i4 = i3 + 1;
        counterLength = i4;
        if (i4 >= iArr.length) {
            final int[] iArr2 = new int[(i4 << 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i4);
            counters = iArr2;
        }
    }

    private int findStartPattern() throws NotFoundException {
        for (int i2 = 1; i2 < counterLength; i2 += 2) {
            final int narrowWidePattern = this.toNarrowWidePattern(i2);
            if (-1 != narrowWidePattern && CodaBarReader.arrayContains(CodaBarReader.STARTEND_ENCODING, CodaBarReader.ALPHABET[narrowWidePattern])) {
                int i3 = 0;
                for (int i4 = i2; i4 < i2 + 7; i4++) {
                    i3 += counters[i4];
                }
                if (1 == i2 || counters[i2 - 1] >= i3 / 2) {
                    return i2;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    static boolean arrayContains(final char[] cArr, final char c2) {
        if (null != cArr) {
            for (final char c3 : cArr) {
                if (c3 == c2) {
                    return true;
                }
            }
        }
        return false;
    }

    private int toNarrowWidePattern(final int i2) {
        final int i3 = i2 + 7;
        if (i3 >= counterLength) {
            return -1;
        }
        final int[] iArr = counters;
        int i4 = Integer.MAX_VALUE;
        int i5 = 0;
        int i6 = Integer.MAX_VALUE;
        int i7 = 0;
        for (int i8 = i2; i8 < i3; i8 += 2) {
            final int i9 = iArr[i8];
            if (i9 < i6) {
                i6 = i9;
            }
            if (i9 > i7) {
                i7 = i9;
            }
        }
        final int i10 = (i6 + i7) / 2;
        int i11 = 0;
        for (int i12 = i2 + 1; i12 < i3; i12 += 2) {
            final int i13 = iArr[i12];
            if (i13 < i4) {
                i4 = i13;
            }
            if (i13 > i11) {
                i11 = i13;
            }
        }
        final int i14 = (i4 + i11) / 2;
        int i15 = 128;
        int i16 = 0;
        for (int i17 = 0; 7 > i17; i17++) {
            i15 >>= 1;
            if (iArr[i2 + i17] > (0 == (i17 & 1) ? i10 : i14)) {
                i16 |= i15;
            }
        }
        while (true) {
            final int[] iArr2 = CodaBarReader.CHARACTER_ENCODINGS;
            if (i5 >= iArr2.length) {
                return -1;
            }
            if (iArr2[i5] == i16) {
                return i5;
            }
            i5++;
        }
    }
}

package com.google.zxing.oned;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.zxing.*;
import com.google.zxing.common.BitArray;

import java.util.Arrays;
import java.util.Map;

public final class Code93Reader extends OneDReader {
    private static final char[] ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. /+%abcd*".toCharArray();
    private static final int ASTERISK_ENCODING;
    static final int[] CHARACTER_ENCODINGS;
    private final int[] counters = new int[6];
    private final StringBuilder decodeRowResult = new StringBuilder(20);

    static {
        final int[] iArr = {276, 328, 324, 322, 296, 292, 290, 336, 274, 266, TypedValues.CycleType.TYPE_WAVE_OFFSET, TypedValues.CycleType.TYPE_EASING, 418, 404, TypedValues.CycleType.TYPE_VISIBILITY, 394, 360, 356, 354, 308, 282, 344, 332, 326, MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION, 278, 436, 434, 428, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, 406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350};
        CHARACTER_ENCODINGS = iArr;
        ASTERISK_ENCODING = iArr[47];
    }

    public Result decodeRow(final int i2, final BitArray bitArray, final Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        final int[] findAsteriskPattern = this.findAsteriskPattern(bitArray);
        int nextSet = bitArray.getNextSet(findAsteriskPattern[1]);
        final int size = bitArray.getSize();
        final int[] iArr = counters;
        Arrays.fill(iArr, 0);
        final StringBuilder sb = decodeRowResult;
        sb.setLength(0);
        while (true) {
            recordPattern(bitArray, nextSet, iArr);
            final int pattern = Code93Reader.toPattern(iArr);
            if (0 <= pattern) {
                final char patternToChar = Code93Reader.patternToChar(pattern);
                sb.append(patternToChar);
                int i3 = nextSet;
                for (final int i4 : iArr) {
                    i3 += i4;
                }
                final int nextSet2 = bitArray.getNextSet(i3);
                if ('*' == patternToChar) {
                    sb.deleteCharAt(sb.length() - 1);
                    int i5 = 0;
                    for (final int i6 : iArr) {
                        i5 += i6;
                    }
                    if (nextSet2 == size || !bitArray.get(nextSet2)) {
                        throw NotFoundException.getNotFoundInstance();
                    } else if (2 <= sb.length()) {
                        Code93Reader.checkChecksums(sb);
                        sb.setLength(sb.length() - 2);
                        final float f2 = i2;
                        return new Result(Code93Reader.decodeExtended(sb), null, new ResultPoint[]{new ResultPoint((findAsteriskPattern[1] + findAsteriskPattern[0]) / 2.0f, f2), new ResultPoint(nextSet + (i5 / 2.0f), f2)}, BarcodeFormat.CODE_93);
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                } else {
                    nextSet = nextSet2;
                }
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    private int[] findAsteriskPattern(final BitArray bitArray) throws NotFoundException {
        final int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        Arrays.fill(counters, 0);
        final int[] iArr = counters;
        final int length = iArr.length;
        boolean z = false;
        int i2 = 0;
        int i3 = nextSet;
        while (nextSet < size) {
            if (bitArray.get(nextSet) ^ z) {
                iArr[i2] = iArr[i2] + 1;
            } else {
                final int i4 = length - 1;
                if (i2 != i4) {
                    i2++;
                } else if (Code93Reader.toPattern(iArr) == Code93Reader.ASTERISK_ENCODING) {
                    return new int[]{i3, nextSet};
                } else {
                    i3 += iArr[0] + iArr[1];
                    final int i5 = length - 2;
                    System.arraycopy(iArr, 2, iArr, 0, i5);
                    iArr[i5] = 0;
                    iArr[i4] = 0;
                    i2--;
                }
                iArr[i2] = 1;
                z = !z;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int toPattern(final int[] iArr) {
        int i2 = 0;
        for (final int i3 : iArr) {
            i2 += i3;
        }
        final int length = iArr.length;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            final int round = Math.round((iArr[i5] * 9.0f) / i2);
            if (0 >= round || 4 < round) {
                return -1;
            }
            if (0 == (i5 & 1)) {
                for (int i6 = 0; i6 < round; i6++) {
                    i4 = (i4 << 1) | 1;
                }
            } else {
                i4 <<= round;
            }
        }
        return i4;
    }

    private static char patternToChar(final int i2) throws NotFoundException {
        int i3 = 0;
        while (true) {
            final int[] iArr = Code93Reader.CHARACTER_ENCODINGS;
            if (i3 >= iArr.length) {
                throw NotFoundException.getNotFoundInstance();
            } else if (iArr[i3] == i2) {
                return Code93Reader.ALPHABET[i3];
            } else {
                i3++;
            }
        }
    }

    private static java.lang.String decodeExtended(final java.lang.CharSequence r9) throws com.google.zxing.FormatException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code93Reader.decodeExtended(java.lang.CharSequence):java.lang.String");
    }

    private static void checkChecksums(final CharSequence charSequence) throws ChecksumException {
        final int length = charSequence.length();
        Code93Reader.checkOneChecksum(charSequence, length - 2, 20);
        Code93Reader.checkOneChecksum(charSequence, length - 1, 15);
    }

    private static void checkOneChecksum(final CharSequence charSequence, final int i2, final int i3) throws ChecksumException {
        int i4 = 0;
        int i5 = 1;
        for (int i6 = i2 - 1; 0 <= i6; i6--) {
            i4 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. /+%abcd*".indexOf(charSequence.charAt(i6)) * i5;
            i5++;
            if (i5 > i3) {
                i5 = 1;
            }
        }
        if (charSequence.charAt(i2) != Code93Reader.ALPHABET[i4 % 47]) {
            throw ChecksumException.getChecksumInstance();
        }
    }
}

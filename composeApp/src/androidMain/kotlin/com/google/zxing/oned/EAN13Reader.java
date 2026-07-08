package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class EAN13Reader extends UPCEANReader {
    static final int[] FIRST_DIGIT_ENCODINGS = {0, 11, 13, 14, 19, 25, 28, 21, 22, 26};
    private final int[] decodeMiddleCounters = new int[4];

    
    public int decodeMiddle(final BitArray bitArray, final int[] iArr, final StringBuilder sb) throws NotFoundException {
        final int[] iArr2 = decodeMiddleCounters;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        final int size = bitArray.getSize();
        int i2 = iArr[1];
        int i3 = 0;
        for (int i4 = 0; 6 > i4 && i2 < size; i4++) {
            final int decodeDigit = decodeDigit(bitArray, iArr2, i2, L_AND_G_PATTERNS);
            sb.append((char) ((decodeDigit % 10) + 48));
            for (final int i5 : iArr2) {
                i2 += i5;
            }
            if (10 <= decodeDigit) {
                i3 |= 1 << (5 - i4);
            }
        }
        EAN13Reader.determineFirstDigit(sb, i3);
        int i6 = findGuardPattern(bitArray, i2, true, MIDDLE_PATTERN)[1];
        for (int i7 = 0; 6 > i7 && i6 < size; i7++) {
            sb.append((char) (decodeDigit(bitArray, iArr2, i6, L_PATTERNS) + 48));
            for (final int i8 : iArr2) {
                i6 += i8;
            }
        }
        return i6;
    }

    
    public BarcodeFormat getBarcodeFormat() {
        return BarcodeFormat.EAN_13;
    }

    private static void determineFirstDigit(final StringBuilder sb, final int i2) throws NotFoundException {
        for (int i3 = 0; 10 > i3; i3++) {
            if (i2 == EAN13Reader.FIRST_DIGIT_ENCODINGS[i3]) {
                sb.insert(0, (char) (i3 + 48));
                return;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }
}

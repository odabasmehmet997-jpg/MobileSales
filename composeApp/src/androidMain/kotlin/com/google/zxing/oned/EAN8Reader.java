package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class EAN8Reader extends UPCEANReader {
    private final int[] decodeMiddleCounters = new int[4];

    
    public int decodeMiddle(final BitArray bitArray, final int[] iArr, final StringBuilder sb) throws NotFoundException {
        final int[] iArr2 = decodeMiddleCounters;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        final int size = bitArray.getSize();
        int i2 = iArr[1];
        for (int i3 = 0; 4 > i3 && i2 < size; i3++) {
            sb.append((char) (decodeDigit(bitArray, iArr2, i2, L_PATTERNS) + 48));
            for (final int i4 : iArr2) {
                i2 += i4;
            }
        }
        int i5 = findGuardPattern(bitArray, i2, true, MIDDLE_PATTERN)[1];
        for (int i6 = 0; 4 > i6 && i5 < size; i6++) {
            sb.append((char) (decodeDigit(bitArray, iArr2, i5, L_PATTERNS) + 48));
            for (final int i7 : iArr2) {
                i5 += i7;
            }
        }
        return i5;
    }

    
    public BarcodeFormat getBarcodeFormat() {
        return BarcodeFormat.EAN_8;
    }
}

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;

import java.util.EnumMap;
import java.util.Map;

final class UPCEANExtension2Support {
    private final int[] decodeMiddleCounters = new int[4];
    private final StringBuilder decodeRowStringBuffer = new StringBuilder();

    UPCEANExtension2Support() {
    }

    
    public Result decodeRow(final int i2, final BitArray bitArray, final int[] iArr) throws NotFoundException {
        final StringBuilder sb = decodeRowStringBuffer;
        sb.setLength(0);
        final int decodeMiddle = this.decodeMiddle(bitArray, iArr, sb);
        final String sb2 = sb.toString();
        final Map<ResultMetadataType, Object> parseExtensionString = UPCEANExtension2Support.parseExtensionString(sb2);
        final float f2 = i2;
        final Result result = new Result(sb2, null, new ResultPoint[]{new ResultPoint((iArr[0] + iArr[1]) / 2.0f, f2), new ResultPoint(decodeMiddle, f2)}, BarcodeFormat.UPC_EAN_EXTENSION);
        if (null != parseExtensionString) {
            result.putAllMetadata(parseExtensionString);
        }
        return result;
    }

    private int decodeMiddle(final BitArray bitArray, final int[] iArr, final StringBuilder sb) throws NotFoundException {
        final int[] iArr2 = decodeMiddleCounters;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        final int size = bitArray.getSize();
        int i2 = iArr[1];
        int i3 = 0;
        for (int i4 = 0; 2 > i4 && i2 < size; i4++) {
            final int decodeDigit = UPCEANReader.decodeDigit(bitArray, iArr2, i2, UPCEANReader.L_AND_G_PATTERNS);
            sb.append((char) ((decodeDigit % 10) + 48));
            for (final int i5 : iArr2) {
                i2 += i5;
            }
            if (10 <= decodeDigit) {
                i3 |= 1 << (1 - i4);
            }
            if (1 != i4) {
                i2 = bitArray.getNextUnset(bitArray.getNextSet(i2));
            }
        }
        if (2 != sb.length()) {
            throw NotFoundException.getNotFoundInstance();
        } else if (Integer.parseInt(sb.toString()) % 4 == i3) {
            return i2;
        } else {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private static Map<ResultMetadataType, Object> parseExtensionString(final String str) {
        if (2 != str.length()) {
            return null;
        }
        final EnumMap enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put(ResultMetadataType.ISSUE_NUMBER, Integer.valueOf(str));
        return enumMap;
    }
}

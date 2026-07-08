package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;

import java.util.EnumMap;
import java.util.Map;

final class UPCEANExtension5Support {
    private static final int[] CHECK_DIGIT_ENCODINGS = {24, 20, 18, 17, 12, 6, 3, 10, 9, 5};
    private final int[] decodeMiddleCounters = new int[4];
    private final StringBuilder decodeRowStringBuffer = new StringBuilder();

    UPCEANExtension5Support() {
    }

    
    public Result decodeRow(final int i2, final BitArray bitArray, final int[] iArr) throws NotFoundException {
        final StringBuilder sb = decodeRowStringBuffer;
        sb.setLength(0);
        final int decodeMiddle = this.decodeMiddle(bitArray, iArr, sb);
        final String sb2 = sb.toString();
        final Map<ResultMetadataType, Object> parseExtensionString = UPCEANExtension5Support.parseExtensionString(sb2);
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
        for (int i4 = 0; 5 > i4 && i2 < size; i4++) {
            final int decodeDigit = UPCEANReader.decodeDigit(bitArray, iArr2, i2, UPCEANReader.L_AND_G_PATTERNS);
            sb.append((char) ((decodeDigit % 10) + 48));
            for (final int i5 : iArr2) {
                i2 += i5;
            }
            if (10 <= decodeDigit) {
                i3 |= 1 << (4 - i4);
            }
            if (4 != i4) {
                i2 = bitArray.getNextUnset(bitArray.getNextSet(i2));
            }
        }
        if (5 == sb.length()) {
            if (UPCEANExtension5Support.extensionChecksum(sb.toString()) == UPCEANExtension5Support.determineCheckDigit(i3)) {
                return i2;
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int extensionChecksum(final CharSequence charSequence) {
        final int length = charSequence.length();
        int i2 = 0;
        for (int i3 = length - 2; 0 <= i3; i3 -= 2) {
            i2 += charSequence.charAt(i3) - '0';
        }
        int i4 = i2 * 3;
        for (int i5 = length - 1; 0 <= i5; i5 -= 2) {
            i4 += charSequence.charAt(i5) - '0';
        }
        return (i4 * 3) % 10;
    }

    private static int determineCheckDigit(final int i2) throws NotFoundException {
        for (int i3 = 0; 10 > i3; i3++) {
            if (i2 == UPCEANExtension5Support.CHECK_DIGIT_ENCODINGS[i3]) {
                return i3;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static Map<ResultMetadataType, Object> parseExtensionString(final String str) {
        final String parseExtension5String;
        if (5 != str.length() || null == (parseExtension5String = parseExtension5String(str))) {
            return null;
        }
        final EnumMap enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put(ResultMetadataType.SUGGESTED_PRICE, parseExtension5String);
        return enumMap;
    }

    private static String parseExtension5String(final String str) {
        final String str2;
        final String str3;
        final char charAt = str.charAt(0);
        if ('0' == charAt) {
            str2 = "£";
        } else if ('5' != charAt) {
            str2 = "";
            if ('9' == charAt) {
                if ("90000".equals(str)) {
                    return null;
                }
                if ("99991".equals(str)) {
                    return "0.00";
                }
                if ("99990".equals(str)) {
                    return "Used";
                }
            }
        } else {
            str2 = "";
        }
        final int parseInt = Integer.parseInt(str.substring(1));
        final String valueOf = String.valueOf(parseInt / 100);
        final int i2 = parseInt % 100;
        if (10 > i2) {
            str3 = "0" + i2;
        } else {
            str3 = String.valueOf(i2);
        }
        return str2 + valueOf + '.' + str3;
    }
}

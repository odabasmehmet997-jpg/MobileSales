package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class UPCEReader extends UPCEANReader {
    static final int[] CHECK_DIGIT_ENCODINGS = {56, 52, 50, 49, 44, 38, 35, 42, 41, 37};
    private static final int[] MIDDLE_END_PATTERN = {1, 1, 1, 1, 1, 1};
    private static final int[][] NUMSYS_AND_CHECK_DIGIT_PATTERNS = {new int[]{56, 52, 50, 49, 44, 38, 35, 42, 41, 37}, new int[]{7, 11, 13, 14, 19, 25, 28, 21, 22, 26}};
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
        UPCEReader.determineNumSysAndCheckDigit(sb, i3);
        return i2;
    }
    public int[] decodeEnd(final BitArray bitArray, final int i2) throws NotFoundException {
        return findGuardPattern(bitArray, i2, true, UPCEReader.MIDDLE_END_PATTERN);
    }
    public boolean checkChecksum(final String str) throws FormatException {
        return super.checkChecksum(UPCEReader.convertUPCEtoUPCA(str));
    }
    private static void determineNumSysAndCheckDigit(final StringBuilder sb, final int i2) throws NotFoundException {
        for (int i3 = 0; 1 >= i3; i3++) {
            for (int i4 = 0; 10 > i4; i4++) {
                if (i2 == UPCEReader.NUMSYS_AND_CHECK_DIGIT_PATTERNS[i3][i4]) {
                    sb.insert(0, (char) (i3 + 48));
                    sb.append((char) (i4 + 48));
                    return;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }
    public BarcodeFormat getBarcodeFormat() {
        return BarcodeFormat.UPC_E;
    }
    public static String convertUPCEtoUPCA(final String str) {
        final char[] cArr = new char[6];
        str.getChars(1, 7, cArr, 0);
        final StringBuilder sb = new StringBuilder(12);
        sb.append(str.charAt(0));
        final char c2 = cArr[5];
        switch (c2) {
            case '0':
            case '1':
            case '2':
                sb.append(cArr, 0, 2);
                sb.append(c2);
                sb.append("0000");
                sb.append(cArr, 2, 3);
                break;
            case '3':
                sb.append(cArr, 0, 3);
                sb.append("00000");
                sb.append(cArr, 3, 2);
                break;
            case '4':
                sb.append(cArr, 0, 4);
                sb.append("00000");
                sb.append(cArr[4]);
                break;
            default:
                sb.append(cArr, 0, 5);
                sb.append("0000");
                sb.append(c2);
                break;
        }
        sb.append(str.charAt(7));
        return sb.toString();
    }
}

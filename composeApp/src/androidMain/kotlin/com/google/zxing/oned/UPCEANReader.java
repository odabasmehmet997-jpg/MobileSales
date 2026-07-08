package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;

import java.util.Arrays;
import java.util.Map;

public abstract class UPCEANReader extends OneDReader {
    static final int[] END_PATTERN = {1, 1, 1, 1, 1, 1};
    static final int[][] L_AND_G_PATTERNS;
    static final int[][] L_PATTERNS;
    static final int[] MIDDLE_PATTERN = {1, 1, 1, 1, 1};
    static final int[] START_END_PATTERN = {1, 1, 1};
    private final StringBuilder decodeRowStringBuffer = new StringBuilder(20);
    private final EANManufacturerOrgSupport eanManSupport = new EANManufacturerOrgSupport();
    private final UPCEANExtensionSupport extensionReader = new UPCEANExtensionSupport();
    public abstract int decodeMiddle(BitArray bitArray, int[] iArr, StringBuilder sb) throws NotFoundException;
    public abstract BarcodeFormat getBarcodeFormat();
    static {
        final int[][] iArr = {new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
        L_PATTERNS = iArr;
        final int[][] iArr2 = new int[20][];
        L_AND_G_PATTERNS = iArr2;
        System.arraycopy(iArr, 0, iArr2, 0, 10);
        for (int i2 = 10; 20 > i2; i2++) {
            final int[] iArr3 = UPCEANReader.L_PATTERNS[i2 - 10];
            final int[] iArr4 = new int[iArr3.length];
            for (int i3 = 0; i3 < iArr3.length; i3++) {
                iArr4[i3] = iArr3[(iArr3.length - i3) - 1];
            }
            UPCEANReader.L_AND_G_PATTERNS[i2] = iArr4;
        }
    }
    protected UPCEANReader() {
    }
    static int[] findStartGuardPattern(final BitArray bitArray) throws NotFoundException {
        final int[] iArr = new int[UPCEANReader.START_END_PATTERN.length];
        int[] iArr2 = null;
        boolean z = false;
        int i2 = 0;
        while (!z) {
            final int[] iArr3 = UPCEANReader.START_END_PATTERN;
            Arrays.fill(iArr, 0, iArr3.length, 0);
            iArr2 = UPCEANReader.findGuardPattern(bitArray, i2, false, iArr3, iArr);
            final int i3 = iArr2[0];
            final int i4 = iArr2[1];
            final int i5 = i3 - (i4 - i3);
            if (0 <= i5) {
                z = bitArray.isRange(i5, i3, false);
            }
            i2 = i4;
        }
        return iArr2;
    }
    public Result decodeRow(final int i2, final BitArray bitArray, final Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        return this.decodeRow(i2, bitArray, UPCEANReader.findStartGuardPattern(bitArray), map);
    }
    public Result decodeRow(final int i2, final BitArray bitArray, final int[] iArr, final Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        final ResultPointCallback resultPointCallback;
        int i3;
        final String lookupCountryIdentifier;
        int[] iArr2 = null;
        if (null == map) {
            resultPointCallback = null;
        } else {
            resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        }
        int i4 = 0;
        if (null != resultPointCallback) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint((iArr[0] + iArr[1]) / 2.0f, i2));
        }
        final StringBuilder sb = decodeRowStringBuffer;
        sb.setLength(0);
        final int decodeMiddle = this.decodeMiddle(bitArray, iArr, sb);
        if (null != resultPointCallback) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint(decodeMiddle, i2));
        }
        final int[] decodeEnd = this.decodeEnd(bitArray, decodeMiddle);
        if (null != resultPointCallback) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint((decodeEnd[0] + decodeEnd[1]) / 2.0f, i2));
        }
        final int i5 = decodeEnd[1];
        final int i6 = (i5 - decodeEnd[0]) + i5;
        if (i6 >= bitArray.getSize() || !bitArray.isRange(i5, i6, false)) {
            throw NotFoundException.getNotFoundInstance();
        }
        final String sb2 = sb.toString();
        if (8 > sb2.length()) {
            throw FormatException.getFormatInstance();
        } else if (this.checkChecksum(sb2)) {
            final BarcodeFormat barcodeFormat = this.getBarcodeFormat();
            final float f2 = i2;
            final Result result = new Result(sb2, null, new ResultPoint[]{new ResultPoint((iArr[1] + iArr[0]) / 2.0f, f2), new ResultPoint((decodeEnd[1] + decodeEnd[0]) / 2.0f, f2)}, barcodeFormat);
            try {
                final Result decodeRow = extensionReader.decodeRow(i2, bitArray, decodeEnd[1]);
                result.putMetadata(ResultMetadataType.UPC_EAN_EXTENSION, decodeRow.getText());
                result.putAllMetadata(decodeRow.getResultMetadata());
                result.addResultPoints(decodeRow.getResultPoints());
                i3 = decodeRow.getText().length();
            } catch (final ReaderException unused) {
                i3 = 0;
            }
            if (null != map) {
                iArr2 = (int[]) map.get(DecodeHintType.ALLOWED_EAN_EXTENSIONS);
            }
            if (null != iArr2) {
                final int length = iArr2.length;
                while (i4 < length) {
                    if (i3 != iArr2[i4]) {
                        i4++;
                    }
                }
                throw NotFoundException.getNotFoundInstance();
            }
            if ((BarcodeFormat.EAN_13 == barcodeFormat || BarcodeFormat.UPC_A == barcodeFormat) && null != (lookupCountryIdentifier = this.eanManSupport.lookupCountryIdentifier(sb2))) {
                result.putMetadata(ResultMetadataType.POSSIBLE_COUNTRY, lookupCountryIdentifier);
            }
            return result;
        } else {
            throw ChecksumException.getChecksumInstance();
        }
    }
    public boolean checkChecksum(final String str) throws FormatException {
        return UPCEANReader.checkStandardUPCEANChecksum(str);
    }
    static boolean checkStandardUPCEANChecksum(final CharSequence charSequence) throws FormatException {
        final int length = charSequence.length();
        if (0 == length) {
            return false;
        }
        int i2 = 0;
        for (int i3 = length - 2; 0 <= i3; i3 -= 2) {
            final int charAt = charSequence.charAt(i3) - '0';
            if (0 > charAt || 9 < charAt) {
                throw FormatException.getFormatInstance();
            }
            i2 += charAt;
        }
        int i4 = i2 * 3;
        for (int i5 = length - 1; 0 <= i5; i5 -= 2) {
            final int charAt2 = charSequence.charAt(i5) - '0';
            if (0 > charAt2 || 9 < charAt2) {
                throw FormatException.getFormatInstance();
            }
            i4 += charAt2;
        }
        return 0 == i4 % 10;
    }
    public int[] decodeEnd(final BitArray bitArray, final int i2) throws NotFoundException {
        return UPCEANReader.findGuardPattern(bitArray, i2, false, UPCEANReader.START_END_PATTERN);
    }
    static int[] findGuardPattern(final BitArray bitArray, final int i2, final boolean z, final int[] iArr) throws NotFoundException {
        return UPCEANReader.findGuardPattern(bitArray, i2, z, iArr, new int[iArr.length]);
    }
    private static int[] findGuardPattern(final BitArray bitArray, final int i2, final boolean z, final int[] iArr, final int[] iArr2) throws NotFoundException {
        final int size = bitArray.getSize();
        int nextUnset = z ? bitArray.getNextUnset(i2) : bitArray.getNextSet(i2);
        final int length = iArr.length;
        boolean z2 = z;
        int i3 = 0;
        int i4 = nextUnset;
        while (nextUnset < size) {
            if (bitArray.get(nextUnset) ^ z2) {
                iArr2[i3] = iArr2[i3] + 1;
            } else {
                final int i5 = length - 1;
                if (i3 != i5) {
                    i3++;
                } else if (0.48f > OneDReader.patternMatchVariance(iArr2, iArr, 0.7f)) {
                    return new int[]{i4, nextUnset};
                } else {
                    i4 += iArr2[0] + iArr2[1];
                    final int i6 = length - 2;
                    System.arraycopy(iArr2, 2, iArr2, 0, i6);
                    iArr2[i6] = 0;
                    iArr2[i5] = 0;
                    i3--;
                }
                iArr2[i3] = 1;
                z2 = !z2;
            }
            nextUnset++;
        }
        throw NotFoundException.getNotFoundInstance();
    }
    static int decodeDigit(final BitArray bitArray, final int[] iArr, final int i2, final int[][] iArr2) throws NotFoundException {
        recordPattern(bitArray, i2, iArr);
        final int length = iArr2.length;
        float f2 = 0.48f;
        int i3 = -1;
        for (int i4 = 0; i4 < length; i4++) {
            final float patternMatchVariance = patternMatchVariance(iArr, iArr2[i4], 0.7f);
            if (patternMatchVariance < f2) {
                i3 = i4;
                f2 = patternMatchVariance;
            }
        }
        if (0 <= i3) {
            return i3;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}

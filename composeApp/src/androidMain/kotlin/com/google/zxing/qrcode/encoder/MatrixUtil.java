package com.google.zxing.qrcode.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Version;
import org.kxml2.wap.Wbxml;

final class MatrixUtil {
    private static final int[][] POSITION_DETECTION_PATTERN = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};
    private static final int[][] POSITION_ADJUSTMENT_PATTERN = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};
    private static final int[][] POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, Wbxml.EXT_T_2, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, 138, -1}, new int[]{6, 30, 58, 86, 114, 142, -1}, new int[]{6, 34, 62, 90, 118, 146, -1}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, Wbxml.LITERAL_A, 158}, new int[]{6, 32, 58, 84, 110, 136, 162}, new int[]{6, 26, 54, 82, 110, 138, 166}, new int[]{6, 30, 58, 86, 114, 142, 170}};
    private static final int[][] TYPE_INFO_COORDINATES = {new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};
    private static boolean isEmpty(int r1) {
        return r1 == -1;
    }
    private MatrixUtil() {
    }
    static void clearMatrix(ByteMatrix byteMatrix) {
        byteMatrix.clear((byte) -1);
    }
    static void buildMatrix(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, Version version, int r3, ByteMatrix byteMatrix) throws WriterException {
        clearMatrix(byteMatrix);
        embedBasicPatterns(version, byteMatrix);
        embedTypeInfo(errorCorrectionLevel, r3, byteMatrix);
        maybeEmbedVersionInfo(version, byteMatrix);
        embedDataBits(bitArray, r3, byteMatrix);
    }
    static void embedBasicPatterns(Version version, ByteMatrix byteMatrix) throws WriterException {
        embedPositionDetectionPatternsAndSeparators(byteMatrix);
        embedDarkDotAtLeftBottomCorner(byteMatrix);
        maybeEmbedPositionAdjustmentPatterns(version, byteMatrix);
        embedTimingPatterns(byteMatrix);
    }
    static void embedTypeInfo(ErrorCorrectionLevel errorCorrectionLevel, int r6, ByteMatrix byteMatrix) throws WriterException {
        BitArray bitArray = new BitArray();
        makeTypeInfoBits(errorCorrectionLevel, r6, bitArray);
        for (int r62 = 0; r62 < bitArray.getSize(); r62++) {
            boolean z = bitArray.get((bitArray.getSize() - 1) - r62);
            int[] r3 = TYPE_INFO_COORDINATES[r62];
            byteMatrix.set(r3[0], r3[1], z);
            if (r62 < 8) {
                byteMatrix.set((byteMatrix.getWidth() - r62) - 1, 8, z);
            } else {
                byteMatrix.set(8, (byteMatrix.getHeight() - 7) + (r62 - 8), z);
            }
        }
    }
    static void maybeEmbedVersionInfo(Version version, ByteMatrix byteMatrix) throws WriterException {
        if (version.getVersionNumber() < 7) {
            return;
        }
        BitArray bitArray = new BitArray();
        makeVersionInfoBits(version, bitArray);
        int r1 = 17;
        for (int r2 = 0; r2 < 6; r2++) {
            for (int r3 = 0; r3 < 3; r3++) {
                boolean z = bitArray.get(r1);
                r1--;
                byteMatrix.set(r2, (byteMatrix.getHeight() - 11) + r3, z);
                byteMatrix.set((byteMatrix.getHeight() - 11) + r3, r2, z);
            }
        }
    }
    static void embedDataBits(BitArray bitArray, int r11, ByteMatrix byteMatrix) throws WriterException {
        boolean z;
        int width = byteMatrix.getWidth() - 1;
        int height = byteMatrix.getHeight() - 1;
        int r4 = 0;
        int r5 = -1;
        while (width > 0) {
            if (width == 6) {
                width--;
            }
            while (height >= 0 && height < byteMatrix.getHeight()) {
                for (int r6 = 0; r6 < 2; r6++) {
                    int r7 = width - r6;
                    if (isEmpty(byteMatrix.get(r7, height))) {
                        if (r4 < bitArray.getSize()) {
                            z = bitArray.get(r4);
                            r4++;
                        } else {
                            z = false;
                        }
                        if (r11 != -1 && MaskUtil.getDataMaskBit(r11, r7, height)) {
                            z = !z;
                        }
                        byteMatrix.set(r7, height, z);
                    }
                }
                height += r5;
            }
            r5 = -r5;
            height += r5;
            width -= 2;
        }
        if (r4 == bitArray.getSize()) {
            return;
        }
        throw new WriterException("Not all bits consumed: " + r4 + '/' + bitArray.getSize());
    }
    static int findMSBSet(int r0) {
        return 32 - Integer.numberOfLeadingZeros(r0);
    }
    static int calculateBCHCode(int r2, int r3) {
        if (r3 == 0) {
            throw new IllegalArgumentException("0 polynomial");
        }
        int r0 = findMSBSet(r3);
        int r22 = r2 << (r0 - 1);
        while (findMSBSet(r22) >= r0) {
            r22 ^= r3 << (findMSBSet(r22) - r0);
        }
        return r22;
    }
    static void makeTypeInfoBits(ErrorCorrectionLevel errorCorrectionLevel, int r2, BitArray bitArray) throws WriterException {
        if (!QRCode.isValidMaskPattern(r2)) {
            throw new WriterException("Invalid mask pattern");
        }
        int bits = (errorCorrectionLevel.getBits() << 3) | r2;
        bitArray.appendBits(bits, 5);
        bitArray.appendBits(calculateBCHCode(bits, 1335), 10);
        BitArray bitArray2 = new BitArray();
        bitArray2.appendBits(21522, 15);
        bitArray.xor(bitArray2);
        if (bitArray.getSize() == 15) {
            return;
        }
        throw new WriterException("should not happen but we got: " + bitArray.getSize());
    }
    static void makeVersionInfoBits(Version version, BitArray bitArray) throws WriterException {
        bitArray.appendBits(version.getVersionNumber(), 6);
        bitArray.appendBits(calculateBCHCode(version.getVersionNumber(), 7973), 12);
        if (bitArray.getSize() == 18) {
            return;
        }
        throw new WriterException("should not happen but we got: " + bitArray.getSize());
    }
    private static void embedTimingPatterns(ByteMatrix byteMatrix) {
        int r1 = 8;
        while (r1 < byteMatrix.getWidth() - 8) {
            int r2 = r1 + 1;
            int r3 = r2 % 2;
            if (isEmpty(byteMatrix.get(r1, 6))) {
                byteMatrix.set(r1, 6, r3);
            }
            if (isEmpty(byteMatrix.get(6, r1))) {
                byteMatrix.set(6, r1, r3);
            }
            r1 = r2;
        }
    }
    private static void embedDarkDotAtLeftBottomCorner(ByteMatrix byteMatrix) throws WriterException {
        if (byteMatrix.get(8, byteMatrix.getHeight() - 8) == 0) {
            throw new WriterException();
        }
        byteMatrix.set(8, byteMatrix.getHeight() - 8, 1);
    }
    private static void embedHorizontalSeparationPattern(int r4, int r5, ByteMatrix byteMatrix) throws WriterException {
        for (int r1 = 0; r1 < 8; r1++) {
            int r2 = r4 + r1;
            if (!isEmpty(byteMatrix.get(r2, r5))) {
                throw new WriterException();
            }
            byteMatrix.set(r2, r5, 0);
        }
    }
    private static void embedVerticalSeparationPattern(int r4, int r5, ByteMatrix byteMatrix) throws WriterException {
        for (int r1 = 0; r1 < 7; r1++) {
            int r2 = r5 + r1;
            if (!isEmpty(byteMatrix.get(r4, r2))) {
                throw new WriterException();
            }
            byteMatrix.set(r4, r2, 0);
        }
    }
    private static void embedPositionAdjustmentPattern(int r7, int r8, ByteMatrix byteMatrix) {
        for (int r1 = 0; r1 < 5; r1++) {
            for (int r3 = 0; r3 < 5; r3++) {
                byteMatrix.set(r7 + r3, r8 + r1, POSITION_ADJUSTMENT_PATTERN[r1][r3]);
            }
        }
    }
    private static void embedPositionDetectionPattern(int r7, int r8, ByteMatrix byteMatrix) {
        for (int r1 = 0; r1 < 7; r1++) {
            for (int r3 = 0; r3 < 7; r3++) {
                byteMatrix.set(r7 + r3, r8 + r1, POSITION_DETECTION_PATTERN[r1][r3]);
            }
        }
    }
    private static void embedPositionDetectionPatternsAndSeparators(ByteMatrix byteMatrix) throws WriterException {
        int length = POSITION_DETECTION_PATTERN[0].length;
        embedPositionDetectionPattern(0, 0, byteMatrix);
        embedPositionDetectionPattern(byteMatrix.getWidth() - length, 0, byteMatrix);
        embedPositionDetectionPattern(0, byteMatrix.getWidth() - length, byteMatrix);
        embedHorizontalSeparationPattern(0, 7, byteMatrix);
        embedHorizontalSeparationPattern(byteMatrix.getWidth() - 8, 7, byteMatrix);
        embedHorizontalSeparationPattern(0, byteMatrix.getWidth() - 8, byteMatrix);
        embedVerticalSeparationPattern(7, 0, byteMatrix);
        embedVerticalSeparationPattern(byteMatrix.getHeight() - 8, 0, byteMatrix);
        embedVerticalSeparationPattern(7, byteMatrix.getHeight() - 7, byteMatrix);
    }
    private static void maybeEmbedPositionAdjustmentPatterns(Version version, ByteMatrix byteMatrix) {
        if (version.getVersionNumber() < 2) {
            return;
        }
        int[] r7 = POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[version.getVersionNumber() - 1];
        for (int r4 : r7) {
            for (int r5 : r7) {
                if (r5 != -1 && r4 != -1 && isEmpty(byteMatrix.get(r5, r4))) {
                    embedPositionAdjustmentPattern(r5 - 2, r4 - 2, byteMatrix);
                }
            }
        }
    }
}

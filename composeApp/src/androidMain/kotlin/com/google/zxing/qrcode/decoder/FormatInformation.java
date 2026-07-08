package com.google.zxing.qrcode.decoder;

final class FormatInformation {
    private static final int[][] FORMAT_INFO_DECODE_LOOKUP = {new int[]{21522, 0}, new int[]{20773, 1}, new int[]{24188, 2}, new int[]{23371, 3}, new int[]{17913, 4}, new int[]{16590, 5}, new int[]{20375, 6}, new int[]{19104, 7}, new int[]{30660, 8}, new int[]{29427, 9}, new int[]{32170, 10}, new int[]{30877, 11}, new int[]{26159, 12}, new int[]{25368, 13}, new int[]{27713, 14}, new int[]{26998, 15}, new int[]{5769, 16}, new int[]{5054, 17}, new int[]{7399, 18}, new int[]{6608, 19}, new int[]{1890, 20}, new int[]{597, 21}, new int[]{3340, 22}, new int[]{2107, 23}, new int[]{13663, 24}, new int[]{12392, 25}, new int[]{16177, 26}, new int[]{14854, 27}, new int[]{9396, 28}, new int[]{8579, 29}, new int[]{11994, 30}, new int[]{11245, 31}};
    private final byte dataMask;
    private final ErrorCorrectionLevel errorCorrectionLevel;
    private FormatInformation(int r2) {
        this.errorCorrectionLevel = ErrorCorrectionLevel.forBits((r2 >> 3) & 3);
        this.dataMask = (byte) (r2 & 7);
    }
    static int numBitsDiffering(int r0, int r1) {
        return Integer.bitCount(r0 ^ r1);
    }
    static FormatInformation decodeFormatInformation(int r1, int r2) {
        FormatInformation formatInformationDoDecodeFormatInformation = doDecodeFormatInformation(r1, r2);
        return formatInformationDoDecodeFormatInformation != null ? formatInformationDoDecodeFormatInformation : doDecodeFormatInformation(r1 ^ 21522, r2 ^ 21522);
    }
    private static FormatInformation doDecodeFormatInformation(int r10, int r11) {
        int r7;
        int r2 = Integer.MAX_VALUE;
        int r5 = 0;
        for (int[] r6 : FORMAT_INFO_DECODE_LOOKUP) {
            int r72 = r6[0];
            if (r72 == r10 || r72 == r11) {
                return new FormatInformation(r6[1]);
            }
            int r9 = numBitsDiffering(r10, r72);
            if (r9 < r2) {
                r5 = r6[1];
                r2 = r9;
            }
            if (r10 != r11 && (r7 = numBitsDiffering(r11, r72)) < r2) {
                r5 = r6[1];
                r2 = r7;
            }
        }
        if (r2 <= 3) {
            return new FormatInformation(r5);
        }
        return null;
    }
    ErrorCorrectionLevel getErrorCorrectionLevel() {
        return this.errorCorrectionLevel;
    }
    byte getDataMask() {
        return this.dataMask;
    }
    public int hashCode() {
        return this.dataMask | (this.errorCorrectionLevel.ordinal() << 3);
    }
    public boolean equals(Object obj) {
        if (!(obj instanceof FormatInformation formatInformation)) {
            return false;
        }
        return this.errorCorrectionLevel == formatInformation.errorCorrectionLevel && this.dataMask == formatInformation.dataMask;
    }
}

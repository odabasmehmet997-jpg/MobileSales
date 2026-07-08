package com.google.zxing.qrcode.decoder;

public enum ErrorCorrectionLevel {
    L(1),
    M(0),
    Q(3),
    H(2);
    private static final ErrorCorrectionLevel[] FOR_BITS;
    private final int bits;
    static {
        ErrorCorrectionLevel errorCorrectionLevel = L;
        ErrorCorrectionLevel errorCorrectionLevel2 = M;
        ErrorCorrectionLevel errorCorrectionLevel3 = Q;
        FOR_BITS = new ErrorCorrectionLevel[]{errorCorrectionLevel2, errorCorrectionLevel, H, errorCorrectionLevel3};
    }
    ErrorCorrectionLevel(int r3) {
        this.bits = r3;
    }
    public int getBits() {
        return this.bits;
    }
    public static ErrorCorrectionLevel forBits(int r2) {
        if (r2 >= 0 && r2 < FOR_BITS.length) {
            return FOR_BITS[r2];
        }
        throw new IllegalArgumentException();
    }
}

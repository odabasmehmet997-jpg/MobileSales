package com.google.zxing.aztec.encoder;

final class BinaryShiftToken extends Token {
    private final short binaryShiftByteCount;
    private final short binaryShiftStart;

    public String toString() {
        final String sb = "<" + this.binaryShiftStart +
                "::" +
                ((this.binaryShiftStart + this.binaryShiftByteCount) - 1) +
                '>';
        return sb;
    }
}

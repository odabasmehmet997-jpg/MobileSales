package com.google.zxing.aztec.encoder;

final class SimpleToken extends Token {
    private final short bitCount;
    private final short value;

    SimpleToken(Token token, int i2, int i3) {
        super(token);
        this.value = (short) i2;
        this.bitCount = (short) i3;
    }

    public String toString() {
        short s = this.value;
        short s2 = this.bitCount;
        short s3 = (s & ((1 << s2) - 1)) | (1 << s2);
        return "<" + Integer.toBinaryString(s3 | (1 << this.bitCount)).substring(1) + '>';
    }
}

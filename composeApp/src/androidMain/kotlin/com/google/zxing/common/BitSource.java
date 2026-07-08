package com.google.zxing.common;

public final class BitSource {
    private int bitOffset;
    private int byteOffset;
    private final byte[] bytes;

    public BitSource(final byte[] bArr) {
        bytes = bArr;
    }

    public int getBitOffset() {
        return bitOffset;
    }

    public int getByteOffset() {
        return byteOffset;
    }

    public int readBits(int i2) {
        if (0 >= i2 || 32 < i2 || i2 > this.available()) {
            throw new IllegalArgumentException(String.valueOf(i2));
        }
        final int i3 = bitOffset;
        byte b2 = 0;
        if (0 < i3) {
            final int i4 = 8 - i3;
            final int i5 = i2 < i4 ? i2 : i4;
            final int i6 = i4 - i5;
            final byte[] bArr = bytes;
            final int i7 = byteOffset;
            final int i8 = (((255 >> (8 - i5)) << i6) & bArr[i7]) >> i6;
            i2 -= i5;
            final int i9 = i3 + i5;
            bitOffset = i9;
            if (8 == i9) {
                bitOffset = 0;
                byteOffset = i7 + 1;
            }
            b2 = i8;
        }
        if (0 >= i2) {
            return b2;
        }
        while (8 <= i2) {
            final int i10 = b2 << 8;
            final byte[] bArr2 = bytes;
            final int i11 = byteOffset;
            b2 = (bArr2[i11] & 255) | i10;
            byteOffset = i11 + 1;
            i2 -= 8;
        }
        if (0 >= i2) {
            return b2;
        }
        final int i12 = 8 - i2;
        final int i13 = (b2 << i2) | ((((255 >> i12) << i12) & bytes[byteOffset]) >> i12);
        bitOffset += i2;
        return i13;
    }

    public int available() {
        return ((bytes.length - byteOffset) * 8) - bitOffset;
    }
}

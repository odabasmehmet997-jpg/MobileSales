package com.google.zxing.common;

import okhttp3.internal.ws.WebSocketProtocol;

import java.util.Arrays;

public final class BitArray implements Cloneable {
    private int[] bits;
    private int size;

    public BitArray() {
        size = 0;
        bits = new int[1];
    }

    public BitArray(final int i2) {
        size = i2;
        bits = BitArray.makeArray(i2);
    }

    BitArray(final int[] iArr, final int i2) {
        bits = iArr;
        size = i2;
    }

    public int getSize() {
        return size;
    }

    public int getSizeInBytes() {
        return (size + 7) / 8;
    }

    private void ensureCapacity(final int i2) {
        if (i2 > (bits.length << 5)) {
            final int[] makeArray = BitArray.makeArray(i2);
            final int[] iArr = bits;
            System.arraycopy(iArr, 0, makeArray, 0, iArr.length);
            bits = makeArray;
        }
    }

    public boolean get(final int i2) {
        return 0 != ((1 << (i2 & 31)) & this.bits[i2 / 32]);
    }

    public void set(final int i2) {
        final int[] iArr = bits;
        final int i3 = i2 / 32;
        iArr[i3] = (1 << (i2 & 31)) | iArr[i3];
    }

    public int getNextSet(final int i2) {
        final int i3 = size;
        if (i2 >= i3) {
            return i3;
        }
        int i4 = i2 / 32;
        int i5 = (~((1 << (i2 & 31)) - 1)) & bits[i4];
        while (0 == i5) {
            i4++;
            final int[] iArr = bits;
            if (i4 == iArr.length) {
                return size;
            }
            i5 = iArr[i4];
        }
        final int numberOfTrailingZeros = (i4 << 5) + Integer.numberOfTrailingZeros(i5);
        final int i6 = size;
        return numberOfTrailingZeros > i6 ? i6 : numberOfTrailingZeros;
    }

    public int getNextUnset(final int i2) {
        final int i3 = size;
        if (i2 >= i3) {
            return i3;
        }
        int i4 = i2 / 32;
        int i5 = (~((1 << (i2 & 31)) - 1)) & (~bits[i4]);
        while (0 == i5) {
            i4++;
            final int[] iArr = bits;
            if (i4 == iArr.length) {
                return size;
            }
            i5 = ~iArr[i4];
        }
        final int numberOfTrailingZeros = (i4 << 5) + Integer.numberOfTrailingZeros(i5);
        final int i6 = size;
        return numberOfTrailingZeros > i6 ? i6 : numberOfTrailingZeros;
    }

    public void setBulk(final int i2, final int i3) {
        bits[i2 / 32] = i3;
    }

    public void clear() {
        final int length = bits.length;
        for (int i2 = 0; i2 < length; i2++) {
            bits[i2] = 0;
        }
    }

    public boolean isRange(final int i2, final int i3, final boolean z) {
        if (i3 < i2 || 0 > i2 || i3 > size) {
            throw new IllegalArgumentException();
        } else if (i3 == i2) {
            return true;
        } else {
            final int i4 = i3 - 1;
            final int i5 = i2 / 32;
            final int i6 = i4 / 32;
            int i7 = i5;
            while (i7 <= i6) {
                int i8 = 31;
                final int i9 = i7 > i5 ? 0 : i2 & 31;
                if (i7 >= i6) {
                    i8 = 31 & i4;
                }
                int i10 = (2 << i8) - (1 << i9);
                final int i11 = bits[i7] & i10;
                if (!z) {
                    i10 = 0;
                }
                if (i11 != i10) {
                    return false;
                }
                i7++;
            }
            return true;
        }
    }

    public void appendBit(final boolean z) {
        this.ensureCapacity(size + 1);
        if (z) {
            final int[] iArr = bits;
            final int i2 = size;
            final int i3 = i2 / 32;
            iArr[i3] = (1 << (i2 & 31)) | iArr[i3];
        }
        size++;
    }

    public void appendBits(final int i2, int i3) {
        if (0 > i3 || 32 < i3) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        this.ensureCapacity(size + i3);
        while (0 < i3) {
            boolean z = 1 == ((i2 >> (i3 - 1)) & 1);
            this.appendBit(z);
            i3--;
        }
    }

    public void appendBitArray(final BitArray bitArray) {
        final int i2 = bitArray.size;
        this.ensureCapacity(size + i2);
        for (int i3 = 0; i3 < i2; i3++) {
            this.appendBit(bitArray.get(i3));
        }
    }

    public void xor(final BitArray bitArray) {
        if (size == bitArray.size) {
            int i2 = 0;
            while (true) {
                final int[] iArr = bits;
                if (i2 < iArr.length) {
                    iArr[i2] = iArr[i2] ^ bitArray.bits[i2];
                    i2++;
                } else {
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("Sizes don't match");
        }
    }

    public void toBytes(int i2, final byte[] bArr, final int i3, final int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = 0;
            for (int i7 = 0; 8 > i7; i7++) {
                if (this.get(i2)) {
                    i6 |= 1 << (7 - i7);
                }
                i2++;
            }
            bArr[i3 + i5] = (byte) i6;
        }
    }

    public int[] getBitArray() {
        return bits;
    }

    public void reverse() {
        final int[] iArr = new int[bits.length];
        final int i2 = (size - 1) / 32;
        final int i3 = i2 + 1;
        for (int i4 = 0; i4 < i3; i4++) {
            final long j2 = bits[i4];
            final long j3 = ((j2 & 1431655765) << 1) | ((j2 >> 1) & 1431655765);
            final long j4 = ((j3 & 858993459) << 2) | ((j3 >> 2) & 858993459);
            final long j5 = ((j4 & 252645135) << 4) | ((j4 >> 4) & 252645135);
            final long j6 = ((j5 & 16711935) << 8) | ((j5 >> 8) & 16711935);
            iArr[i2 - i4] = (int) (((j6 & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 16) | ((j6 >> 16) & WebSocketProtocol.PAYLOAD_SHORT_MAX));
        }
        final int i5 = size;
        final int i6 = i3 << 5;
        if (i5 != i6) {
            final int i7 = i6 - i5;
            int i8 = iArr[0] >>> i7;
            for (int i9 = 1; i9 < i3; i9++) {
                final int i10 = iArr[i9];
                iArr[i9 - 1] = i8 | (i10 << (32 - i7));
                i8 = i10 >>> i7;
            }
            iArr[i2] = i8;
        }
        bits = iArr;
    }

    private static int[] makeArray(final int i2) {
        return new int[((i2 + 31) / 32)];
    }

    public boolean equals(final Object obj) {
        if (!(obj instanceof BitArray bitArray)) {
            return false;
        }
        return size == bitArray.size && Arrays.equals(bits, bitArray.bits);
    }

    public int hashCode() {
        return (size * 31) + Arrays.hashCode(bits);
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder(size);
        for (int i2 = 0; i2 < size; i2++) {
            if (0 == (i2 & 7)) {
                sb.append(' ');
            }
            sb.append(this.get(i2) ? 'X' : '.');
        }
        return sb.toString();
    }

    public BitArray clone() {
        return new BitArray(bits.clone(), size);
    }
}

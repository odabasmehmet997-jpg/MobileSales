package com.sun.mail.auth;

public final class MD4 {
    private static final byte[] padding;
    private int bufOfs;
    private final byte[] buffer = new byte[64];
    private long bytesProcessed;
    private final int[] state = new int[4];
    private final int[] x = new int[16];
    private static int FF(final int i2, final int i3, final int i4, final int i5, final int i6, final int i7) {
        final int i8 = i2 + (((~i3) & i5) | (i4 & i3)) + i6;
        return (i8 >>> (32 - i7)) | (i8 << i7);
    }
    private static int GG(final int i2, final int i3, final int i4, final int i5, final int i6, final int i7) {
        final int i8 = i2 + ((i3 & (i4 | i5)) | (i4 & i5)) + i6 + 1518500249;
        return (i8 >>> (32 - i7)) | (i8 << i7);
    }
    private static int HH(final int i2, final int i3, final int i4, final int i5, final int i6, final int i7) {
        final int i8 = i2 + ((i3 ^ i4) ^ i5) + i6 + 1859775393;
        return (i8 >>> (32 - i7)) | (i8 << i7);
    }
    static {
        final byte[] bArr = new byte[136];
        padding = bArr;
        bArr[0] = Byte.MIN_VALUE;
    }
    public MD4() {
        this.implReset();
    }
    public byte[] digest(final byte[] bArr) {
        this.implReset();
        this.engineUpdate(bArr, 0, bArr.length);
        final byte[] bArr2 = new byte[16];
        this.implDigest(bArr2, 0);
        return bArr2;
    }
    private void implReset() {
        final int[] iArr = state;
        iArr[0] = 1732584193;
        iArr[1] = -271733879;
        iArr[2] = -1732584194;
        iArr[3] = 271733878;
        bufOfs = 0;
        bytesProcessed = 0;
    }
    private void implDigest(final byte[] bArr, int i2) {
        final long j2 = bytesProcessed;
        final long j3 = j2 << 3;
        final int i3 = ((int) j2) & 63;
        int i4 = 0;
        this.engineUpdate(MD4.padding, 0, 56 > i3 ? 56 - i3 : 120 - i3);
        final byte[] bArr2 = buffer;
        bArr2[56] = (byte) ((int) j3);
        bArr2[57] = (byte) ((int) (j3 >> 8));
        bArr2[58] = (byte) ((int) (j3 >> 16));
        bArr2[59] = (byte) ((int) (j3 >> 24));
        bArr2[60] = (byte) ((int) (j3 >> 32));
        bArr2[61] = (byte) ((int) (j3 >> 40));
        bArr2[62] = (byte) ((int) (j3 >> 48));
        bArr2[63] = (byte) ((int) (j3 >> 56));
        this.implCompress(bArr2, 0);
        while (true) {
            final int[] iArr = state;
            if (i4 < iArr.length) {
                final int i5 = iArr[i4];
                bArr[i2] = (byte) i5;
                bArr[i2 + 1] = (byte) (i5 >> 8);
                final int i6 = i2 + 3;
                bArr[i2 + 2] = (byte) (i5 >> 16);
                i2 += 4;
                bArr[i6] = (byte) (i5 >> 24);
                i4++;
            } else {
                return;
            }
        }
    }
    private void engineUpdate(final byte[] bArr, int i2, int i3) {
        if (0 != i3) {
            if (0 > i2 || 0 > i3 || i2 > bArr.length - i3) {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (0 > this.bytesProcessed) {
                this.implReset();
            }
            bytesProcessed += i3;
            final int i4 = bufOfs;
            if (0 != i4) {
                final int min = Math.min(i3, 64 - i4);
                System.arraycopy(bArr, i2, buffer, bufOfs, min);
                final int i5 = bufOfs + min;
                bufOfs = i5;
                i2 += min;
                i3 -= min;
                if (64 <= i5) {
                    this.implCompress(buffer, 0);
                    bufOfs = 0;
                }
            }
            while (64 <= i3) {
                this.implCompress(bArr, i2);
                i3 -= 64;
                i2 += 64;
            }
            if (0 < i3) {
                System.arraycopy(bArr, i2, buffer, 0, i3);
                bufOfs = i3;
            }
        }
    }
    private void implCompress(final byte[] bArr, final int i2) {
        int i3 = i2;
        int i4 = 0;
        while (true) {
            final int[] iArr = x;
            if (i4 < iArr.length) {
                iArr[i4] = (bArr[i3] & 255) | ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3 + 2] & 255) << 16) | ((bArr[i3 + 3] & 255) << 24);
                i3 += 4;
                i4++;
            } else {
                final int[] iArr2 = state;
                final int i5 = iArr2[0];
                final int i6 = iArr2[1];
                final int i7 = iArr2[2];
                final int i8 = iArr2[3];
                final int FF = MD4.FF(i5, i6, i7, i8, iArr[0], 3);
                final int FF2 = MD4.FF(i8, FF, i6, i7, x[1], 7);
                final int FF3 = MD4.FF(i7, FF2, FF, i6, x[2], 11);
                final int FF4 = MD4.FF(i6, FF3, FF2, FF, x[3], 19);
                final int FF5 = MD4.FF(FF, FF4, FF3, FF2, x[4], 3);
                final int FF6 = MD4.FF(FF2, FF5, FF4, FF3, x[5], 7);
                final int FF7 = MD4.FF(FF3, FF6, FF5, FF4, x[6], 11);
                final int FF8 = MD4.FF(FF4, FF7, FF6, FF5, x[7], 19);
                final int FF9 = MD4.FF(FF5, FF8, FF7, FF6, x[8], 3);
                final int FF10 = MD4.FF(FF6, FF9, FF8, FF7, x[9], 7);
                final int FF11 = MD4.FF(FF7, FF10, FF9, FF8, x[10], 11);
                final int FF12 = MD4.FF(FF8, FF11, FF10, FF9, x[11], 19);
                final int FF13 = MD4.FF(FF9, FF12, FF11, FF10, x[12], 3);
                final int FF14 = MD4.FF(FF10, FF13, FF12, FF11, x[13], 7);
                final int FF15 = MD4.FF(FF11, FF14, FF13, FF12, x[14], 11);
                final int FF16 = MD4.FF(FF12, FF15, FF14, FF13, x[15], 19);
                final int GG = MD4.GG(FF13, FF16, FF15, FF14, x[0], 3);
                final int GG2 = MD4.GG(FF14, GG, FF16, FF15, x[4], 5);
                final int GG3 = MD4.GG(FF15, GG2, GG, FF16, x[8], 9);
                final int GG4 = MD4.GG(FF16, GG3, GG2, GG, x[12], 13);
                final int GG5 = MD4.GG(GG, GG4, GG3, GG2, x[1], 3);
                final int GG6 = MD4.GG(GG2, GG5, GG4, GG3, x[5], 5);
                final int GG7 = MD4.GG(GG3, GG6, GG5, GG4, x[9], 9);
                final int GG8 = MD4.GG(GG4, GG7, GG6, GG5, x[13], 13);
                final int GG9 = MD4.GG(GG5, GG8, GG7, GG6, x[2], 3);
                final int GG10 = MD4.GG(GG6, GG9, GG8, GG7, x[6], 5);
                final int GG11 = MD4.GG(GG7, GG10, GG9, GG8, x[10], 9);
                final int GG12 = MD4.GG(GG8, GG11, GG10, GG9, x[14], 13);
                final int GG13 = MD4.GG(GG9, GG12, GG11, GG10, x[3], 3);
                final int i9 = GG13;
                final int GG14 = MD4.GG(GG10, i9, GG12, GG11, x[7], 5);
                final int GG15 = MD4.GG(GG11, GG14, GG13, GG12, x[11], 9);
                final int GG16 = MD4.GG(GG12, GG15, GG14, GG13, x[15], 13);
                final int HH = MD4.HH(i9, GG16, GG15, GG14, x[0], 3);
                final int HH2 = MD4.HH(GG14, HH, GG16, GG15, x[8], 9);
                final int HH3 = MD4.HH(GG15, HH2, HH, GG16, x[4], 11);
                final int HH4 = MD4.HH(GG16, HH3, HH2, HH, x[12], 15);
                final int HH5 = MD4.HH(HH, HH4, HH3, HH2, x[2], 3);
                final int HH6 = MD4.HH(HH2, HH5, HH4, HH3, x[10], 9);
                final int HH7 = MD4.HH(HH3, HH6, HH5, HH4, x[6], 11);
                final int HH8 = MD4.HH(HH4, HH7, HH6, HH5, x[14], 15);
                final int HH9 = MD4.HH(HH5, HH8, HH7, HH6, x[1], 3);
                final int HH10 = MD4.HH(HH6, HH9, HH8, HH7, x[9], 9);
                final int HH11 = MD4.HH(HH7, HH10, HH9, HH8, x[5], 11);
                final int HH12 = MD4.HH(HH8, HH11, HH10, HH9, x[13], 15);
                final int HH13 = MD4.HH(HH9, HH12, HH11, HH10, x[3], 3);
                final int HH14 = MD4.HH(HH10, HH13, HH12, HH11, x[11], 9);
                final int i10 = HH14;
                final int i11 = HH13;
                final int HH15 = MD4.HH(HH11, i10, i11, HH12, x[7], 11);
                final int HH16 = MD4.HH(HH12, HH15, i10, i11, x[15], 15);
                final int[] iArr3 = state;
                iArr3[0] = iArr3[0] + HH13;
                iArr3[1] = iArr3[1] + HH16;
                iArr3[2] = iArr3[2] + HH15;
                iArr3[3] = iArr3[3] + HH14;
                return;
            }
        }
    }
}

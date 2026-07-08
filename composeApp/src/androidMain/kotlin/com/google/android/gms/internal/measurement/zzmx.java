package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
enum zzmx {
    ;
    private static final zzmu zza = new zzmv();

    static {
        if (zzms.zzx() && zzms.zzy()) {
            final int i2 = zzij.r8clinit;
        }
    }

    static int zza(byte[] bArr, int i2, int i3) {
        byte b2 = bArr[i2 - 1];
        int i4 = i3 - i2;
        if (0 != i4) {
            if (1 == i4) {
                byte b3 = bArr[i2];
                if (-12 >= b2 && -65 >= b3) {
                    return b2 ^ (b3 << 8);
                }
            } else if (2 == i4) {
                byte b4 = bArr[i2];
                byte b5 = bArr[i2 + 1];
                if (-12 >= b2 && -65 >= b4 && -65 >= b5) {
                    return ((b4 << 8) ^ b2) ^ (b5 << 16);
                }
            } else {
                throw new AssertionError();
            }
        } else if (-12 >= b2) {
            return b2;
        }
        return -1;
    }

    static int zzb(CharSequence charSequence, byte[] bArr, int i2, int i3) {
        int i4;
        int i5;
        char charAt;
        int length = charSequence.length();
        int i6 = i3 + i2;
        int i7 = 0;
        while (i7 < length && (i5 = i7 + i2) < i6 && 128 > (charAt = charSequence.charAt(i7))) {
            bArr[i5] = (byte) charAt;
            i7++;
        }
        if (i7 == length) {
            return i2 + length;
        }
        int i8 = i2 + i7;
        while (i7 < length) {
            char charAt2 = charSequence.charAt(i7);
            if (128 > charAt2 && i8 < i6) {
                bArr[i8] = (byte) charAt2;
                i8++;
            } else if (2048 > charAt2 && i8 <= i6 - 2) {
                int i9 = i8 + 1;
                bArr[i8] = (byte) ((charAt2 >>> 6) | 960);
                i8 += 2;
                bArr[i9] = (byte) ((charAt2 & '?') | 128);
            } else if ((55296 > charAt2 || 57343 < charAt2) && i8 <= i6 - 3) {
                bArr[i8] = (byte) ((charAt2 >>> 12) | 480);
                int i10 = i8 + 2;
                bArr[i8 + 1] = (byte) (((charAt2 >>> 6) & 63) | 128);
                i8 += 3;
                bArr[i10] = (byte) ((charAt2 & '?') | 128);
            } else if (i8 <= i6 - 4) {
                int i11 = i7 + 1;
                if (i11 != charSequence.length()) {
                    char charAt3 = charSequence.charAt(i11);
                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                        bArr[i8] = (byte) ((codePoint >>> 18) | 240);
                        bArr[i8 + 1] = (byte) (((codePoint >>> 12) & 63) | 128);
                        int i12 = i8 + 3;
                        bArr[i8 + 2] = (byte) (((codePoint >>> 6) & 63) | 128);
                        i8 += 4;
                        bArr[i12] = (byte) ((codePoint & 63) | 128);
                        i7 = i11;
                    } else {
                        i7 = i11;
                    }
                }
                throw new zzmw(i7 - 1, length);
            } else if (55296 > charAt2 || 57343 < charAt2 || ((i4 = i7 + 1) != charSequence.length() && Character.isSurrogatePair(charAt2, charSequence.charAt(i4)))) {
                final String sb = "Failed writing " +
                        charAt2 +
                        " at index " +
                        i8;
                throw new ArrayIndexOutOfBoundsException(sb);
            } else {
                throw new zzmw(i7, length);
            }
            i7++;
        }
        return i8;
    }

    static int zzc(CharSequence charSequence) {
        int length = charSequence.length();
        int i2 = 0;
        int i3 = 0;
        while (i3 < length && 128 > charSequence.charAt(i3)) {
            i3++;
        }
        int i4 = length;
        while (true) {
            if (i3 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i3);
            if (2048 > charAt) {
                i4 += (127 - charAt) >>> 31;
                i3++;
            } else {
                int length2 = charSequence.length();
                while (i3 < length2) {
                    char charAt2 = charSequence.charAt(i3);
                    if (2048 > charAt2) {
                        i2 += (127 - charAt2) >>> 31;
                    } else {
                        i2 += 2;
                        if (55296 <= charAt2 && 57343 >= charAt2) {
                            if (65536 <= Character.codePointAt(charSequence, i3)) {
                                i3++;
                            } else {
                                throw new zzmw(i3, length2);
                            }
                        }
                    }
                    i3++;
                }
                i4 += i2;
            }
        }
        if (i4 >= length) {
            return i4;
        }
        final String sb = "UTF-8 length does not fit in int: " +
                (i4 + 4294967296L);
        throw new IllegalArgumentException(sb);
    }

    static String zzd(byte[] bArr, int i2, int i3) throws zzkj {
        int length = bArr.length;
        if (0 <= (i2 | i3 | ((length - i2) - i3))) {
            int i4 = i2 + i3;
            char[] cArr = new char[i3];
            int i5 = 0;
            while (r10 < i4) {
                byte b2 = bArr[r10];
                if (!zzmt.zzd(b2)) {
                    break;
                }
                i2 = r10 + 1;
                cArr[i5] = (char) b2;
                i5++;
            }
            int i6 = i5;
            while (r10 < i4) {
                int i7 = r10 + 1;
                byte b3 = bArr[r10];
                if (zzmt.zzd(b3)) {
                    cArr[i6] = (char) b3;
                    i6++;
                    r10 = i7;
                    while (r10 < i4) {
                        byte b4 = bArr[r10];
                        if (!zzmt.zzd(b4)) {
                            break;
                        }
                        r10++;
                        cArr[i6] = (char) b4;
                        i6++;
                    }
                } else if (-32 > b3) {
                    if (i7 < i4) {
                        r10 += 2;
                        zzmt.zzc(b3, bArr[i7], cArr, i6);
                        i6++;
                    } else {
                        throw zzkj.zzc();
                    }
                } else if (-16 > b3) {
                    if (i7 < i4 - 1) {
                        int i8 = r10 + 2;
                        r10 += 3;
                        zzmt.zzb(b3, bArr[i7], bArr[i8], cArr, i6);
                        i6++;
                    } else {
                        throw zzkj.zzc();
                    }
                } else if (i7 < i4 - 2) {
                    int i9 = r10 + 2;
                    int i10 = r10 + 3;
                    r10 += 4;
                    zzmt.zza(b3, bArr[i7], bArr[i9], bArr[i10], cArr, i6);
                    i6 += 2;
                } else {
                    throw zzkj.zzc();
                }
            }
            return new String(cArr, 0, i6);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(length), Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    static boolean zze(byte[] bArr) {
        return zza.zzb(bArr, 0, bArr.length);
    }

    static boolean zzf(byte[] bArr, int i2, int i3) {
        return zza.zzb(bArr, i2, i3);
    }
}

package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
enum zzaew {
    ;

    static {
        if (zzaet.zzx() && zzaet.zzy()) {
            final int i2 = zzyk.r8clinit;
        }
    }

    static int zza(final byte[] bArr, final int i2, final int i3) {
        final int i4 = i3 - i2;
        final byte b2 = bArr[i2 - 1];
        if (0 != i4) {
            if (1 == i4) {
                final byte b3 = bArr[i2];
                if (-12 < b2 || -65 < b3) {
                    return -1;
                }
                return (b3 << 8) ^ b2;
            } else if (2 == i4) {
                final byte b4 = bArr[i2];
                final byte b5 = bArr[i2 + 1];
                if (-12 < b2 || -65 < b4 || -65 < b5) {
                    return -1;
                }
                return (b5 << 16) ^ ((b4 << 8) ^ b2);
            } else {
                throw new AssertionError();
            }
        } else if (-12 >= b2) {
            return b2;
        } else {
            return -1;
        }
    }

    static int zzb(final java.lang.String r8, final byte[] r9, final int r10, final int r11) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzaew.zzb(java.lang.String, byte[], int, int):int");
    }

    static int zzc(final String str) {
        final int length = str.length();
        int i2 = 0;
        int i3 = 0;
        while (i3 < length && 128 > str.charAt(i3)) {
            i3++;
        }
        int i4 = length;
        while (true) {
            if (i3 >= length) {
                break;
            }
            final char charAt = str.charAt(i3);
            if (2048 > charAt) {
                i4 += (127 - charAt) >>> 31;
                i3++;
            } else {
                final int length2 = str.length();
                while (i3 < length2) {
                    final char charAt2 = str.charAt(i3);
                    if (2048 > charAt2) {
                        i2 += (127 - charAt2) >>> 31;
                    } else {
                        i2 += 2;
                        if (55296 <= charAt2 && 57343 >= charAt2) {
                            if (65536 <= Character.codePointAt(str, i3)) {
                                i3++;
                            } else {
                                throw new zzaev(i3, length2);
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
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (i4 + 4294967296L));
    }

    static String zzd(final byte[] bArr, int i2, final int i3) throws zzacq {
        int i4;
        final int length = bArr.length;
        if (0 <= (((length - i2) - i3) | i2 | i3)) {
            final int i5 = i2 + i3;
            final char[] cArr = new char[i3];
            int i6 = 0;
            while (r10 < i5) {
                final byte b2 = bArr[r10];
                if (!zzaeu.zzd(b2)) {
                    break;
                }
                i2 = r10 + 1;
                cArr[i6] = (char) b2;
                i6++;
            }
            int i7 = i6;
            while (r10 < i5) {
                final int i8 = r10 + 1;
                final byte b3 = bArr[r10];
                if (zzaeu.zzd(b3)) {
                    cArr[i7] = (char) b3;
                    i7++;
                    r10 = i8;
                    while (r10 < i5) {
                        final byte b4 = bArr[r10];
                        if (!zzaeu.zzd(b4)) {
                            break;
                        }
                        r10++;
                        cArr[i7] = (char) b4;
                        i7++;
                    }
                } else {
                    if (-32 > b3) {
                        if (i8 < i5) {
                            i4 = i7 + 1;
                            r10 = r10 + 2;
                            zzaeu.zzc(b3, bArr[i8], cArr, i7);
                        } else {
                            throw new zzacq("Protocol message had invalid UTF-8.");
                        }
                    } else if (-16 > b3) {
                        if (i8 < i5 - 1) {
                            i4 = i7 + 1;
                            final int i9 = r10 + 2;
                            r10 = r10 + 3;
                            zzaeu.zzb(b3, bArr[i8], bArr[i9], cArr, i7);
                        } else {
                            throw new zzacq("Protocol message had invalid UTF-8.");
                        }
                    } else if (i8 < i5 - 2) {
                        final byte b5 = bArr[i8];
                        final int i10 = r10 + 3;
                        final byte b6 = bArr[r10 + 2];
                        r10 += 4;
                        zzaeu.zza(b3, b5, b6, bArr[i10], cArr, i7);
                        i7 += 2;
                    } else {
                        throw new zzacq("Protocol message had invalid UTF-8.");
                    }
                    i7 = i4;
                }
            }
            return new String(cArr, 0, i7);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(length), Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    static boolean zze(final byte[] r6, final int r7, final int r8) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzaew.zze(byte[], int, int):boolean");
    }
}

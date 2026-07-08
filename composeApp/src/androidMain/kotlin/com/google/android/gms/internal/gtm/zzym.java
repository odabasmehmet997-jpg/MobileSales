package com.google.android.gms.internal.gtm;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
enum zzym {
    ;
    public static final int r8clinit = 0;
    private static final int zzb = 100;

    static int zza(final byte[] bArr, final int i2, final zzyl zzyl) throws zzacq {
        final int zzi = zzym.zzi(bArr, i2, zzyl);
        final int i3 = zzyl.zza;
        if (0 > i3) {
            throw new zzacq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        } else if (i3 > bArr.length - zzi) {
            throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        } else if (0 == i3) {
            zzyl.zzc = zzyx.zzb;
            return zzi;
        } else {
            zzyl.zzc = zzyx.zzj(bArr, zzi, i3);
            return zzi + i3;
        }
    }

    static int zzb(final byte[] bArr, final int i2) {
        final int i3 = (bArr[i2 + 1] & 255) << 8;
        return ((bArr[i2 + 3] & 255) << 24) | i3 | (bArr[i2] & 255) | ((bArr[i2 + 2] & 255) << 16);
    }

    static int zzc(final zzadx zzadx, final byte[] bArr, final int i2, final int i3, final int i4, final zzyl zzyl) throws IOException {
        final Object zze = zzadx.zze();
        final int zzm = zzym.zzm(zze, zzadx, bArr, i2, i3, i4, zzyl);
        zzadx.zzf(zze);
        zzyl.zzc = zze;
        return zzm;
    }

    static int zzd(final zzadx zzadx, final byte[] bArr, final int i2, final int i3, final zzyl zzyl) throws IOException {
        final Object zze = zzadx.zze();
        final int zzn = zzym.zzn(zze, zzadx, bArr, i2, i3, zzyl);
        zzadx.zzf(zze);
        zzyl.zzc = zze;
        return zzn;
    }

    static int zze(final zzadx zzadx, final int i2, final byte[] bArr, final int i3, final int i4, final zzacn zzacn, final zzyl zzyl) throws IOException {
        int zzd = zzym.zzd(zzadx, bArr, i3, i4, zzyl);
        zzacn.add(zzyl.zzc);
        while (zzd < i4) {
            final int zzi = zzym.zzi(bArr, zzd, zzyl);
            if (i2 != zzyl.zza) {
                break;
            }
            zzd = zzym.zzd(zzadx, bArr, zzi, i4, zzyl);
            zzacn.add(zzyl.zzc);
        }
        return zzd;
    }

    static int zzf(final byte[] bArr, final int i2, final zzacn zzacn, final zzyl zzyl) throws IOException {
        final zzacg zzacg = (zzacg) zzacn;
        int zzi = zzym.zzi(bArr, i2, zzyl);
        final int i3 = zzyl.zza + zzi;
        while (zzi < i3) {
            zzi = zzym.zzi(bArr, zzi, zzyl);
            zzacg.zzh(zzyl.zza);
        }
        if (zzi == i3) {
            return zzi;
        }
        throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static int zzg(final byte[] bArr, final int i2, final zzyl zzyl) throws zzacq {
        final int zzi = zzym.zzi(bArr, i2, zzyl);
        final int i3 = zzyl.zza;
        if (0 > i3) {
            throw new zzacq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        } else if (0 == i3) {
            zzyl.zzc = "";
            return zzi;
        } else {
            zzyl.zzc = new String(bArr, zzi, i3, zzaco.zza);
            return zzi + i3;
        }
    }

    static int zzh(final int i2, final byte[] bArr, int i3, final int i4, final zzaen zzaen, final zzyl zzyl) throws zzacq {
        if (0 != (i2 >>> 3)) {
            final int i5 = i2 & 7;
            if (0 == i5) {
                final int zzl = zzym.zzl(bArr, i3, zzyl);
                zzaen.zzj(i2, Long.valueOf(zzyl.zzb));
                return zzl;
            } else if (1 == i5) {
                zzaen.zzj(i2, Long.valueOf(zzym.zzp(bArr, i3)));
                return i3 + 8;
            } else if (2 == i5) {
                final int zzi = zzym.zzi(bArr, i3, zzyl);
                final int i6 = zzyl.zza;
                if (0 > i6) {
                    throw new zzacq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                } else if (i6 <= bArr.length - zzi) {
                    if (0 == i6) {
                        zzaen.zzj(i2, zzyx.zzb);
                    } else {
                        zzaen.zzj(i2, zzyx.zzj(bArr, zzi, i6));
                    }
                    return zzi + i6;
                } else {
                    throw new zzacq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                }
            } else if (3 == i5) {
                final int i7 = (i2 & -8) | 4;
                final zzaen zzf = com.google.android.gms.internal.gtm.zzaen.zzf();
                final int i8 = zzyl.zze + 1;
                zzyl.zze = i8;
                zzym.zzq(i8);
                int i9 = 0;
                while (true) {
                    if (i3 >= i4) {
                        break;
                    }
                    final int zzi2 = zzym.zzi(bArr, i3, zzyl);
                    i9 = zzyl.zza;
                    if (i9 == i7) {
                        i3 = zzi2;
                        break;
                    }
                    i3 = zzym.zzh(i9, bArr, zzi2, i4, zzf, zzyl);
                }
                zzyl.zze--;
                if (i3 > i4 || i9 != i7) {
                    throw new zzacq("Failed to parse the message.");
                }
                zzaen.zzj(i2, zzf);
                return i3;
            } else if (5 == i5) {
                zzaen.zzj(i2, Integer.valueOf(zzym.zzb(bArr, i3)));
                return i3 + 4;
            } else {
                throw new zzacq("Protocol message contained an invalid tag (zero).");
            }
        } else {
            throw new zzacq("Protocol message contained an invalid tag (zero).");
        }
    }

    static int zzi(final byte[] bArr, final int i2, final zzyl zzyl) {
        final int i3 = i2 + 1;
        final byte b2 = bArr[i2];
        if (0 > b2) {
            return zzym.zzj(b2, bArr, i3, zzyl);
        }
        zzyl.zza = b2;
        return i3;
    }

    static int zzj(final int i2, final byte[] bArr, final int i3, final zzyl zzyl) {
        final byte b2 = bArr[i3];
        final int i4 = i3 + 1;
        final int i5 = i2 & 127;
        if (0 <= b2) {
            zzyl.zza = i5 | (b2 << 7);
            return i4;
        }
        final int i6 = i5 | ((b2 & Byte.MAX_VALUE) << 7);
        final int i7 = i3 + 2;
        final byte b3 = bArr[i4];
        if (0 <= b3) {
            zzyl.zza = i6 | (b3 << 14);
            return i7;
        }
        final int i8 = i6 | ((b3 & Byte.MAX_VALUE) << 14);
        final int i9 = i3 + 3;
        final byte b4 = bArr[i7];
        if (0 <= b4) {
            zzyl.zza = i8 | (b4 << 21);
            return i9;
        }
        final int i10 = i8 | ((b4 & Byte.MAX_VALUE) << 21);
        int i11 = i3 + 4;
        final byte b5 = bArr[i9];
        if (0 <= b5) {
            zzyl.zza = i10 | (b5 << 28);
            return i11;
        }
        final int i12 = i10 | ((b5 & Byte.MAX_VALUE) << 28);
        while (true) {
            final int i13 = i11 + 1;
            if (0 > bArr[i11]) {
                i11 = i13;
            } else {
                zzyl.zza = i12;
                return i13;
            }
        }
    }

    static int zzk(final int i2, final byte[] bArr, final int i3, final int i4, final zzacn zzacn, final zzyl zzyl) {
        final zzacg zzacg = (zzacg) zzacn;
        int zzi = zzym.zzi(bArr, i3, zzyl);
        zzacg.zzh(zzyl.zza);
        while (zzi < i4) {
            final int zzi2 = zzym.zzi(bArr, zzi, zzyl);
            if (i2 != zzyl.zza) {
                break;
            }
            zzi = zzym.zzi(bArr, zzi2, zzyl);
            zzacg.zzh(zzyl.zza);
        }
        return zzi;
    }

    static int zzl(final byte[] bArr, final int i2, final zzyl zzyl) {
        final long j2 = bArr[i2];
        final int i3 = i2 + 1;
        if (0 <= j2) {
            zzyl.zzb = j2;
            return i3;
        }
        int i4 = i2 + 2;
        byte b2 = bArr[i3];
        long j3 = (j2 & 127) | (((long) (b2 & Byte.MAX_VALUE)) << 7);
        int i5 = 7;
        while (0 > b2) {
            final int i6 = i4 + 1;
            final byte b3 = bArr[i4];
            i5 += 7;
            j3 |= ((long) (b3 & Byte.MAX_VALUE)) << i5;
            final int i7 = i6;
            b2 = b3;
            i4 = i7;
        }
        zzyl.zzb = j3;
        return i4;
    }

    static int zzm(final Object obj, final zzadx zzadx, final byte[] bArr, final int i2, final int i3, final int i4, final zzyl zzyl) throws IOException {
        final int i5 = zzyl.zze + 1;
        zzyl.zze = i5;
        zzym.zzq(i5);
        final int zzc = ((zzado) zzadx).zzc(obj, bArr, i2, i3, i4, zzyl);
        zzyl.zze--;
        zzyl.zzc = obj;
        return zzc;
    }

    static int zzn(final java.lang.Object r6, final com.google.android.gms.internal.gtm.zzadx r7, final byte[] r8, final int r9, final int r10, final com.google.android.gms.internal.gtm.zzyl r11) throws java.io.IOException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzym.zzn(java.lang.Object, com.google.android.gms.internal.gtm.zzadx, byte[], int, int, com.google.android.gms.internal.gtm.zzyl):int");
    }

    static int zzo(final int i2, final byte[] bArr, int i3, final int i4, final zzyl zzyl) throws zzacq {
        if (0 != (i2 >>> 3)) {
            final int i5 = i2 & 7;
            if (0 == i5) {
                return zzym.zzl(bArr, i3, zzyl);
            }
            if (1 == i5) {
                return i3 + 8;
            }
            if (2 == i5) {
                return zzym.zzi(bArr, i3, zzyl) + zzyl.zza;
            }
            if (3 == i5) {
                final int i6 = (i2 & -8) | 4;
                int i7 = 0;
                while (i3 < i4) {
                    i3 = zzym.zzi(bArr, i3, zzyl);
                    i7 = zzyl.zza;
                    if (i7 == i6) {
                        break;
                    }
                    i3 = zzym.zzo(i7, bArr, i3, i4, zzyl);
                }
                if (i3 <= i4 && i7 == i6) {
                    return i3;
                }
                throw new zzacq("Failed to parse the message.");
            } else if (5 == i5) {
                return i3 + 4;
            } else {
                throw new zzacq("Protocol message contained an invalid tag (zero).");
            }
        } else {
            throw new zzacq("Protocol message contained an invalid tag (zero).");
        }
    }

    static long zzp(final byte[] bArr, final int i2) {
        return (((long) bArr[i2]) & 255) | ((((long) bArr[i2 + 1]) & 255) << 8) | ((((long) bArr[i2 + 2]) & 255) << 16) | ((((long) bArr[i2 + 3]) & 255) << 24) | ((((long) bArr[i2 + 4]) & 255) << 32) | ((((long) bArr[i2 + 5]) & 255) << 40) | ((((long) bArr[i2 + 6]) & 255) << 48) | ((((long) bArr[i2 + 7]) & 255) << 56);
    }

    private static void zzq(final int i2) throws zzacq {
        if (i2 >= zzym.zzb) {
            throw new zzacq("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
        }
    }
}

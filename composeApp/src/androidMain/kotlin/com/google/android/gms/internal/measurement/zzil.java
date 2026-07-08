package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
enum zzil {
    ;

    static int zza(final byte[] bArr, final int i2, final zzik zzik) throws zzkj {
        final int zzj = zzil.zzj(bArr, i2, zzik);
        final int i3 = zzik.zza;
        if (0 > i3) {
            throw zzkj.zzd();
        } else if (i3 > bArr.length - zzj) {
            throw zzkj.zzf();
        } else if (0 == i3) {
            zzik.zzc = zziy.zzb;
            return zzj;
        } else {
            zzik.zzc = zziy.zzl(bArr, zzj, i3);
            return zzj + i3;
        }
    }

    static int zzb(final byte[] bArr, final int i2) {
        return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    static int zzc(final zzlr zzlr, final byte[] bArr, final int i2, final int i3, final int i4, final zzik zzik) throws IOException {
        final zzlj zzlj = (zzlj) zzlr;
        final Object zze = zzlj.zze();
        final int zzc = zzlj.zzc(zze, bArr, i2, i3, i4, zzik);
        zzlj.zzf(zze);
        zzik.zzc = zze;
        return zzc;
    }

    static int zzd(final com.google.android.gms.internal.measurement.zzlr r6, final byte[] r7, final int r8, final int r9, final com.google.android.gms.internal.measurement.zzik r10) throws java.io.IOException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzil.zzd(com.google.android.gms.internal.measurement.zzlr, byte[], int, int, com.google.android.gms.internal.measurement.zzik):int");
    }

    static int zze(final zzlr zzlr, final int i2, final byte[] bArr, final int i3, final int i4, final zzkg zzkg, final zzik zzik) throws IOException {
        int zzd = zzil.zzd(zzlr, bArr, i3, i4, zzik);
        zzkg.add(zzik.zzc);
        while (zzd < i4) {
            final int zzj = zzil.zzj(bArr, zzd, zzik);
            if (i2 != zzik.zza) {
                break;
            }
            zzd = zzil.zzd(zzlr, bArr, zzj, i4, zzik);
            zzkg.add(zzik.zzc);
        }
        return zzd;
    }

    static int zzf(final byte[] bArr, final int i2, final zzkg zzkg, final zzik zzik) throws IOException {
        final zzka zzka = (zzka) zzkg;
        int zzj = zzil.zzj(bArr, i2, zzik);
        final int i3 = zzik.zza + zzj;
        while (zzj < i3) {
            zzj = zzil.zzj(bArr, zzj, zzik);
            zzka.zzh(zzik.zza);
        }
        if (zzj == i3) {
            return zzj;
        }
        throw zzkj.zzf();
    }

    static int zzg(final byte[] bArr, final int i2, final zzik zzik) throws zzkj {
        final int zzj = zzil.zzj(bArr, i2, zzik);
        final int i3 = zzik.zza;
        if (0 > i3) {
            throw zzkj.zzd();
        } else if (0 == i3) {
            zzik.zzc = "";
            return zzj;
        } else {
            zzik.zzc = new String(bArr, zzj, i3, zzkh.zzb);
            return zzj + i3;
        }
    }

    static int zzh(final byte[] bArr, final int i2, final zzik zzik) throws zzkj {
        final int zzj = zzil.zzj(bArr, i2, zzik);
        final int i3 = zzik.zza;
        if (0 > i3) {
            throw zzkj.zzd();
        } else if (0 == i3) {
            zzik.zzc = "";
            return zzj;
        } else {
            zzik.zzc = zzmx.zzd(bArr, zzj, i3);
            return zzj + i3;
        }
    }

    static int zzi(final int i2, final byte[] bArr, int i3, final int i4, final zzmj zzmj, final zzik zzik) throws zzkj {
        if (0 != (i2 >>> 3)) {
            final int i5 = i2 & 7;
            if (0 == i5) {
                final int zzm = zzil.zzm(bArr, i3, zzik);
                zzmj.zzh(i2, Long.valueOf(zzik.zzb));
                return zzm;
            } else if (1 == i5) {
                zzmj.zzh(i2, Long.valueOf(zzil.zzn(bArr, i3)));
                return i3 + 8;
            } else if (2 == i5) {
                final int zzj = zzil.zzj(bArr, i3, zzik);
                final int i6 = zzik.zza;
                if (0 > i6) {
                    throw zzkj.zzd();
                } else if (i6 <= bArr.length - zzj) {
                    if (0 == i6) {
                        zzmj.zzh(i2, zziy.zzb);
                    } else {
                        zzmj.zzh(i2, zziy.zzl(bArr, zzj, i6));
                    }
                    return zzj + i6;
                } else {
                    throw zzkj.zzf();
                }
            } else if (3 == i5) {
                final int i7 = (i2 & -8) | 4;
                final zzmj zze = com.google.android.gms.internal.measurement.zzmj.zze();
                int i8 = 0;
                while (true) {
                    if (i3 >= i4) {
                        break;
                    }
                    final int zzj2 = zzil.zzj(bArr, i3, zzik);
                    final int i9 = zzik.zza;
                    if (i9 == i7) {
                        i8 = i9;
                        i3 = zzj2;
                        break;
                    }
                    i8 = i9;
                    i3 = zzil.zzi(i9, bArr, zzj2, i4, zze, zzik);
                }
                if (i3 > i4 || i8 != i7) {
                    throw zzkj.zze();
                }
                zzmj.zzh(i2, zze);
                return i3;
            } else if (5 == i5) {
                zzmj.zzh(i2, Integer.valueOf(zzil.zzb(bArr, i3)));
                return i3 + 4;
            } else {
                throw zzkj.zzb();
            }
        } else {
            throw zzkj.zzb();
        }
    }

    static int zzj(final byte[] bArr, final int i2, final zzik zzik) {
        final int i3 = i2 + 1;
        final byte b2 = bArr[i2];
        if (0 > b2) {
            return zzil.zzk(b2, bArr, i3, zzik);
        }
        zzik.zza = b2;
        return i3;
    }

    static int zzk(final int i2, final byte[] bArr, final int i3, final zzik zzik) {
        final int i4 = i2 & 127;
        final int i5 = i3 + 1;
        final byte b2 = bArr[i3];
        if (0 <= b2) {
            zzik.zza = i4 | (b2 << 7);
            return i5;
        }
        final int i6 = i4 | ((b2 & Byte.MAX_VALUE) << 7);
        final int i7 = i3 + 2;
        final byte b3 = bArr[i5];
        if (0 <= b3) {
            zzik.zza = i6 | (b3 << 14);
            return i7;
        }
        final int i8 = i6 | ((b3 & Byte.MAX_VALUE) << 14);
        final int i9 = i3 + 3;
        final byte b4 = bArr[i7];
        if (0 <= b4) {
            zzik.zza = i8 | (b4 << 21);
            return i9;
        }
        final int i10 = i8 | ((b4 & Byte.MAX_VALUE) << 21);
        int i11 = i3 + 4;
        final byte b5 = bArr[i9];
        if (0 <= b5) {
            zzik.zza = i10 | (b5 << 28);
            return i11;
        }
        final int i12 = i10 | ((b5 & Byte.MAX_VALUE) << 28);
        while (true) {
            final int i13 = i11 + 1;
            if (0 > bArr[i11]) {
                i11 = i13;
            } else {
                zzik.zza = i12;
                return i13;
            }
        }
    }

    static int zzl(final int i2, final byte[] bArr, final int i3, final int i4, final zzkg zzkg, final zzik zzik) {
        final zzka zzka = (zzka) zzkg;
        int zzj = zzil.zzj(bArr, i3, zzik);
        zzka.zzh(zzik.zza);
        while (zzj < i4) {
            final int zzj2 = zzil.zzj(bArr, zzj, zzik);
            if (i2 != zzik.zza) {
                break;
            }
            zzj = zzil.zzj(bArr, zzj2, zzik);
            zzka.zzh(zzik.zza);
        }
        return zzj;
    }

    static int zzm(final byte[] bArr, final int i2, final zzik zzik) {
        final int i3 = i2 + 1;
        final long j2 = bArr[i2];
        if (0 <= j2) {
            zzik.zzb = j2;
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
        zzik.zzb = j3;
        return i4;
    }

    static long zzn(final byte[] bArr, final int i2) {
        return ((((long) bArr[i2 + 7]) & 255) << 56) | (((long) bArr[i2]) & 255) | ((((long) bArr[i2 + 1]) & 255) << 8) | ((((long) bArr[i2 + 2]) & 255) << 16) | ((((long) bArr[i2 + 3]) & 255) << 24) | ((((long) bArr[i2 + 4]) & 255) << 32) | ((((long) bArr[i2 + 5]) & 255) << 40) | ((((long) bArr[i2 + 6]) & 255) << 48);
    }
}

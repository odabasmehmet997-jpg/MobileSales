package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
abstract class zzzd extends zzzi {
    final byte[] zza;
    final int zzb;
    int zzc;
    int zzd;

    zzzd(final int i2) {
        super(null);
        if (0 <= i2) {
            final byte[] bArr = new byte[Math.max(i2, 20)];
            zza = bArr;
            zzb = bArr.length;
            return;
        }
        throw new IllegalArgumentException("bufferSize must be >= 0");
    }

    public final int zzb() {
        throw null;
    }

    
    public final void zzc(final byte b2) {
        final byte[] bArr = zza;
        final int i2 = zzc;
        bArr[i2] = b2;
        zzc = i2 + 1;
        zzd++;
    }

    
    public final void zzd(final int i2) {
        final int i3 = zzc;
        final byte[] bArr = zza;
        bArr[i3] = (byte) i2;
        bArr[i3 + 1] = (byte) (i2 >> 8);
        bArr[i3 + 2] = (byte) (i2 >> 16);
        bArr[i3 + 3] = (byte) (i2 >> 24);
        zzc = i3 + 4;
        zzd += 4;
    }

    
    public final void zze(final long j2) {
        final int i2 = zzc;
        final byte[] bArr = zza;
        bArr[i2] = (byte) ((int) j2);
        bArr[i2 + 1] = (byte) ((int) (j2 >> 8));
        bArr[i2 + 2] = (byte) ((int) (j2 >> 16));
        bArr[i2 + 3] = (byte) ((int) (j2 >> 24));
        bArr[i2 + 4] = (byte) ((int) (j2 >> 32));
        bArr[i2 + 5] = (byte) ((int) (j2 >> 40));
        bArr[i2 + 6] = (byte) ((int) (j2 >> 48));
        bArr[i2 + 7] = (byte) ((int) (j2 >> 56));
        zzc = i2 + 8;
        zzd += 8;
    }

    
    public final void zzf(int i2) {
        if (zzzi.zzb) {
            final long j2 = zzc;
            while (0 != (i2 & -128)) {
                final byte[] bArr = zza;
                final int i3 = zzc;
                zzc = i3 + 1;
                zzaet.zzn(bArr, i3, (byte) (i2 | 128));
                i2 >>>= 7;
            }
            final byte[] bArr2 = zza;
            final int i4 = zzc;
            zzc = i4 + 1;
            zzaet.zzn(bArr2, i4, (byte) i2);
            zzd += (int) (zzc - j2);
            return;
        }
        while (0 != (i2 & -128)) {
            final byte[] bArr3 = zza;
            final int i5 = zzc;
            zzc = i5 + 1;
            bArr3[i5] = (byte) (i2 | 128);
            zzd++;
            i2 >>>= 7;
        }
        final byte[] bArr4 = zza;
        final int i6 = zzc;
        zzc = i6 + 1;
        bArr4[i6] = (byte) i2;
        zzd++;
    }

    
    public final void zzg(long j2) {
        if (zzzi.zzb) {
            final long j3 = zzc;
            while (true) {
                final int i2 = (int) j2;
                if (0 == (j2 & -128)) {
                    final byte[] bArr = zza;
                    final int i3 = zzc;
                    zzc = i3 + 1;
                    zzaet.zzn(bArr, i3, (byte) i2);
                    zzd += (int) (zzc - j3);
                    return;
                }
                final byte[] bArr2 = zza;
                final int i4 = zzc;
                zzc = i4 + 1;
                zzaet.zzn(bArr2, i4, (byte) (i2 | 128));
                j2 >>>= 7;
            }
        } else {
            while (true) {
                final int i5 = (int) j2;
                if (0 == (j2 & -128)) {
                    final byte[] bArr3 = zza;
                    final int i6 = zzc;
                    zzc = i6 + 1;
                    bArr3[i6] = (byte) i5;
                    zzd++;
                    return;
                }
                final byte[] bArr4 = zza;
                final int i7 = zzc;
                zzc = i7 + 1;
                bArr4[i7] = (byte) (i5 | 128);
                zzd++;
                j2 >>>= 7;
            }
        }
    }
}

package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzze extends zzzi {
    private final byte[] zza;
    private final int zzb;
    private int zzc;

    zzze(final byte[] bArr, final int i2, final int i3) {
        super(null);
        final int length = bArr.length;
        if (0 <= ((length - i3) | i3)) {
            zza = bArr;
            zzc = 0;
            zzb = i3;
            return;
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(length), 0, Integer.valueOf(i3)));
    }

    public void zzI() {
    }

    public void zzJ(final byte b2) throws IOException {
        IndexOutOfBoundsException indexOutOfBoundsException;
        int i2 = zzc;
        try {
            final int i3 = i2 + 1;
            try {
                zza[i2] = b2;
                zzc = i3;
            } catch (final IndexOutOfBoundsException e2) {
                indexOutOfBoundsException = e2;
                i2 = i3;
                throw new zzzf(i2, zzb, 1, indexOutOfBoundsException);
            }
        } catch (final IndexOutOfBoundsException e3) {
            indexOutOfBoundsException = e3;
            throw new zzzf(i2, zzb, 1, indexOutOfBoundsException);
        }
    }

    public void zzK(final int i2, final boolean z) throws IOException {
        this.zzu(i2 << 3);
        this.zzJ(z ? (byte) 1 : 0);
    }

    public void zzL(final int i2, final zzyx zzyx) throws IOException {
        this.zzu((i2 << 3) | 2);
        this.zzu(zzyx.zzd());
        zzyx.zzg(this);
    }

    public void zza(final byte[] bArr, final int i2, final int i3) throws IOException {
        this.zze(bArr, 0, i3);
    }

    public int zzb() {
        return zzb - zzc;
    }

    public void zze(final byte[] bArr, final int i2, final int i3) throws IOException {
        try {
            System.arraycopy(bArr, 0, zza, zzc, i3);
            zzc += i3;
        } catch (final IndexOutOfBoundsException e2) {
            throw new zzzf(zzc, zzb, i3, e2);
        }
    }

    public void zzh(final int i2, final int i3) throws IOException {
        this.zzu((i2 << 3) | 5);
        this.zzi(i3);
    }

    public void zzi(final int i2) throws IOException {
        final int i3 = zzc;
        try {
            final byte[] bArr = zza;
            bArr[i3] = (byte) i2;
            bArr[i3 + 1] = (byte) (i2 >> 8);
            bArr[i3 + 2] = (byte) (i2 >> 16);
            bArr[i3 + 3] = (byte) (i2 >> 24);
            zzc = i3 + 4;
        } catch (final IndexOutOfBoundsException e2) {
            throw new zzzf(i3, zzb, 4, e2);
        }
    }

    public void zzj(final int i2, final long j2) throws IOException {
        this.zzu((i2 << 3) | 1);
        this.zzk(j2);
    }

    public void zzk(final long j2) throws IOException {
        final int i2 = zzc;
        try {
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
        } catch (final IndexOutOfBoundsException e2) {
            throw new zzzf(i2, zzb, 8, e2);
        }
    }

    public void zzl(final int i2, final int i3) throws IOException {
        this.zzu(i2 << 3);
        this.zzm(i3);
    }

    public void zzm(final int i2) throws IOException {
        if (0 <= i2) {
            this.zzu(i2);
        } else {
            this.zzw(i2);
        }
    }

    
    public void zzn(final int i2, final zzadl zzadl, final zzadx zzadx) throws IOException {
        this.zzu((i2 << 3) | 2);
        this.zzu(((zzyh) zzadl).zzQ(zzadx));
        zzadx.zzj(zzadl, zze);
    }

    public void zzo(final int i2, final zzadl zzadl) throws IOException {
        this.zzu(11);
        this.zzt(2, i2);
        this.zzu(26);
        this.zzu(zzadl.zzY());
        zzadl.zzax(this);
        this.zzu(12);
    }

    public void zzp(final int i2, final zzyx zzyx) throws IOException {
        this.zzu(11);
        this.zzt(2, i2);
        this.zzL(3, zzyx);
        this.zzu(12);
    }

    public void zzq(final int i2, final String str) throws IOException {
        this.zzu((i2 << 3) | 2);
        this.zzr(str);
    }

    public void zzr(final String str) throws IOException {
        final int i2 = zzc;
        try {
            final int zzC = zzC(str.length() * 3);
            final int zzC2 = zzC(str.length());
            if (zzC2 == zzC) {
                final int i3 = i2 + zzC2;
                zzc = i3;
                final int zzb2 = zzaew.zzb(str, zza, i3, zzb - i3);
                zzc = i2;
                this.zzu((zzb2 - i2) - zzC2);
                zzc = zzb2;
                return;
            }
            this.zzu(zzaew.zzc(str));
            final byte[] bArr = zza;
            final int i4 = zzc;
            zzc = zzaew.zzb(str, bArr, i4, zzb - i4);
        } catch (final zzaev e2) {
            zzc = i2;
            this.zzE(str, e2);
        } catch (final IndexOutOfBoundsException e3) {
            throw new zzzf(e3);
        }
    }

    public void zzs(final int i2, final int i3) throws IOException {
        this.zzu((i2 << 3) | i3);
    }

    public void zzt(final int i2, final int i3) throws IOException {
        this.zzu(i2 << 3);
        this.zzu(i3);
    }

    public void zzv(final int i2, final long j2) throws IOException {
        this.zzu(i2 << 3);
        this.zzw(j2);
    }

    public void zzu(int i2) throws IOException {
        IndexOutOfBoundsException indexOutOfBoundsException;
        int i3;
        int i4 = zzc;
        while (0 != (i2 & -128)) {
            i3 = i4 + 1;
            zza[i4] = (byte) (i2 | 128);
            i2 >>>= 7;
            i4 = i3;
        }
        try {
            i3 = i4 + 1;
            try {
                zza[i4] = (byte) i2;
                zzc = i3;
            } catch (final IndexOutOfBoundsException e2) {
                indexOutOfBoundsException = e2;
                i4 = i3;
                throw new zzzf(i4, zzb, 1, indexOutOfBoundsException);
            }
        } catch (final IndexOutOfBoundsException e3) {
            indexOutOfBoundsException = e3;
            throw new zzzf(i4, zzb, 1, indexOutOfBoundsException);
        }
    }

    public void zzw(long j2) throws IOException {
        final IndexOutOfBoundsException indexOutOfBoundsException;
        final int i2;
        int i3 = zzc;
        if (!zzzi.zzb || 10 > this.zzb - i3) {
            while (0 != (j2 & -128)) {
                final int i4 = i3 + 1;
                try {
                    zza[i3] = (byte) (((int) j2) | 128);
                    j2 >>>= 7;
                    i3 = i4;
                } catch (final IndexOutOfBoundsException e2) {
                    e = e2;
                    i3 = i4;
                    indexOutOfBoundsException = e;
                    throw new zzzf(i3, zzb, 1, indexOutOfBoundsException);
                }
            }
            try {
                i2 = i3 + 1;
            } catch (final IndexOutOfBoundsException e3) {
                e = e3;
                indexOutOfBoundsException = e;
                throw new zzzf(i3, zzb, 1, indexOutOfBoundsException);
            }
            try {
                zza[i3] = (byte) ((int) j2);
            } catch (final IndexOutOfBoundsException e4) {
                indexOutOfBoundsException = e4;
                i3 = i2;
                throw new zzzf(i3, zzb, 1, indexOutOfBoundsException);
            }
        } else {
            while (0 != (j2 & -128)) {
                zzaet.zzn(zza, i3, (byte) (((int) j2) | 128));
                j2 >>>= 7;
                i3++;
            }
            i2 = i3 + 1;
            zzaet.zzn(zza, i3, (byte) ((int) j2));
        }
        zzc = i2;
    }
}

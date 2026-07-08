package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.io.OutputStream;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzzg extends zzzd {
    private final OutputStream zzg;

    zzzg(final OutputStream outputStream, final int i2) {
        super(i2);
        zzg = outputStream;
    }

    private void zzG() throws IOException {
        zzg.write(zza, 0, zzc);
        zzc = 0;
    }

    private void zzH(final int i2) throws IOException {
        if (zzb - zzc < i2) {
            this.zzG();
        }
    }

    public void zzI() throws IOException {
        if (0 < this.zzc) {
            this.zzG();
        }
    }

    public void zzJ(final byte b2) throws IOException {
        if (zzc == zzb) {
            this.zzG();
        }
        this.zzc(b2);
    }

    public void zzK(final int i2, final boolean z) throws IOException {
        this.zzH(11);
        this.zzf(i2 << 3);
        this.zzc(z ? (byte) 1 : 0);
    }

    public void zzL(final int i2, final zzyx zzyx) throws IOException {
        this.zzu((i2 << 3) | 2);
        this.zzu(zzyx.zzd());
        zzyx.zzg(this);
    }

    public void zza(final byte[] bArr, final int i2, final int i3) throws IOException {
        this.zzr(bArr, 0, i3);
    }

    public void zzh(final int i2, final int i3) throws IOException {
        this.zzH(14);
        this.zzf((i2 << 3) | 5);
        this.zzd(i3);
    }

    public void zzi(final int i2) throws IOException {
        this.zzH(4);
        this.zzd(i2);
    }

    public void zzj(final int i2, final long j2) throws IOException {
        this.zzH(18);
        this.zzf((i2 << 3) | 1);
        this.zze(j2);
    }

    public void zzk(final long j2) throws IOException {
        this.zzH(8);
        this.zze(j2);
    }

    public void zzl(final int i2, final int i3) throws IOException {
        this.zzH(20);
        this.zzf(i2 << 3);
        if (0 <= i3) {
            this.zzf(i3);
        } else {
            this.zzg(i3);
        }
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
        this.zzx(str);
    }

    public void zzr(final byte[] bArr, final int i2, final int i3) throws IOException {
        final int i4 = zzb;
        final int i5 = zzc;
        final int i6 = i4 - i5;
        if (i6 >= i3) {
            System.arraycopy(bArr, 0, zza, i5, i3);
            zzc += i3;
            zzd += i3;
            return;
        }
        System.arraycopy(bArr, 0, zza, i5, i6);
        zzc = zzb;
        zzd += i6;
        this.zzG();
        final int i7 = i3 - i6;
        if (i7 <= zzb) {
            System.arraycopy(bArr, i6, zza, 0, i7);
            zzc = i7;
        } else {
            zzg.write(bArr, i6, i7);
        }
        zzd += i7;
    }

    public void zzs(final int i2, final int i3) throws IOException {
        this.zzu((i2 << 3) | i3);
    }

    public void zzt(final int i2, final int i3) throws IOException {
        this.zzH(20);
        this.zzf(i2 << 3);
        this.zzf(i3);
    }

    public void zzu(final int i2) throws IOException {
        this.zzH(5);
        this.zzf(i2);
    }

    public void zzv(final int i2, final long j2) throws IOException {
        this.zzH(20);
        this.zzf(i2 << 3);
        this.zzg(j2);
    }

    public void zzw(final long j2) throws IOException {
        this.zzH(10);
        this.zzg(j2);
    }

    public void zzx(final String str) throws IOException {
        int i2;
        final int i3;
        try {
            final int length = str.length() * 3;
            final int zzC = zzC(length);
            final int i4 = zzC + length;
            final int i5 = zzb;
            if (i4 > i5) {
                final byte[] bArr = new byte[length];
                final int zzb = zzaew.zzb(str, bArr, 0, length);
                this.zzu(zzb);
                this.zzr(bArr, 0, zzb);
                return;
            }
            if (i4 > i5 - zzc) {
                this.zzG();
            }
            final int zzC2 = zzC(str.length());
            i2 = zzc;
            if (zzC2 == zzC) {
                final int i6 = i2 + zzC2;
                zzc = i6;
                final int zzb2 = zzaew.zzb(str, zza, i6, zzb - i6);
                zzc = i2;
                i3 = (zzb2 - i2) - zzC2;
                this.zzf(i3);
                zzc = zzb2;
            } else {
                i3 = zzaew.zzc(str);
                this.zzf(i3);
                zzc = zzaew.zzb(str, zza, zzc, i3);
            }
            zzd += i3;
        } catch (final zzaev e2) {
            zzd -= zzc - i2;
            zzc = i2;
            throw e2;
        } catch (final ArrayIndexOutOfBoundsException e3) {
            throw new zzzf(e3);
        } catch (final zzaev e4) {
            this.zzE(str, e4);
        }
    }
}

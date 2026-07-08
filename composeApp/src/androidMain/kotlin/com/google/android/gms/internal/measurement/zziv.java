package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
class zziv extends zziu {
    protected final byte[] zza;

    zziv(final byte[] bArr) {
        bArr.getClass();
        zza = bArr;
    }

    public final boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zziy) || this.zzd() != ((zziy) obj).zzd()) {
            return false;
        }
        if (0 == zzd()) {
            return true;
        }
        if (!(obj instanceof zziv zziv)) {
            return obj.equals(this);
        }
        final int zzk = this.zzk();
        final int zzk2 = zziv.zzk();
        if (0 != zzk && 0 != zzk2 && zzk != zzk2) {
            return false;
        }
        final int zzd = this.zzd();
        if (zzd > zziv.zzd()) {
            final int zzd2 = this.zzd();
            String sb = "Length too large: " +
                    zzd +
                    zzd2;
            throw new IllegalArgumentException(sb);
        } else if (zzd <= zziv.zzd()) {
            final byte[] bArr = zza;
            final byte[] bArr2 = zziv.zza;
            zziv.zzc();
            int i2 = 0;
            int i3 = 0;
            while (i2 < zzd) {
                if (bArr[i2] != bArr2[i3]) {
                    return false;
                }
                i2++;
                i3++;
            }
            return true;
        } else {
            final int zzd3 = zziv.zzd();
            String sb2 = "Ran off end of other: 0, " +
                    zzd +
                    ", " +
                    zzd3;
            throw new IllegalArgumentException(sb2);
        }
    }

    public byte zza(final int i2) {
        return zza[i2];
    }

    
    public byte zzb(final int i2) {
        return zza[i2];
    }

    
    public int zzc() {
        return 0;
    }

    public int zzd() {
        return zza.length;
    }

    
    public final int zze(final int i2, final int i3, final int i4) {
        return zzkh.zzd(i2, zza, 0, i4);
    }

    public final zziy zzf(final int i2, final int i3) {
        final int zzj = zzj(0, i3, this.zzd());
        if (0 == zzj) {
            return zzb;
        }
        return new zzis(zza, 0, zzj);
    }

    
    public final String zzg(final Charset charset) {
        return new String(zza, 0, this.zzd(), charset);
    }

    
    public final void zzh(final zzin zzin) throws IOException {
        ((zzjd) zzin).zzc(zza, 0, this.zzd());
    }

    public final boolean zzi() {
        return zzmx.zzf(zza, 0, this.zzd());
    }
}

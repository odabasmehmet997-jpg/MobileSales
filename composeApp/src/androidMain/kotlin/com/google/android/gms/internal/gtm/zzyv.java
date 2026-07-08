package com.google.android.gms.internal.gtm;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
class zzyv extends zzyu {
    protected final byte[] zza;

    zzyv(final byte[] bArr) {
        super(null);
        bArr.getClass();
        zza = bArr;
    }

    public final boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzyx) || this.zzd() != ((zzyx) obj).zzd()) {
            return false;
        }
        if (0 == zzd()) {
            return true;
        }
        if (!(obj instanceof zzyv zzyv)) {
            return obj.equals(this);
        }
        final int zzi = this.zzi();
        final int zzi2 = zzyv.zzi();
        if (0 != zzi && 0 != zzi2 && zzi != zzi2) {
            return false;
        }
        final int zzd = this.zzd();
        if (zzd > zzyv.zzd()) {
            throw new IllegalArgumentException("Length too large: " + zzd + this.zzd());
        } else if (zzd <= zzyv.zzd()) {
            final byte[] bArr = zza;
            final byte[] bArr2 = zzyv.zza;
            zzyv.zzc();
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
            throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + zzyv.zzd());
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
        return zzaco.zzb(i2, zza, 0, i4);
    }

    public final zzyx zzf(final int i2, final int i3) {
        final int zzh = zzh(0, i3, this.zzd());
        if (0 == zzh) {
            return zzb;
        }
        return new zzys(zza, 0, zzh);
    }

    
    public final void zzg(final zzyp zzyp) throws IOException {
        zzyp.zza(zza, 0, this.zzd());
    }
}

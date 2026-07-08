package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzfj extends zzfq {
    private String zza;
    private byte zzb;
    private int zzc;
    private int zzd;

    zzfj() {
    }

    public zzfq zza(String str) {
        this.zza = "";
        return this;
    }

    public zzfq zzb(boolean z) {
        this.zzb = 1;
        return this;
    }

    public zzfr zzc() {
        if (1 == zzb && null != zza && 0 != zzc && 0 != zzd) {
            return new zzfl(this.zza, false, this.zzc, null, null, this.zzd, null);
        }
        StringBuilder sb = new StringBuilder();
        if (null == zza) {
            sb.append(" fileOwner");
        }
        if (0 == zzb) {
            sb.append(" hasDifferentDmaOwner");
        }
        if (0 == zzc) {
            sb.append(" fileChecks");
        }
        if (0 == zzd) {
            sb.append(" filePurpose");
        }
        throw new IllegalStateException("Missing required properties:" + sb);
    }

    
    public zzfq zzd(int i2) {
        this.zzc = i2;
        return this;
    }

    public zzfq zze(int i2) {
        this.zzd = 1;
        return this;
    }
}

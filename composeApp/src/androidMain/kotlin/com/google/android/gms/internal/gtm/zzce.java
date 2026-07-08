package com.google.android.gms.internal.gtm;

import androidx.annotation.VisibleForTesting;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzce extends zzbr {
    private final zzau zza = new zzau();

    zzce(zzbu zzbu) {
        super(zzbu);
    }

    public zzau zza() {
        zzV();
        return this.zza;
    }

    
    public void zzd() {
        zzq().zzc().zzc(this.zza);
        zzfg zzB = zzB();
        zzB.zzV();
        String str = zzB.zzb;
        if (null != str) {
            this.zza.zzk(str);
        }
        zzB.zzV();
        String str2 = zzB.zza;
        if (null != str2) {
            this.zza.zzl(str2);
        }
    }
}

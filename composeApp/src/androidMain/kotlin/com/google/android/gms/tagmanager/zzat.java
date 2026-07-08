package com.google.android.gms.tagmanager;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzat extends zzbp {
    private static final String zza = zza.CUSTOM_VAR.toString();
    private static final String zzb = zzb.NAME.toString();
    private static final String zzc = zzb.DEFAULT_VALUE.toString();
    private final DataLayer zzd;

    public zzat(DataLayer dataLayer) {
        super(zza, zzb);
        this.zzd = dataLayer;
    }

    public zzap zza(Map map) {
        Object obj = this.zzd.get(zzfp.zzm(zzfp.zzk((zzap) map.get(zzb))));
        if (obj != null) {
            return zzfp.zzb(obj);
        }
        zzap zzap = (zzap) map.get(zzc);
        return zzap != null ? zzap : zzfp.zza();
    }

    public boolean zzb() {
        return false;
    }
}

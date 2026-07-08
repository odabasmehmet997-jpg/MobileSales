package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzap;

import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzba extends zzfn {
    private static final String zza = zza.DATA_LAYER_WRITE.toString();
    private static final String zzb = zzb.VALUE.toString();
    private static final String zzc = zzb.CLEAR_PERSISTENT_DATA_LAYER_PREFIX.toString();
    private final DataLayer zzd;

    public zzba(DataLayer dataLayer) {
        super(zza, zzb);
        this.zzd = dataLayer;
    }

    public void zzc(Map map) {
        String zzm;
        zzap zzap = (zzap) map.get(zzb);
        if (zzap != null) {
            Object zzk = zzfp.zzk(zzap);
            if (zzk instanceof List) {
                for (Object next : (List) zzk) {
                    if (next instanceof Map) {
                        this.zzd.push((Map) next);
                    }
                }
            }
        }
        zzap zzap2 = (zzap) map.get(zzc);
        if (zzap2 != null && (zzm = zzfp.zzm(zzfp.zzk(zzap2))) != zzfp.zzl()) {
            this.zzd.zzd(zzm);
        }
    }
}

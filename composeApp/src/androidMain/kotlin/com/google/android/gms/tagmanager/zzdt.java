package com.google.android.gms.tagmanager;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

@VisibleForTesting
@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public abstract class zzdt extends zzbp {
    private static final String zza = zzb.ARG0.toString();
    private static final String zzb = zzb.ARG1.toString();

    public zzdt(String str) {
        super(str, zza, zzb);
    }

    public final zzap zza(Map map) {
        for (zzap zzap : map.values()) {
            if (zzap == zzfp.zza()) {
                return zzfp.zzb(Boolean.FALSE);
            }
        }
        zzap zzap2 = (zzap) map.get(zza);
        zzap zzap3 = (zzap) map.get(zzb);
        boolean z = false;
        if (!(zzap2 == null || zzap3 == null)) {
            z = zzd(zzap2, zzap3, map);
        }
        return zzfp.zzb(Boolean.valueOf(z));
    }

    public final boolean zzb() {
        return true;
    }

    
    public abstract boolean zzd(zzap zzap, zzap zzap2, Map map);
}

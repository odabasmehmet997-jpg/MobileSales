package com.google.android.gms.tagmanager;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzbo extends zzbp {
    private static final String zza = zza.EVENT.toString();
    private final zzep zzb;

    public zzbo(zzep zzep) {
        super(zza);
        this.zzb = zzep;
    }

    public zzap zza(Map map) {
        String zzb2 = this.zzb.zzb();
        if (zzb2 == null) {
            return zzfp.zza();
        }
        return zzfp.zzb(zzb2);
    }

    public boolean zzb() {
        return false;
    }
}

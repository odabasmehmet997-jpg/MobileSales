package com.google.android.gms.tagmanager;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zze extends zzbp {
    private static final String zza = zza.ADVERTISER_ID.toString();
    private final zzd zzb;

    @VisibleForTesting
    zze(zzd zzd) {
        super(zza);
        this.zzb = zzd;
        zzd.zzc();
    }

    public zzap zza(Map map) {
        String zzc = this.zzb.zzc();
        return zzc == null ? zzfp.zza() : zzfp.zzb(zzc);
    }

    public boolean zzb() {
        return false;
    }
}

package com.google.android.gms.tagmanager;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzap;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzf extends zzbp {
    private static final String zza = zza.ADVERTISING_TRACKING_ENABLED.toString();
    private final zzd zzb;

    @VisibleForTesting
    zzf(zzd zzd) {
        super(zza);
        this.zzb = zzd;
    }

    public zzap zza(Map map) {
        return zzfp.zzb(Boolean.valueOf(!this.zzb.zzf()));
    }

    public boolean zzb() {
        return false;
    }
}

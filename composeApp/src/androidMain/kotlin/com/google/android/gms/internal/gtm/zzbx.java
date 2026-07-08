package com.google.android.gms.internal.gtm;

import com.google.android.gms.analytics.zzr;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzbx extends zzcv {
    final zzcb zza;

    
    zzbx(zzcb zzcb, zzbu zzbu) {
        super(zzbu);
        this.zza = zzcb;
    }

    public void zza() {
        zzr.zzh();
        zzcb zzcb = this.zza;
        if (zzcb.zzg()) {
            zzcb.zzN("Inactivity, disconnecting from device AnalyticsService");
            zzcb.zzc();
        }
    }
}

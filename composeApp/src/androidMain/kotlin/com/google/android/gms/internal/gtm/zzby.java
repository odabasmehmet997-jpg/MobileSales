package com.google.android.gms.internal.gtm;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
record zzby(zzca zza, zzel zzb) implements Runnable {

    public void run() {
        if (!this.zza.zza.zzg()) {
            this.zza.zza.zzE("Connected to service after a timeout");
            zzca zzca = this.zza;
            zzcb.zzi(zzca.zza, this.zzb);
        }
    }
}

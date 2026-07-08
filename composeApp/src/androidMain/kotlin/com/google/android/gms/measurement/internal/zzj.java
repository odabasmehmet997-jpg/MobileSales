package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzcf;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 * @param zzd synthetic
 * @param zze synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-sdk@@20.1.1 */
record zzj(AppMeasurementDynamiteService zze, zzcf zza, String zzb, String zzc, boolean zzd) implements Runnable {

    public void run() {
        zze.zza.zzt().zzy(zza, zzb, zzc, zzd);
    }
}

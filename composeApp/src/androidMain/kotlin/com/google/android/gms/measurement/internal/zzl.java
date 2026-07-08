package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzcf;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 * @param zzd synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-sdk@@20.1.1 */
record zzl(AppMeasurementDynamiteService zzd, zzcf zza, String zzb, String zzc) implements Runnable {

    public void run() {
        zzd.zza.zzt().zzv(zza, zzb, zzc);
    }
}

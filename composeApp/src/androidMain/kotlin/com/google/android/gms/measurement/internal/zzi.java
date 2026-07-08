package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzcf;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 * @param zzd synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-sdk@@20.1.1 */
record zzi(AppMeasurementDynamiteService zzd, zzcf zza, zzau zzb, String zzc) implements Runnable {

    public void run() {
        this.zzd.zza.zzt().zzB(this.zza, this.zzb, this.zzc);
    }
}

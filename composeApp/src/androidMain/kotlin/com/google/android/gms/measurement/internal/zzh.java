package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzcf;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-sdk@@20.1.1 */
record zzh(AppMeasurementDynamiteService zzb, zzcf zza) implements Runnable {

    public void run() {
        this.zzb.zza.zzt().zzt(this.zza);
    }
}

package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzcf;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-sdk@@20.1.1 */
record zzm(AppMeasurementDynamiteService zzb, zzcf zza) implements Runnable {

    public void run() {
        this.zzb.zza.zzv().zzO(this.zza, this.zzb.zza.zzI());
    }
}

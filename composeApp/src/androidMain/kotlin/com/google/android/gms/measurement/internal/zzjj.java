package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;

/**
 * @param zza synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzjj(zzjl zza) implements Runnable {

    public void run() {
        zzjm zzjm = this.zza.zza;
        Context zzau = zzjm.zzs.zzau();
        this.zza.zza.zzs.zzaw();
        com.google.android.gms.measurement.internal.zzjm.zzo(zzjm, new ComponentName(zzau, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}

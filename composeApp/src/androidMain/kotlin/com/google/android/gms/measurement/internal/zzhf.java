package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzhf(AtomicReference zza, boolean zzb, zzhy zzc) implements Runnable {
    public void run() {
        zzc.zzs.zzt().zzx(zza, zzb);
    }
}

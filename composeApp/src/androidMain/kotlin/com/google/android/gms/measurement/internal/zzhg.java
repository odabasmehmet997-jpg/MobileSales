package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzhg(zzhy zzb, long zza) implements Runnable {

    public void run() {
        zzb.zzL(zza, true);
        zzb.zzs.zzt().zzu(new AtomicReference());
    }
}

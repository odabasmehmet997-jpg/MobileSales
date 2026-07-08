package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzhq(zzhy zzb, AtomicReference zza) implements Runnable {

    public void run() {
        synchronized (zza) {
            try {
                zza.set(Double.valueOf(zzb.zzs.zzf().zza(zzb.zzs.zzh().zzl(), zzdw.zzN)));
                zza.notify();
            } catch (final Throwable th) {
                throw th;
            }
        }
    }
}

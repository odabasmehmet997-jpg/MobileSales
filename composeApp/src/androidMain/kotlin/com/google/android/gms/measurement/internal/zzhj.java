package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzhj(zzhy zzb, AtomicReference zza) implements Runnable {

    public void run() {
        synchronized (zza) {
            try {
                zza.set(Boolean.valueOf(zzb.zzs.zzf().zzs(zzb.zzs.zzh().zzl(), zzdw.zzJ)));
                zza.notify();
            } catch (final Throwable th) {
                throw th;
            }
        }
    }
}

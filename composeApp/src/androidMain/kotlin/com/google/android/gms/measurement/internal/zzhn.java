package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzhn(zzhy zzb, AtomicReference zza) implements Runnable {

    public void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(this.zzb.zzs.zzf().zzo(this.zzb.zzs.zzh().zzl(), zzdw.zzK));
                this.zza.notify();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

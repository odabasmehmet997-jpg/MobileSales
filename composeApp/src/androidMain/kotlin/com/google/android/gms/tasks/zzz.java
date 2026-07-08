package com.google.android.gms.tasks;

import java.util.concurrent.Callable;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
record zzz(zzw zza, Callable zzb) implements Runnable {

    public void run() {
        try {
            this.zza.zzb(this.zzb.call());
        } catch (Exception e2) {
            this.zza.zza(e2);
        } catch (Throwable th) {
            this.zza.zza(new RuntimeException(th));
        }
    }
}

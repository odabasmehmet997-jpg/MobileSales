package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.Preconditions;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
record zzk(zzl zzb, Task zza) implements Runnable {

    public void run() {
        synchronized (this.zzb.zzb) {
            try {
                zzl zzl = this.zzb;
                if (null != zzl.zzc) {
                    zzl.zzc.onFailure(Preconditions.checkNotNull(this.zza.getException()));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

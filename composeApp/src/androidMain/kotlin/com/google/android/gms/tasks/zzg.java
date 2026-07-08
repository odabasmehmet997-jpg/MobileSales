package com.google.android.gms.tasks;

/**
 * @param zza synthetic
 */ /* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
record zzg(zzh zza) implements Runnable {

    public void run() {
        synchronized (this.zza.zzb) {
            try {
                zzh zzh = this.zza;
                if (null != zzh.zzc) {
                    zzh.zzc.onCanceled();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

package com.google.android.gms.tasks;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
record zzi(zzj zzb, Task zza) implements Runnable {

    public void run() {
        synchronized (this.zzb.zzb) {
            try {
                zzj zzj = this.zzb;
                if (null != zzj.zzc) {
                    zzj.zzc.onComplete(this.zza);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

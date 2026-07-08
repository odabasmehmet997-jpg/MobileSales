package com.google.android.gms.tasks;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
record zzm(zzn zzb, Task zza) implements Runnable {

    public void run() {
        synchronized (this.zzb.zzb) {
            try {
                zzn zzn = this.zzb;
                if (null != zzn.zzc) {
                    zzn.zzc.onSuccess(this.zza.getResult());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

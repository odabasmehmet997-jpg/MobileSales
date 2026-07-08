package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
record zze(zzf zzb, Task zza) implements Runnable {

    public void run() {
        try {
            Task task = (Task) this.zzb.zzb.then(this.zza);
            if (null == task) {
                this.zzb.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
            zzf zzf = this.zzb;
            Executor executor = TaskExecutors.zza;
            task.addOnSuccessListener(executor, zzf);
            task.addOnFailureListener(executor, this.zzb);
            task.addOnCanceledListener(executor, this.zzb);
        } catch (RuntimeExecutionException e2) {
            if (e2.getCause() instanceof Exception) {
                this.zzb.zzc.zza((Exception) e2.getCause());
            } else {
                this.zzb.zzc.zza(e2);
            }
        } catch (Exception e3) {
            this.zzb.zzc.zza(e3);
        }
    }
}

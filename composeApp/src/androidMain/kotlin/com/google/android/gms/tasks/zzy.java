package com.google.android.gms.tasks;

import com.google.android.gms.internal.tasks.zza;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 */ /* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
public record zzy(zza zza, TaskCompletionSource zzb, zzb zzc) implements OnCompleteListener {
    public void onComplete(Task task) {
        this.zza.removeCallbacksAndMessages(null);
        TaskCompletionSource taskCompletionSource = this.zzb;
        if (task.isSuccessful()) {
            taskCompletionSource.trySetResult(task.getResult());
        } else if (task.isCanceled()) {
            this.zzc.zza();
        } else {
            Exception exception = task.getException();
            exception.getClass();
            taskCompletionSource.trySetException(exception);
        }
    }
}

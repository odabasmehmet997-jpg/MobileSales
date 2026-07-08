package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
final class zzl implements zzq {
    private final Executor zza;
    
    public final Object zzb = new Object();
    
    public OnFailureListener zzc;

    public zzl(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        this.zza = executor;
        this.zzc = onFailureListener;
    }

    public void zzc() {
        synchronized (this.zzb) {
            this.zzc = null;
        }
    }

    public void zzd(@NonNull Task task) {
        if (!task.isSuccessful() && !task.isCanceled()) {
            synchronized (this.zzb) {
                try {
                    if (null != zzc) {
                        this.zza.execute(new zzk(this, task));
                    }
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
        }
    }
}

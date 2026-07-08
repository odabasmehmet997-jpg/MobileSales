package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
final class zzh implements zzq {
    private final Executor zza;
    
    public final Object zzb = new Object();
    
    public OnCanceledListener zzc;

    public zzh(@NonNull Executor executor, @NonNull OnCanceledListener onCanceledListener) {
        this.zza = executor;
        this.zzc = onCanceledListener;
    }

    public void zzc() {
        synchronized (this.zzb) {
            this.zzc = null;
        }
    }

    public void zzd(@NonNull Task task) {
        if (task.isCanceled()) {
            synchronized (this.zzb) {
                try {
                    if (null != zzc) {
                        this.zza.execute(new zzg(this));
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

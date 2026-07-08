package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
final class zzn implements zzq {
    private final Executor zza;
    
    public final Object zzb = new Object();
    
    public OnSuccessListener zzc;

    public zzn(@NonNull Executor executor, @NonNull OnSuccessListener onSuccessListener) {
        this.zza = executor;
        this.zzc = onSuccessListener;
    }

    public void zzc() {
        synchronized (this.zzb) {
            this.zzc = null;
        }
    }

    public void zzd(@NonNull Task task) {
        if (task.isSuccessful()) {
            synchronized (this.zzb) {
                try {
                    if (null != zzc) {
                        this.zza.execute(new zzm(this, task));
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

package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
final class zzj implements zzq {
    private final Executor zza;
    
    public final Object zzb = new Object();
    
    public OnCompleteListener zzc;

    public zzj(@NonNull Executor executor, @NonNull OnCompleteListener onCompleteListener) {
        this.zza = executor;
        this.zzc = onCompleteListener;
    }

    public void zzc() {
        synchronized (this.zzb) {
            this.zzc = null;
        }
    }

    public void zzd(@NonNull Task task) {
        synchronized (this.zzb) {
            try {
                if (null != zzc) {
                    this.zza.execute(new zzi(this, task));
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }
}

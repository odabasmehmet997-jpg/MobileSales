package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
final class zzd implements zzq {
    private final Executor zza;
    
    public final Continuation zzb;
    
    public final zzw zzc;

    public zzd(@NonNull Executor executor, @NonNull Continuation continuation, @NonNull zzw zzw) {
        this.zza = executor;
        this.zzb = continuation;
        this.zzc = zzw;
    }

    public void zzc() {
        throw new UnsupportedOperationException();
    }

    public void zzd(@NonNull Task task) {
        this.zza.execute(new zzc(this, task));
    }
}

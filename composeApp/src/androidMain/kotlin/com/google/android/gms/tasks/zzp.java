package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
final class zzp<TResult, TContinuationResult> implements OnSuccessListener<TContinuationResult>, OnFailureListener, OnCanceledListener, zzq {
    private final Executor zza;
    
    public final SuccessContinuation zzb;
    private final zzw zzc;

    public zzp(@NonNull Executor executor, @NonNull SuccessContinuation successContinuation, @NonNull zzw zzw) {
        this.zza = executor;
        this.zzb = successContinuation;
        this.zzc = zzw;
    }

    public void onCanceled() {
        this.zzc.zzc();
    }

    public void onFailure(@NonNull Exception exc) {
        this.zzc.zza(exc);
    }

    public void onSuccess(TContinuationResult tcontinuationresult) {
        this.zzc.zzb(tcontinuationresult);
    }

    public void zzc() {
        throw new UnsupportedOperationException();
    }

    public void zzd(@NonNull Task task) {
        this.zza.execute(new zzo(this, task));
    }
}

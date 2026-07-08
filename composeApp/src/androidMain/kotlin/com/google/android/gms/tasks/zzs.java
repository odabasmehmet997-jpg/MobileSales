package com.google.android.gms.tasks;

/**
 * @param zza synthetic
 */ /* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
record zzs(TaskCompletionSource zza) implements OnTokenCanceledListener {
    public void onCanceled() {
        this.zza.zza.zzc();
    }
}

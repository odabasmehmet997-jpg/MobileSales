package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public record zzjq(zzjt zza, zzej zzb, JobParameters zzc) implements Runnable {
    /* synthetic */

    public void run() {
        zza.zzd(zzb, zzc);
    }
}

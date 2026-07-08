package com.google.android.gms.internal.gtm;

import android.app.job.JobParameters;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 */ /* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public record zzex(zzfa zza, zzeo zzb, JobParameters zzc) implements Runnable {
    /* synthetic */

    public void run() {
        zza.zzd(zzb, zzc);
    }
}

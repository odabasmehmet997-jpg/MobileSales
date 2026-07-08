package com.google.android.gms.internal.gtm;

/**
 * @param zza synthetic
 */ /* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
record zzbt(zzbu zza) implements Thread.UncaughtExceptionHandler {

    public void uncaughtException(Thread thread, Throwable th) {
        zzeo zzn = this.zza.zzn();
        if (null != zzn) {
            zzn.zzJ("Job execution failed", th);
        }
    }
}

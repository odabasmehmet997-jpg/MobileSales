package com.google.android.gms.internal.gtm;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
record zzey(zzfa zzb, Runnable zza) implements zzcy {

    public void zza(Throwable th) {
        this.zzb.zzb.post(this.zza);
    }
}

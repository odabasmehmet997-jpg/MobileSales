package com.google.android.gms.internal.gtm;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 */ /* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
record zzbj(zzbp zzc, String zza, Runnable zzb) implements Runnable {

    public void run() {
        zzc.zza.zzn(zza);
        zzb.run();
    }
}

package com.google.android.gms.measurement.internal;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzji(zzjl zzb, zzdz zza) implements Runnable {

    public void run() {
        synchronized (zzb) {
            try {
                zzb.zzb = false;
                if (!zzb.zza.zzL()) {
                    zzb.zza.zzs.zzay().zzc().zza("Connected to remote service");
                    zzb.zza.zzJ(zza);
                }
            } catch (final Throwable th) {
                throw th;
            }
        }
    }
}

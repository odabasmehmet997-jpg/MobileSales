package com.google.android.gms.measurement.internal;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 * @param zzd synthetic
 * @param zze synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzhu(zzhy zze, zzah zza, int zzb, long zzc, boolean zzd) implements Runnable {

    public void run() {
        this.zze.zzW(this.zza);
        zzhy.zzv(this.zze, this.zza, this.zzb, this.zzc, false, this.zzd);
    }
}

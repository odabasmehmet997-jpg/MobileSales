package com.google.android.gms.measurement.internal;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 * @param zzd synthetic
 * @param zze synthetic
 * @param zzf synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzhs(zzhy zzf, zzah zza, long zzb, int zzc, long zzd, boolean zze) implements Runnable {

    public void run() {
        this.zzf.zzW(this.zza);
        this.zzf.zzL(this.zzb, false);
        zzhy.zzv(this.zzf, this.zza, this.zzc, this.zzd, true, this.zze);
    }
}

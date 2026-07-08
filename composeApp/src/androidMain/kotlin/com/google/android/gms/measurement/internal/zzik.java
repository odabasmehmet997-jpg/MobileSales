package com.google.android.gms.measurement.internal;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzik(zzim zzc, zzif zza, long zzb) implements Runnable {

    public void run() {
        this.zzc.zzC(this.zza, false, this.zzb);
        zzim zzim = this.zzc;
        zzim.zza = null;
        zzim.zzs.zzt().zzG(null);
    }
}

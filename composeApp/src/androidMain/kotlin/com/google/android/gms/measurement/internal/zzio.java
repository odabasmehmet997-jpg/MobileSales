package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 * @param zzd synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzio(zzjm zzd, zzp zza, boolean zzb, zzku zzc) implements Runnable {

    public void run() {
        zzku zzku;
        zzjm zzjm = this.zzd;
        zzdz zzh = zzjm.zzb;
        if (null == zzh) {
            zzjm.zzs.zzay().zzd().zza("Discarding data. Failed to set user property");
            return;
        }
        Preconditions.checkNotNull(this.zza);
        zzjm zzjm2 = this.zzd;
        if (this.zzb) {
            zzku = null;
        } else {
            zzku = this.zzc;
        }
        zzjm2.zzD(zzh, zzku, this.zza);
        this.zzd.zzQ();
    }
}

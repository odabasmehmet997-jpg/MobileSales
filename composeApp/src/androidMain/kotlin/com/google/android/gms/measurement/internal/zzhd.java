package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 * @param zzd synthetic
 * @param zze synthetic
 * @param zzf synthetic
 * @param zzg synthetic
 * @param zzh synthetic
 * @param zzi synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzhd(zzhy zzi, String zza, String zzb, long zzc, Bundle zzd, boolean zze, boolean zzf, boolean zzg,
            String zzh) implements Runnable {

    public void run() {
        this.zzi.zzI(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh);
    }
}

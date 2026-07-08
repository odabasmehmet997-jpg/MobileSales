package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzpi;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 * @param zzd synthetic
 * @param zze synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
record zzgk(zzgl zze, String zza, String zzb, String zzc, long zzd) implements Runnable {

    public void run() {
        zzpi.zzc();
        if (zze.zza.zzg().zzs(null, zzdw.zzat)) {
            final String str = zza;
            if (null == str) {
                zze.zza.zzQ(zzb, null);
                return;
            }
            zze.zza.zzQ(zzb, new zzif(zzc, str, zzd));
            return;
        }
        final String str2 = zza;
        if (null == str2) {
            zze.zza.zzq().zzs().zzy(zzb, null);
            return;
        }
        zze.zza.zzq().zzs().zzy(zzb, new zzif(zzc, str2, zzd));
    }
}

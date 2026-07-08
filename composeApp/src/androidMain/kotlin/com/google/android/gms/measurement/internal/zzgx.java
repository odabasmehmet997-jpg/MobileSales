package com.google.android.gms.measurement.internal;

/**
 * @param zza synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public record zzgx(zzhy zza) implements Runnable {
    /* synthetic */

    public void run() {
        final zzhy zzhy = zza;
        zzhy.zzg();
        if (!zzhy.zzs.zzm().zzm.zzb()) {
            final long zza2 = zzhy.zzs.zzm().zzn.zza();
            zzhy.zzs.zzm().zzn.zzb(1 + zza2);
            zzhy.zzs.zzf();
            if (5 <= zza2) {
                zzhy.zzs.zzay().zzk().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
                zzhy.zzs.zzm().zzm.zza(true);
                return;
            }
            zzhy.zzs.zzE();
            return;
        }
        zzhy.zzs.zzay().zzc().zza("Deferred Deep Link already retrieved. Not fetching again.");
    }
}

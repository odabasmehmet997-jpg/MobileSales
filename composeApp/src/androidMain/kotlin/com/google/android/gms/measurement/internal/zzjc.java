package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzjc implements Runnable {
    final zzp zza;
    final boolean zzb;
    final zzab zzc;
    final zzab zzd;
    final zzjm zze;

    zzjc(final zzjm zzjm, final boolean z, final zzp zzp, final boolean z2, final zzab zzab, final zzab zzab2) {
        zze = zzjm;
        zza = zzp;
        zzb = z2;
        zzc = zzab;
        zzd = zzab2;
    }

    public void run() {
        final zzab zzab;
        final zzjm zzjm = zze;
        final zzdz zzh = zzjm.zzb;
        if (null == zzh) {
            zzjm.zzs.zzay().zzd().zza("Discarding data. Failed to send conditional user property to service");
            return;
        }
        Preconditions.checkNotNull(zza);
        final zzjm zzjm2 = zze;
        if (zzb) {
            zzab = null;
        } else {
            zzab = zzc;
        }
        zzjm2.zzD(zzh, zzab, zza);
        zze.zzQ();
    }
}

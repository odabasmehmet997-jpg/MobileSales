package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzjb implements Runnable {
    final zzp zza;
    final boolean zzb;
    final zzau zzc;
    final String zzd;
    final zzjm zze;

    zzjb(final zzjm zzjm, final boolean z, final zzp zzp, final boolean z2, final zzau zzau, final String str) {
        zze = zzjm;
        zza = zzp;
        zzb = z2;
        zzc = zzau;
        zzd = str;
    }

    public void run() {
        final zzau zzau;
        final zzjm zzjm = zze;
        final zzdz zzh = zzjm.zzb;
        if (null == zzh) {
            zzjm.zzs.zzay().zzd().zza("Discarding data. Failed to send event to service");
            return;
        }
        Preconditions.checkNotNull(zza);
        final zzjm zzjm2 = zze;
        if (zzb) {
            zzau = null;
        } else {
            zzau = zzc;
        }
        zzjm2.zzD(zzh, zzau, zza);
        zze.zzQ();
    }
}

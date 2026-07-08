package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zziu(zzjm zzb, zzif zza) implements Runnable {

    public void run() {
        final zzjm zzjm = zzb;
        final zzdz zzh = zzjm.zzb;
        if (null == zzh) {
            zzjm.zzs.zzay().zzd().zza("Failed to send current screen to service");
            return;
        }
        try {
            final zzif zzif = zza;
            if (null == zzif) {
                zzh.zzq(0, (String) null, (String) null, zzjm.zzs.zzau().getPackageName());
            } else {
                zzh.zzq(zzif.zzc, zzif.zza, zzif.zzb, zzjm.zzs.zzau().getPackageName());
            }
            zzb.zzQ();
        } catch (final RemoteException e2) {
            zzb.zzs.zzay().zzd().zzb("Failed to send current screen to the service", e2);
        }
    }
}

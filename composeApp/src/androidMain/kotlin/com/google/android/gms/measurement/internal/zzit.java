package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzit(zzjm zzb, zzp zza) implements Runnable {

    public void run() {
        final zzjm zzjm = zzb;
        final zzdz zzh = zzjm.zzb;
        if (null == zzh) {
            zzjm.zzs.zzay().zzd().zza("Discarding data. Failed to send app launch");
            return;
        }
        try {
            Preconditions.checkNotNull(zza);
            zzh.zzj(zza);
            zzb.zzs.zzi().zzm();
            zzb.zzD(zzh, null, zza);
            zzb.zzQ();
        } catch (final RemoteException e2) {
            zzb.zzs.zzay().zzd().zzb("Failed to send app launch to the service", e2);
        }
    }
}

package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zziv(zzjm zzc, zzp zza, Bundle zzb) implements Runnable {

    public void run() {
        final zzjm zzjm = zzc;
        final zzdz zzh = zzjm.zzb;
        if (null == zzh) {
            zzjm.zzs.zzay().zzd().zza("Failed to send default event parameters to service");
            return;
        }
        try {
            Preconditions.checkNotNull(zza);
            zzh.zzr(zzb, zza);
        } catch (final RemoteException e2) {
            zzc.zzs.zzay().zzd().zzb("Failed to send default event parameters to service", e2);
        }
    }
}

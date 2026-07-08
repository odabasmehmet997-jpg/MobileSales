package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/**
 * @param zza synthetic
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zziq(zzjm zzb, zzp zza) implements Runnable {

    public void run() {
        zzjm zzjm = this.zzb;
        zzdz zzh = zzjm.zzb;
        if (null == zzh) {
            zzjm.zzs.zzay().zzd().zza("Failed to reset data on the service: not connected to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.zza);
            zzh.zzm(this.zza);
        } catch (RemoteException e2) {
            this.zzb.zzs.zzay().zzd().zzb("Failed to reset data on the service: remote exception", e2);
        }
        this.zzb.zzQ();
    }
}

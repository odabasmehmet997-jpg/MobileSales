package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 * @param zzd synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzip(zzjm zzd, AtomicReference zza, zzp zzb, boolean zzc) implements Runnable {

    public void run() {
        AtomicReference atomicReference;
        synchronized (this.zza) {
            try {
                zzjm zzjm = this.zzd;
                zzdz zzh = zzjm.zzb;
                if (null == zzh) {
                    zzjm.zzs.zzay().zzd().zza("Failed to get all user properties; not connected to service");
                    this.zza.notify();
                    return;
                }
                Preconditions.checkNotNull(this.zzb);
                this.zza.set(zzh.zze(this.zzb, this.zzc));
                this.zzd.zzQ();
                atomicReference = this.zza;
                atomicReference.notify();
            } catch (RemoteException e2) {
                try {
                    this.zzd.zzs.zzay().zzd().zzb("Failed to get all user properties; remote exception", e2);
                    atomicReference = this.zza;
                } catch (Throwable th) {
                    this.zza.notify();
                    throw th;
                }
            }
        }
    }
}

package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzir(zzjm zzc, AtomicReference zza, zzp zzb) implements Runnable {

    public void run() {
        AtomicReference atomicReference;
        synchronized (zza) {
            try {
                if (!zzc.zzs.zzm().zzc().zzi(zzag.ANALYTICS_STORAGE)) {
                    zzc.zzs.zzay().zzl().zza("Analytics storage consent denied; will not get app instance id");
                    zzc.zzs.zzq().zzO(null);
                    zzc.zzs.zzm().zze.zzb(null);
                    zza.set(null);
                    zza.notify();
                    return;
                }
                final zzjm zzjm = zzc;
                final zzdz zzh = zzjm.zzb;
                if (null == zzh) {
                    zzjm.zzs.zzay().zzd().zza("Failed to get app instance id");
                    zza.notify();
                    return;
                }
                Preconditions.checkNotNull(zzb);
                zza.set(zzh.zzd(zzb));
                final String str = (String) zza.get();
                if (null != str) {
                    zzc.zzs.zzq().zzO(str);
                    zzc.zzs.zzm().zze.zzb(str);
                }
                zzc.zzQ();
                atomicReference = zza;
                atomicReference.notify();
            } catch (final RemoteException e2) {
                try {
                    zzc.zzs.zzay().zzd().zzb("Failed to get app instance id", e2);
                    atomicReference = zza;
                } catch (final Throwable th) {
                    zza.notify();
                    throw th;
                }
            }
        }
    }
}

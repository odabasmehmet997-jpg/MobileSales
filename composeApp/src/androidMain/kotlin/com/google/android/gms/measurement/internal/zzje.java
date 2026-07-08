package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcf;
import java.util.ArrayList;

/**
 * @param zza synthetic
 * @param zzb synthetic
 * @param zzc synthetic
 * @param zzd synthetic
 * @param zze synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzje(zzjm zze, String zza, String zzb, zzp zzc, zzcf zzd) implements Runnable {

    public void run() {
        zzft zzft;
        ArrayList arrayList = new ArrayList();
        try {
            final zzjm zzjm = zze;
            final zzdz zzh = zzjm.zzb;
            if (null == zzh) {
                zzjm.zzs.zzay().zzd().zzc("Failed to get conditional properties; not connected to service", zza, zzb);
                zzft = zze.zzs;
            } else {
                Preconditions.checkNotNull(zzc);
                arrayList = zzky.zzG(zzh.zzf(zza, zzb, zzc));
                zze.zzQ();
                zzft = zze.zzs;
            }
        } catch (final RemoteException e2) {
            zze.zzs.zzay().zzd().zzd("Failed to get conditional properties; remote exception", zza, zzb, e2);
            zzft = zze.zzs;
        } catch (final Throwable th) {
            zze.zzs.zzv().zzP(zzd, arrayList);
            throw th;
        }
        zzft.zzv().zzP(zzd, arrayList);
    }
}

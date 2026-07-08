package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzjd implements Runnable {
    final AtomicReference zza;
    final String zzb;
    final String zzc;
    final zzp zzd;
    final zzjm zze;

    zzjd(final zzjm zzjm, final AtomicReference atomicReference, final String str, final String str2, final String str3, final zzp zzp) {
        zze = zzjm;
        zza = atomicReference;
        zzb = str2;
        zzc = str3;
        zzd = zzp;
    }

    public void run() {
        AtomicReference atomicReference;
        synchronized (zza) {
            try {
                final zzjm zzjm = zze;
                final zzdz zzh = zzjm.zzb;
                if (null == zzh) {
                    zzjm.zzs.zzay().zzd().zzd("(legacy) Failed to get conditional properties; not connected to service", null, zzb, zzc);
                    zza.set(Collections.emptyList());
                    zza.notify();
                    return;
                }
                if (TextUtils.isEmpty(null)) {
                    Preconditions.checkNotNull(zzd);
                    zza.set(zzh.zzf(zzb, zzc, zzd));
                } else {
                    zza.set(zzh.zzg(null, zzb, zzc));
                }
                zze.zzQ();
                atomicReference = zza;
                atomicReference.notify();
            } catch (final RemoteException e2) {
                try {
                    zze.zzs.zzay().zzd().zzd("(legacy) Failed to get conditional properties; remote exception", null, zzb, e2);
                    zza.set(Collections.emptyList());
                    atomicReference = zza;
                } catch (final Throwable th) {
                    zza.notify();
                    throw th;
                }
            }
        }
    }
}

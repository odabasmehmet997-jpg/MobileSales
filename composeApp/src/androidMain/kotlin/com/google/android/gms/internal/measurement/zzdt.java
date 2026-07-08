package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
abstract class zzdt implements Runnable {
    final long zzh;
    final long zzi;
    final boolean zzj;
    final zzee zzk;

    zzdt(final zzee zzee, final boolean z) {
        zzk = zzee;
        zzh = zzee.zza.currentTimeMillis();
        zzi = zzee.zza.elapsedRealtime();
        zzj = z;
    }

    public final void run() {
        if (zzk.zzh) {
            this.zzb();
            return;
        }
        try {
            this.zza();
        } catch (final Exception e2) {
            zzk.zzS(e2, false, zzj);
            this.zzb();
        }
    }

    
    public abstract void zza() throws RemoteException;

    
    public void zzb() {
    }
}

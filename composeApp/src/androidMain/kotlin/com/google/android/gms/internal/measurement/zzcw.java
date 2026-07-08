package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzcw extends zzdt {
    final long zza;
    final zzee zzb;

    
    zzcw(zzee zzee, long j2) {
        super(zzee, true);
        this.zzb = zzee;
        this.zza = j2;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(this.zzb.zzj).setSessionTimeoutDuration(this.zza);
    }
}

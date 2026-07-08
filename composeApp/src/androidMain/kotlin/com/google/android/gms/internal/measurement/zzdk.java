package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzdk extends zzdt {
    final zzbz zza;
    final zzee zzb;

    
    zzdk(final zzee zzee, final zzbz zzbz) {
        super(zzee, true);
        zzb = zzee;
        zza = zzbz;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(zzb.zzj).getAppInstanceId(zza);
    }

    
    public void zzb() {
        zza.zzd(null);
    }
}

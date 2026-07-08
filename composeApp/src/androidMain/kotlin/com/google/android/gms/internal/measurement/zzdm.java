package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzdm extends zzdt {
    final boolean zza;
    final zzee zzb;

    
    zzdm(final zzee zzee, final boolean z) {
        super(zzee, true);
        zzb = zzee;
        zza = z;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(zzb.zzj).setDataCollectionEnabled(zza);
    }
}

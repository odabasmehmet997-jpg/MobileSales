package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzcz extends zzdt {
    final String zza;
    final zzee zzb;

    
    zzcz(final zzee zzee, final String str) {
        super(zzee, true);
        zzb = zzee;
        zza = str;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(zzb.zzj).endAdUnitExposure(zza, zzi);
    }
}

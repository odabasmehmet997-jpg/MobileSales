package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzcy extends zzdt {
    final String zza;
    final zzee zzb;

    
    zzcy(zzee zzee, String str) {
        super(zzee, true);
        this.zzb = zzee;
        this.zza = str;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(this.zzb.zzj).beginAdUnitExposure(this.zza, this.zzi);
    }
}

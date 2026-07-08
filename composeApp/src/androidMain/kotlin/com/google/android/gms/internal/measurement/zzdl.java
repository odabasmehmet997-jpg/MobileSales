package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzdl extends zzdt {
    final zzbz zza;
    final int zzb;
    final zzee zzc;

    
    zzdl(final zzee zzee, final zzbz zzbz, final int i2) {
        super(zzee, true);
        zzc = zzee;
        zza = zzbz;
        zzb = i2;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(zzc.zzj).getTestFlag(zza, zzb);
    }

    
    public void zzb() {
        zza.zzd(null);
    }
}

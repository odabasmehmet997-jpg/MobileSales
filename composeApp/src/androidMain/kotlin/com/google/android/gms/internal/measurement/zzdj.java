package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzdj extends zzdt {
    final String zza;
    final zzbz zzb;
    final zzee zzc;

    
    zzdj(final zzee zzee, final String str, final zzbz zzbz) {
        super(zzee, true);
        zzc = zzee;
        zza = str;
        zzb = zzbz;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(zzc.zzj).getMaxUserProperties(zza, zzb);
    }

    
    public void zzb() {
        zzb.zzd(null);
    }
}

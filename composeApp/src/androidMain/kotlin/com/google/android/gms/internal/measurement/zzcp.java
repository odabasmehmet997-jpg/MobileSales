package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzcp extends zzdt {
    final String zza;
    final String zzb;
    final zzbz zzc;
    final zzee zzd;

    
    zzcp(final zzee zzee, final String str, final String str2, final zzbz zzbz) {
        super(zzee, true);
        zzd = zzee;
        zza = str;
        zzb = str2;
        zzc = zzbz;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(zzd.zzj).getConditionalUserProperties(zza, zzb, zzc);
    }

    
    public void zzb() {
        zzc.zzd(null);
    }
}

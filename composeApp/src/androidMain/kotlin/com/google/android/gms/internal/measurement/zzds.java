package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzds extends zzdt {
    final String zza;
    final String zzb;
    final Object zzc;
    final boolean zzd;
    final zzee zze;

    
    zzds(final zzee zzee, final String str, final String str2, final Object obj, final boolean z) {
        super(zzee, true);
        zze = zzee;
        zza = str;
        zzb = str2;
        zzc = obj;
        zzd = z;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(zze.zzj).setUserProperty(zza, zzb, ObjectWrapper.wrap(zzc), zzd, zzh);
    }
}

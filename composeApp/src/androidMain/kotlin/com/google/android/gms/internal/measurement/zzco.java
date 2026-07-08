package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzco extends zzdt {
    final String zza;
    final String zzb;
    final Bundle zzc;
    final zzee zzd;

    
    zzco(final zzee zzee, final String str, final String str2, final Bundle bundle) {
        super(zzee, true);
        zzd = zzee;
        zza = str;
        zzb = str2;
        zzc = bundle;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(zzd.zzj).clearConditionalUserProperty(zza, zzb, zzc);
    }
}

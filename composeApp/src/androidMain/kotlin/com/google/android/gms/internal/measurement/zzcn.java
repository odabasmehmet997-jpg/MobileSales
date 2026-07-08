package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzcn extends zzdt {
    final Bundle zza;
    final zzee zzb;

    
    zzcn(final zzee zzee, final Bundle bundle) {
        super(zzee, true);
        zzb = zzee;
        zza = bundle;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(zzb.zzj).setConditionalUserProperty(zza, zzh);
    }
}

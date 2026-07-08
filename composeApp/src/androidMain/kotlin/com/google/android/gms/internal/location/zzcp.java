package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzcp extends zzcq {
    final List zza;

    
    public void doExecute(final Api.AnyClient anyClient) throws RemoteException {
        ((zzdz) anyClient).zzF(zzem.zza(zza), zzcr.zza(this));
    }
}

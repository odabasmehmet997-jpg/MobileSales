package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzax extends zzba {
    final boolean zza;

    
    public void doExecute(final Api.AnyClient anyClient) throws RemoteException {
        final zzdz zzdz = (zzdz) anyClient;
        if (zza) {
            zzdz.zzy(zzbb.zza(this));
        } else {
            zzdz.zzz(zzbb.zza(this));
        }
    }
}

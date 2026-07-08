package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationCallback;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzaw extends zzba {
    final LocationCallback zza;

    
    public void doExecute(final Api.AnyClient anyClient) throws RemoteException {
        ((zzdz) anyClient).zzw(ListenerHolders.createListenerKey(zza, LocationCallback.class.getSimpleName()), true, zzbb.zza(this));
    }
}

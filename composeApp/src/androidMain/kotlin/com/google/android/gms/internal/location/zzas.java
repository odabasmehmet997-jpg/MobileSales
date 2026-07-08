package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationRequest;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzas extends zzba {
    final ListenerHolder zza;
    final LocationRequest zzb;

    
    public void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzdz) anyClient).zzt(new zzaz(this.zza), this.zzb, zzbb.zza(this));
    }
}

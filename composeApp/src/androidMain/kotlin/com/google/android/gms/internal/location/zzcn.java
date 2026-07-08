package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.location.GeofencingRequest;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzcn extends zzcq {
    final GeofencingRequest zza;
    final PendingIntent zzb;

    
    public void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzdz) anyClient).zzE(this.zza, this.zzb, zzcr.zza(this));
    }
}

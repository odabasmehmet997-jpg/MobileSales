package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.location.LocationRequest;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzat extends zzba {
    final PendingIntent zza;
    final LocationRequest zzb;

    
    public void doExecute(final Api.AnyClient anyClient) throws RemoteException {
        ((zzdz) anyClient).zzu(zza, zzb, zzbb.zza(this));
    }
}

package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzav extends zzba {
    final PendingIntent zza;

    
    public void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzdz) anyClient).zzx(this.zza, zzbb.zza(this), null);
    }
}

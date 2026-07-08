package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzad extends zzae {
    final PendingIntent zza;

    
    public void doExecute(final Api.AnyClient anyClient) throws RemoteException {
        ((zzg) anyClient).zzp(zza);
        this.setResult(Status.RESULT_SUCCESS);
    }
}

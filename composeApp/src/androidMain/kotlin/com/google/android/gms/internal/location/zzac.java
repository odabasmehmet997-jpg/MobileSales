package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzac extends zzae {
    final long zza;
    final PendingIntent zzb;

    
    public void doExecute(final Api.AnyClient anyClient) throws RemoteException {
        final PendingIntent pendingIntent = zzb;
        final zzg zzg = (zzg) anyClient;
        Preconditions.checkNotNull(pendingIntent);
        final long j2 = zza;
        Preconditions.checkArgument(0 <= j2, "detectionIntervalMillis must be >= 0");
        ((zzv) zzg.getService()).zzh(j2, true, pendingIntent);
        this.setResult(Status.RESULT_SUCCESS);
    }
}

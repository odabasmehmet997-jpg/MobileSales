package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.LocationSettingsRequest;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzcx extends zzcy {
    final LocationSettingsRequest zza;

    
    public void doExecute(final Api.AnyClient anyClient) throws RemoteException {
        final LocationSettingsRequest locationSettingsRequest = zza;
        Preconditions.checkArgument(null != locationSettingsRequest, "locationSettingsRequest can't be null");
        ((zzv) ((zzdz) anyClient).getService()).zzD(locationSettingsRequest, new zzdf(this), null);
    }
}

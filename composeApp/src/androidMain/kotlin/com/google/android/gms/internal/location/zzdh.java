package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdh extends LocationCallback {
    final TaskCompletionSource zza;
    final zzdz zzb;

    zzdh(final zzdz zzdz, final TaskCompletionSource taskCompletionSource) {
        zza = taskCompletionSource;
        zzb = zzdz;
    }

    public void onLocationResult(final LocationResult locationResult) {
        zza.trySetResult(locationResult.getLastLocation());
        try {
            zzb.zzw(ListenerHolders.createListenerKey(this, "GetCurrentLocation"), false, new TaskCompletionSource());
        } catch (final RemoteException unused) {
        }
    }
}

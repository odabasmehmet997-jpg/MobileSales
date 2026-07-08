package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdb implements RemoteCall {
    private final LocationSettingsRequest zza;

    public void accept(final Object obj, final Object obj2) {
        final TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
        final zzdz zzdz = (zzdz) obj;
        final LocationSettingsRequest locationSettingsRequest = zza;
        Preconditions.checkArgument(null != locationSettingsRequest, "locationSettingsRequest can't be null");
        ((zzv) zzdz.getService()).zzD(locationSettingsRequest, new zzde(taskCompletionSource), null);
    }
}

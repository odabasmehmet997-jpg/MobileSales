package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzcl implements RemoteCall {
    private final ListenerHolder zza;
    private final DeviceOrientationRequest zzb;

    public void accept(final Object obj, final Object obj2) {
        ((zzdz) obj).zzC(zza, zzb, (TaskCompletionSource) obj2);
    }
}

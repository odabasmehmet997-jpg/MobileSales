package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzbm implements RemoteCall {
    private final ListenerHolder zza;
    private final DeviceOrientationRequest zzb;

    public void accept(final Object obj, final Object obj2) {
        final Api api = zzbi.zzb;
        ((zzdz) obj).zzC(zza, zzb, (TaskCompletionSource) obj2);
    }
}

package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzbt implements RemoteCall {
    private final zzbh zza;
    private final LocationRequest zzb;

    public void accept(final Object obj, final Object obj2) {
        final Api api = zzbi.zzb;
        ((zzdz) obj).zzs(zza, zzb, (TaskCompletionSource) obj2);
    }
}

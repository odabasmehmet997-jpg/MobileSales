package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzbl implements RemoteCall {
    private final Location zza;

    public void accept(final Object obj, final Object obj2) {
        final Api api = zzbi.zzb;
        ((zzdz) obj).zzA(zza, (TaskCompletionSource) obj2);
    }
}

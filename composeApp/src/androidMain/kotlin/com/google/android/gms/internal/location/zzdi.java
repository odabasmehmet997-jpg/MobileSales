package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdi implements zzdr {
    final ListenerHolder zza;
    final TaskCompletionSource zzb;

    zzdi(final zzdz zzdz, final ListenerHolder listenerHolder, final TaskCompletionSource taskCompletionSource) {
        zza = listenerHolder;
        zzb = taskCompletionSource;
    }

    public ListenerHolder zza() {
        return zza;
    }

    public void zzb(final ListenerHolder listenerHolder) {
        throw new IllegalStateException();
    }

    public void zzc() {
        zzb.trySetResult(null);
    }
}

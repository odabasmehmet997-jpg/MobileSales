package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzcd implements zzbg {
    static final zzcd zza = new zzcd();

    private zzcd() {
    }

    public void zza(final zzdz zzdz, final ListenerHolder.ListenerKey listenerKey, final boolean z, final TaskCompletionSource taskCompletionSource) {
        zzdz.zzv(listenerKey, z, taskCompletionSource);
    }
}

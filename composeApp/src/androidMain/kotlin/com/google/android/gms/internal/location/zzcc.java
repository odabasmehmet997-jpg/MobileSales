package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzcc implements RemoteCall {
    static final zzcc zza = new zzcc();

    private zzcc() {
    }

    public void accept(final Object obj, final Object obj2) {
        ((zzdz) obj).zzz((TaskCompletionSource) obj2);
    }
}

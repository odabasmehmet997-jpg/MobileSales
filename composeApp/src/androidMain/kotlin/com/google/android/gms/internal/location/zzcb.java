package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzcb implements RemoteCall {
    static final zzcb zza = new zzcb();

    private zzcb() {
    }

    public void accept(final Object obj, final Object obj2) {
        ((zzdz) obj).zzy((TaskCompletionSource) obj2);
    }
}

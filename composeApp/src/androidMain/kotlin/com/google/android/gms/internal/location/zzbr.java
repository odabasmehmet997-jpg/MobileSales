package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.zzac;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzbr implements RemoteCall {
    static final zzbr zza = new zzbr();

    private zzbr() {
    }

    public void accept(final Object obj, final Object obj2) {
        final Api api = zzbi.zzb;
        ((zzdz) obj).zzp(zzac.zza(), (TaskCompletionSource) obj2);
    }
}

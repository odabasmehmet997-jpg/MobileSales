package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzcv implements RemoteCall {
    private final List zza;

    public void accept(final Object obj, final Object obj2) {
        ((zzdz) obj).zzF(zzem.zza(zza), (TaskCompletionSource) obj2);
    }
}

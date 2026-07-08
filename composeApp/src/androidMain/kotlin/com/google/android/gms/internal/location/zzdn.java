package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdn extends zzq {
    final Object zza;
    final TaskCompletionSource zzb;

    zzdn(final Object obj, final TaskCompletionSource taskCompletionSource) {
        zza = obj;
        zzb = taskCompletionSource;
    }

    public void zzd(final zzl zzl) {
        TaskUtil.setResultOrApiException(zzl.getStatus(), zza, zzb);
    }

    public void zze() {
    }
}

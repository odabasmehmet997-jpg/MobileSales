package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdk extends zzy {
    final TaskCompletionSource zza;

    zzdk(final TaskCompletionSource taskCompletionSource) {
        zza = taskCompletionSource;
    }

    public void zzb(final Status status, final Location location) {
        TaskUtil.setResultOrApiException(status, location, zza);
    }
}

package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdl extends zzw {
    final TaskCompletionSource zza;

    zzdl(final TaskCompletionSource taskCompletionSource) {
        zza = taskCompletionSource;
    }

    public void zzb(final Status status, final LocationAvailability locationAvailability) {
        TaskUtil.setResultOrApiException(status, locationAvailability, zza);
    }
}

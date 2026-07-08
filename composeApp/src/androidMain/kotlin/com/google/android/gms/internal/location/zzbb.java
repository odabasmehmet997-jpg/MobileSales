package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzbb implements FusedLocationProviderApi {
    static TaskCompletionSource zza(final BaseImplementation.ResultHolder resultHolder) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.getTask().addOnCompleteListener(new zzbd(resultHolder));
        return taskCompletionSource;
    }
}

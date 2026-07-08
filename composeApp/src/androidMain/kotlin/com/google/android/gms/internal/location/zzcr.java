package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.location.GeofencingApi;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzcr implements GeofencingApi {
    static TaskCompletionSource zza(final BaseImplementation.ResultHolder resultHolder) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.getTask().addOnCompleteListener(new zzcs(resultHolder));
        return taskCompletionSource;
    }
}

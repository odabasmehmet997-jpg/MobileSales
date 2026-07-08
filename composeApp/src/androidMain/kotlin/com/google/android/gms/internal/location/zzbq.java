package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzbq implements Continuation {
    private final TaskCompletionSource zza;

    public Object then(final Task task) {
        final Api api = zzbi.zzb;
        final TaskCompletionSource taskCompletionSource = zza;
        if (task.isSuccessful()) {
            taskCompletionSource.trySetResult(task.getResult());
            return null;
        }
        final Exception exception = task.getException();
        Objects.requireNonNull(exception);
        taskCompletionSource.trySetException(exception);
        return null;
    }
}

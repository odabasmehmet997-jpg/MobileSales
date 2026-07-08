package com.google.android.gms.internal.location;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzea implements OnCompleteListener {
    private final TaskCompletionSource zza;

    zzea(final TaskCompletionSource taskCompletionSource) {
        zza = taskCompletionSource;
    }

    public void onComplete(final Task task) {
        final int i2 = zzdz.r8clinit;
        if (!task.isSuccessful()) {
            final TaskCompletionSource taskCompletionSource = zza;
            final Exception exception = task.getException();
            Objects.requireNonNull(exception);
            taskCompletionSource.trySetException(exception);
        }
    }
}

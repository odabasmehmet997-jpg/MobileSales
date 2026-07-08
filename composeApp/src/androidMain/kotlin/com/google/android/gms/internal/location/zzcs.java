package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzcs implements OnCompleteListener {
    private final BaseImplementation.ResultHolder zza;

    zzcs(final BaseImplementation.ResultHolder resultHolder) {
        zza = resultHolder;
    }

    public void onComplete(final Task task) {
        final BaseImplementation.ResultHolder resultHolder = zza;
        if (task.isSuccessful()) {
            resultHolder.setResult(Status.RESULT_SUCCESS);
        } else if (task.isCanceled()) {
            resultHolder.setFailedResult(Status.RESULT_CANCELED);
        } else {
            final Exception exception = task.getException();
            if (exception instanceof ApiException) {
                resultHolder.setFailedResult(((ApiException) exception).getStatus());
            } else {
                resultHolder.setFailedResult(Status.RESULT_INTERNAL_ERROR);
            }
        }
    }
}

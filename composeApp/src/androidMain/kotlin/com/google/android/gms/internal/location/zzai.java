package com.google.android.gms.internal.location;

import androidx.annotation.Nullable;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzai implements BaseImplementation.ResultHolder {
    private final TaskCompletionSource zza;

    public zzai(final TaskCompletionSource taskCompletionSource) {
        Preconditions.checkNotNull(taskCompletionSource);
        zza = taskCompletionSource;
    }

    public void setFailedResult(@Nullable final Status status) {
        if (null != status) {
            zza.setException(new ApiException(status));
        }
    }

    public void setResult(final Object obj) {
        TaskUtil.setResultOrApiException((Status) obj, null, zza);
    }
}

package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzah extends IStatusCallback.Stub {
    final TaskCompletionSource zza;

    zzah(zzaj zzaj, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public void onResult(Status status) {
        TaskUtil.setResultOrApiException(status, this.zza);
    }
}

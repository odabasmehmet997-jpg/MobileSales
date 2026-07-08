package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public class StatusCallback extends IStatusCallback.Stub {
    @KeepForSdk
    private final BaseImplementation.ResultHolder<Status> resultHolder;

    @KeepForSdk
    public StatusCallback(@NonNull final BaseImplementation.ResultHolder<Status> resultHolder2) {
        resultHolder = resultHolder2;
    }

    @KeepForSdk
    public void onResult(@NonNull final Status status) {
        resultHolder.setResult(status);
    }
}

package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public class StatusPendingResult extends BasePendingResult<Status> {
    
    @NonNull
    public final Result createFailedResult(@NonNull final Status status) {
        return status;
    }
}

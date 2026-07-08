package com.google.android.gms.internal.location;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
abstract class zzae extends BaseImplementation.ApiMethodImpl {
    public final Result createFailedResult(Status status) {
        return status;
    }

    @KeepForSdk
    public final void setResult(Object obj) {
        this.setResult((Result) obj);
    }
}

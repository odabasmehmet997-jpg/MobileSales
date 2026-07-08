package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
abstract class zaf extends zag {
    protected zaf(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    public final Result createFailedResult(Status status) {
        return status;
    }
}

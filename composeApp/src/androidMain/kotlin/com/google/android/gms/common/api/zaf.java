package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.BasePendingResult;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zaf<R extends Result> extends BasePendingResult<R> {
    private final Result zae;

    
    public R createFailedResult(final Status status) {
        if (status.getStatusCode() == zae.getStatus().getStatusCode()) {
            return zae;
        }
        throw new UnsupportedOperationException("Creating failed results is not supported");
    }
}

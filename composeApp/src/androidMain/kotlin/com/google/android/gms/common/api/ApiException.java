package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class ApiException extends Exception {
    @NonNull
    @Deprecated
    protected final Status mStatus;

    public ApiException(@androidx.annotation.NonNull final com.google.android.gms.common.api.Status r4) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.ApiException.<init>(com.google.android.gms.common.api.Status):void");
    }

    @NonNull
    public Status getStatus() {
        return mStatus;
    }

    public int getStatusCode() {
        return mStatus.getStatusCode();
    }

    @Deprecated
    @Nullable
    public String getStatusMessage() {
        return mStatus.getStatusMessage();
    }
}

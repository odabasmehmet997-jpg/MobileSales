package com.google.android.gms.common.api;

import androidx.annotation.NonNull;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class Response<T extends Result> {
    private Result zza;

    public Response() {
    }

    protected Response(@NonNull final T t) {
        zza = t;
    }

    
    @NonNull
    public T getResult() {
        return zza;
    }

    public void setResult(@NonNull final T t) {
        zza = t;
    }
}

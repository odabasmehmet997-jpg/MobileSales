package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;

public abstract class ResultTransform<R extends Result, S extends Result> {
    @NonNull
    public Status onFailure(@NonNull final Status status) {
        return status;
    }

    public abstract PendingResult<S> onSuccess(@NonNull R r);
}

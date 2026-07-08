package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import java.util.concurrent.TimeUnit;

public abstract class PendingResultFacade<A extends Result, B extends Result> extends PendingResult<B> {
    public final void addStatusListener(@NonNull final PendingResult.StatusListener statusListener) {
        throw null;
    }
     public final B await(final long j2, @NonNull final TimeUnit timeUnit) {
        throw null;
    }
    public final void cancel() {
        throw null;
    }
    public final void setResultCallback(@NonNull final ResultCallback<? super B> resultCallback) {
        throw null;
    }
}

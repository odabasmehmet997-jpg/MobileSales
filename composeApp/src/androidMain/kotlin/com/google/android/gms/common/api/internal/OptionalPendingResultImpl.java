package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import java.util.concurrent.TimeUnit;

public final class OptionalPendingResultImpl<R extends Result> extends OptionalPendingResult<R> {
    private final BasePendingResult zaa;
    public void addStatusListener( final PendingResult.StatusListener statusListener) {
        zaa.addStatusListener(statusListener);
    }
    public void cancel() {
        zaa.cancel();
    }
    public void setResultCallback( final ResultCallback<? super R> resultCallback) {
        zaa.setResultCallback(resultCallback);
    }
    public R await(final long j2,  final TimeUnit timeUnit) {
        return zaa.await(j2, timeUnit);
    }
}

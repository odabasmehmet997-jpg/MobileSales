package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
;
import java.util.concurrent.TimeUnit;

public abstract class PendingResult<R extends Result> {

    public interface StatusListener {

        void onComplete( Status status);
    }

    public void addStatusListener( final StatusListener statusListener) {
        throw new UnsupportedOperationException();
    }

    public abstract R await(long j2,  TimeUnit timeUnit);

    public abstract void cancel();

    public abstract void setResultCallback(  ResultCallback<? super R> resultCallback);
}

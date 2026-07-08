package com.google.android.gms.common.api;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;

public abstract class ResultCallbacks<R extends Result> implements ResultCallback<R> {
    public abstract void onFailure(@NonNull Status status);

    @KeepForSdk
    public final void onResult(@NonNull final R r) {
        final Status status = r.getStatus();
        if (status.isSuccess()) {
            this.onSuccess(r);
            return;
        }
        this.onFailure(status);
        if (r instanceof Releasable) {
            try {
                ((Releasable) r).release();
            } catch (final RuntimeException e2) {
                Log.w("ResultCallbacks", "Unable to release " + r, e2);
            }
        }
    }

    public abstract void onSuccess(@NonNull R r);
}

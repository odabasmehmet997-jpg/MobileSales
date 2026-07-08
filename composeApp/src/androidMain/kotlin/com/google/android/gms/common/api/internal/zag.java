package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zag extends zac {
    private final TaskApiCall zaa;
    private final TaskCompletionSource zab;
    private final StatusExceptionMapper zad;

    public zag(final int i2, final TaskApiCall taskApiCall, final TaskCompletionSource taskCompletionSource, final StatusExceptionMapper statusExceptionMapper) {
        super(i2);
        zab = taskCompletionSource;
        zaa = taskApiCall;
        zad = statusExceptionMapper;
        if (2 == i2 && taskApiCall.shouldAutoResolveMissingFeatures()) {
            throw new IllegalArgumentException("Best-effort write calls cannot pass methods that should auto-resolve missing features.");
        }
    }

    public boolean zaa(final zabq zabq) {
        return zaa.shouldAutoResolveMissingFeatures();
    }

    @Nullable
    public Feature[] zab(final zabq zabq) {
        return zaa.zab();
    }

    public void zad(@NonNull final Status status) {
        zab.trySetException(zad.getException(status));
    }

    public void zae(@NonNull final Exception exc) {
        zab.trySetException(exc);
    }

    public void zaf(final zabq zabq) throws DeadObjectException {
        try {
            zaa.doExecute(zabq.zaf(), zab);
        } catch (final DeadObjectException e2) {
            throw e2;
        } catch (final RemoteException e3) {
            this.zad(zah(e3));
        } catch (final RuntimeException e4) {
            zab.trySetException(e4);
        }
    }

    public void zag(@NonNull final zaad zaad, final boolean z) {
        zaad.zad(zab, z);
    }
}

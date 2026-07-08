package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zae extends zai {
    private final BaseImplementation.ApiMethodImpl zaa;

    public zae(final int i2, final BaseImplementation.ApiMethodImpl apiMethodImpl) {
        super(i2);
        zaa = Preconditions.checkNotNull(apiMethodImpl, "Null methods are not runnable.");
    }

    public void zad(@NonNull final Status status) {
        try {
            zaa.setFailedResult(status);
        } catch (final IllegalStateException e2) {
            Log.w("ApiCallRunner", "Exception reporting failure", e2);
        }
    }

    public void zae(@NonNull final Exception exc) {
        final String simpleName = exc.getClass().getSimpleName();
        final String localizedMessage = exc.getLocalizedMessage();
        try {
            zaa.setFailedResult(new Status(10, simpleName + ": " + localizedMessage));
        } catch (final IllegalStateException e2) {
            Log.w("ApiCallRunner", "Exception reporting failure", e2);
        }
    }

    public void zaf(final zabq zabq) throws DeadObjectException {
        try {
            zaa.run(zabq.zaf());
        } catch (final RuntimeException e2) {
            this.zae(e2);
        }
    }

    public void zag(@NonNull final zaad zaad, final boolean z) {
        zaad.zac(zaa, z);
    }
}

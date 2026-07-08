package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
abstract class zad extends zac {
    protected final TaskCompletionSource zaa;

    protected zad(final int i2, final TaskCompletionSource taskCompletionSource) {
        super(i2);
        zaa = taskCompletionSource;
    }

    
    public abstract void zac(zabq zabq) throws RemoteException;

    public final void zad(@NonNull final Status status) {
        zaa.trySetException(new ApiException(status));
    }

    public final void zae(@NonNull final Exception exc) {
        zaa.trySetException(exc);
    }

    public final void zaf(final zabq zabq) throws DeadObjectException {
        try {
            this.zac(zabq);
        } catch (final DeadObjectException e2) {
            this.zad(zah(e2));
            throw e2;
        } catch (final RemoteException e3) {
            this.zad(zah(e3));
        } catch (final RuntimeException e4) {
            zaa.trySetException(e4);
        }
    }

    public void zag(@NonNull final zaad zaad, final boolean z) {
    }
}

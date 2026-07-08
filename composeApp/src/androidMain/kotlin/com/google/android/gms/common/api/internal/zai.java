package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public abstract class zai {
    public final int zac;

    protected zai(final int i2) {
        zac = i2;
    }

    static Status zah(final RemoteException remoteException) {
        return new Status(19, remoteException.getClass().getSimpleName() + ": " + remoteException.getLocalizedMessage());
    }

    public abstract void zad(@NonNull Status status);

    public abstract void zae(@NonNull Exception exc);

    public abstract void zaf(zabq zabq) throws DeadObjectException;

    public abstract void zag(@NonNull zaad zaad, boolean z);
}

package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.NonNull;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class RuntimeRemoteException extends RuntimeException {
    public RuntimeRemoteException(@NonNull final RemoteException remoteException) {
        super(remoteException);
    }
}

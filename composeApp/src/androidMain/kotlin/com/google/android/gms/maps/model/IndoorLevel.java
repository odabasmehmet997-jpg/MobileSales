package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.maps.zzab;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class IndoorLevel {
    private final zzab zza;

    public boolean equals(@Nullable final Object obj) {
        if (!(obj instanceof IndoorLevel)) {
            return false;
        }
        try {
            return zza.zzh(((IndoorLevel) obj).zza);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public int hashCode() {
        try {
            return zza.zzd();
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
}

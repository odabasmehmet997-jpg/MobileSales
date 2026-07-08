package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.maps.zzau;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class TileOverlay {
    private final zzau zza;

    public boolean equals(@Nullable final Object obj) {
        if (!(obj instanceof TileOverlay)) {
            return false;
        }
        try {
            return zza.zzn(((TileOverlay) obj).zza);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public int hashCode() {
        try {
            return zza.zzf();
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
}

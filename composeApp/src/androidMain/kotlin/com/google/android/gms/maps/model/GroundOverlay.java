package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzv;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class GroundOverlay {
    private final zzv zza;

    public GroundOverlay(final zzv zzv) {
        zza = Preconditions.checkNotNull(zzv);
    }

    public boolean equals(@Nullable final Object obj) {
        if (!(obj instanceof GroundOverlay)) {
            return false;
        }
        try {
            return zza.zzz(((GroundOverlay) obj).zza);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public int hashCode() {
        try {
            return zza.zzi();
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
}

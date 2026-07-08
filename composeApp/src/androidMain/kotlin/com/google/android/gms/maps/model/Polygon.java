package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzam;

public final class Polygon {
    private final zzam zza;
    public Polygon(final zzam zzam) {
        zza = Preconditions.checkNotNull(zzam);
    }
    public boolean equals(@Nullable final Object obj) {
        if (!(obj instanceof Polygon)) {
            return false;
        }
        try {
            return zza.zzB(((Polygon) obj).zza);
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

package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzap;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class Polyline {
    private final zzap zza;

    public Polyline(final zzap zzap) {
        zza = Preconditions.checkNotNull(zzap);
    }

    public boolean equals(@Nullable final Object obj) {
        if (!(obj instanceof Polyline)) {
            return false;
        }
        try {
            return zza.zzD(((Polyline) obj).zza);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public int hashCode() {
        try {
            return zza.zzh();
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
}

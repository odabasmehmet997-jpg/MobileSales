package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzl;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class Circle {
    private final zzl zza;

    public Circle(final zzl zzl) {
        zza = Preconditions.checkNotNull(zzl);
    }

    public boolean equals(@Nullable final Object obj) {
        if (!(obj instanceof Circle)) {
            return false;
        }
        try {
            return zza.zzy(((Circle) obj).zza);
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

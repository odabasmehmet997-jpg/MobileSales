package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzy;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class IndoorBuilding {
    private final zzy zza;

    public IndoorBuilding(final zzy zzy) {
        zzl zzl = com.google.android.gms.maps.model.zzl.zza;
        zza = Preconditions.checkNotNull(zzy, "delegate");
        final zzl zzl2 = Preconditions.checkNotNull(zzl, "shim");
    }

    public boolean equals(final Object obj) {
        if (!(obj instanceof IndoorBuilding)) {
            return false;
        }
        try {
            return zza.zzh(((IndoorBuilding) obj).zza);
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

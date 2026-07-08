package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzah;

public class Marker {
   protected final zzah zza;
    public Marker(final zzah zzah) {
        zza = Preconditions.checkNotNull(zzah);
    }
    public boolean equals(@Nullable final Object obj) {
        if (!(obj instanceof Marker)) {
            return false;
        }
        try {
            return zza.zzE(((Marker) obj).zza);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
    @NonNull
    public LatLng getPosition() {
        try {
            return zza.zzj();
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
    public int hashCode() {
        try {
            return zza.zzg();
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
    public void setDraggable(final boolean z) {
        try {
            zza.zzr(z);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
    public void setTitle(@Nullable final String str) {
        try {
            zza.zzA(str);
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
    public void setPosition(@NonNull final LatLng latLng) {
        if (null != latLng) {
            try {
                zza.zzw(latLng);
            } catch (final RemoteException e2) {
                throw new RuntimeRemoteException(e2);
            }
        } else {
            throw new IllegalArgumentException("latlng cannot be null - a position is required.");
        }
    }
}

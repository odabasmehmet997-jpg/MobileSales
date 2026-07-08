package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class CameraUpdateFactory {
    private static ICameraUpdateFactoryDelegate zza;
    public static CameraUpdate newLatLng(final LatLng latLng) {
        Preconditions.checkNotNull(latLng, "latLng must not be null");
        try {
            return new CameraUpdate(CameraUpdateFactory.zzb().newLatLng(latLng));
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
    public static CameraUpdate newLatLngZoom(final LatLng latLng, final float f2) {
        Preconditions.checkNotNull(latLng, "latLng must not be null");
        try {
            return new CameraUpdate(CameraUpdateFactory.zzb().newLatLngZoom(latLng, f2));
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
    public static void zza(final ICameraUpdateFactoryDelegate iCameraUpdateFactoryDelegate) {
        CameraUpdateFactory.zza = Preconditions.checkNotNull(iCameraUpdateFactoryDelegate);
    }
    private static ICameraUpdateFactoryDelegate zzb() {
        return Preconditions.checkNotNull(CameraUpdateFactory.zza, "CameraUpdateFactory is not initialized");
    }
}

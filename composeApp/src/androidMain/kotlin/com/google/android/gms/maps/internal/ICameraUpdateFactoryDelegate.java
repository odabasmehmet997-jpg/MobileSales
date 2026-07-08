package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public interface ICameraUpdateFactoryDelegate extends IInterface {
    @NonNull
    IObjectWrapper newLatLng(@NonNull LatLng latLng) throws RemoteException;

    @NonNull
    IObjectWrapper newLatLngZoom(@NonNull LatLng latLng, float f2) throws RemoteException;
}

package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.maps.model.MarkerOptions;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public interface IGoogleMapDelegate extends IInterface {
    zzah addMarker(MarkerOptions markerOptions) throws RemoteException;

    void animateCamera(@NonNull IObjectWrapper iObjectWrapper) throws RemoteException;

    @NonNull
    IUiSettingsDelegate getUiSettings() throws RemoteException;

    void setBuildingsEnabled(boolean z) throws RemoteException;

    boolean setIndoorEnabled(boolean z) throws RemoteException;

    void setMapType(int i2) throws RemoteException;

    void setMyLocationEnabled(boolean z) throws RemoteException;

    void setOnMapClickListener(zzan zzan) throws RemoteException;

    void setOnMarkerDragListener(zzax zzax) throws RemoteException;

    void setOnMyLocationButtonClickListener(zzaz zzaz) throws RemoteException;

    void setTrafficEnabled(boolean z) throws RemoteException;
}

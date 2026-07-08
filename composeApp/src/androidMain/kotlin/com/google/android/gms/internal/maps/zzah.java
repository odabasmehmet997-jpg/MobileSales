package com.google.android.gms.internal.maps;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.maps.model.LatLng;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public interface zzah extends IInterface {
    void zzA(String str) throws RemoteException;

    boolean zzE(zzah zzah) throws RemoteException;

    int zzg() throws RemoteException;

    LatLng zzj() throws RemoteException;

    void zzr(boolean z) throws RemoteException;

    void zzw(LatLng latLng) throws RemoteException;
}

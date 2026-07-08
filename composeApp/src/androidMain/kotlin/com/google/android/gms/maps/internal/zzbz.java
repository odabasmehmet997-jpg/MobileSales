package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zzbz extends zza implements IStreetViewPanoramaViewDelegate {
    zzbz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
    }

    public void getStreetViewPanoramaAsync(zzbt zzbt) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbt);
        zzc(9, zza);
    }

    public IObjectWrapper getView() throws RemoteException {
        Parcel zzJ = zzJ(8, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzJ.readStrongBinder());
        zzJ.recycle();
        return asInterface;
    }

    public void onCreate(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, bundle);
        zzc(2, zza);
    }

    public void onDestroy() throws RemoteException {
        zzc(5, zza());
    }

    public void onLowMemory() throws RemoteException {
        zzc(6, zza());
    }

    public void onPause() throws RemoteException {
        zzc(4, zza());
    }

    public void onResume() throws RemoteException {
        zzc(3, zza());
    }

    public void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, bundle);
        Parcel zzJ = zzJ(7, zza);
        if (0 != zzJ.readInt()) {
            bundle.readFromParcel(zzJ);
        }
        zzJ.recycle();
    }

    public void onStart() throws RemoteException {
        zzc(10, zza());
    }

    public void onStop() throws RemoteException {
        zzc(11, zza());
    }
}

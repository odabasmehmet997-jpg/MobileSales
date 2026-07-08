package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.GoogleMapOptions;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zzk extends zza implements IMapFragmentDelegate {
    zzk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IMapFragmentDelegate");
    }

    public void getMapAsync(zzat zzat) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzat);
        zzc(12, zza);
    }

    public void onCreate(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, bundle);
        zzc(3, zza);
    }

    public IObjectWrapper onCreateView(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc.zzg(zza, iObjectWrapper2);
        zzc.zze(zza, bundle);
        Parcel zzJ = zzJ(4, zza);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzJ.readStrongBinder());
        zzJ.recycle();
        return asInterface;
    }

    public void onDestroy() throws RemoteException {
        zzc(8, zza());
    }

    public void onDestroyView() throws RemoteException {
        zzc(7, zza());
    }

    public void onInflate(IObjectWrapper iObjectWrapper, GoogleMapOptions googleMapOptions, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc.zze(zza, googleMapOptions);
        zzc.zze(zza, bundle);
        zzc(2, zza);
    }

    public void onLowMemory() throws RemoteException {
        zzc(9, zza());
    }

    public void onPause() throws RemoteException {
        zzc(6, zza());
    }

    public void onResume() throws RemoteException {
        zzc(5, zza());
    }

    public void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, bundle);
        Parcel zzJ = zzJ(10, zza);
        if (0 != zzJ.readInt()) {
            bundle.readFromParcel(zzJ);
        }
        zzJ.recycle();
    }

    public void onStart() throws RemoteException {
        zzc(15, zza());
    }

    public void onStop() throws RemoteException {
        zzc(16, zza());
    }
}

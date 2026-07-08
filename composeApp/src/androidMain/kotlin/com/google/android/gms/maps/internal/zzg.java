package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzag;
import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.MarkerOptions;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zzg extends zza implements IGoogleMapDelegate {
    zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
    }

    public zzah addMarker(MarkerOptions markerOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, markerOptions);
        Parcel zzJ = zzJ(11, zza);
        zzah zzb = zzag.zzb(zzJ.readStrongBinder());
        zzJ.recycle();
        return zzb;
    }

    public void animateCamera(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc(5, zza);
    }

    public com.google.android.gms.maps.internal.IUiSettingsDelegate getUiSettings() throws android.os.RemoteException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzg.getUiSettings():com.google.android.gms.maps.internal.IUiSettingsDelegate");
    }

    public void setBuildingsEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        final int i2 = zzc.r8clinit;
        zza.writeInt(z ? 1 : 0);
        zzc(41, zza);
    }

    public boolean setIndoorEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        final int i2 = zzc.r8clinit;
        zza.writeInt(z ? 1 : 0);
        Parcel zzJ = zzJ(20, zza);
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public void setMapType(int i2) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i2);
        zzc(16, zza);
    }

    public void setMyLocationEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        final int i2 = zzc.r8clinit;
        zza.writeInt(z ? 1 : 0);
        zzc(22, zza);
    }

    public void setOnMapClickListener(zzan zzan) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzan);
        zzc(28, zza);
    }

    public void setOnMarkerDragListener(zzax zzax) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzax);
        zzc(31, zza);
    }

    public void setOnMyLocationButtonClickListener(zzaz zzaz) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzaz);
        zzc(37, zza);
    }

    public void setTrafficEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        final int i2 = zzc.r8clinit;
        zza.writeInt(z ? 1 : 0);
        zzc(18, zza);
    }
}

package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzh;
import com.google.android.gms.internal.maps.zzi;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zze extends zza implements zzf {
    zze(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.ICreator");
    }

    public int zzd() throws RemoteException {
        Parcel zzJ = zzJ(9, zza());
        int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }

    public com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate zze() throws android.os.RemoteException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zze.zze():com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
    }

    public com.google.android.gms.maps.internal.IMapFragmentDelegate zzf(com.google.android.gms.dynamic.IObjectWrapper r4) throws android.os.RemoteException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zze.zzf(com.google.android.gms.dynamic.IObjectWrapper):com.google.android.gms.maps.internal.IMapFragmentDelegate");
    }


    public com.google.android.gms.maps.internal.IMapViewDelegate zzg(com.google.android.gms.dynamic.IObjectWrapper r3, com.google.android.gms.maps.GoogleMapOptions r4) throws android.os.RemoteException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zze.zzg(com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.maps.GoogleMapOptions):com.google.android.gms.maps.internal.IMapViewDelegate");
    }

    public com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate zzh(com.google.android.gms.dynamic.IObjectWrapper r4) throws android.os.RemoteException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zze.zzh(com.google.android.gms.dynamic.IObjectWrapper):com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
    }

    public com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate zzi(com.google.android.gms.dynamic.IObjectWrapper r3, com.google.android.gms.maps.StreetViewPanoramaOptions r4) throws android.os.RemoteException {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zze.zzi(com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.maps.StreetViewPanoramaOptions):com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
    }

    public zzi zzj() throws RemoteException {
        Parcel zzJ = zzJ(5, zza());
        zzi zzb = zzh.zzb(zzJ.readStrongBinder());
        zzJ.recycle();
        return zzb;
    }

    public void zzk(IObjectWrapper iObjectWrapper, int i2) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zza.writeInt(19000000);
        zzc(6, zza);
    }

    public void zzl(IObjectWrapper iObjectWrapper, int i2) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zza.writeInt(i2);
        zzc(10, zza);
    }

    public void zzm(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc(11, zza);
    }
}

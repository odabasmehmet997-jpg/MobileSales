package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzbm;
import com.google.android.gms.internal.measurement.zzbo;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public final class zzdx extends zzbm implements zzdz {
    zzdx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    public String zzd(zzp zzp) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzp);
        Parcel zzb = zzb(11, zza);
        String readString = zzb.readString();
        zzb.recycle();
        return readString;
    }

    public List zze(zzp zzp, boolean z) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzp);
        zzbo.zzc(zza, z);
        Parcel zzb = zzb(7, zza);
        ArrayList<zzku> createTypedArrayList = zzb.createTypedArrayList(zzku.CREATOR);
        zzb.recycle();
        return createTypedArrayList;
    }

    public List zzf(String str, String str2, zzp zzp) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzbo.zzd(zza, zzp);
        Parcel zzb = zzb(16, zza);
        ArrayList<zzab> createTypedArrayList = zzb.createTypedArrayList(zzab.CREATOR);
        zzb.recycle();
        return createTypedArrayList;
    }

    public List zzg(String str, String str2, String str3) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(null);
        zza.writeString(str2);
        zza.writeString(str3);
        Parcel zzb = zzb(17, zza);
        ArrayList<zzab> createTypedArrayList = zzb.createTypedArrayList(zzab.CREATOR);
        zzb.recycle();
        return createTypedArrayList;
    }

    public List zzh(String str, String str2, boolean z, zzp zzp) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzbo.zzc(zza, z);
        zzbo.zzd(zza, zzp);
        Parcel zzb = zzb(14, zza);
        ArrayList<zzku> createTypedArrayList = zzb.createTypedArrayList(zzku.CREATOR);
        zzb.recycle();
        return createTypedArrayList;
    }

    public List zzi(String str, String str2, String str3, boolean z) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(null);
        zza.writeString(str2);
        zza.writeString(str3);
        zzbo.zzc(zza, z);
        Parcel zzb = zzb(15, zza);
        ArrayList<zzku> createTypedArrayList = zzb.createTypedArrayList(zzku.CREATOR);
        zzb.recycle();
        return createTypedArrayList;
    }

    public void zzj(zzp zzp) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzp);
        zzc(4, zza);
    }

    public void zzk(zzau zzau, zzp zzp) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzau);
        zzbo.zzd(zza, zzp);
        zzc(1, zza);
    }

    public void zzm(zzp zzp) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzp);
        zzc(18, zza);
    }

    public void zzn(zzab zzab, zzp zzp) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzab);
        zzbo.zzd(zza, zzp);
        zzc(12, zza);
    }

    public void zzp(zzp zzp) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzp);
        zzc(20, zza);
    }

    public void zzq(long j2, String str, String str2, String str3) throws RemoteException {
        Parcel zza = zza();
        zza.writeLong(j2);
        zza.writeString(str);
        zza.writeString(str2);
        zza.writeString(str3);
        zzc(10, zza);
    }

    public void zzr(Bundle bundle, zzp zzp) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, bundle);
        zzbo.zzd(zza, zzp);
        zzc(19, zza);
    }

    public void zzs(zzp zzp) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzp);
        zzc(6, zza);
    }

    public void zzt(zzku zzku, zzp zzp) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzku);
        zzbo.zzd(zza, zzp);
        zzc(2, zza);
    }

    public byte[] zzu(zzau zzau, String str) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzau);
        zza.writeString(str);
        Parcel zzb = zzb(9, zza);
        byte[] createByteArray = zzb.createByteArray();
        zzb.recycle();
        return createByteArray;
    }
}

package com.google.android.gms.internal.maps;

import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zzas extends zza implements zzau {
    public int zzf() throws RemoteException {
        Parcel zzJ = zzJ(9, zza());
        int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }

    public boolean zzn(zzau zzau) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzau);
        Parcel zzJ = zzJ(8, zza);
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }
}

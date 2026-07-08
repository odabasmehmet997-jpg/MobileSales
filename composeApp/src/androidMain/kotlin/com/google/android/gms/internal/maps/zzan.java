package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zzan extends zza implements zzap {
    zzan(final IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IPolylineDelegate");
    }

    public boolean zzD(final zzap zzap) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzg(zza, zzap);
        final Parcel zzJ = this.zzJ(15, zza);
        final boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public int zzh() throws RemoteException {
        final Parcel zzJ = this.zzJ(16, this.zza());
        final int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }
}

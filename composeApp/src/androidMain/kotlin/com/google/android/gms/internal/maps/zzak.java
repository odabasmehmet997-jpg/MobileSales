package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zzak extends zza implements zzam {
    zzak(final IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IPolygonDelegate");
    }

    public boolean zzB(final zzam zzam) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzg(zza, zzam);
        final Parcel zzJ = this.zzJ(19, zza);
        final boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public int zzi() throws RemoteException {
        final Parcel zzJ = this.zzJ(20, this.zza());
        final int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }
}

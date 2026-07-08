package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zzj extends zza implements zzl {
    zzj(final IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ICircleDelegate");
    }

    public int zzi() throws RemoteException {
        final Parcel zzJ = this.zzJ(18, this.zza());
        final int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }

    public boolean zzy(final zzl zzl) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzg(zza, zzl);
        final Parcel zzJ = this.zzJ(17, zza);
        final boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }
}

package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zzn extends zza implements zzp {
    zzn(final IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IFeatureDelegate");
    }

    public int zzd() throws RemoteException {
        final Parcel zzJ = this.zzJ(1, this.zza());
        final int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }
}

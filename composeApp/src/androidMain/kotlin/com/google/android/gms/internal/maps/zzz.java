package com.google.android.gms.internal.maps;

import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zzz extends zza implements zzab {
    public int zzd() throws RemoteException {
        final Parcel zzJ = this.zzJ(5, this.zza());
        final int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }

    public boolean zzh(final zzab zzab) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zzg(zza, zzab);
        final Parcel zzJ = this.zzJ(4, zza);
        final boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }
}

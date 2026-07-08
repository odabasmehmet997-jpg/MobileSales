package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.Tile;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zzav extends zza implements zzax {
    zzav(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }

    public Tile zzb(int i2, int i3, int i4) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i2);
        zza.writeInt(i3);
        zza.writeInt(i4);
        Parcel zzJ = zzJ(1, zza);
        Tile tile = (Tile) zzc.zza(zzJ, Tile.CREATOR);
        zzJ.recycle();
        return tile;
    }
}

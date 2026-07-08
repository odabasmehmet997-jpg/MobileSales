package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.Tile;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public abstract class zzaw extends zzb implements zzax {
    protected zzaw() {
        super("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }

    public static zzax zzc(final IBinder iBinder) {
        if (null == iBinder) {
            return null;
        }
        final IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
        return queryLocalInterface instanceof zzax ? (zzax) queryLocalInterface : new zzav(iBinder);
    }

    
    public final boolean zza(final int i2, final Parcel parcel, final Parcel parcel2, final int i3) throws RemoteException {
        if (1 != i2) {
            return false;
        }
        final int readInt = parcel.readInt();
        final int readInt2 = parcel.readInt();
        final int readInt3 = parcel.readInt();
        zzc.zzd(parcel);
        final Tile zzb = this.zzb(readInt, readInt2, readInt3);
        parcel2.writeNoException();
        zzc.zzf(parcel2, zzb);
        return true;
    }
}

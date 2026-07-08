package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.location.zzb;
import com.google.android.gms.internal.location.zzc;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public abstract class zzy extends zzb implements zzz {
    protected zzy() {
        super("com.google.android.gms.location.ILocationListener");
    }

    public static zzz zzb(final IBinder iBinder) {
        final IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationListener");
        return queryLocalInterface instanceof zzz ? (zzz) queryLocalInterface : new zzx(iBinder);
    }

    
    public final boolean zza(final int i2, final Parcel parcel, final Parcel parcel2, final int i3) throws RemoteException {
        if (1 == i2) {
            zzc.zzd(parcel);
            this.zzd((Location) zzc.zza(parcel, Location.CREATOR));
        } else if (2 != i2) {
            return false;
        } else {
            this.zze();
        }
        return true;
    }
}

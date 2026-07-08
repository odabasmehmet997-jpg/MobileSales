package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.location.zzb;
import com.google.android.gms.internal.location.zzc;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public abstract class zzv extends zzb implements zzw {
    protected zzv() {
        super("com.google.android.gms.location.ILocationCallback");
    }

    public static zzw zzb(final IBinder iBinder) {
        final IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
        return queryLocalInterface instanceof zzw ? (zzw) queryLocalInterface : new zzu(iBinder);
    }

    
    public final boolean zza(final int i2, final Parcel parcel, final Parcel parcel2, final int i3) throws RemoteException {
        if (1 == i2) {
            zzc.zzd(parcel);
            this.zzd((LocationResult) zzc.zza(parcel, LocationResult.CREATOR));
        } else if (2 == i2) {
            zzc.zzd(parcel);
            this.zze((LocationAvailability) zzc.zza(parcel, LocationAvailability.CREATOR));
        } else if (3 != i2) {
            return false;
        } else {
            this.zzf();
        }
        return true;
    }
}

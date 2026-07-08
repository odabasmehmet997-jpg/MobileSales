package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationAvailability;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public abstract class zzw extends zzb implements zzx {
    protected zzw() {
        super("com.google.android.gms.location.internal.ILocationAvailabilityStatusCallback");
    }

    
    public final boolean zza(final int i2, final Parcel parcel, final Parcel parcel2, final int i3) throws RemoteException {
        if (1 != i2) {
            return false;
        }
        zzc.zzd(parcel);
        this.zzb((Status) zzc.zza(parcel, Status.CREATOR), (LocationAvailability) zzc.zza(parcel, LocationAvailability.CREATOR));
        return true;
    }
}

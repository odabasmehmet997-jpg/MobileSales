package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.location.LocationSettingsResult;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public abstract class zzaa extends zzb implements zzab {
    protected zzaa() {
        super("com.google.android.gms.location.internal.ISettingsCallbacks");
    }

    
    public final boolean zza(final int i2, final Parcel parcel, final Parcel parcel2, final int i3) throws RemoteException {
        if (1 != i2) {
            return false;
        }
        zzc.zzd(parcel);
        this.zzb((LocationSettingsResult) zzc.zza(parcel, LocationSettingsResult.CREATOR));
        return true;
    }
}

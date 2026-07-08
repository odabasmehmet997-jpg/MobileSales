package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public abstract class zzq extends zzb implements zzr {
    protected zzq() {
        super("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
    }

    
    public final boolean zza(final int i2, final Parcel parcel, final Parcel parcel2, final int i3) throws RemoteException {
        if (1 == i2) {
            zzc.zzd(parcel);
            this.zzd((zzl) zzc.zza(parcel, zzl.CREATOR));
        } else if (2 != i2) {
            return false;
        } else {
            this.zze();
        }
        return true;
    }
}

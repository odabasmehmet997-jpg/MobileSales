package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public abstract class zzn extends zzb implements zzo {
    protected zzn() {
        super("com.google.android.gms.location.internal.IBooleanStatusCallback");
    }

    
    public final boolean zza(final int i2, final Parcel parcel, final Parcel parcel2, final int i3) throws RemoteException {
        boolean z = false;
        if (1 != i2) {
            return false;
        }
        final Status status = (Status) zzc.zza(parcel, Status.CREATOR);
        if (0 != parcel.readInt()) {
            z = true;
        }
        zzc.zzd(parcel);
        this.zzb(status, z);
        return true;
    }
}

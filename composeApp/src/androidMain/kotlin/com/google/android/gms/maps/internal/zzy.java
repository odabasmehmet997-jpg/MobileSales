package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzu;
import com.google.android.gms.internal.maps.zzv;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public abstract class zzy extends zzb implements zzz {
    protected zzy() {
        super("com.google.android.gms.maps.internal.IOnGroundOverlayClickListener");
    }

    
    public final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (1 != i2) {
            return false;
        }
        zzv zzb = zzu.zzb(parcel.readStrongBinder());
        zzc.zzd(parcel);
        zzb(zzb);
        parcel2.writeNoException();
        return true;
    }
}

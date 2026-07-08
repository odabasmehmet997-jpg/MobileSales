package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public abstract class zzao extends zzb implements zzap {
    protected zzao() {
        super("com.google.android.gms.maps.internal.IOnMapLoadedCallback");
    }

    
    public final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (1 != i2) {
            return false;
        }
        zzb();
        parcel2.writeNoException();
        return true;
    }
}

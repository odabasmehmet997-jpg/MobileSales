package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public abstract class zzay extends zzb implements zzaz {
    protected zzay() {
        super("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
    }

    
    public final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (1 != i2) {
            return false;
        }
        boolean zzb = zzb();
        parcel2.writeNoException();
        final int i4 = zzc.r8clinit;
        parcel2.writeInt(zzb ? 1 : 0);
        return true;
    }
}

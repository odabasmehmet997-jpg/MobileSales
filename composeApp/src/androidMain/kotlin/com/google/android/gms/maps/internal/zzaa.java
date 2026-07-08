package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.internal.maps.zzy;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public abstract class zzaa extends zzb implements zzab {
    protected zzaa() {
        super("com.google.android.gms.maps.internal.IOnIndoorStateChangeListener");
    }

    
    public final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (1 == i2) {
            zzb();
        } else if (2 != i2) {
            return false;
        } else {
            zzy zzb = zzx.zzb(parcel.readStrongBinder());
            zzc.zzd(parcel);
            zzc(zzb);
        }
        parcel2.writeNoException();
        return true;
    }
}

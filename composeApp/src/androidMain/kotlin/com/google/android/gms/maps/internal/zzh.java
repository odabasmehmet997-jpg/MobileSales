package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzag;
import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public abstract class zzh extends zzb implements zzi {
    protected zzh() {
        super("com.google.android.gms.maps.internal.IInfoWindowAdapter");
    }

    
    public final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (1 == i2) {
            zzah zzb = zzag.zzb(parcel.readStrongBinder());
            zzc.zzd(parcel);
            IObjectWrapper zzc = zzc(zzb);
            parcel2.writeNoException();
            zzc.zzg(parcel2, zzc);
        } else if (2 != i2) {
            return false;
        } else {
            zzah zzb2 = zzag.zzb(parcel.readStrongBinder());
            zzc.zzd(parcel);
            IObjectWrapper zzb3 = zzb(zzb2);
            parcel2.writeNoException();
            zzc.zzg(parcel2, zzb3);
        }
        return true;
    }
}

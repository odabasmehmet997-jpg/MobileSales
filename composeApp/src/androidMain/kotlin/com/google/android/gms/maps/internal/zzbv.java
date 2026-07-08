package com.google.android.gms.maps.internal;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public abstract class zzbv extends zzb implements zzbw {
    protected zzbv() {
        super("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
    }

    
    public final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (1 == i2) {
            zzc.zzd(parcel);
            zzb((Bitmap) zzc.zza(parcel, Bitmap.CREATOR));
        } else if (2 != i2) {
            return false;
        } else {
            IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
            zzc.zzd(parcel);
            zzc(asInterface);
        }
        parcel2.writeNoException();
        return true;
    }
}

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzag;
import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public abstract class zzaw extends zzb implements zzax {
    protected zzaw() {
        super("com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }

    
    public final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (1 == i2) {
            zzah zzb = zzag.zzb(parcel.readStrongBinder());
            zzc.zzd(parcel);
            zzd(zzb);
        } else if (2 == i2) {
            zzah zzb2 = zzag.zzb(parcel.readStrongBinder());
            zzc.zzd(parcel);
            zzb(zzb2);
        } else if (3 != i2) {
            return false;
        } else {
            zzah zzb3 = zzag.zzb(parcel.readStrongBinder());
            zzc.zzd(parcel);
            zzc(zzb3);
        }
        parcel2.writeNoException();
        return true;
    }
}

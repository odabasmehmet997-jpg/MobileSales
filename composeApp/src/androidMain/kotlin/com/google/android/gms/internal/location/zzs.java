package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public abstract class zzs extends zzb implements zzt {
    protected zzs() {
        super("com.google.android.gms.location.internal.IGeofencerCallbacks");
    }

    
    public final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (1 == i2) {
            int readInt = parcel.readInt();
            String[] createStringArray = parcel.createStringArray();
            zzc.zzd(parcel);
            zzb(readInt, createStringArray);
        } else if (2 == i2) {
            int readInt2 = parcel.readInt();
            String[] createStringArray2 = parcel.createStringArray();
            zzc.zzd(parcel);
            zzc(readInt2, createStringArray2);
        } else if (3 != i2) {
            return false;
        } else {
            zzc.zzd(parcel);
            zzd(parcel.readInt(), (PendingIntent) zzc.zza(parcel, PendingIntent.CREATOR));
        }
        return true;
    }
}

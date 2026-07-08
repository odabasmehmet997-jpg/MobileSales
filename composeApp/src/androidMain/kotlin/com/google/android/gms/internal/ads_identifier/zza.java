package com.google.android.gms.internal.ads_identifier;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-identifier@@17.1.0 */
public class zza implements IInterface {
    private final IBinder zza;
    private final String zzb = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";

    protected zza(final IBinder iBinder, final String str) {
        zza = iBinder;
    }

    public final IBinder asBinder() {
        return zza;
    }

    
    public final Parcel zza() {
        final Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(zzb);
        return obtain;
    }

    
    public final Parcel zzb(final int i2, Parcel parcel) throws RemoteException {
        parcel = Parcel.obtain();
        try {
            zza.transact(i2, parcel, parcel, 0);
            parcel.readException();
            return parcel;
        } catch (final RuntimeException e2) {
            throw e2;
        } finally {
            parcel.recycle();
        }
    }
}

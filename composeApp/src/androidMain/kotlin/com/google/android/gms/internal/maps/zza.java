package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public class zza implements IInterface {
    private final IBinder zza;
    private final String zzb;

    protected zza(final IBinder iBinder, final String str) {
        zza = iBinder;
        zzb = str;
    }

    public final IBinder asBinder() {
        return zza;
    }

    
    public final Parcel zzJ(final int i2, Parcel parcel) throws RemoteException {
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

    
    public final Parcel zza() {
        final Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(zzb);
        return obtain;
    }

    
    public final void zzc(final int i2, final Parcel parcel) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        try {
            zza.transact(i2, parcel, obtain, 0);
            obtain.readException();
        } finally {
            parcel.recycle();
            obtain.recycle();
        }
    }
}

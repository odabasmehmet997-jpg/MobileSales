package com.google.android.gms.internal.base;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public class zaa implements IInterface {
    private final IBinder zaa;
    private final String zab;

    protected zaa(final IBinder iBinder, final String str) {
        zaa = iBinder;
        zab = str;
    }

    public final IBinder asBinder() {
        return zaa;
    }

    
    public final Parcel zaa() {
        final Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(zab);
        return obtain;
    }

    
    public final Parcel zab(final int i2, Parcel parcel) throws RemoteException {
        parcel = Parcel.obtain();
        try {
            zaa.transact(2, parcel, parcel, 0);
            parcel.readException();
            return parcel;
        } catch (final RuntimeException e2) {
            throw e2;
        } finally {
            parcel.recycle();
        }
    }

    
    public final void zac(final int i2, final Parcel parcel) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        try {
            zaa.transact(i2, parcel, obtain, 0);
            obtain.readException();
        } finally {
            parcel.recycle();
            obtain.recycle();
        }
    }

    
    public final void zad(final int i2, final Parcel parcel) throws RemoteException {
        try {
            zaa.transact(1, parcel, (Parcel) null, 1);
        } finally {
            parcel.recycle();
        }
    }
}

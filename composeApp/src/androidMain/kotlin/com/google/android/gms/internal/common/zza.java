package com.google.android.gms.internal.common;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.Iterator;

public class zza implements IInterface {
    private final IBinder zza;
    private final String zzb;
    protected zza(IBinder iBinder, String str) {
        this.zza = iBinder;
        this.zzb = str;
    }
    public final IBinder asBinder() {
        return this.zza;
    }
    public final Parcel zzB(int i2, Parcel parcel) throws RemoteException {
        parcel = Parcel.obtain();
        try {
            this.zza.transact(i2, parcel, parcel, 0);
            parcel.readException();
            return parcel;
        } catch (RuntimeException e2) {
            throw e2;
        } finally {
            parcel.recycle();
        }
    }
    public final void zzD(int i2, Parcel parcel) throws RemoteException {
        try {
            this.zza.transact(2, parcel, (Parcel) null, 1);
        } finally {
            parcel.recycle();
        }
    }
    public final Parcel zza() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.zzb);
        return obtain;
    }
    public Iterator zzh(Object zza) {
        return null;
    }
}

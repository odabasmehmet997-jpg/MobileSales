package com.google.android.gms.internal.common;

import android.os.*;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class zzb extends Binder implements IInterface {
    protected zzb(String str) {
        attachInterface(this, str);
    }

    public final IBinder asBinder() {
        return this;
    }

    public final boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (16777215 >= i2) {
            parcel.enforceInterface(getInterfaceDescriptor());
        } else if (super.onTransact(i2, parcel, parcel2, i3)) {
            return true;
        }
        return zza(i2, parcel, parcel2, i3);
    }

    
    public boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        return false;
    }
}

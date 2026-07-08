package com.google.android.gms.internal.gtm;

import android.os.*;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public class zzas extends Binder implements IInterface {
    public IBinder asBinder() {
        return this;
    }

    public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (16777215 >= i2) {
            parcel.enforceInterface(getInterfaceDescriptor());
        } else if (super.onTransact(i2, parcel, parcel2, i3)) {
            return true;
        }
        return zza(i2, parcel, parcel2, i3);
    }

    
    public boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        throw null;
    }
}

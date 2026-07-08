package com.google.android.gms.internal.base;

import android.os.*;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public class zab extends Binder implements IInterface {
    protected zab(final String str) {
        this.attachInterface(this, str);
    }

    public final IBinder asBinder() {
        return this;
    }

    public final boolean onTransact(final int i2, final Parcel parcel, final Parcel parcel2, final int i3) throws RemoteException {
        if (16777215 >= i2) {
            parcel.enforceInterface(this.getInterfaceDescriptor());
        } else if (super.onTransact(i2, parcel, parcel2, i3)) {
            return true;
        }
        return this.zaa(i2, parcel, parcel2, i3);
    }

    
    public boolean zaa(final int i2, final Parcel parcel, final Parcel parcel2, final int i3) throws RemoteException {
        throw null;
    }
}

package com.google.android.gms.internal.maps;

import android.os.*;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public class zzb extends Binder implements IInterface {
    protected zzb(final String str) {
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
        return this.zza(i2, parcel, parcel2, i3);
    }

    
    public boolean zza(final int i2, final Parcel parcel, final Parcel parcel2, final int i3) throws RemoteException {
        throw null;
    }
}

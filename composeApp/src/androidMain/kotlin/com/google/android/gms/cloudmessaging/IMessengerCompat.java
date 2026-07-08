package com.google.android.gms.cloudmessaging;

import android.os.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
 
interface IMessengerCompat extends IInterface {
    class Impl extends Binder implements IMessengerCompat {
        public IBinder asBinder() {
            throw null;
        }

        public boolean onTransact(final int i2, final Parcel parcel, final Parcel parcel2, final int i3) throws RemoteException {
            throw null;
        }

        public void send(final Message message) throws RemoteException {
            throw null;
        }
    }
    class Proxy implements IMessengerCompat {
        private final IBinder zza = null;
        public IBinder asBinder() {
            return zza;
        }

        public void send(final Message message) throws RemoteException {
            final Parcel obtain = Parcel.obtain();
            obtain.writeInterfaceToken("com.google.android.gms.iid.IMessengerCompat");
            obtain.writeInt(1);
            message.writeToParcel(obtain, 0);
            try {
                zza.transact(1, obtain, (Parcel) null, 1);
            } finally {
                obtain.recycle();
            }
        }
    }
    void send(Message message) throws RemoteException;
}

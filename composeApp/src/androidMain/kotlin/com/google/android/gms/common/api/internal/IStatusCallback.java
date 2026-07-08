package com.google.android.gms.common.api.internal;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.base.zab;
import com.google.android.gms.internal.base.zac;

public interface IStatusCallback extends IInterface {
    abstract class Stub extends zab implements IStatusCallback {
        protected Stub() {
            super("com.google.android.gms.common.api.internal.IStatusCallback");
        }

        
        public final boolean zaa(final int i2, @NonNull final Parcel parcel, @NonNull final Parcel parcel2, final int i3) throws RemoteException {
            if (1 != i2) {
                return false;
            }
            zac.zab(parcel);
            this.onResult((Status) zac.zaa(parcel, Status.CREATOR));
            return true;
        }
    }

    void onResult(@NonNull Status status) throws RemoteException;
}

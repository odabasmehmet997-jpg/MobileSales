package com.google.android.gms.common.internal.service;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.base.zab;
import com.google.android.gms.internal.base.zac;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public abstract class zaj extends zab implements zak {
    protected zaj() {
        super("com.google.android.gms.common.internal.service.ICommonCallbacks");
    }

    
    public final boolean zaa(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (1 != i2) {
            return false;
        }
        int readInt = parcel.readInt();
        zac.zab(parcel);
        zab(readInt);
        return true;
    }
}

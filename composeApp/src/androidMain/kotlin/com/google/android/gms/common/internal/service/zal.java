package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.base.zaa;
import com.google.android.gms.internal.base.zac;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zal extends zaa {
    zal(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.service.ICommonService");
    }

    public void zae(zak zak) throws RemoteException {
        Parcel zaa = zaa();
        zac.zad(zaa, zak);
        zad(1, zaa);
    }
}

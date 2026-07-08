package com.google.android.gms.common.moduleinstall.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.internal.base.zaa;
import com.google.android.gms.internal.base.zac;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zaf extends zaa {
    zaf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.moduleinstall.internal.IModuleInstallService");
    }

    public void zae(zae zae, ApiFeatureRequest apiFeatureRequest) throws RemoteException {
        Parcel zaa = zaa();
        zac.zad(zaa, zae);
        zac.zac(zaa, apiFeatureRequest);
        zac(1, zaa);
    }

    public void zaf(zae zae, ApiFeatureRequest apiFeatureRequest) throws RemoteException {
        Parcel zaa = zaa();
        zac.zad(zaa, zae);
        zac.zac(zaa, apiFeatureRequest);
        zac(3, zaa);
    }

    public void zag(zae zae, ApiFeatureRequest apiFeatureRequest, zah zah) throws RemoteException {
        Parcel zaa = zaa();
        zac.zad(zaa, zae);
        zac.zac(zaa, apiFeatureRequest);
        zac.zad(zaa, zah);
        zac(2, zaa);
    }

    public void zah(IStatusCallback iStatusCallback, ApiFeatureRequest apiFeatureRequest) throws RemoteException {
        Parcel zaa = zaa();
        zac.zad(zaa, iStatusCallback);
        zac.zac(zaa, apiFeatureRequest);
        zac(4, zaa);
    }

    public void zai(IStatusCallback iStatusCallback, zah zah) throws RemoteException {
        Parcel zaa = zaa();
        zac.zad(zaa, iStatusCallback);
        zac.zad(zaa, zah);
        zac(6, zaa);
    }
}

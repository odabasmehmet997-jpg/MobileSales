package com.google.android.gms.common.internal.service;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zac extends zaf {
    zac(zae zae, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    
    public void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zah) anyClient).getService().zae(new zad(this));
    }
}

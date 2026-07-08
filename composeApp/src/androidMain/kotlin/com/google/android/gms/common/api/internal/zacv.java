package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zacv extends TaskApiCall {
    final TaskApiCall.Builder zaa;

    zacv(final TaskApiCall.Builder builder, final Feature[] featureArr, final boolean z, final int i2) {
        super(featureArr, z, i2);
        zaa = builder;
    }

    public void doExecute(final Api.AnyClient anyClient, final TaskCompletionSource taskCompletionSource) throws RemoteException {
        zaa.zaa.accept(anyClient, taskCompletionSource);
    }
}

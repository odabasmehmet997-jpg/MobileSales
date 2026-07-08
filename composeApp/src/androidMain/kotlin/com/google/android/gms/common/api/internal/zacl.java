package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zacl extends UnregisterListenerMethod {
    final RegistrationMethods.Builder zaa;

    zacl(final RegistrationMethods.Builder builder, final ListenerHolder.ListenerKey listenerKey) {
        super(listenerKey);
        zaa = builder;
    }

    
    public void unregisterListener(final Api.AnyClient anyClient, final TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        zaa.zab.accept(anyClient, taskCompletionSource);
    }
}

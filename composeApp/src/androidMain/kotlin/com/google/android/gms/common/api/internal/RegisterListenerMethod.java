package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class RegisterListenerMethod<A extends Api.AnyClient, L> {
    private final ListenerHolder zaa;
    @Nullable
    private final Feature[] zab;
    private final boolean zac;
    private final int zad;

    @KeepForSdk
    protected RegisterListenerMethod(@NonNull final ListenerHolder<L> listenerHolder, @Nullable final Feature[] featureArr, final boolean z, final int i2) {
        zaa = listenerHolder;
        zab = featureArr;
        zac = z;
        zad = i2;
    }
    public void clearListener() {
        zaa.clear();
    }

    public ListenerHolder.ListenerKey<L> getListenerKey() {
        return zaa.getListenerKey();
    }

    @KeepForSdk
    @Nullable
    public Feature[] getRequiredFeatures() {
        return zab;
    }

    
    @KeepForSdk
    public abstract void registerListener(@NonNull A a2, @NonNull TaskCompletionSource<Void> taskCompletionSource) throws RemoteException;

    public final int zaa() {
        return zad;
    }

    public final boolean zab() {
        return zac;
    }
}

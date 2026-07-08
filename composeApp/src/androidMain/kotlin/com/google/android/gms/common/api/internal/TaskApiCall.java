package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public abstract class TaskApiCall<A extends Api.AnyClient, ResultT> {
    @Nullable
    private final Feature[] zaa;
    private final boolean zab;
    private final int zac;

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    public static class Builder<A extends Api.AnyClient, ResultT> {
        
        public RemoteCall zaa;
        private boolean zab = true;
        private Feature[] zac;
        private int zad;

        private Builder() {
        }

        Builder(final zacw zacw) {
        }

        @NonNull
        @KeepForSdk
        public TaskApiCall<A, ResultT> build() {
            Preconditions.checkArgument(null != this.zaa, "execute parameter required");
            return new zacv(this, zac, zab, zad);
        }

        @CanIgnoreReturnValue
        @NonNull
        @KeepForSdk
        public Builder<A, ResultT> run(@NonNull final RemoteCall<A, TaskCompletionSource<ResultT>> remoteCall) {
            zaa = remoteCall;
            return this;
        }

        @CanIgnoreReturnValue
        @NonNull
        @KeepForSdk
        public Builder<A, ResultT> setAutoResolveMissingFeatures(final boolean z) {
            zab = z;
            return this;
        }

        @CanIgnoreReturnValue
        @NonNull
        @KeepForSdk
        public Builder<A, ResultT> setFeatures(@NonNull final Feature... featureArr) {
            zac = featureArr;
            return this;
        }

        @CanIgnoreReturnValue
        @NonNull
        @KeepForSdk
        public Builder<A, ResultT> setMethodKey(final int i2) {
            zad = i2;
            return this;
        }
    }

    @KeepForSdk
    @Deprecated
    protected TaskApiCall() {
        zaa = null;
        zab = false;
        zac = 0;
    }

    @KeepForSdk
    protected TaskApiCall(@Nullable final Feature[] featureArr, final boolean z, final int i2) {
        zaa = featureArr;
        boolean z2 = null != featureArr && z;
        zab = z2;
        zac = i2;
    }

    @NonNull
    @KeepForSdk
    public static <A extends Api.AnyClient, ResultT> Builder<A, ResultT> builder() {
        return new Builder<>(null);
    }

    
    @KeepForSdk
    public abstract void doExecute(@NonNull A a2, @NonNull TaskCompletionSource<ResultT> taskCompletionSource) throws RemoteException;

    @KeepForSdk
    public boolean shouldAutoResolveMissingFeatures() {
        return zab;
    }

    public final int zaa() {
        return zac;
    }

    @Nullable
    public final Feature[] zab() {
        return zaa;
    }
}

package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public class RegistrationMethods<A extends Api.AnyClient, L> {
    @NonNull
    @KeepForSdk
    public final RegisterListenerMethod<A, L> register;
    @NonNull
    public final UnregisterListenerMethod zaa;
    @NonNull
    public final Runnable zab;

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    public static class Builder<A extends Api.AnyClient, L> {
        
        public RemoteCall zaa;
        
        public RemoteCall zab;
        private final Runnable zac = zacj.zaa;
        private ListenerHolder zad;
        private Feature[] zae;
        private final boolean zaf = true;
        private int zag;

        private Builder() {
        }

        Builder(final zacm zacm) {
        }

        @NonNull
        @KeepForSdk
        public RegistrationMethods<A, L> build() {
            boolean z = false;
            Preconditions.checkArgument(null != this.zaa, "Must set register function");
            Preconditions.checkArgument(null != this.zab, "Must set unregister function");
            if (null != this.zad) {
                z = true;
            }
            Preconditions.checkArgument(z, "Must set holder");
            return new RegistrationMethods<>(new zack(this, zad, zae, zaf, zag), new zacl(this, Preconditions.checkNotNull(zad.getListenerKey(), "Key must not be null")), zac, null);
        }

        @CanIgnoreReturnValue
        @NonNull
        @KeepForSdk
        public Builder<A, L> register(@NonNull final RemoteCall<A, TaskCompletionSource<Void>> remoteCall) {
            zaa = remoteCall;
            return this;
        }

        @CanIgnoreReturnValue
        @NonNull
        @KeepForSdk
        public Builder<A, L> setMethodKey(final int i2) {
            zag = i2;
            return this;
        }

        @CanIgnoreReturnValue
        @NonNull
        @KeepForSdk
        public Builder<A, L> unregister(@NonNull final RemoteCall<A, TaskCompletionSource<Boolean>> remoteCall) {
            zab = remoteCall;
            return this;
        }

        @CanIgnoreReturnValue
        @NonNull
        @KeepForSdk
        public Builder<A, L> withHolder(@NonNull final ListenerHolder<L> listenerHolder) {
            zad = listenerHolder;
            return this;
        }
    }

    RegistrationMethods(final RegisterListenerMethod registerListenerMethod, final UnregisterListenerMethod unregisterListenerMethod, final Runnable runnable, final zacn zacn) {
        register = registerListenerMethod;
        zaa = unregisterListenerMethod;
        zab = runnable;
    }

    @NonNull
    @KeepForSdk
    public static <A extends Api.AnyClient, L> Builder<A, L> builder() {
        return new Builder<>(null);
    }
}

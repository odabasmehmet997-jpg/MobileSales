package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Set;

public final class Api<O extends ApiOptions> {
    private final AbstractClientBuilder zaa;
    private final ClientKey zab;
    private final String zac;

    @KeepForSdk
    
    public static abstract class AbstractClientBuilder<T extends Client, O> extends BaseClientBuilder<T, O> {
        @NonNull
        @KeepForSdk
        @Deprecated
        public T buildClient(@NonNull final Context context, @NonNull final Looper looper, @NonNull final ClientSettings clientSettings, @NonNull final O o, @NonNull final GoogleApiClient.ConnectionCallbacks connectionCallbacks, @NonNull final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return this.buildClient(context, looper, clientSettings, o, connectionCallbacks, (OnConnectionFailedListener) onConnectionFailedListener);
        }

        @NonNull
        @KeepForSdk
        public T buildClient(@NonNull final Context context, @NonNull final Looper looper, @NonNull final ClientSettings clientSettings, @NonNull final O o, @NonNull final ConnectionCallbacks connectionCallbacks, @NonNull final OnConnectionFailedListener onConnectionFailedListener) {
            throw new UnsupportedOperationException("buildClient must be implemented");
        }
    }

    public interface AnyClient {
    }
    public static class AnyClientKey<C extends AnyClient> {
    }
    public interface ApiOptions {
        @NonNull
        NoOptions NO_OPTIONS = new NoOptions(null);
        interface HasAccountOptions extends HasOptions, NotRequiredOptions {
            @NonNull
            Account getAccount();
        }
        interface HasGoogleSignInAccountOptions extends HasOptions {
            @Nullable
            GoogleSignInAccount getGoogleSignInAccount();
        }

        
        interface HasOptions extends ApiOptions {
        }

        
        final class NoOptions implements NotRequiredOptions {
            private NoOptions() {
            }

            NoOptions(final zaa zaa) {
            }
        }

        
        interface NotRequiredOptions extends ApiOptions {
        }

        
        interface Optional extends HasOptions, NotRequiredOptions {
        }
    }

    public static abstract class BaseClientBuilder<T extends AnyClient, O> {
        @KeepForSdk
        public int getPriority() {
            return Integer.MAX_VALUE;
        }
    }
    
    public interface Client extends AnyClient {
        @KeepForSdk
        void connect(@NonNull BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks);

        @KeepForSdk
        void disconnect();

        @KeepForSdk
        void disconnect(@NonNull String str);

        @KeepForSdk
        void dump(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr);

        @NonNull
        @KeepForSdk
        Feature[] getAvailableFeatures();

        @NonNull
        @KeepForSdk
        String getEndpointPackageName();

        @KeepForSdk
        @Nullable
        String getLastDisconnectMessage();

        @KeepForSdk
        int getMinApkVersion();

        @KeepForSdk
        void getRemoteService(@Nullable IAccountAccessor iAccountAccessor, @Nullable Set<Scope> set);

        @NonNull
        @KeepForSdk
        Set<Scope> getScopesForConnectionlessNonSignIn();

        @NonNull
        @KeepForSdk
        Intent getSignInIntent();

        @KeepForSdk
        boolean isConnected();

        @KeepForSdk
        boolean isConnecting();

        @KeepForSdk
        void onUserSignOut(@NonNull BaseGmsClient.SignOutCallbacks signOutCallbacks);

        @KeepForSdk
        boolean providesSignIn();

        @KeepForSdk
        boolean requiresGooglePlayServices();

        @KeepForSdk
        boolean requiresSignIn();
    }

    @KeepForSdk
    
    public static final class ClientKey<C extends Client> extends AnyClientKey<C> {
    }

    @KeepForSdk
    public <C extends Client> Api(@NonNull final String str, @NonNull final AbstractClientBuilder<C, O> abstractClientBuilder, @NonNull final ClientKey<C> clientKey) {
        Preconditions.checkNotNull(abstractClientBuilder, "Cannot construct an Api with a null ClientBuilder");
        Preconditions.checkNotNull(clientKey, "Cannot construct an Api with a null ClientKey");
        zac = str;
        zaa = abstractClientBuilder;
        zab = clientKey;
    }

    @NonNull
    public AbstractClientBuilder zaa() {
        return zaa;
    }

    @NonNull
    public AnyClientKey zab() {
        return zab;
    }

    @NonNull
    public BaseClientBuilder zac() {
        return zaa;
    }

    @NonNull
    public String zad() {
        return zac;
    }
}

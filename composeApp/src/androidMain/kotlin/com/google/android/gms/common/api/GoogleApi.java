package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.*;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

import java.util.Collections;
import java.util.Set;

public abstract class GoogleApi<O extends Api.ApiOptions> implements HasApiKey<O> {
    @NonNull
    protected final GoogleApiManager zaa;
    private final Context zab;
    @Nullable
    private final String zac;
    private final Api zad;
    private final Api.ApiOptions zae;
    private final ApiKey zaf;
    private final Looper zag;
    private final int zah;
    private final GoogleApiClient zai;
    private final StatusExceptionMapper zaj;

    public static class Settings {
        @NonNull
        @KeepForSdk
        public static final Settings DEFAULT_SETTINGS = new Builder().build();
        @NonNull
        public final StatusExceptionMapper zaa;
        @NonNull
        public final Looper zab;

        public static class Builder {
            private StatusExceptionMapper zaa;
            private Looper zab;

            @NonNull
            @KeepForSdk
            public Settings build() {
                if (null == this.zaa) {
                    zaa = new ApiExceptionMapper();
                }
                if (null == this.zab) {
                    zab = Looper.getMainLooper();
                }
                return new Settings(zaa, zab);
            }
        }

        @KeepForSdk
        private Settings(final StatusExceptionMapper statusExceptionMapper, final Looper looper) {
            zaa = statusExceptionMapper;
            zab = looper;
        }
    }

    @MainThread
    @KeepForSdk
    protected GoogleApi(@NonNull final Activity activity, @NonNull final Api<O> api, @NonNull final O o, @NonNull final Settings settings) {
        this(activity, activity, api, o, settings);
    }

    private final BaseImplementation.ApiMethodImpl zad(final int i2, @NonNull final BaseImplementation.ApiMethodImpl apiMethodImpl) {
        apiMethodImpl.zak();
        zaa.zau(this, i2, apiMethodImpl);
        return apiMethodImpl;
    }

    private final Task zae(final int i2, @NonNull final TaskApiCall taskApiCall) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zaa.zav(this, i2, taskApiCall, taskCompletionSource, zaj);
        return taskCompletionSource.getTask();
    }

    
    @NonNull
    @KeepForSdk
    public ClientSettings.Builder createClientSettingsBuilder() {
        final Account account;
        final Set<Scope> set;
        final GoogleSignInAccount googleSignInAccount;
        final ClientSettings.Builder builder = new ClientSettings.Builder();
        final Api.ApiOptions apiOptions = zae;
        if (!(apiOptions instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) || null == (googleSignInAccount = ((Api.ApiOptions.HasGoogleSignInAccountOptions) apiOptions).getGoogleSignInAccount())) {
            final Api.ApiOptions apiOptions2 = zae;
            account = apiOptions2 instanceof Api.ApiOptions.HasAccountOptions ? ((Api.ApiOptions.HasAccountOptions) apiOptions2).getAccount() : null;
        } else {
            account = googleSignInAccount.getAccount();
        }
        builder.zab(account);
        final Api.ApiOptions apiOptions3 = zae;
        if (apiOptions3 instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) {
            final GoogleSignInAccount googleSignInAccount2 = ((Api.ApiOptions.HasGoogleSignInAccountOptions) apiOptions3).getGoogleSignInAccount();
            if (null == googleSignInAccount2) {
                set = Collections.emptySet();
            } else {
                set = googleSignInAccount2.getRequestedScopes();
            }
        } else {
            set = Collections.emptySet();
        }
        builder.zaa(set);
        builder.zac(zab.getClass().getName());
        builder.setRealClientPackageName(zab.getPackageName());
        return builder;
    }

    @NonNull
    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doWrite(@NonNull final T t) {
        this.zad(1, t);
        return t;
    }

    
    @Nullable
    public String getApiFallbackAttributionTag(@NonNull final Context context) {
        return null;
    }

    @NonNull
    public final ApiKey<O> getApiKey() {
        return zaf;
    }

    
    @KeepForSdk
    @Nullable
    public String getContextAttributionTag() {
        return zac;
    }
    public Looper getLooper() {
        return zag;
    }

    public final int zaa() {
        return zah;
    }

    @WorkerThread
    public final Api.Client zab(final Looper looper, final zabq zabq) {
        final Api.Client buildClient = Preconditions.checkNotNull(zad.zaa()).buildClient(zab, looper, this.createClientSettingsBuilder().build(), zae, zabq, zabq);
        final String contextAttributionTag = this.zac;
        if (null != contextAttributionTag && (buildClient instanceof BaseGmsClient)) {
            ((BaseGmsClient) buildClient).setAttributionTag(contextAttributionTag);
        }
        if (null != contextAttributionTag && (buildClient instanceof NonGmsServiceBrokerClient)) {
            ((NonGmsServiceBrokerClient) buildClient).zac(contextAttributionTag);
        }
        return buildClient;
    }

    public final zact zac(final Context context, final Handler handler) {
        return new zact(context, handler, this.createClientSettingsBuilder().build());
    }
    public <TResult, A extends Api.AnyClient> Task<TResult> doBestEffortWrite(@NonNull final TaskApiCall<A, TResult> taskApiCall) {
        return this.zae(2, taskApiCall);
    }
    public <TResult, A extends Api.AnyClient> Task<TResult> doRead(@NonNull final TaskApiCall<A, TResult> taskApiCall) {
        return this.zae(0, taskApiCall);
    }

    
    @NonNull
    @KeepForSdk
    public Task<Boolean> doUnregisterEventListener(@NonNull final ListenerHolder.ListenerKey<?> listenerKey, final int i2) {
        Preconditions.checkNotNull(listenerKey, "Listener key cannot be null.");
        return zaa.zap(this, listenerKey, i2);
    }

    private GoogleApi(@NonNull final Context context, @Nullable final Activity activity, final Api api, final Api.ApiOptions apiOptions, final Settings settings) {
        final String str;
        Preconditions.checkNotNull(context, "Null context is not permitted.");
        Preconditions.checkNotNull(api, "Api must not be null.");
        Preconditions.checkNotNull(settings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        final Context context2 = Preconditions.checkNotNull(context.getApplicationContext(), "The provided context did not have an application context.");
        zab = context2;
        if (30 <= Build.VERSION.SDK_INT) {
            str = context.getAttributionTag();
        } else {
            str = this.getApiFallbackAttributionTag(context);
        }
        zac = str;
        zad = api;
        zae = apiOptions;
        zag = settings.zab;
        final ApiKey sharedApiKey = ApiKey.getSharedApiKey(api, apiOptions, str);
        zaf = sharedApiKey;
        zai = new zabv(this);
        final GoogleApiManager zak = GoogleApiManager.zak(context2);
        zaa = zak;
        zah = zak.zaa();
        zaj = settings.zaa;
        if (null != activity && !(activity instanceof GoogleApiActivity) && Looper.myLooper() == Looper.getMainLooper()) {
            zaae.zad(activity, zak, sharedApiKey);
        }
        zak.zaz(this);
    }

    
    @NonNull
    @KeepForSdk
    public <A extends Api.AnyClient> Task<Void> doRegisterEventListener(@NonNull final RegistrationMethods<A, ?> registrationMethods) {
        Preconditions.checkNotNull(registrationMethods);
        Preconditions.checkNotNull(registrationMethods.register.getListenerKey(), "Listener has already been released.");
        Preconditions.checkNotNull(registrationMethods.zaa.getListenerKey(), "Listener has already been released.");
        return zaa.zao(this, registrationMethods.register, registrationMethods.zaa, registrationMethods.zab);
    }

    @KeepForSdk
    protected GoogleApi(@NonNull final Context context, @NonNull final Api<O> api, @NonNull final O o, @NonNull final Settings settings) {
        this(context, null, api, o, settings);
    }
}

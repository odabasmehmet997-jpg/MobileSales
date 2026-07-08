package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zav;
import com.google.android.gms.signin.internal.zac;
import com.google.android.gms.signin.internal.zak;
import com.google.android.gms.signin.zae;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zact extends zac implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final Api.AbstractClientBuilder zaa = zad.zac;
    private final Context zab;
    private final Handler zac;
    private final Api.AbstractClientBuilder zad;
    private final Set zae;
    private final ClientSettings zaf;
    private zae zag;
    
    public zacs zah;

    @WorkerThread
    public zact(final Context context, final Handler handler, @NonNull final ClientSettings clientSettings) {
        final Api.AbstractClientBuilder abstractClientBuilder = zact.zaa;
        zab = context;
        zac = handler;
        zaf = Preconditions.checkNotNull(clientSettings, "ClientSettings must not be null");
        zae = clientSettings.getRequiredScopes();
        zad = abstractClientBuilder;
    }

    static void zad(final zact zact, final zak zak) {
        final ConnectionResult zaa2 = zak.zaa();
        if (zaa2.isSuccess()) {
            final zav zav = Preconditions.checkNotNull(zak.zab());
            final ConnectionResult zaa3 = zav.zaa();
            if (!zaa3.isSuccess()) {
                final String valueOf = String.valueOf(zaa3);
                Log.wtf("SignInCoordinator", "Sign-in succeeded with resolve account failure: " + valueOf, new Exception());
                zact.zah.zae(zaa3);
                zact.zag.disconnect();
                return;
            }
            zact.zah.zaf(zav.zab(), zact.zae);
        } else {
            zact.zah.zae(zaa2);
        }
        zact.zag.disconnect();
    }

    @WorkerThread
    public void onConnected(@Nullable final Bundle bundle) {
        zag.zad(this);
    }

    @WorkerThread
    public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        zah.zae(connectionResult);
    }

    @WorkerThread
    public void onConnectionSuspended(final int i2) {
        zah.zag(i2);
    }

    @BinderThread
    public void zab(final zak zak) {
        zac.post(new zacr(this, zak));
    }

    public void zae(final zacs zacs) {
        final zae zae2 = zag;
        if (null != zae2) {
            zae2.disconnect();
        }
        zaf.zae(Integer.valueOf(System.identityHashCode(this)));
        final Api.AbstractClientBuilder abstractClientBuilder = zad;
        final Context context = zab;
        final Handler handler = zac;
        final ClientSettings clientSettings = zaf;
        zag = abstractClientBuilder.buildClient(context, handler.getLooper(), clientSettings, clientSettings.zaa(), (GoogleApiClient.ConnectionCallbacks) this, (GoogleApiClient.OnConnectionFailedListener) this);
        zah = zacs;
        final Set set = zae;
        if (null == set || set.isEmpty()) {
            zac.post(new zacq(this));
        } else {
            zag.zab();
        }
    }

    public void zaf() {
        final zae zae2 = zag;
        if (null != zae2) {
            zae2.disconnect();
        }
    }
}

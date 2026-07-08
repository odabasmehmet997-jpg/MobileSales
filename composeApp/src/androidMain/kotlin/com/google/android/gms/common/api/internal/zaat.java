package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zaat implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    final zaaw zaa;

    zaat(final zaaw zaaw, final zaas zaas) {
        zaa = zaaw;
    }

    public void onConnected(@Nullable final Bundle bundle) {
        final ClientSettings clientSettings = Preconditions.checkNotNull(zaa.zar);
        Preconditions.checkNotNull(zaa.zak).zad(new zaar(zaa));
    }

    public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        zaa.zab.lock();
        try {
            if (zaa.zaI(connectionResult)) {
                zaa.zaA();
                zaa.zaF();
            } else {
                zaa.zaD(connectionResult);
            }
        } finally {
            zaa.zab.unlock();
        }
    }

    public void onConnectionSuspended(final int i2) {
    }
}

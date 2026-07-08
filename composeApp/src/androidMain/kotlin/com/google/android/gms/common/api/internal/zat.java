package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zat implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public final Api zaa;
    private final boolean zab;
    @Nullable
    private zau zac;

    private zau zab() {
        Preconditions.checkNotNull(zac, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
        return zac;
    }

    public void onConnected(@Nullable final Bundle bundle) {
        this.zab().onConnected(bundle);
    }

    public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        this.zab().zaa(connectionResult, zaa, zab);
    }

    public void onConnectionSuspended(final int i2) {
        this.zab().onConnectionSuspended(i2);
    }

    public void zaa(final zau zau) {
        zac = zau;
    }
}

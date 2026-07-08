package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;

import java.util.Collections;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zaax implements zabf {
    private final zabi zaa;

    public zaax(final zabi zabi) {
        zaa = zabi;
    }

    public BaseImplementation.ApiMethodImpl zab(final BaseImplementation.ApiMethodImpl apiMethodImpl) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    public void zad() {
        for (final Api.Client disconnect : zaa.zaa.values()) {
            disconnect.disconnect();
        }
        zaa.zag.zad = Collections.emptySet();
    }

    public void zae() {
        zaa.zaj();
    }

    public void zag(@Nullable final Bundle bundle) {
    }

    public void zah(final ConnectionResult connectionResult, final Api api, final boolean z) {
    }

    public void zai(final int i2) {
    }

    public boolean zaj() {
        return true;
    }
}

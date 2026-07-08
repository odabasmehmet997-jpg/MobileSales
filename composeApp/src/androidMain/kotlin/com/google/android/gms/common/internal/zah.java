package com.google.android.gms.common.internal;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;

/**
 * @param zaa synthetic
 */ /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
record zah(ConnectionCallbacks zaa) implements BaseGmsClient.BaseConnectionCallbacks {

    public void onConnected(@Nullable Bundle bundle) {
        this.zaa.onConnected(bundle);
    }

    public void onConnectionSuspended(int i2) {
        this.zaa.onConnectionSuspended(i2);
    }
}

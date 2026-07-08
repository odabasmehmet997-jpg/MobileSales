package com.google.android.gms.common.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;

/**
 * @param zaa synthetic
 */ /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
record zai(OnConnectionFailedListener zaa) implements BaseGmsClient.BaseOnConnectionFailedListener {

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zaa.onConnectionFailed(connectionResult);
    }
}

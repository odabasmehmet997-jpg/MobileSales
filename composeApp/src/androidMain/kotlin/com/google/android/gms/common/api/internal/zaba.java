package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

class zaba(StatusPendingResult zaa) implements GoogleApiClient.OnConnectionFailedListener {
    public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        zaa.setResult(new Status(8));
    }
}

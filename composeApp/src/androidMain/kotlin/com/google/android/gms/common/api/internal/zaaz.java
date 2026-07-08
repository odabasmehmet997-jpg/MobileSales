package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

final class zaaz implements GoogleApiClient.ConnectionCallbacks {
       AtomicReference zaa;
       StatusPendingResult zab;
      zabe zac;

    public   void onConnected( Bundle bundle) {
        this.zac.zam(Preconditions.checkNotNull((GoogleApiClient) this.zaa.get()), this.zab, true);
    }
    public   void onConnectionSuspended(int i2) {
    }
}

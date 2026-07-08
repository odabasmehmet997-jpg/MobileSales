package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

import java.util.concurrent.CancellationException;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zacc extends zap {
    private TaskCompletionSource zad;

    public void onDestroy() {
        super.onDestroy();
        zad.trySetException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
    }

    
    public void zab(final ConnectionResult connectionResult, final int i2) {
        String errorMessage = connectionResult.getErrorMessage();
        if (null == errorMessage) {
            errorMessage = "Error connecting to Google Play services";
        }
        zad.setException(new ApiException(new Status(connectionResult, errorMessage, connectionResult.getErrorCode())));
    }

    
    public void zac() {
        final Activity lifecycleActivity = mLifecycleFragment.getLifecycleActivity();
        if (null == lifecycleActivity) {
            zad.trySetException(new ApiException(new Status(8)));
            return;
        }
        final int isGooglePlayServicesAvailable = zac.isGooglePlayServicesAvailable(lifecycleActivity);
        if (0 == isGooglePlayServicesAvailable) {
            zad.trySetResult(null);
        } else if (!zad.getTask().isComplete()) {
            this.zah(new ConnectionResult(isGooglePlayServicesAvailable, null), 0);
        }
    }
}

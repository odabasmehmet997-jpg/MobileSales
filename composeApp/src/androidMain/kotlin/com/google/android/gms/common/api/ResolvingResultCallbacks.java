package com.google.android.gms.common.api;

import android.app.Activity;
import android.content.IntentSender;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public abstract class ResolvingResultCallbacks<R extends Result> extends ResultCallbacks<R> {
    private final Activity zza;
    private final int zzb;

    @KeepForSdk
    public final void onFailure(@NonNull final Status status) {
        if (status.hasResolution()) {
            try {
                status.startResolutionForResult(zza, zzb);
            } catch (final IntentSender.SendIntentException e2) {
                Log.e("ResolvingResultCallback", "Failed to start resolution", e2);
                this.onUnresolvableFailure(new Status(8));
            }
        } else {
            this.onUnresolvableFailure(status);
        }
    }

    public abstract void onUnresolvableFailure(@NonNull Status status);
}

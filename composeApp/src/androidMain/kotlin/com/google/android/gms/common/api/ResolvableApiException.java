package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender;
import androidx.annotation.NonNull;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class ResolvableApiException extends ApiException {
    public ResolvableApiException(@NonNull final Status status) {
        super(status);
    }

    @NonNull
    public PendingIntent getResolution() {
        return this.getStatus().getResolution();
    }

    public void startResolutionForResult(@NonNull final Activity activity, final int i2) throws IntentSender.SendIntentException {
        this.getStatus().startResolutionForResult(activity, i2);
    }
}

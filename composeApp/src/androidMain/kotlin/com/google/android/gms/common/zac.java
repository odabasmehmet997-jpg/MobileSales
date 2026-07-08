package com.google.android.gms.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.DialogInterface;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;

/**
 * @param zaa synthetic
 * @param zab synthetic
 * @param zac synthetic
 * @param zad synthetic
 */ /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
record zac(Activity zaa, int zab, ActivityResultLauncher zac,
           GoogleApiAvailability zad) implements DialogInterface.OnClickListener {
    public void onClick(final DialogInterface dialogInterface, final int i2) {
        dialogInterface.dismiss();
        final PendingIntent errorResolutionPendingIntent = zad.getErrorResolutionPendingIntent(zaa, zab, 0);
        if (null != errorResolutionPendingIntent) {
            zac.launch(new IntentSenderRequest.Builder(errorResolutionPendingIntent.getIntentSender()).build());
        }
    }
}

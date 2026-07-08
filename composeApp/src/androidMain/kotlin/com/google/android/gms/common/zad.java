package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.base.zau;

@SuppressLint("HandlerLeak")
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zad extends zau {
    final GoogleApiAvailability zaa;
    private final Context zab;

    public zad(final GoogleApiAvailability googleApiAvailability, final Context context) {
        super(null == Looper.myLooper() ? Looper.getMainLooper() : Looper.myLooper());
        zaa = googleApiAvailability;
        zab = context.getApplicationContext();
    }

    public void handleMessage(final Message message) {
        final int i2 = message.what;
        if (1 != i2) {
            Log.w("GoogleApiAvailability", "Don't know how to handle this message: " + i2);
            return;
        }
        final GoogleApiAvailability googleApiAvailability = zaa;
        final int isGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(zab);
        if (googleApiAvailability.isUserResolvableError(isGooglePlayServicesAvailable)) {
            zaa.showErrorNotification(zab, isGooglePlayServicesAvailable);
        }
    }
}

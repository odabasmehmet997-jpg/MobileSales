package com.google.android.gms.common.api.internal;

import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.base.zau;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zabc extends zau {
    final zabe zaa;

    public void handleMessage(final Message message) {
        final int i2 = message.what;
        if (1 == i2) {
            zabe.zaj(zaa);
        } else if (2 != i2) {
            Log.w("GoogleApiClientImpl", "Unknown message id: " + i2);
        } else {
            zabe.zai(zaa);
        }
    }
}

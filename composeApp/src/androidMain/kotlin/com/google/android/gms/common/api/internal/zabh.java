package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.base.zau;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zabh extends zau {
    final zabi zaa;

    
    zabh(final zabi zabi, final Looper looper) {
        super(looper);
        zaa = zabi;
    }

    public void handleMessage(final Message message) {
        final int i2 = message.what;
        if (1 == i2) {
            ((zabg) message.obj).zab(zaa);
        } else if (2 != i2) {
            Log.w("GACStateManager", "Unknown message id: " + i2);
        } else {
            throw ((RuntimeException) message.obj);
        }
    }
}

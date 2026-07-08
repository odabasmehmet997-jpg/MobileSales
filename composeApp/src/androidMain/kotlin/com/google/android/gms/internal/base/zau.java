package com.google.android.gms.internal.base;

import android.os.Handler;
import android.os.Looper;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public class zau extends Handler {
    private final Looper zaa = Looper.getMainLooper();

    public zau() {
    }

    public zau(final Looper looper) {
        super(looper);
    }

    public zau(final Looper looper, final Handler.Callback callback) {
        super(looper, callback);
    }
}

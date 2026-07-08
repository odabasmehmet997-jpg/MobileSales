package com.google.android.gms.internal.gtm;

import android.os.Handler;
import android.os.Looper;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public class zzfy extends Handler {
    public zzfy() {
        Looper.getMainLooper();
    }

    public zzfy(final Looper looper) {
        super(looper);
        Looper.getMainLooper();
    }

    public zzfy(final Looper looper, final Handler.Callback callback) {
        super(looper, callback);
        Looper.getMainLooper();
    }
}

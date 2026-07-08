package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zabx extends BroadcastReceiver {
    @Nullable
    Context zaa;
    private final zabw zab;

    public zabx(final zabw zabw) {
        zab = zabw;
    }

    public void onReceive(final Context context, final Intent intent) {
        final Uri data = intent.getData();
        if ("com.google.android.gms".equals(null != data ? data.getSchemeSpecificPart() : null)) {
            zab.zaa();
            this.zab();
        }
    }

    public void zaa(final Context context) {
        zaa = context;
    }

    public synchronized void zab() {
        try {
            final Context context = zaa;
            if (null != context) {
                context.unregisterReceiver(this);
            }
            zaa = null;
        } catch (final Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
}

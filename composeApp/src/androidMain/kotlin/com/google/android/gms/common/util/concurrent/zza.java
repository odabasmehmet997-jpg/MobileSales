package com.google.android.gms.common.util.concurrent;

import android.os.Process;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zza implements Runnable {
    private final Runnable zza;

    public zza(final Runnable runnable, final int i2) {
        zza = runnable;
    }

    public void run() {
        Process.setThreadPriority(0);
        zza.run();
    }
}

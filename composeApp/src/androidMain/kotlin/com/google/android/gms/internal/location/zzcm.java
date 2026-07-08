package com.google.android.gms.internal.location;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzcm implements Executor {
    static final zzcm zza = new zzcm();

    private zzcm() {
    }

    public void execute(final Runnable runnable) {
        runnable.run();
    }
}

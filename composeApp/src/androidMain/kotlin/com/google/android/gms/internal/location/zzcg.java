package com.google.android.gms.internal.location;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzcg implements Executor {
    static final zzcg zza = new zzcg();

    private zzcg() {
    }

    public void execute(final Runnable runnable) {
        runnable.run();
    }
}

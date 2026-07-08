package com.google.android.gms.internal.location;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzcf implements Executor {
    static final zzcf zza = new zzcf();

    private zzcf() {
    }

    public void execute(final Runnable runnable) {
        runnable.run();
    }
}

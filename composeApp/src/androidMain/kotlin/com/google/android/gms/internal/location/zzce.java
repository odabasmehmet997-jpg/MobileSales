package com.google.android.gms.internal.location;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzce implements Executor {
    static final zzce zza = new zzce();

    private zzce() {
    }

    public void execute(Runnable runnable) {
        runnable.run();
    }
}

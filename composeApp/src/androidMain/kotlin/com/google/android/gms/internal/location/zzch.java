package com.google.android.gms.internal.location;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzch implements Executor {
    static final zzch zza = new zzch();

    private zzch() {
    }

    public void execute(Runnable runnable) {
        runnable.run();
    }
}

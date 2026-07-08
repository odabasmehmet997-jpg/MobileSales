package com.google.android.gms.internal.measurement;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzdi implements ThreadFactory {
    private final ThreadFactory zza = Executors.defaultThreadFactory();

    zzdi(final zzee zzee) {
    }

    public Thread newThread(final Runnable runnable) {
        final Thread newThread = zza.newThread(runnable);
        newThread.setName("ScionFrontendApi");
        return newThread;
    }
}

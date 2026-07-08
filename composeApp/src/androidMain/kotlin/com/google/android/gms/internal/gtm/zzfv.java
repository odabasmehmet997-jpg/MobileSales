package com.google.android.gms.internal.gtm;

import java.util.concurrent.*;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzfv implements zzfu {
    private zzfv() {
        throw null;
    }

    zzfv(final zzfw zzfw) {
    }

    public ExecutorService zza(final int i2) {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Executors.defaultThreadFactory());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return Executors.unconfigurableExecutorService(threadPoolExecutor);
    }
}

package com.google.android.gms.internal.base;

import java.util.concurrent.*;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zas implements zaq {
    private zas() {
    }

    zas(final zar zar) {
    }

    public ExecutorService zaa(final ThreadFactory threadFactory, final int i2) {
        return this.zac(1, threadFactory, 1);
    }

    public ExecutorService zac(final int i2, final ThreadFactory threadFactory, final int i3) {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i2, i2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return Executors.unconfigurableExecutorService(threadPoolExecutor);
    }
}

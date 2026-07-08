package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
abstract class zaav implements Runnable {
    final zaaw zab;

    zaav(final zaaw zaaw, final zaau zaau) {
        zab = zaaw;
    }

    @WorkerThread
    public final void run() {
        zab.zab.lock();
        try {
            if (!Thread.interrupted()) {
                this.zaa();
            }
        } catch (final RuntimeException e2) {
            zab.zaa.zam(e2);
        } catch (final Throwable th) {
            zab.zab.unlock();
            throw th;
        }
        zab.zab.unlock();
    }

    
    @WorkerThread
    public abstract void zaa();
}

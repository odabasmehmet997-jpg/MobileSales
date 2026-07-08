package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
final class zzad<T> implements zzae<T> {
    private final CountDownLatch zza = new CountDownLatch(1);

    private zzad() {
    }

    public void onCanceled() {
        this.zza.countDown();
    }

    public void onFailure(@NonNull Exception exc) {
        this.zza.countDown();
    }

    public void onSuccess(T t) {
        this.zza.countDown();
    }

    public void zza() throws InterruptedException {
        this.zza.await();
    }

    public boolean zzb(long j2, TimeUnit timeUnit) throws InterruptedException {
        return this.zza.await(j2, timeUnit);
    }

    zzad(zzac zzac) {
    }
}

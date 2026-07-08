package com.google.android.gms.internal.location;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzbc implements OnCompleteListener {
    private final AtomicReference zza;
    private final CountDownLatch zzb;

    public void onComplete(Task task) {
        if (task.isSuccessful()) {
            this.zza.set(task.getResult());
        }
        this.zzb.countDown();
    }
}

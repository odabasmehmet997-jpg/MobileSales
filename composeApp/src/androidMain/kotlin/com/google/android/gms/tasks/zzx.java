package com.google.android.gms.tasks;

import java.util.concurrent.TimeoutException;

/**
 * @param zza synthetic
 */ /* compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
public record zzx(TaskCompletionSource zza) implements Runnable {
    public void run() {
        this.zza.trySetException(new TimeoutException());
    }
}

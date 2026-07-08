package com.google.android.gms.common.util.concurrent;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class NumberedThreadFactory implements ThreadFactory {
    private final String zza;
    private final AtomicInteger zzb = new AtomicInteger();
    private final ThreadFactory zzc = Executors.defaultThreadFactory();

    @KeepForSdk
    public NumberedThreadFactory(@NonNull final String str) {
        Preconditions.checkNotNull(str, "Name must not be null");
        zza = str;
    }

    @NonNull
    public final Thread newThread(@NonNull final Runnable runnable) {
        final Thread newThread = zzc.newThread(new zza(runnable, 0));
        final int andIncrement = zzb.getAndIncrement();
        newThread.setName(zza + "[" + andIncrement + "]");
        return newThread;
    }
}

package com.google.android.gms.common.util.concurrent;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class NamedThreadFactory implements ThreadFactory {
    private final String zza;
    private final ThreadFactory zzb = Executors.defaultThreadFactory();

    @KeepForSdk
    public NamedThreadFactory(@NonNull final String str) {
        Preconditions.checkNotNull(str, "Name must not be null");
        zza = str;
    }

    @NonNull
    public final Thread newThread(@NonNull final Runnable runnable) {
        final Thread newThread = zzb.newThread(new zza(runnable, 0));
        newThread.setName(zza);
        return newThread;
    }
}

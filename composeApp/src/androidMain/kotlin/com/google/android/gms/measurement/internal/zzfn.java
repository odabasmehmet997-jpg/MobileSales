package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzfn implements Thread.UncaughtExceptionHandler {
    final zzfq zza;
    private final String zzb;

    public zzfn(final zzfq zzfq, final String str) {
        zza = zzfq;
        Preconditions.checkNotNull(str);
        zzb = str;
    }

    public synchronized void uncaughtException(final Thread thread, final Throwable th) {
        zza.zzs.zzay().zzd().zzb(zzb, th);
    }
}

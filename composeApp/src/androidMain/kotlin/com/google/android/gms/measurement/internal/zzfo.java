package com.google.android.gms.measurement.internal;

import androidx.annotation.NonNull;
import androidx.core.location.LocationRequestCompat;
import com.google.android.gms.common.internal.Preconditions;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzfo extends FutureTask implements Comparable {
    final boolean zza;
    final zzfq zzb;
    private final long zzc;
    private final String zzd;

    
    zzfo(final zzfq zzfq, final Runnable runnable, final boolean z, final String str) {
        super(runnable, null);
        zzb = zzfq;
        Preconditions.checkNotNull(str);
        final long andIncrement = com.google.android.gms.measurement.internal.zzfq.zza.getAndIncrement();
        zzc = andIncrement;
        zzd = str;
        zza = z;
        if (LocationRequestCompat.PASSIVE_INTERVAL == andIncrement) {
            zzfq.zzs.zzay().zzd().zza("Tasks index overflow");
        }
    }

    public int compareTo(@NonNull final Object obj) {
        final zzfo zzfo = (zzfo) obj;
        final boolean z = zza;
        if (z == zzfo.zza) {
            final int i2 = (zzc > zzfo.zzc ? 1 : (zzc == zzfo.zzc ? 0 : -1));
            if (0 > i2) {
                return -1;
            }
            if (0 >= i2) {
                zzb.zzs.zzay().zzh().zzb("Two tasks share the same index. index", Long.valueOf(zzc));
                return 0;
            }
        } else if (z) {
            return -1;
        }
        return 1;
    }

    
    public void setException(final Throwable th) {
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
        zzb.zzs.zzay().zzd().zzb(zzd, th);
        if ((th instanceof zzfm) && null != (defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler())) {
            defaultUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }

    
    zzfo(final zzfq zzfq, final Callable callable, final boolean z, final String str) {
        super(callable);
        zzb = zzfq;
        Preconditions.checkNotNull("Task exception on worker thread");
        final long andIncrement = com.google.android.gms.measurement.internal.zzfq.zza.getAndIncrement();
        zzc = andIncrement;
        zzd = "Task exception on worker thread";
        zza = z;
        if (LocationRequestCompat.PASSIVE_INTERVAL == andIncrement) {
            zzfq.zzs.zzay().zzd().zza("Tasks index overflow");
        }
    }
}

package com.google.android.gms.measurement.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public final class zzfq extends zzgn {
    
    public static final AtomicLong zza = new AtomicLong(Long.MIN_VALUE);

    public zzfp zzb;
    public zzfp zzc;
    private final PriorityBlockingQueue zzd = new PriorityBlockingQueue();
    private final BlockingQueue zze = new LinkedBlockingQueue();
    private final Thread.UncaughtExceptionHandler zzf = new zzfn(this, "Thread death: Uncaught exception on worker thread");
    private final Thread.UncaughtExceptionHandler zzg = new zzfn(this, "Thread death: Uncaught exception on network thread");
    
    public final Object zzh = new Object();
    
    public final Semaphore zzi = new Semaphore(2);
    
    public volatile boolean zzj;

    zzfq(final zzft zzft) {
        super(zzft);
    }

    private void zzt(final zzfo zzfo) {
        synchronized (zzh) {
            try {
                zzd.add(zzfo);
                final zzfp zzfp = zzb;
                if (null == zzfp) {
                    final zzfp zzfp2 = new zzfp(this, "Measurement Worker", zzd);
                    zzb = zzfp2;
                    zzfp2.setUncaughtExceptionHandler(zzf);
                    zzb.start();
                } else {
                    zzfp.zza();
                }
            } catch (final Throwable th) {
                throw th;
            }
        }
    }

    public void zzax() {
        if (Thread.currentThread() != zzc) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    public java.lang.Object zzd(final java.util.concurrent.atomic.AtomicReference r2, final long r3, final java.lang.String r5, final java.lang.Runnable r6) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfq.zzd(java.util.concurrent.atomic.AtomicReference, long, java.lang.String, java.lang.Runnable):java.lang.Object");
    }

    
    public boolean zzf() {
        return false;
    }

    public void zzg() {
        if (Thread.currentThread() != zzb) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public Future zzh(final Callable callable) throws IllegalStateException {
        this.zzu();
        Preconditions.checkNotNull(callable);
        final zzfo zzfo = new zzfo(this, callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == zzb) {
            if (!zzd.isEmpty()) {
                zzs.zzay().zzk().zza("Callable skipped the worker queue.");
            }
            zzfo.run();
        } else {
            this.zzt(zzfo);
        }
        return zzfo;
    }

    public Future zzi(final Callable callable) throws IllegalStateException {
        this.zzu();
        Preconditions.checkNotNull(callable);
        final zzfo zzfo = new zzfo(this, callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == zzb) {
            zzfo.run();
        } else {
            this.zzt(zzfo);
        }
        return zzfo;
    }

    public void zzo(final Runnable runnable) throws IllegalStateException {
        this.zzu();
        Preconditions.checkNotNull(runnable);
        final zzfo zzfo = new zzfo(this, runnable, false, "Task exception on network thread");
        synchronized (zzh) {
            try {
                zze.add(zzfo);
                final zzfp zzfp = zzc;
                if (null == zzfp) {
                    final zzfp zzfp2 = new zzfp(this, "Measurement Network", zze);
                    zzc = zzfp2;
                    zzfp2.setUncaughtExceptionHandler(zzg);
                    zzc.start();
                } else {
                    zzfp.zza();
                }
            } catch (final Throwable th) {
                throw th;
            }
        }
    }

    public void zzp(final Runnable runnable) throws IllegalStateException {
        this.zzu();
        Preconditions.checkNotNull(runnable);
        this.zzt(new zzfo(this, runnable, false, "Task exception on worker thread"));
    }

    public void zzq(final Runnable runnable) throws IllegalStateException {
        this.zzu();
        Preconditions.checkNotNull(runnable);
        this.zzt(new zzfo(this, runnable, true, "Task exception on worker thread"));
    }

    public boolean zzs() {
        return Thread.currentThread() == zzb;
    }
}

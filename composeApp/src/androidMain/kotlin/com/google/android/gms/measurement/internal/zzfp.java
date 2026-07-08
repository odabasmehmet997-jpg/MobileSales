package com.google.android.gms.measurement.internal;

import androidx.annotation.GuardedBy;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzfp extends Thread {
    final zzfq zza;
    private final Object zzb;
    private final BlockingQueue zzc;
    @GuardedBy("threadLifeCycleLock")
    private boolean zzd;

    public zzfp(zzfq zzfq, String str, BlockingQueue blockingQueue) {
        this.zza = zzfq;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(blockingQueue);
        this.zzb = new Object();
        this.zzc = blockingQueue;
        setName(str);
    }

    private void zzb() {
        synchronized (this.zza.zzh) {
            try {
                if (!this.zzd) {
                    this.zza.zzi.release();
                    this.zza.zzh.notifyAll();
                    zzfq zzfq = this.zza;
                    if (this == zzfq.zzb) {
                        zzfq.zzb = null;
                    } else if (this == zzfq.zzc) {
                        zzfq.zzc = null;
                    } else {
                        zzfq.zzs.zzay().zzd().zza("Current scheduler thread is neither worker nor network");
                    }
                    this.zzd = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void zzc(InterruptedException interruptedException) {
        this.zza.zzs.zzay().zzk().zzb(getName() + " was interrupted", interruptedException);
    }

    /*  WARNING: Code restructure failed: missing block: B:44:0x007d, code lost:
        zzb();
     */
    /*  WARNING: Code restructure failed: missing block: B:45:0x0080, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r6 = this;
            r0 = 0
        L_0x0001:
            r1 = 1
            if (r0 != 0) goto L_0x0014
            com.google.android.gms.measurement.internal.zzfq r2 = r6.zza     // Catch:{ InterruptedException -> 0x000f }
            java.util.concurrent.Semaphore r2 = r2.zzi     // Catch:{ InterruptedException -> 0x000f }
            r2.acquire()     // Catch:{ InterruptedException -> 0x000f }
            r0 = r1
            goto L_0x0001
        L_0x000f:
            r1 = move-exception
            r6.zzc(r1)
            goto L_0x0001
        L_0x0014:
            int r0 = android.os.Process.myTid()     // Catch:{ all -> 0x0035 }
            int r0 = android.os.Process.getThreadPriority(r0)     // Catch:{ all -> 0x0035 }
        L_0x001c:
            java.util.concurrent.BlockingQueue r2 = r6.zzc     // Catch:{ all -> 0x0035 }
            java.lang.Object r2 = r2.poll()     // Catch:{ all -> 0x0035 }
            com.google.android.gms.measurement.internal.zzfo r2 = (com.google.android.gms.measurement.internal.zzfo) r2     // Catch:{ all -> 0x0035 }
            if (r2 == 0) goto L_0x0037
            boolean r3 = r2.zza     // Catch:{ all -> 0x0035 }
            if (r1 == r3) goto L_0x002d
            r3 = 10
            goto L_0x002e
        L_0x002d:
            r3 = r0
        L_0x002e:
            android.os.Process.setThreadPriority(r3)     // Catch:{ all -> 0x0035 }
            r2.run()     // Catch:{ all -> 0x0035 }
            goto L_0x001c
        L_0x0035:
            r0 = move-exception
            goto L_0x0087
        L_0x0037:
            java.lang.Object r2 = r6.zzb     // Catch:{ all -> 0x0035 }
            monitor-enter(r2)     // Catch:{ all -> 0x0035 }
            java.util.concurrent.BlockingQueue r3 = r6.zzc     // Catch:{ all -> 0x004f }
            java.lang.Object r3 = r3.peek()     // Catch:{ all -> 0x004f }
            if (r3 != 0) goto L_0x0055
            com.google.android.gms.measurement.internal.zzfq r3 = r6.zza     // Catch:{ all -> 0x004f }
            boolean unused = r3.zzj     // Catch:{ all -> 0x004f }
            java.lang.Object r3 = r6.zzb     // Catch:{ InterruptedException -> 0x0051 }
            r4 = 30000(0x7530, double:1.4822E-319)
            r3.wait(r4)     // Catch:{ InterruptedException -> 0x0051 }
            goto L_0x0055
        L_0x004f:
            r0 = move-exception
            goto L_0x0085
        L_0x0051:
            r3 = move-exception
            r6.zzc(r3)     // Catch:{ all -> 0x004f }
        L_0x0055:
            monitor-exit(r2)     // Catch:{ all -> 0x004f }
            com.google.android.gms.measurement.internal.zzfq r2 = r6.zza     // Catch:{ all -> 0x0035 }
            java.lang.Object r2 = r2.zzh     // Catch:{ all -> 0x0035 }
            monitor-enter(r2)     // Catch:{ all -> 0x0035 }
            java.util.concurrent.BlockingQueue r3 = r6.zzc     // Catch:{ all -> 0x007a }
            java.lang.Object r3 = r3.peek()     // Catch:{ all -> 0x007a }
            if (r3 != 0) goto L_0x0081
            com.google.android.gms.measurement.internal.zzfq r0 = r6.zza     // Catch:{ all -> 0x007a }
            com.google.android.gms.measurement.internal.zzft r0 = r0.zzs     // Catch:{ all -> 0x007a }
            com.google.android.gms.measurement.internal.zzaf r0 = r0.zzf()     // Catch:{ all -> 0x007a }
            com.google.android.gms.measurement.internal.zzdv r1 = com.google.android.gms.measurement.internal.zzdw.zzae     // Catch:{ all -> 0x007a }
            r3 = 0
            boolean r0 = r0.zzs(r3, r1)     // Catch:{ all -> 0x007a }
            if (r0 == 0) goto L_0x007c
            r6.zzb()     // Catch:{ all -> 0x007a }
            goto L_0x007c
        L_0x007a:
            r0 = move-exception
            goto L_0x0083
        L_0x007c:
            monitor-exit(r2)     // Catch:{ all -> 0x007a }
            r6.zzb()
            return
        L_0x0081:
            monitor-exit(r2)     // Catch:{ all -> 0x007a }
            goto L_0x001c
        L_0x0083:
            monitor-exit(r2)     // Catch:{ all -> 0x007a }
            throw r0     // Catch:{ all -> 0x0035 }
        L_0x0085:
            monitor-exit(r2)     // Catch:{ all -> 0x004f }
            throw r0     // Catch:{ all -> 0x0035 }
        L_0x0087:
            r6.zzb()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfp.run():void");
    }

    public void zza() {
        synchronized (this.zzb) {
            this.zzb.notifyAll();
        }
    }
}

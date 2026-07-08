package com.google.android.gms.tagmanager;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzee implements zzad {
    private final String zza;
    private final Context zzb;
    private final ScheduledExecutorService zzc;
    private ScheduledFuture zzd;
    private boolean zze;
    private final zzak zzf;
    private String zzg;
    private zzdb zzh;
    private final zzed zzi;

    private synchronized void zzd() {
        if (this.zze) {
            throw new IllegalStateException("called method after closed");
        }
    }

    public synchronized void release() {
        try {
            zzd();
            ScheduledFuture scheduledFuture = this.zzd;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            this.zzc.shutdown();
            this.zze = true;
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized void zza(long j2, String str) {
        try {
            zzdc.zzb.zzd("loadAfterDelay: containerId=" + this.zza + " delay=" + j2);
            zzd();
            if (this.zzh != null) {
                ScheduledFuture scheduledFuture = this.zzd;
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                }
                ScheduledExecutorService scheduledExecutorService = this.zzc;
                zzed zzed = this.zzi;
                zzak zzak = this.zzf;
                zzee zzee = zzed.zza();
                zzeb zzeb = new zzeb(zzee.zzb, zzee.zza, zzak);
                zzeb.zzb(this.zzh);
                zzeb.zza(this.zzg);
                zzeb.zzc(str);
                this.zzd = scheduledExecutorService.schedule(zzeb, j2, TimeUnit.MILLISECONDS);
            } else {
                throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void zzb(String str) {
        zzd();
        this.zzg = str;
    }
}

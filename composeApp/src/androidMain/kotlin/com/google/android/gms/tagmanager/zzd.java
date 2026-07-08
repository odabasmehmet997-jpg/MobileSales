package com.google.android.gms.tagmanager;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkRequest;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;

public final class zzd {
    private static final Object zza = new Object();
    private static zzd zzb;
    private final long zzc = PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS;
    private final long zzd = WorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS;
    private volatile boolean zze = false;
    private volatile AdvertisingIdClient.Info zzf;
    private volatile long zzg;
    private volatile long zzh;
    
    public final Context zzi;
    private final Clock zzj;
    private final Thread zzk;
    private final Object zzl = new Object();
    private final zzc zzm = new zza(this);

    @VisibleForTesting
    public zzd(Context context, zzc zzc2, Clock clock) {
        this.zzj = clock;
        if (context != null) {
            this.zzi = context.getApplicationContext();
        } else {
            this.zzi = null;
        }
        this.zzg = clock.currentTimeMillis();
        this.zzk = new Thread(new zzb(this));
    }

    public static zzd zzb(Context context) {
        if (zzb == null) {
            synchronized (zza) {
                try {
                    if (zzb == null) {
                        zzd zzd2 = new zzd(context, null, DefaultClock.getInstance());
                        zzb = zzd2;
                        zzd2.zzk.start();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return zzb;
    }

    static void zzd(com.google.android.gms.tagmanager.zzd r4) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzd.zzd(com.google.android.gms.tagmanager.zzd):void");
    }

    private void zzg() {
        if (this.zzj.currentTimeMillis() - this.zzh > 3600000) {
            this.zzf = null;
        }
    }

    private void zzh() {
        if (this.zzj.currentTimeMillis() - this.zzg > this.zzd) {
            synchronized (this.zzl) {
                this.zzl.notify();
            }
            this.zzg = this.zzj.currentTimeMillis();
        }
    }

    private void zzi() {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzd.zzi():void");
    }

    public String zzc() {
        if (this.zzf == null) {
            zzi();
        } else {
            zzh();
        }
        zzg();
        if (this.zzf == null) {
            return null;
        }
        return this.zzf.getId();
    }
    public void zze() {
        this.zze = true;
        this.zzk.interrupt();
    }

    public boolean zzf() {
        if (this.zzf == null) {
            zzi();
        } else {
            zzh();
        }
        zzg();
        return this.zzf == null || this.zzf.isLimitAdTrackingEnabled();
    }
}

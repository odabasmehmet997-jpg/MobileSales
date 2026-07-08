package com.google.android.gms.internal.gtm;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
abstract class zzcv {
    private static volatile Handler zza;
    
    public final zzbu zzb;
    private final Runnable zzc = new zzcu(this);
    
    public volatile long zzd;

    zzcv(zzbu zzbu) {
        Preconditions.checkNotNull(zzbu);
        this.zzb = zzbu;
    }

    private final Handler zzi() {
        Handler handler;
        if (null != zzcv.zza) {
            return zza;
        }
        synchronized (zzcv.class) {
            try {
                if (null == zzcv.zza) {
                    zza = new zzfy(this.zzb.zza().getMainLooper());
                }
                handler = zza;
            } catch (Throwable th) {
                throw th;
            }
        }
        return handler;
    }

    public abstract void zza();

    public final long zzb() {
        if (0 == zzd) {
            return 0;
        }
        return Math.abs(this.zzb.zzr().currentTimeMillis() - this.zzd);
    }

    public final void zze(long j2) {
        if (zzh()) {
            long j3 = 0;
            if (0 > j2) {
                zzf();
                return;
            }
            long abs = j2 - Math.abs(this.zzb.zzr().currentTimeMillis() - this.zzd);
            if (0 <= abs) {
                j3 = abs;
            }
            zzi().removeCallbacks(this.zzc);
            if (!zzi().postDelayed(this.zzc, j3)) {
                this.zzb.zzm().zzJ("Failed to adjust delayed post. time", Long.valueOf(j3));
            }
        }
    }

    public final void zzf() {
        this.zzd = 0;
        zzi().removeCallbacks(this.zzc);
    }

    public final void zzg(long j2) {
        zzf();
        if (0 <= j2) {
            this.zzd = this.zzb.zzr().currentTimeMillis();
            if (!zzi().postDelayed(this.zzc, j2)) {
                this.zzb.zzm().zzJ("Failed to schedule delayed post. time", Long.valueOf(j2));
            }
        }
    }

    public final boolean zzh() {
        return 0 != zzd;
    }
}

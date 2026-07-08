package com.google.android.gms.measurement.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzby;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
abstract class zzan {
    private static volatile Handler zza;
    private final zzgo zzb;
    private final Runnable zzc;
    
    public volatile long zzd;

    zzan(zzgo zzgo) {
        Preconditions.checkNotNull(zzgo);
        this.zzb = zzgo;
        this.zzc = new zzam(this, zzgo);
    }

    private final Handler zzf() {
        Handler handler;
        if (null != zzan.zza) {
            return zza;
        }
        synchronized (zzan.class) {
            try {
                if (null == zzan.zza) {
                    zza = new zzby(this.zzb.zzau().getMainLooper());
                }
                handler = zza;
            } catch (Throwable th) {
                throw th;
            }
        }
        return handler;
    }

    
    public final void zzb() {
        this.zzd = 0;
        zzf().removeCallbacks(this.zzc);
    }

    public abstract void zzc();

    public final void zzd(long j2) {
        zzb();
        if (0 <= j2) {
            this.zzd = this.zzb.zzav().currentTimeMillis();
            if (!zzf().postDelayed(this.zzc, j2)) {
                this.zzb.zzay().zzd().zzb("Failed to schedule delayed post. time", Long.valueOf(j2));
            }
        }
    }

    public final boolean zze() {
        return 0 != zzd;
    }
}

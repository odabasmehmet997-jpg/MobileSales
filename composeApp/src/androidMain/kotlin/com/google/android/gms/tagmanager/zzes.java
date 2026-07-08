package com.google.android.gms.tagmanager;

import android.util.Log;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzes implements zzdx {
    private double zza = 60.0d;
    private long zzb;
    private final Object zzc = new Object();
    private final Clock zzd = DefaultClock.getInstance();

    public boolean zza() {
        synchronized (this.zzc) {
            try {
                long currentTimeMillis = this.zzd.currentTimeMillis();
                double d2 = this.zza;
                if (d2 < 60.0d) {
                    double d3 = ((double) (currentTimeMillis - this.zzb)) / 2000.0d;
                    if (d3 > 0.0d) {
                        d2 = Math.min(60.0d, d2 + d3);
                        this.zza = d2;
                    }
                }
                this.zzb = currentTimeMillis;
                if (d2 >= 1.0d) {
                    this.zza = d2 - 4.0d;
                    return true;
                }
                Log.w("GoogleTagManager", "No more tokens available.");
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

package com.google.android.gms.internal.gtm;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.util.Clock;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzem {
    private double zza = 60.0d;
    private long zzb;
    private final Object zzc = new Object();
    private final String zzd = "tracking";
    private final Clock zze;

    public zzem(int i2, long j2, String str, Clock clock) {
        this.zze = clock;
    }

    public boolean zza() {
        synchronized (this.zzc) {
            try {
                long currentTimeMillis = this.zze.currentTimeMillis();
                double d2 = this.zza;
                if (60.0d > d2) {
                    double d3 = (currentTimeMillis - this.zzb) / 2000.0d;
                    if (0.0d < d3) {
                        d2 = Math.min(60.0d, d2 + d3);
                        this.zza = d2;
                    }
                }
                this.zzb = currentTimeMillis;
                if (1.0d <= d2) {
                    this.zza = d2 - 4.0d;
                    return true;
                }
                final String str = this.zzd;
                zzen.zze("Excessive " + str + " detected; call ignored.");
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

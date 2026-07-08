package com.google.android.gms.tagmanager;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.google.android.gms.common.util.Clock;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
final class zzda implements zzdx {
    private double zza;
    private long zzb;
    private final Object zzc;
    private final String zzd;
    private final Clock zze;

    public boolean zza() {
        synchronized (this.zzc) {
            try {
                long currentTimeMillis = this.zze.currentTimeMillis();
                long j2 = currentTimeMillis - this.zzb;
                if (j2 < CoroutineLiveDataKt.DEFAULT_TIMEOUT) {
                    String str = this.zzd;
                    Log.w("GoogleTagManager", "Excessive " + str + " detected; call ignored.");
                    return false;
                }
                double d2 = this.zza;
                if (d2 < 5.0d) {
                    double d3 = ((double) j2) / 900000.0d;
                    if (d3 > 0.0d) {
                        d2 = Math.min(5.0d, d2 + d3);
                        this.zza = d2;
                    }
                }
                this.zzb = currentTimeMillis;
                if (d2 >= 1.0d) {
                    this.zza = d2 - 4.0d;
                    return true;
                }
                String str2 = this.zzd;
                Log.w("GoogleTagManager", "Excessive " + str2 + " detected; call ignored.");
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

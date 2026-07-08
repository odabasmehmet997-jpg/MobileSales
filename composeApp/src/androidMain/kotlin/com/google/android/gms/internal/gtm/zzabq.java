package com.google.android.gms.internal.gtm;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public class zzabq {
    static final zzabq zza = new zzabq(true);
    private static volatile zzabq zzd;
    private final Map zze;

    zzabq() {
        this.zze = new HashMap();
    }

    public static zzabq zza() {
        zzabq zzabq = zzd;
        if (null != zzabq) {
            return zzabq;
        }
        synchronized (zzabq.class) {
            try {
                zzabq zzabq2 = zzd;
                if (null != zzabq2) {
                    return zzabq2;
                }
                final int i2 = zzadt.r8clinit;
                zzabq zzb = zzaby.zzb(zzabq.class);
                zzd = zzb;
                return zzb;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public zzace zzb(zzadl zzadl, int i2) {
        return (zzace) this.zze.get(new zzabp(zzadl, i2));
    }

    zzabq(boolean z) {
        this.zze = Collections.emptyMap();
    }
}

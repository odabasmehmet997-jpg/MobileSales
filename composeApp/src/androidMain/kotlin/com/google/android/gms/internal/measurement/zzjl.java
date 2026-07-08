package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public final class zzjl {
    static final zzjl zza = new zzjl(true);
    private static volatile zzjl zzc;
    private static volatile zzjl zzd;
    private final Map zze;

    zzjl() {
        this.zze = new HashMap();
    }

    public static zzjl zza() {
        zzjl zzjl = zzc;
        if (null == zzjl) {
            synchronized (zzjl.class) {
                try {
                    zzjl = zzc;
                    if (null == zzjl) {
                        zzjl = zza;
                        zzc = zzjl;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return zzjl;
    }

    public zzjx zzc(zzlg zzlg, int i2) {
        return (zzjx) this.zze.get(new zzjk(zzlg, i2));
    }

    zzjl(boolean z) {
        this.zze = Collections.emptyMap();
    }

    public static zzjl zzb() {
        zzjl zzjl = zzd;
        if (null != zzjl) {
            return zzjl;
        }
        synchronized (zzjl.class) {
            try {
                zzjl zzjl2 = zzd;
                if (null != zzjl2) {
                    return zzjl2;
                }
                zzjl zzb = zzjt.zzb(zzjl.class);
                zzd = zzb;
                return zzb;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

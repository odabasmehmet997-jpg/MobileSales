package com.google.android.gms.internal.gtm;

import androidx.webkit.ProxyConfig;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzafm extends zzacf {
    
    public static final zzafm zza;
    private int zzd;
    private int zze;
    private final String zzf = ProxyConfig.MATCH_ALL_SCHEMES;

    static {
        final zzafm zzafm = new zzafm();
        zza = zzafm;
        zzao(zzafm.class, zzafm);
    }

    private zzafm() {
    }

    public static zzafm zze() {
        return zzafm.zza;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzafm.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", zzafn.zza, "zzf"});
        } else if (3 == i3) {
            return new zzafm();
        } else {
            if (4 == i3) {
                return new zzafl(null);
            }
            if (5 == i3) {
                return zzafm.zza;
            }
            throw null;
        }
    }
}

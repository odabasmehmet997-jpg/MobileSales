package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzafu extends zzacf {
    
    public static final zzafu zza;
    private int zzd;
    private zzafq zze;

    static {
        zzafu zzafu = new zzafu();
        zza = zzafu;
        zzacf.zzao(zzafu.class, zzafu);
    }

    private zzafu() {
    }

    public static zzafu zze() {
        return zza;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zzd", "zze"});
        } else if (3 == i3) {
            return new zzafu();
        } else {
            if (4 == i3) {
                return new zzaft(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

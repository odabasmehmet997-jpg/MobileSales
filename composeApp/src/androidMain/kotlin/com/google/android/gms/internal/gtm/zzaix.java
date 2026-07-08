package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzaix extends zzacf {
    
    public static final zzaix zza;
    private int zzd;
    private long zze;
    private int zzf;

    static {
        zzaix zzaix = new zzaix();
        zza = zzaix;
        zzacf.zzao(zzaix.class, zzaix);
    }

    private zzaix() {
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002᠌\u0001", new Object[]{"zzd", "zze", "zzf", zzaiw.zza});
        } else if (3 == i3) {
            return new zzaix();
        } else {
            if (4 == i3) {
                return new zzaiv(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

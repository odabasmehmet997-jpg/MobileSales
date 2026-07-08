package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzale extends zzacf {
    
    public static final zzale zza;
    private int zzd;
    private int zze;

    static {
        zzale zzale = new zzale();
        zza = zzale;
        zzacf.zzao(zzale.class, zzale);
    }

    private zzale() {
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zzd", "zze", zzald.zza});
        } else if (3 == i3) {
            return new zzale();
        } else {
            if (4 == i3) {
                return new zzalc(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzahu extends zzacf {
    
    public static final zzahu zza;
    private int zzd;
    private boolean zze;

    static {
        zzahu zzahu = new zzahu();
        zza = zzahu;
        zzacf.zzao(zzahu.class, zzahu);
    }

    private zzahu() {
    }

    public static zzahu zze() {
        return zza;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဇ\u0000", new Object[]{"zzd", "zze"});
        } else if (3 == i3) {
            return new zzahu();
        } else {
            if (4 == i3) {
                return new zzaht(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

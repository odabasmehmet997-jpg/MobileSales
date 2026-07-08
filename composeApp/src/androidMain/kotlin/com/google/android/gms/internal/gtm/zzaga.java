package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzaga extends zzacf {
    
    public static final zzaga zza;
    private final zzack zzd = zzacf.zzah();

    static {
        zzaga zzaga = new zzaga();
        zza = zzaga;
        zzacf.zzao(zzaga.class, zzaga);
    }

    private zzaga() {
    }

    public static zzaga zze() {
        return zza;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001ࠞ", new Object[]{"zzd", zzage.zza});
        } else if (3 == i3) {
            return new zzaga();
        } else {
            if (4 == i3) {
                return new zzafz(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzsu extends zzacf {
    
    public static final zzsu zza;
    private final zzacn zzd = zzacf.zzai();

    static {
        zzsu zzsu = new zzsu();
        zza = zzsu;
        zzacf.zzao(zzsu.class, zzsu);
    }

    private zzsu() {
    }

    public static zzsu zzc() {
        return zza;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzd", zzss.class});
        } else if (3 == i3) {
            return new zzsu();
        } else {
            if (4 == i3) {
                return new zzst(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

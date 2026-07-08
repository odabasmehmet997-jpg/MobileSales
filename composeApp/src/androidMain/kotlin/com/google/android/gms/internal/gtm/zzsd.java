package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzsd extends zzacf {
    
    public static final zzsd zza;
    private final zzacn zzd = zzai();

    static {
        final zzsd zzsd = new zzsd();
        zza = zzsd;
        zzao(zzsd.class, zzsd);
    }

    private zzsd() {
    }

    public static zzsd zzc() {
        return zzsd.zza;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzsd.zza, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001Ț", new Object[]{"zzd"});
        } else if (3 == i3) {
            return new zzsd();
        } else {
            if (4 == i3) {
                return new zzsb(null);
            }
            if (5 == i3) {
                return zzsd.zza;
            }
            throw null;
        }
    }
}

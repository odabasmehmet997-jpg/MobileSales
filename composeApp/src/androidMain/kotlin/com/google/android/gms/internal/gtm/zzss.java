package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzss extends zzacf {
    
    public static final zzss zza;
    private int zzd;
    private final int zze = 1;
    private int zzf;

    static {
        final zzss zzss = new zzss();
        zza = zzss;
        zzao(zzss.class, zzss);
    }

    private zzss() {
    }

    public static zzss zzc() {
        return zzss.zza;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzss.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (3 == i3) {
            return new zzss();
        } else {
            if (4 == i3) {
                return new zzsr(null);
            }
            if (5 == i3) {
                return zzss.zza;
            }
            throw null;
        }
    }
}

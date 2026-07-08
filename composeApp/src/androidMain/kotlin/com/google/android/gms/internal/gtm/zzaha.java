package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzaha extends zzacf {
    
    public static final zzaha zza;
    private int zzd;
    private final zzack zze = zzah();
    private int zzf;

    static {
        final zzaha zzaha = new zzaha();
        zza = zzaha;
        zzao(zzaha.class, zzaha);
    }

    private zzaha() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzaha.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u0016\u0002င\u0000", new Object[]{"zzd", "zze", "zzf"});
        } else if (3 == i3) {
            return new zzaha();
        } else {
            if (4 == i3) {
                return new zzagy(null);
            }
            if (5 == i3) {
                return zzaha.zza;
            }
            throw null;
        }
    }
}

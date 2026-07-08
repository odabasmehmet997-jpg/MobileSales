package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzalp extends zzacf {
    
    public static final zzalp zza;
    private int zzd;
    private zzalo zze;
    private zzali zzf;

    static {
        final zzalp zzalp = new zzalp();
        zza = zzalp;
        zzao(zzalp.class, zzalp);
    }

    private zzalp() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzalp.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (3 == i3) {
            return new zzalp();
        } else {
            if (4 == i3) {
                return new zzalf(null);
            }
            if (5 == i3) {
                return zzalp.zza;
            }
            throw null;
        }
    }
}

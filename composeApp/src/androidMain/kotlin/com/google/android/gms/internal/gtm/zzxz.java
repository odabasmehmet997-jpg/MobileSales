package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzxz extends zzacf {
    
    public static final zzxz zza;
    private int zzd;
    private final String zze = "";
    private final boolean zzf = true;
    private final String zzg = "";

    static {
        zzxz zzxz = new zzxz();
        zza = zzxz;
        zzacf.zzao(zzxz.class, zzxz);
    }

    private zzxz() {
    }

    public static zzxz zzc() {
        return zza;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဈ\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        } else if (3 == i3) {
            return new zzxz();
        } else {
            if (4 == i3) {
                return new zzxx(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzzu extends zzacf {
    
    public static final zzzu zza;
    private int zzd;
    private final String zze = "";
    private final String zzf = "";
    private boolean zzg;
    private final String zzh = "";

    static {
        zzzu zzzu = new zzzu();
        zza = zzzu;
        zzacf.zzao(zzzu.class, zzzu);
    }

    private zzzu() {
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return new zzadv(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004ဈ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        } else if (3 == i3) {
            return new zzzu();
        } else {
            if (4 == i3) {
                return new zzzt(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

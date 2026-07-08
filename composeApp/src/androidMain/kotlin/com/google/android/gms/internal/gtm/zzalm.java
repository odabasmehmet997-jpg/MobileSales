package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzalm extends zzacf {
    
    public static final zzalm zza;
    private int zzd;
    private final String zze = "";
    private final String zzf = "";
    private final String zzg = "";
    private final String zzh = "";
    private final String zzi = "";

    static {
        zzalm zzalm = new zzalm();
        zza = zzalm;
        zzacf.zzao(zzalm.class, zzalm);
    }

    private zzalm() {
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
        } else if (3 == i3) {
            return new zzalm();
        } else {
            if (4 == i3) {
                return new zzall(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

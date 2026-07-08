package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzxw extends zzacf {
    
    public static final zzxw zza;
    private int zzd;
    private final String zze = "";
    private final String zzf = "";
    private final String zzg = "";
    private zzye zzh;

    static {
        zzxw zzxw = new zzxw();
        zza = zzxw;
        zzacf.zzao(zzxw.class, zzxw);
    }

    private zzxw() {
    }

    public static zzxw zzc() {
        return zza;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဉ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        } else if (3 == i3) {
            return new zzxw();
        } else {
            if (4 == i3) {
                return new zzxu(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

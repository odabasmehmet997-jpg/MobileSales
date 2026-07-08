package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzzs extends zzacf {
    
    public static final zzzs zza;
    private int zzd;
    private int zze;
    private final String zzf = "";
    private final String zzg = "";
    private boolean zzh;
    private boolean zzi;

    static {
        zzzs zzzs = new zzzs();
        zza = zzzs;
        zzacf.zzao(zzzs.class, zzzs);
    }

    private zzzs() {
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return new zzadv(zza, "\u0001\u0005\u0000\u0001\u0001\u0006\u0005\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0005ဇ\u0003\u0006ဇ\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
        } else if (3 == i3) {
            return new zzzs();
        } else {
            if (4 == i3) {
                return new zzzr(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

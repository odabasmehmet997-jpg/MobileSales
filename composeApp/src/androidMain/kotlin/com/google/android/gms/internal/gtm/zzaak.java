package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzaak extends zzacf {
    
    public static final zzaak zza;
    private int zzd;
    private int zze;
    private int zzf;
    private final String zzg = "";
    private int zzh;

    static {
        zzaak zzaak = new zzaak();
        zza = zzaak;
        zzacf.zzao(zzaak.class, zzaak);
    }

    private zzaak() {
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            zzacj zzacj = zzzk.zza;
            return new zzadv(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003ဈ\u0002\u0004᠌\u0003", new Object[]{"zzd", "zze", zzacj, "zzf", zzacj, "zzg", "zzh", zzacj});
        } else if (3 == i3) {
            return new zzaak();
        } else {
            if (4 == i3) {
                return new zzaaj(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

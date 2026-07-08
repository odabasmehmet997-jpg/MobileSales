package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzaai extends zzacf {
    
    public static final zzaai zza;
    private int zzd;
    private int zze;
    private final String zzf = "";
    private int zzg;
    private final String zzh = "";

    static {
        zzaai zzaai = new zzaai();
        zza = zzaai;
        zzacf.zzao(zzaai.class, zzaai);
    }

    private zzaai() {
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            zzacj zzacj = zzzk.zza;
            return new zzadv(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0003\u0002ဈ\u0001\u0003᠌\u0000\u0004᠌\u0002", new Object[]{"zzd", "zzh", "zzf", "zze", zzacj, "zzg", zzacj});
        } else if (3 == i3) {
            return new zzaai();
        } else {
            if (4 == i3) {
                return new zzaah(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

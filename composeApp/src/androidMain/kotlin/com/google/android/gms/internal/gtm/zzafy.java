package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzafy extends zzacf {
    
    public static final zzafy zza;
    private int zzd;
    private boolean zze;
    private boolean zzf;
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;

    static {
        zzafy zzafy = new zzafy();
        zza = zzafy;
        zzacf.zzao(zzafy.class, zzafy);
    }

    private zzafy() {
    }

    public static zzafy zze() {
        return zza;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0003\u0003ဇ\u0004\u0004ဇ\u0001\u0005ဇ\u0002", new Object[]{"zzd", "zze", "zzh", "zzi", "zzf", "zzg"});
        } else if (3 == i3) {
            return new zzafy();
        } else {
            if (4 == i3) {
                return new zzafx(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

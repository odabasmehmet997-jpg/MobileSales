package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzafw extends zzacf {
    
    public static final zzafw zza;
    private int zzd;
    private final zzack zze = zzacf.zzah();
    private zzafq zzf;
    private zzafq zzg;

    static {
        zzafw zzafw = new zzafw();
        zza = zzafw;
        zzacf.zzao(zzafw.class, zzafw);
    }

    private zzafw() {
    }

    public static zzafw zze() {
        return zza;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ࠞ\u0002ဉ\u0000\u0003ဉ\u0001", new Object[]{"zzd", "zze", zzage.zza, "zzf", "zzg"});
        } else if (3 == i3) {
            return new zzafw();
        } else {
            if (4 == i3) {
                return new zzafv(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

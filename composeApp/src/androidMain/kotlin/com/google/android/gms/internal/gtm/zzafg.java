package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzafg extends zzacf {
    
    public static final zzafg zza;
    private final zzacn zzd = zzacf.zzai();
    private final zzack zze = zzacf.zzah();
    private final zzack zzf = zzacf.zzah();
    private final zzack zzg = zzacf.zzah();
    private final zzacn zzh = zzacf.zzai();

    static {
        zzafg zzafg = new zzafg();
        zza = zzafg;
        zzacf.zzao(zzafg.class, zzafg);
    }

    private zzafg() {
    }

    public static zzafg zze() {
        return zza;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0001\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0005\u0000\u0001\u001a\u0002ࠞ\u0003\u0016\u0004\u001a\u0005ࠬ", new Object[]{"zzd", "zze", zzafj.zza, "zzg", "zzh", "zzf", zzafk.zza});
        } else if (3 == i3) {
            return new zzafg();
        } else {
            if (4 == i3) {
                return new zzaff(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

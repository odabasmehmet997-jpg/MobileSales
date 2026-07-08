package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzafe extends zzacf {
    
    public static final zzafe zza;
    private final zzacn zzd = zzai();
    private final zzack zze = zzah();
    private final zzack zzf = zzah();

    static {
        final zzafe zzafe = new zzafe();
        zza = zzafe;
        zzao(zzafe.class, zzafe);
    }

    private zzafe() {
    }

    public static zzafe zze() {
        return zzafe.zza;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzafe.zza, "\u0001\u0003\u0000\u0000\u0001\u0005\u0003\u0000\u0003\u0000\u0001\u001a\u0002ࠞ\u0005ࠬ", new Object[]{"zzd", "zze", zzafj.zza, "zzf", zzafk.zza});
        } else if (3 == i3) {
            return new zzafe();
        } else {
            if (4 == i3) {
                return new zzafd(null);
            }
            if (5 == i3) {
                return zzafe.zza;
            }
            throw null;
        }
    }
}

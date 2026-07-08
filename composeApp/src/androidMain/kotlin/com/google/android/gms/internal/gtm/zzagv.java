package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzagv extends zzacf {
    
    public static final zzagv zza;
    private int zzd;
    private int zze;
    private final String zzf = "";
    private final String zzg = "";
    private final String zzh = "";
    private final zzack zzi = zzah();
    private final String zzj = "";

    static {
        final zzagv zzagv = new zzagv();
        zza = zzagv;
        zzao(zzagv.class, zzagv);
    }

    private zzagv() {
    }

    public static zzagv zze() {
        return zzagv.zza;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzagv.zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001င\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ࠞ\u0006ဈ\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", zzagu.zza, "zzj"});
        } else if (3 == i3) {
            return new zzagv();
        } else {
            if (4 == i3) {
                return new zzagt(null);
            }
            if (5 == i3) {
                return zzagv.zza;
            }
            throw null;
        }
    }
}

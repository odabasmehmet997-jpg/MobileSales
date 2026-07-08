package com.google.android.gms.internal.gtm;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzad extends zzacf {
    public static final zzace zza;
    
    public static final zzad zzd;
    private int zze;
    private final zzack zzf = zzah();
    private final zzack zzg = zzah();
    private final zzack zzh = zzah();
    private int zzi;
    private final zzack zzj = zzah();
    private int zzk;
    private int zzl;

    static {
        final zzad zzad = new zzad();
        zzd = zzad;
        zzao(zzad.class, zzad);
        zza = zzac(zzap.zzi(), zzad, zzad, null, 101, zzaex.MESSAGE, zzad.class);
    }

    private zzad() {
    }

    public int zza() {
        return zzk;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzad.zzd, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0004\u0000\u0001\u0016\u0002\u0016\u0003\u0016\u0004င\u0000\u0005\u0016\u0006င\u0001\u0007င\u0002", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
        } else if (3 == i3) {
            return new zzad();
        } else {
            if (4 == i3) {
                return new zzac(null);
            }
            if (5 == i3) {
                return zzad.zzd;
            }
            throw null;
        }
    }

    public int zzc() {
        return zzg.size();
    }

    public int zzd() {
        return zzh.size();
    }

    public List zzf() {
        return zzf;
    }

    public List zzg() {
        return zzg;
    }

    public List zzh() {
        return zzh;
    }

    public List zzi() {
        return zzj;
    }
}

package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzgo extends zzjz {
    
    public static final zzgo zza;
    private int zze;
    private final zzkg zzf = zzby();
    private zzgk zzg;

    static {
        final zzgo zzgo = new zzgo();
        zza = zzgo;
        zzbE(zzgo.class, zzgo);
    }

    private zzgo() {
    }

    public zzgk zza() {
        final zzgk zzgk = zzg;
        return null == zzgk ? com.google.android.gms.internal.measurement.zzgk.zzc() : zzgk;
    }

    public List zzc() {
        return zzf;
    }

    
    public Object zzl(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzbD(zzgo.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002ဉ\u0000", new Object[]{"zze", "zzf", zzgt.class, "zzg"});
        } else if (3 == i3) {
            return new zzgo();
        } else {
            if (4 == i3) {
                return new zzgn(null);
            }
            if (5 != i3) {
                return null;
            }
            return zzgo.zza;
        }
    }
}

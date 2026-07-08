package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzgm extends zzjz {
    
    public static final zzgm zza;
    private int zze;
    private final String zzf = "";
    private final zzkg zzg = zzby();

    static {
        final zzgm zzgm = new zzgm();
        zza = zzgm;
        zzbE(zzgm.class, zzgm);
    }

    private zzgm() {
    }

    public String zzb() {
        return zzf;
    }

    public List zzc() {
        return zzg;
    }

    
    public Object zzl(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzbD(zzgm.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b", new Object[]{"zze", "zzf", "zzg", zzgt.class});
        } else if (3 == i3) {
            return new zzgm();
        } else {
            if (4 == i3) {
                return new zzgl(null);
            }
            if (5 != i3) {
                return null;
            }
            return zzgm.zza;
        }
    }
}

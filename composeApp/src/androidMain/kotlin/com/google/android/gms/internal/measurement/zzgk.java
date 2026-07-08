package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzgk extends zzjz {
    
    public static final zzgk zza;
    private final zzkg zze = zzjz.zzby();

    static {
        zzgk zzgk = new zzgk();
        zza = zzgk;
        zzjz.zzbE(zzgk.class, zzgk);
    }

    private zzgk() {
    }

    public static zzgk zzc() {
        return zza;
    }

    public int zza() {
        return this.zze.size();
    }

    public List zzd() {
        return this.zze;
    }

    
    public Object zzl(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzjz.zzbD(zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzgm.class});
        } else if (3 == i3) {
            return new zzgk();
        } else {
            if (4 == i3) {
                return new zzgj(null);
            }
            if (5 != i3) {
                return null;
            }
            return zza;
        }
    }
}

package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzfw extends zzjz {
    
    public static final zzfw zza;
    private zzkg zze = zzjz.zzby();

    static {
        zzfw zzfw = new zzfw();
        zza = zzfw;
        zzjz.zzbE(zzfw.class, zzfw);
    }

    private zzfw() {
    }

    public static zzfv zza() {
        return (zzfv) zza.zzbs();
    }

    static void zze(zzfw zzfw, zzfy zzfy) {
        zzfy.getClass();
        zzkg zzkg = zzfw.zze;
        if (!zzkg.zzc()) {
            zzfw.zze = zzjz.zzbz(zzkg);
        }
        zzfw.zze.add(zzfy);
    }

    public zzfy zzc(int i2) {
        return (zzfy) this.zze.get(0);
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
            return zzjz.zzbD(zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzfy.class});
        } else if (3 == i3) {
            return new zzfw();
        } else {
            if (4 == i3) {
                return new zzfv(null);
            }
            if (5 != i3) {
                return null;
            }
            return zza;
        }
    }
}

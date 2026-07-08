package com.google.android.gms.internal.gtm;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzv extends zzacf {
    
    public static final zzv zza;
    private final zzacn zzd = zzai();
    private final zzacn zze = zzai();
    private final zzacn zzf = zzai();
    private byte zzg = 2;

    static {
        final zzv zzv = new zzv();
        zza = zzv;
        zzao(zzv.class, zzv);
    }

    private zzv() {
    }

    public static zzv zzc() {
        return zzv.zza;
    }

    public List zzd() {
        return zzf;
    }

    public List zze() {
        return zze;
    }

    public List zzf() {
        return zzd;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zzg);
        }
        if (2 == i3) {
            return zzal(zzv.zza, "\u0004\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0003\u0002\u0001Л\u0002Л\u0003\u001b", new Object[]{"zzd", zzap.class, "zze", zzap.class, "zzf", zzt.class});
        } else if (3 == i3) {
            return new zzv();
        } else {
            if (4 == i3) {
                return new zzu(null);
            }
            if (5 == i3) {
                return zzv.zza;
            }
            zzg = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

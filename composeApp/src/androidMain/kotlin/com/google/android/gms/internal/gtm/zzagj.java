package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzagj extends zzacf {
    
    public static final zzagj zza;
    private int zzd;
    private final zzacn zze = zzai();
    private final String zzf = "";

    static {
        final zzagj zzagj = new zzagj();
        zza = zzagj;
        zzao(zzagj.class, zzagj);
    }

    private zzagj() {
    }

    public static zzagj zze() {
        return zzagj.zza;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzagj.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001a\u0002ဈ\u0000", new Object[]{"zzd", "zze", "zzf"});
        } else if (3 == i3) {
            return new zzagj();
        } else {
            if (4 == i3) {
                return new zzagi(null);
            }
            if (5 == i3) {
                return zzagj.zza;
            }
            throw null;
        }
    }
}

package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzwl extends zzacf {
    
    public static final zzwl zza;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;

    static {
        final zzwl zzwl = new zzwl();
        zza = zzwl;
        zzao(zzwl.class, zzwl);
    }

    private zzwl() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            final zzacj zzacj = zzwk.zza;
            return zzal(zzwl.zza, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003᠌\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006᠌\u0005\u0007᠌\u0006", new Object[]{"zzd", "zze", zzacj, "zzf", zzacj, "zzg", zzacj, "zzh", zzacj, "zzi", zzacj, "zzj", zzacj, "zzk", zzacj});
        } else if (3 == i3) {
            return new zzwl();
        } else {
            if (4 == i3) {
                return new zzwj(null);
            }
            if (5 == i3) {
                return zzwl.zza;
            }
            throw null;
        }
    }
}

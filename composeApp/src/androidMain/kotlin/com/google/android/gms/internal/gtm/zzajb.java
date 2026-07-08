package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzajb extends zzacf {
    
    public static final zzajb zza;
    private int zzd;
    private int zze;
    private int zzf;

    static {
        final zzajb zzajb = new zzajb();
        zza = zzajb;
        zzao(zzajb.class, zzajb);
    }

    private zzajb() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzajb.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (3 == i3) {
            return new zzajb();
        } else {
            if (4 == i3) {
                return new zzaja(null);
            }
            if (5 == i3) {
                return zzajb.zza;
            }
            throw null;
        }
    }
}

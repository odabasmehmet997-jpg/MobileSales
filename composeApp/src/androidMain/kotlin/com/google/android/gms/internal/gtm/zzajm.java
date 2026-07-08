package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzajm extends zzacf {
    
    public static final zzajm zza;
    private int zzd;
    private boolean zze;

    static {
        final zzajm zzajm = new zzajm();
        zza = zzajm;
        zzao(zzajm.class, zzajm);
    }

    private zzajm() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzajm.zza, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဇ\u0000", new Object[]{"zzd", "zze"});
        } else if (3 == i3) {
            return new zzajm();
        } else {
            if (4 == i3) {
                return new zzajl(null);
            }
            if (5 == i3) {
                return zzajm.zza;
            }
            throw null;
        }
    }
}

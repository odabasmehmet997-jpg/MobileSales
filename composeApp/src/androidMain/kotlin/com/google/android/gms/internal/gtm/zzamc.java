package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzamc extends zzacf {
    
    public static final zzamc zza;
    private int zzd;
    private int zze;

    static {
        final zzamc zzamc = new zzamc();
        zza = zzamc;
        zzao(zzamc.class, zzamc);
    }

    private zzamc() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzamc.zza, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zzd", "zze", zzamb.zza});
        } else if (3 == i3) {
            return new zzamc();
        } else {
            if (4 == i3) {
                return new zzama(null);
            }
            if (5 == i3) {
                return zzamc.zza;
            }
            throw null;
        }
    }
}

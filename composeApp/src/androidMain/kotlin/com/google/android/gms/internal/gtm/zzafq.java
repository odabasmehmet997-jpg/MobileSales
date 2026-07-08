package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzafq extends zzacf {
    
    public static final zzafq zza;
    private int zzd;
    private final zzack zze = zzah();
    private zzagc zzf;

    static {
        final zzafq zzafq = new zzafq();
        zza = zzafq;
        zzao(zzafq.class, zzafq);
    }

    private zzafq() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzafq.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ࠬ\u0002ဉ\u0000", new Object[]{"zzd", "zze", zzage.zza, "zzf"});
        } else if (3 == i3) {
            return new zzafq();
        } else {
            if (4 == i3) {
                return new zzafp(null);
            }
            if (5 == i3) {
                return zzafq.zza;
            }
            throw null;
        }
    }
}

package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzagq extends zzacf {
    
    public static final zzagq zza;
    private int zzd;
    private boolean zze;
    private boolean zzf;

    static {
        final zzagq zzagq = new zzagq();
        zza = zzagq;
        zzao(zzagq.class, zzagq);
    }

    private zzagq() {
    }

    public static zzagq zze() {
        return zzagq.zza;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzagq.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (3 == i3) {
            return new zzagq();
        } else {
            if (4 == i3) {
                return new zzagp(null);
            }
            if (5 == i3) {
                return zzagq.zza;
            }
            throw null;
        }
    }
}

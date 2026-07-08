package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzalk extends zzacf {
    
    public static final zzalk zza;
    private int zzd;
    private boolean zze;
    private boolean zzf;
    private boolean zzg;
    private boolean zzh;

    static {
        final zzalk zzalk = new zzalk();
        zza = zzalk;
        zzao(zzalk.class, zzalk);
    }

    private zzalk() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzalk.zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဇ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        } else if (3 == i3) {
            return new zzalk();
        } else {
            if (4 == i3) {
                return new zzalj(null);
            }
            if (5 == i3) {
                return zzalk.zza;
            }
            throw null;
        }
    }
}

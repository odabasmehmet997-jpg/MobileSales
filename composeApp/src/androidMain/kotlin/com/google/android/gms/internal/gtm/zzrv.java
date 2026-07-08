package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzrv extends zzacf {
    
    public static final zzrv zza;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        final zzrv zzrv = new zzrv();
        zza = zzrv;
        zzao(zzrv.class, zzrv);
    }

    private zzrv() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzrv.zza, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003᠌\u0002", new Object[]{"zzd", "zze", zzrt.zza, "zzf", zzrs.zza, "zzg", zzru.zza});
        } else if (3 == i3) {
            return new zzrv();
        } else {
            if (4 == i3) {
                return new zzrr(null);
            }
            if (5 == i3) {
                return zzrv.zza;
            }
            throw null;
        }
    }
}

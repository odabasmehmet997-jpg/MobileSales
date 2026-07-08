package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzamh extends zzacf {
    
    public static final zzamh zza;
    private int zzd;
    private boolean zze;
    private boolean zzf;
    private boolean zzg;
    private boolean zzh;

    static {
        zzamh zzamh = new zzamh();
        zza = zzamh;
        zzacf.zzao(zzamh.class, zzamh);
    }

    private zzamh() {
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဇ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        } else if (3 == i3) {
            return new zzamh();
        } else {
            if (4 == i3) {
                return new zzamg(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

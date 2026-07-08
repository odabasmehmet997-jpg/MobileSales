package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzye extends zzacf {
    
    public static final zzye zza;
    private int zzd;
    private final String zze = "";
    private int zzf;

    static {
        zzye zzye = new zzye();
        zza = zzye;
        zzacf.zzao(zzye.class, zzye);
    }

    private zzye() {
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002᠌\u0001", new Object[]{"zzd", "zze", "zzf", zzyc.zza});
        } else if (3 == i3) {
            return new zzye();
        } else {
            if (4 == i3) {
                return new zzyb(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

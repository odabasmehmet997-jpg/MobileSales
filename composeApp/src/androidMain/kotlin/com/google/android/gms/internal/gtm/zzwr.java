package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzwr extends zzacf {
    
    public static final zzwr zza;
    private final int zzd;
    private Object zze;
    private final int zzf;
    private Object zzg;
    private byte zzh = 2;

    static {
        zzwr zzwr = new zzwr();
        zza = zzwr;
        zzacf.zzao(zzwr.class, zzwr);
    }

    private zzwr() {
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(this.zzh);
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0001\u0005\u0002\u0000\u0001\u0005\u0005\u0000\u0000\u0003\u0001м\u0000\u0002м\u0001\u0003м\u0000\u0004;\u0000\u0005;\u0001", new Object[]{"zze", "zzd", "zzg", "zzf", zzxo.class, zzxj.class, zzxm.class});
        } else if (3 == i3) {
            return new zzwr();
        } else {
            if (4 == i3) {
                return new zzwq(null);
            }
            if (5 == i3) {
                return zza;
            }
            this.zzh = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

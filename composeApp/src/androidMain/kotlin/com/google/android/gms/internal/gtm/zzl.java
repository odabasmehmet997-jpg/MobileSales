package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzl extends zzacf {
    
    public static final zzl zza;
    private final zzacn zzd = zzacf.zzai();
    private final zzacn zze = zzacf.zzai();
    private byte zzf = 2;

    static {
        zzl zzl = new zzl();
        zza = zzl;
        zzacf.zzao(zzl.class, zzl);
    }

    private zzl() {
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(this.zzf);
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0004\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0002\u0001Л\u0002Л", new Object[]{"zzd", zzj.class, "zze", zzf.class});
        } else if (3 == i3) {
            return new zzl();
        } else {
            if (4 == i3) {
                return new zzk(null);
            }
            if (5 == i3) {
                return zza;
            }
            this.zzf = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzd extends zzacf {
    public static final zzace zza;
    
    public static final zzd zzd;
    private int zze;
    private zzl zzf;
    private zzf zzg;
    private byte zzh = 2;

    static {
        zzd zzd2 = new zzd();
        zzd = zzd2;
        zzacf.zzao(zzd.class, zzd2);
        zza = zzacf.zzac(zzap.zzi(), zzd2, zzd2, null, 47497405, zzaex.MESSAGE, zzd.class);
    }

    private zzd() {
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(this.zzh);
        }
        if (2 == i3) {
            return zzacf.zzal(zzd, "\u0004\u0002\u0000\u0001\u0001\u0003\u0002\u0000\u0000\u0002\u0001ᐉ\u0000\u0003ᐉ\u0001", new Object[]{"zze", "zzf", "zzg"});
        } else if (3 == i3) {
            return new zzd();
        } else {
            if (4 == i3) {
                return new zzc(null);
            }
            if (5 == i3) {
                return zzd;
            }
            this.zzh = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

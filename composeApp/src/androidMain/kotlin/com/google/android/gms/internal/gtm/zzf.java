package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzf extends zzacf {
    
    public static final zzf zza;
    private int zzd;
    private final zzacn zze = zzacf.zzai();
    private zzap zzf;
    private final String zzg = "";
    private byte zzh = 2;

    static {
        zzf zzf2 = new zzf();
        zza = zzf2;
        zzacf.zzao(zzf.class, zzf2);
    }

    private zzf() {
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(this.zzh);
        }
        if (2 == i3) {
            return zzacf.zzal(this.zzf.zza, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0002\u0001Л\u0002ᐉ\u0000\u0003ဈ\u0001", new Object[]{"zzd", "zze", zzh.class, "zzf", "zzg"});
        } else if (3 == i3) {
            return new zzf();
        } else {
            if (4 == i3) {
                return new zze(null);
            }
            if (5 == i3) {
                return this.zzf.zza;
            }
            this.zzh = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzh extends zzacf {
    
    public static final zzh zza;
    private int zzd;
    private final String zze = "";
    private zzap zzf;
    private byte zzg = 2;

    static {
        final zzh zzh = new zzh();
        zza = zzh;
        zzao(zzh.class, zzh);
    }

    private zzh() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zzg);
        }
        if (2 == i3) {
            return zzal(zzh.zza, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001ဈ\u0000\u0002ᐉ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (3 == i3) {
            return new zzh();
        } else {
            if (4 == i3) {
                return new zzg(null);
            }
            if (5 == i3) {
                return zzh.zza;
            }
            zzg = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

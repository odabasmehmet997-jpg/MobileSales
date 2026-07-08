package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzxm extends zzacf {
    
    public static final zzxm zza;
    private int zzd;
    private zzxo zze;
    private long zzf;
    private byte zzg = 2;

    static {
        final zzxm zzxm = new zzxm();
        zza = zzxm;
        zzao(zzxm.class, zzxm);
    }

    private zzxm() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zzg);
        }
        if (2 == i3) {
            return zzal(zzxm.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001ᐉ\u0000\u0002ဂ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (3 == i3) {
            return new zzxm();
        } else {
            if (4 == i3) {
                return new zzxl(null);
            }
            if (5 == i3) {
                return zzxm.zza;
            }
            zzg = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

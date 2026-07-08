package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzx extends zzacf {
    
    public static final zzx zza;
    private int zzd;
    private int zze;
    private int zzf;
    private byte zzg = 2;

    static {
        final zzx zzx = new zzx();
        zza = zzx;
        zzao(zzx.class, zzx);
    }

    private zzx() {
    }

    public int zza() {
        return zze;
    }

    public int zzc() {
        return zzf;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zzg);
        }
        if (2 == i3) {
            return zzal(zzx.zza, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔄ\u0000\u0002ᔄ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (3 == i3) {
            return new zzx();
        } else {
            if (4 == i3) {
                return new zzw(null);
            }
            if (5 == i3) {
                return zzx.zza;
            }
            zzg = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

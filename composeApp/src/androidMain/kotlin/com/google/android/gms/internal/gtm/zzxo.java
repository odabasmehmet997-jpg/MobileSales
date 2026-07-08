package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzxo extends zzacf {
    public static final zzace zza;
    
    public static final zzxo zzd;
    private int zze;
    private long zzf;
    private int zzg;
    private int zzh;
    private byte zzi = 2;

    static {
        final zzxo zzxo = new zzxo();
        zzd = zzxo;
        zzao(zzxo.class, zzxo);
        zza = zzac(zzaii.zze(), zzxo, zzxo, null, 4156379, zzaex.MESSAGE, zzxo.class);
    }

    private zzxo() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zzi);
        }
        if (2 == i3) {
            return zzal(zzxo.zzd, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0003\u0001ᔂ\u0000\u0002ᔆ\u0001\u0003ᔆ\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
        } else if (3 == i3) {
            return new zzxo();
        } else {
            if (4 == i3) {
                return new zzxn(null);
            }
            if (5 == i3) {
                return zzxo.zzd;
            }
            zzi = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

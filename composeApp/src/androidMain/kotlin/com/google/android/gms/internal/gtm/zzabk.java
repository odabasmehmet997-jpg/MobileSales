package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzabk extends zzacf {
    
    public static final zzabk zza;
    private int zzd;
    private final String zze = "";
    private boolean zzf;
    private byte zzg = 2;

    static {
        final zzabk zzabk = new zzabk();
        zza = zzabk;
        zzao(zzabk.class, zzabk);
    }

    private zzabk() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zzg);
        }
        if (2 == i3) {
            return new zzadv(zzabk.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔈ\u0000\u0002ᔇ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (3 == i3) {
            return new zzabk();
        } else {
            if (4 == i3) {
                return new zzabj(null);
            }
            if (5 == i3) {
                return zzabk.zza;
            }
            zzg = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

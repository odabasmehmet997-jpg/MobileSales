package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzfu extends zzjz {
    
    public static final zzfu zza;
    private int zze;
    private final String zzf = "";
    private final String zzg = "";
    private zzfi zzh;

    static {
        final zzfu zzfu = new zzfu();
        zza = zzfu;
        zzbE(zzfu.class, zzfu);
    }

    private zzfu() {
    }

    
    public Object zzl(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzbD(zzfu.zza, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဉ\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
        } else if (3 == i3) {
            return new zzfu();
        } else {
            if (4 == i3) {
                return new zzft(null);
            }
            if (5 != i3) {
                return null;
            }
            return zzfu.zza;
        }
    }
}

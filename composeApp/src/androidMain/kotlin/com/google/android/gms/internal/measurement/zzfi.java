package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzfi extends zzjz {
    
    public static final zzfi zza;
    private int zze;
    private final String zzf = "";
    private final String zzg = "";
    private final String zzh = "";
    private final String zzi = "";
    private final String zzj = "";
    private final String zzk = "";
    private final String zzl = "";

    static {
        final zzfi zzfi = new zzfi();
        zza = zzfi;
        zzbE(zzfi.class, zzfi);
    }

    private zzfi() {
    }

    
    public Object zzl(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzbD(zzfi.zza, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
        } else if (3 == i3) {
            return new zzfi();
        } else {
            if (4 == i3) {
                return new zzfh(null);
            }
            if (5 != i3) {
                return null;
            }
            return zzfi.zza;
        }
    }
}

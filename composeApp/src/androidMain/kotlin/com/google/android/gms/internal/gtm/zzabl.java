package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzabl extends zzacf {
    
    public static final zzabl zza;
    private int zzd;
    private final zzacn zze = zzadu.zze();
    private final String zzf = "";
    private long zzg;
    private long zzh;
    private double zzi;
    private final zzyx zzj = zzyx.zzb;
    private final String zzk = "";
    private byte zzl = 2;

    static {
        zzabl zzabl = new zzabl();
        zza = zzabl;
        zzacf.zzao(zzabl.class, zzabl);
    }

    private zzabl() {
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(this.zzl);
        }
        if (2 == i3) {
            return new zzadv(zza, "\u0001\u0007\u0000\u0001\u0002\b\u0007\u0000\u0001\u0001\u0002Л\u0003ဈ\u0000\u0004ဃ\u0001\u0005ဂ\u0002\u0006က\u0003\u0007ည\u0004\bဈ\u0005", new Object[]{"zzd", "zze", zzabk.class, "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        } else if (3 == i3) {
            return new zzabl();
        } else {
            if (4 == i3) {
                return new zzabi(null);
            }
            if (5 == i3) {
                return zza;
            }
            this.zzl = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

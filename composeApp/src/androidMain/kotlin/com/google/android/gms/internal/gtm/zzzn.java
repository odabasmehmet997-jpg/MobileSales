package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzzn extends zzacc {
    
    public static final zzzn zzd;
    private int zze;
    private final String zzf = "";
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;
    private zzaae zzj;
    private final zzacn zzk = zzadu.zze();
    private byte zzl = 2;

    static {
        zzzn zzzn = new zzzn();
        zzd = zzzn;
        zzacf.zzao(zzzn.class, zzzn);
    }

    private zzzn() {
    }

    public static zzzn zze() {
        return zzd;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(this.zzl);
        }
        if (2 == i3) {
            return new zzadv(zzd, "\u0001\u0006\u0000\u0001\u0001ϧ\u0006\u0000\u0001\u0002\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0006ဇ\u0003\u0007ᐉ\u0004ϧЛ", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzabl.class});
        } else if (3 == i3) {
            return new zzzn();
        } else {
            if (4 == i3) {
                return new zzzm(null);
            }
            if (5 == i3) {
                return zzd;
            }
            this.zzl = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

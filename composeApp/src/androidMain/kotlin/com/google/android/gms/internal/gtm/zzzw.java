package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzzw extends zzacc {
    
    public static final zzzw zzd;
    private int zze;
    private final zzacn zzf = zzadu.zze();
    private zzzu zzg;
    private final zzacn zzh = zzadu.zze();
    private zzaae zzi;
    private final int zzj = 1;
    private byte zzk = 2;

    static {
        zzzw zzzw = new zzzw();
        zzd = zzzw;
        zzacf.zzao(zzzw.class, zzzw);
    }

    private zzzw() {
    }

    public static zzzw zze() {
        return zzd;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(this.zzk);
        }
        if (2 == i3) {
            return new zzadv(zzd, "\u0001\u0005\u0000\u0001\u0001ϧ\u0005\u0000\u0002\u0002\u0001ဉ\u0000\u0002\u001b\u0003᠌\u00022ᐉ\u0001ϧЛ", new Object[]{"zze", "zzg", "zzh", zzzs.class, "zzj", zzzv.zza, "zzi", "zzf", zzabl.class});
        } else if (3 == i3) {
            return new zzzw();
        } else {
            if (4 == i3) {
                return new zzzq(null);
            }
            if (5 == i3) {
                return zzd;
            }
            this.zzk = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

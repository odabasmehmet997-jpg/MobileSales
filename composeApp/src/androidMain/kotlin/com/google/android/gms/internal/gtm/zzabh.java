package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzabh extends zzacc {
    
    public static final zzabh zzd;
    private int zze;
    private boolean zzf;
    private final double zzg = -1.0d;
    private zzaae zzh;
    private boolean zzi;
    private final zzacn zzj = zzadu.zze();
    private byte zzk = 2;

    static {
        zzabh zzabh = new zzabh();
        zzd = zzabh;
        zzacf.zzao(zzabh.class, zzabh);
    }

    private zzabh() {
    }

    public static zzabh zze() {
        return zzd;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(this.zzk);
        }
        if (2 == i3) {
            return new zzadv(zzd, "\u0001\u0005\u0000\u0001\u0010ϧ\u0005\u0000\u0001\u0002\u0010က\u0001\u0014ဇ\u0000!ဇ\u0003\"ᐉ\u0002ϧЛ", new Object[]{"zze", "zzg", "zzf", "zzi", "zzh", "zzj", zzabl.class});
        } else if (3 == i3) {
            return new zzabh();
        } else {
            if (4 == i3) {
                return new zzabg(null);
            }
            if (5 == i3) {
                return zzd;
            }
            this.zzk = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

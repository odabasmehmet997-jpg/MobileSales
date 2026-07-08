package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzabf extends zzacc {
    
    public static final zzabf zzd;
    private int zze;
    private zzaae zzf;
    private final zzacn zzg = zzadu.zze();
    private byte zzh = 2;

    static {
        zzabf zzabf = new zzabf();
        zzd = zzabf;
        zzacf.zzao(zzabf.class, zzabf);
    }

    private zzabf() {
    }

    public static zzabf zze() {
        return zzd;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(this.zzh);
        }
        if (2 == i3) {
            return new zzadv(zzd, "\u0001\u0002\u0000\u0001\u0001ϧ\u0002\u0000\u0001\u0002\u0001ᐉ\u0000ϧЛ", new Object[]{"zze", "zzf", "zzg", zzabl.class});
        } else if (3 == i3) {
            return new zzabf();
        } else {
            if (4 == i3) {
                return new zzabe(null);
            }
            if (5 == i3) {
                return zzd;
            }
            this.zzh = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

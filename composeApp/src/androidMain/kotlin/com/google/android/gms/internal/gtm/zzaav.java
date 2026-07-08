package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzaav extends zzacc {
    
    public static final zzaav zzd;
    private int zze;
    private boolean zzf;
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;
    private final String zzj = "";
    private boolean zzk;
    private zzaae zzl;
    private final zzacn zzm = zzadu.zze();
    private byte zzn = 2;

    static {
        zzaav zzaav = new zzaav();
        zzd = zzaav;
        zzacf.zzao(zzaav.class, zzaav);
    }

    private zzaav() {
    }

    public static zzaav zze() {
        return zzd;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(this.zzn);
        }
        if (2 == i3) {
            return new zzadv(zzd, "\u0001\b\u0000\u0001\u0001ϧ\b\u0000\u0001\u0002\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0007ဇ\u0003\nဈ\u0004\u000bဇ\u0005\fᐉ\u0006ϧЛ", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", zzabl.class});
        } else if (3 == i3) {
            return new zzaav();
        } else {
            if (4 == i3) {
                return new zzaau(null);
            }
            if (5 == i3) {
                return zzd;
            }
            this.zzn = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

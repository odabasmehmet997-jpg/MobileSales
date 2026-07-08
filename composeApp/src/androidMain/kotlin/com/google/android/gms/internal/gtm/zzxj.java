package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzxj extends zzacf {
    public static final zzace zza;
    
    public static final zzxj zzd;
    private int zze;
    private final int zzf = -1;
    private final int zzg = -1;
    private zzxg zzh;
    private int zzi;
    private int zzj;
    private final int zzk = -1;
    private final int zzl = -1;
    private int zzm;
    private final int zzn = -1;
    private boolean zzo;
    private long zzp;
    private zzxm zzq;
    private final int zzr = -1;
    private byte zzs = 2;

    static {
        zzxj zzxj = new zzxj();
        zzd = zzxj;
        zzacf.zzao(zzxj.class, zzxj);
        zza = zzacf.zzac(zzaii.zze(), zzxj, zzxj, null, 15872052, zzaex.MESSAGE, zzxj.class);
    }

    private zzxj() {
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(this.zzs);
        }
        if (2 == i3) {
            return zzacf.zzal(zzd, "\u0001\r\u0000\u0001\u0001\u000f\r\u0000\u0000\u0001\u0001င\u0000\u0002င\u0003\u0005င\u0005\u0006င\u0006\u0007င\u0007\bင\u0004\tင\b\nဇ\t\u000bင\u0001\fစ\n\rᐉ\u000b\u000eင\f\u000fဉ\u0002", new Object[]{"zze", "zzf", "zzi", "zzk", "zzl", "zzm", "zzj", "zzn", "zzo", "zzg", "zzp", "zzq", "zzr", "zzh"});
        } else if (3 == i3) {
            return new zzxj();
        } else {
            if (4 == i3) {
                return new zzxi(null);
            }
            if (5 == i3) {
                return zzd;
            }
            this.zzs = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

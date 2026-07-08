package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzaaq extends zzacc {
    
    public static final zzaaq zzd;
    private int zze;
    private int zzf;
    private boolean zzg;
    private int zzh;
    private boolean zzi;
    private boolean zzj;
    private boolean zzk;
    private boolean zzl;
    private final zzacn zzm = zzadu.zze();
    private boolean zzn;
    private final boolean zzo = true;
    private boolean zzp;
    private boolean zzq;
    private int zzr;
    private final zzack zzs = zzacg.zzf();
    private final zzacn zzt = zzadu.zze();
    private zzaae zzu;
    private zzaak zzv;
    private final zzacn zzw = zzadu.zze();
    private byte zzx = 2;

    static {
        zzaaq zzaaq = new zzaaq();
        zzd = zzaaq;
        zzacf.zzao(zzaaq.class, zzaaq);
    }

    private zzaaq() {
    }

    public static zzaaq zze() {
        return zzd;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(this.zzx);
        }
        if (2 == i3) {
            return new zzadv(zzd, "\u0001\u0012\u0000\u0001\u0001ϧ\u0012\u0000\u0004\u0002\u0001᠌\u0000\u0002ဇ\u0001\u0003ဇ\u0005\u0005ဇ\u0003\u0006᠌\u0002\nဇ\u0006\u000b\u001b\fဇ\u0007\rဇ\b\u000eဇ\t\u000fဇ\u0004\u0010ဇ\n\u0011᠌\u000b\u0013ࠞ\u0014\u001b\u0015ᐉ\f\u0016ဉ\rϧЛ", new Object[]{"zze", "zzf", zzaag.zza, "zzg", "zzk", "zzi", "zzh", zzaal.zza, "zzl", "zzm", zzaap.class, "zzn", "zzo", "zzp", "zzj", "zzq", "zzr", zzaam.zza, "zzs", zzaan.zza, "zzt", zzaai.class, "zzu", "zzv", "zzw", zzabl.class});
        } else if (3 == i3) {
            return new zzaaq();
        } else {
            if (4 == i3) {
                return new zzaaf(null);
            }
            if (5 == i3) {
                return zzd;
            }
            this.zzx = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

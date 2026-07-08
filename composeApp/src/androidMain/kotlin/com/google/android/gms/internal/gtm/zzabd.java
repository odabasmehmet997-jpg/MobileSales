package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzabd extends zzacc {
    
    public static final zzabd zzd;
    private final int zzA = 1;
    private final int zzB = 2;
    private boolean zzC;
    private int zzD;
    private zzaae zzE;
    private final zzacn zzF = zzadu.zze();
    private byte zzG = 2;
    private int zze;
    private int zzf;
    private final double zzg = -1.0d;
    private boolean zzh;
    private boolean zzi;
    private boolean zzj;
    private final int zzk = 256;
    private final int zzl = 256;
    private int zzm;
    private final int zzn = 3;
    private int zzo;
    private int zzp;
    private final String zzq = "";
    private boolean zzr;
    private boolean zzs;
    private final String zzt = "";
    private final String zzu = "";
    private boolean zzv;
    private final long zzw = -1;
    private final long zzx = -1;
    private final long zzy = 16000000;
    private final long zzz = 16000000;

    static {
        zzabd zzabd = new zzabd();
        zzd = zzabd;
        zzacf.zzao(zzabd.class, zzabd);
    }

    private zzabd() {
    }

    public static zzabd zze() {
        return zzd;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(this.zzG);
        }
        if (2 == i3) {
            zzacj zzacj = zzaba.zza;
            zzacj zzacj2 = zzabb.zza;
            zzacj zzacj3 = zzaax.zza;
            return new zzadv(zzd, "\u0001\u001b\u0000\u0001\u0007ϧ\u001b\u0000\u0001\u0002\u0007᠌\u0000\bက\u0001\tဇ\u0002\nဇ\u0003\u000bဏ\u0005\fဏ\u0006\r᠌\u0007\u000e᠌\b\u000f᠌\t\u0011᠌\n\u0013ဈ\u000b\u0014ဇ\f\u0015ဇ\r\u0016ဈ\u000e\u0017ဈ\u000f\u0018ဂ\u0011\u0019ဂ\u0012\u001aဇ\u0004\u001b᠌\u0016\u001c᠌\u0015\u001dဇ\u0010\u001eဂ\u0013\u001fဂ\u0014!ဇ\u0017\"᠌\u0018#ᐉ\u0019ϧЛ", new Object[]{"zze", "zzf", zzacj, "zzg", "zzh", "zzi", "zzk", "zzl", "zzm", zzacj2, "zzn", zzacj2, "zzo", zzacj3, "zzp", zzacj3, "zzq", "zzr", "zzs", "zzt", "zzu", "zzw", "zzx", "zzj", "zzB", zzaaz.zza, "zzA", zzabc.zza, "zzv", "zzy", "zzz", "zzC", "zzD", zzaay.zza, "zzE", "zzF", zzabl.class});
        } else if (3 == i3) {
            return new zzabd();
        } else {
            if (4 == i3) {
                return new zzaaw(null);
            }
            if (5 == i3) {
                return zzd;
            }
            this.zzG = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

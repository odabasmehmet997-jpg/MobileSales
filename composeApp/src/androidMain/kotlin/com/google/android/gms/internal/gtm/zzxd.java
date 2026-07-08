package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzxd extends zzacc {
    
    public static final zzxd zzd;
    private int zze;
    private int zzf;
    private final int zzg = -1;
    private final zzack zzh = zzah();
    private final String zzi = "";
    private int zzj;
    private zzahd zzk;
    private int zzl;
    private final String zzm = "";
    private boolean zzn;
    private final int zzo = -1;
    private zzwo zzp;
    private byte zzq = 2;

    static {
        final zzxd zzxd = new zzxd();
        zzd = zzxd;
        zzao(zzxd.class, zzxd);
    }

    private zzxd() {
    }

    public static zzxd zzc() {
        return zzxd.zzd;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zzq);
        }
        if (2 == i3) {
            return zzal(zzxd.zzd, "\u0001\u000b\u0000\u0001\u0001è\u000b\u0000\u0001\u0002\u0001င\u0000\u0003င\u0001\u0004\u0016\u0005ဈ\u0002\u0006᠌\u0005\u0007င\u0003\u000bᐉ\u0004\u0011ဈ\u0006ဇ\u0007င\bèᐉ\t", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzl", zzxc.zza, "zzj", "zzk", "zzm", "zzn", "zzo", "zzp"});
        } else if (3 == i3) {
            return new zzxd();
        } else {
            if (4 == i3) {
                return new zzxb(null);
            }
            if (5 == i3) {
                return zzxd.zzd;
            }
            zzq = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

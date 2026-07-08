package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzaae extends zzacc {
    
    public static final zzaae zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private byte zzl = 2;

    static {
        final zzaae zzaae = new zzaae();
        zzd = zzaae;
        zzao(zzaae.class, zzaae);
    }

    private zzaae() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zzl);
        }
        if (2 == i3) {
            return new zzadv(zzaae.zzd, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003᠌\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006᠌\u0005", new Object[]{"zze", "zzf", zzzz.zza, "zzg", zzzy.zza, "zzh", zzaac.zza, "zzi", zzaad.zza, "zzj", zzaab.zza, "zzk", zzaaa.zza});
        } else if (3 == i3) {
            return new zzaae();
        } else {
            if (4 == i3) {
                return new zzzx(null);
            }
            if (5 == i3) {
                return zzaae.zzd;
            }
            zzl = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

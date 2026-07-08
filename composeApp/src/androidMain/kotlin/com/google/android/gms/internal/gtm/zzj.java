package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzj extends zzacf {
    
    public static final zzj zza;
    private int zzd;
    private final zzacn zze = zzai();
    private final zzacn zzf = zzai();
    private final zzacn zzg = zzai();
    private final zzacn zzh = zzai();
    private final zzacn zzi = zzai();
    private final zzacn zzj = zzai();
    private zzap zzk;
    private byte zzl = 2;

    static {
        final zzj zzj2 = new zzj();
        zza = zzj2;
        zzao(zzj.class, zzj2);
    }

    private zzj() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zzl);
        }
        if (2 == i3) {
            return zzal(zzj.zza, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0006\u0007\u0001Л\u0002Л\u0003Л\u0004Л\u0005Л\u0006Л\u0007ᐉ\u0000", new Object[]{"zzd", "zze", zzf.class, "zzf", zzf.class, "zzg", zzf.class, "zzh", zzf.class, "zzi", zzf.class, "zzj", zzf.class, "zzk"});
        } else if (3 == i3) {
            return new zzj();
        } else {
            if (4 == i3) {
                return new zzi(null);
            }
            if (5 == i3) {
                return zzj.zza;
            }
            zzl = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

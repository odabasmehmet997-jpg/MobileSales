package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzzp extends zzacc {
    
    public static final zzzp zzd;
    private int zze;
    private boolean zzf;
    private zzaae zzg;
    private boolean zzh;
    private zzaak zzi;
    private final zzacn zzj = zzadu.zze();
    private byte zzk = 2;

    static {
        final zzzp zzzp = new zzzp();
        zzd = zzzp;
        zzao(zzzp.class, zzzp);
    }

    private zzzp() {
    }

    public static zzzp zze() {
        return zzzp.zzd;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zzk);
        }
        if (2 == i3) {
            return new zzadv(zzzp.zzd, "\u0001\u0005\u0000\u0001\u0001ϧ\u0005\u0000\u0001\u0002\u0001ဇ\u0000\u0002ᐉ\u0001\u0003ဇ\u0002\u0004ဉ\u0003ϧЛ", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", zzabl.class});
        } else if (3 == i3) {
            return new zzzp();
        } else {
            if (4 == i3) {
                return new zzzo(null);
            }
            if (5 == i3) {
                return zzzp.zzd;
            }
            zzk = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

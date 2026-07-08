package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzahd extends zzacf {
    
    public static final zzahd zza;
    private final int zzd;
    private Object zze;
    private final int zzf;
    private Object zzg;
    private byte zzh = 2;

    static {
        final zzahd zzahd = new zzahd();
        zza = zzahd;
        zzao(zzahd.class, zzahd);
    }

    private zzahd() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zzh);
        }
        if (2 == i3) {
            return zzal(zzahd.zza, "\u0001\u0004\u0002\u0000\u0001\u0006\u0004\u0000\u0000\u0002\u0001м\u0000\u0002м\u0000\u00037\u0001\u0006<\u0001", new Object[]{"zze", "zzd", "zzg", "zzf", zzxo.class, zzxm.class, zzaha.class});
        } else if (3 == i3) {
            return new zzahd();
        } else {
            if (4 == i3) {
                return new zzahb(null);
            }
            if (5 == i3) {
                return zzahd.zza;
            }
            zzh = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzahj extends zzacf {
    
    public static final zzahj zza;
    private final zzacn zzd = zzai();
    private byte zze = 2;

    static {
        final zzahj zzahj = new zzahj();
        zza = zzahj;
        zzao(zzahj.class, zzahj);
    }

    private zzahj() {
    }

    public static zzahj zze() {
        return zzahj.zza;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zze);
        }
        if (2 == i3) {
            return zzal(zzahj.zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0001Л", new Object[]{"zzd", zzahi.class});
        } else if (3 == i3) {
            return new zzahj();
        } else {
            if (4 == i3) {
                return new zzahg(null);
            }
            if (5 == i3) {
                return zzahj.zza;
            }
            zze = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

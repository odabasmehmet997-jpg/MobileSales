package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzwy extends zzacf {
    public static final zzace zza = zzab(zzxd.zzc(), null, null, 330, zzaex.INT32, false, Integer.class);
    
    public static final zzwy zzd;

    static {
        final zzwy zzwy = new zzwy();
        zzd = zzwy;
        zzao(zzwy.class, zzwy);
    }

    private zzwy() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzwy.zzd, "\u0001\u0000", null);
        }
        if (3 == i3) {
            return new zzwy();
        }
        if (4 == i3) {
            return new zzwx(null);
        }
        if (5 == i3) {
            return zzwy.zzd;
        }
        throw null;
    }
}

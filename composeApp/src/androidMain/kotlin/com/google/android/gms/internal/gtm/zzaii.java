package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzaii extends zzacc {
    
    public static final zzaii zzd;
    private byte zze = 2;

    static {
        final zzaii zzaii = new zzaii();
        zzd = zzaii;
        zzao(zzaii.class, zzaii);
    }

    private zzaii() {
    }

    public static zzaii zze() {
        return zzaii.zzd;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zze);
        }
        if (2 == i3) {
            return zzal(zzaii.zzd, "\u0003\u0000", null);
        }
        if (3 == i3) {
            return new zzaii();
        }
        if (4 == i3) {
            return new zzaig(null);
        }
        if (5 == i3) {
            return zzaii.zzd;
        }
        zze = null == obj ? (byte) 0 : 1;
        return null;
    }
}

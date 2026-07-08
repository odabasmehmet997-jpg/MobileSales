package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzahp extends zzacc {
    
    public static final zzahp zzd;
    private int zze;
    private zzahr zzf;
    private byte zzg = 2;

    static {
        final zzahp zzahp = new zzahp();
        zzd = zzahp;
        zzao(zzahp.class, zzahp);
    }

    private zzahp() {
    }

    public static zzahp zze() {
        return zzahp.zzd;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zzg);
        }
        if (2 == i3) {
            return zzal(zzahp.zzd, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zze", "zzf"});
        } else if (3 == i3) {
            return new zzahp();
        } else {
            if (4 == i3) {
                return new zzaho(null);
            }
            if (5 == i3) {
                return zzahp.zzd;
            }
            zzg = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

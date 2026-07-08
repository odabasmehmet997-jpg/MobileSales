package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzxt extends zzacf {
    
    public static final zzxt zza;
    private int zzd;
    private final String zze = "";

    static {
        final zzxt zzxt = new zzxt();
        zza = zzxt;
        zzao(zzxt.class, zzxt);
    }

    private zzxt() {
    }

    public static zzxt zzc() {
        return zzxt.zza;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzxt.zza, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"zzd", "zze"});
        } else if (3 == i3) {
            return new zzxt();
        } else {
            if (4 == i3) {
                return new zzxr(null);
            }
            if (5 == i3) {
                return zzxt.zza;
            }
            throw null;
        }
    }
}

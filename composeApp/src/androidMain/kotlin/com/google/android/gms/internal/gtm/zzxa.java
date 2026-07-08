package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzxa extends zzacf {
    public static final zzace zza = zzacf.zzab(zzxd.zzc(), null, null, 260, zzaex.STRING, false, String.class);
    
    public static final zzxa zzd;

    static {
        zzxa zzxa = new zzxa();
        zzd = zzxa;
        zzacf.zzao(zzxa.class, zzxa);
    }

    private zzxa() {
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zzd, "\u0001\u0000", null);
        }
        if (3 == i3) {
            return new zzxa();
        }
        if (4 == i3) {
            return new zzwz(null);
        }
        if (5 == i3) {
            return zzd;
        }
        throw null;
    }
}

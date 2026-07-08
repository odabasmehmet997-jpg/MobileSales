package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzsi extends zzacf {
    
    public static final zzsi zza;
    private final String zzd = "";
    private final String zze = "";

    static {
        zzsi zzsi = new zzsi();
        zza = zzsi;
        zzacf.zzao(zzsi.class, zzsi);
    }

    private zzsi() {
    }

    public static zzsi zzc() {
        return zza;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"zzd", "zze"});
        } else if (3 == i3) {
            return new zzsi();
        } else {
            if (4 == i3) {
                return new zzsg(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

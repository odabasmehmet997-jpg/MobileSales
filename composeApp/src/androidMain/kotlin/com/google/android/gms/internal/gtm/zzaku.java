package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzaku extends zzacf {
    
    public static final zzaku zza;
    private int zzd;
    private final String zze = "";
    private final String zzf = "";

    static {
        zzaku zzaku = new zzaku();
        zza = zzaku;
        zzacf.zzao(zzaku.class, zzaku);
    }

    private zzaku() {
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (3 == i3) {
            return new zzaku();
        } else {
            if (4 == i3) {
                return new zzakt(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }
}

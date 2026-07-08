package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzaek extends zzacf {
    
    public static final zzaek zza;
    private long zzd;
    private int zze;

    static {
        final zzaek zzaek = new zzaek();
        zza = zzaek;
        zzao(zzaek.class, zzaek);
    }

    private zzaek() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return new zzadv(zzaek.zza, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004", new Object[]{"zzd", "zze"});
        } else if (3 == i3) {
            return new zzaek();
        } else {
            if (4 == i3) {
                return new zzaei(null);
            }
            if (5 == i3) {
                return zzaek.zza;
            }
            throw null;
        }
    }
}

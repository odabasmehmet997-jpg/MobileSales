package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzajk extends zzacf {
    
    public static final zzajk zza;
    private final zzacn zzd = zzai();

    static {
        final zzajk zzajk = new zzajk();
        zza = zzajk;
        zzao(zzajk.class, zzajk);
    }

    private zzajk() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzajk.zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"zzd"});
        } else if (3 == i3) {
            return new zzajk();
        } else {
            if (4 == i3) {
                return new zzajj(null);
            }
            if (5 == i3) {
                return zzajk.zza;
            }
            throw null;
        }
    }
}

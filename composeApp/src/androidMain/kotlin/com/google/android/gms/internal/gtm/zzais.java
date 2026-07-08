package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzais extends zzacf {
    
    public static final zzais zza;
    private int zzd;
    private boolean zze;
    private boolean zzf;
    private boolean zzg;

    static {
        final zzais zzais = new zzais();
        zza = zzais;
        zzao(zzais.class, zzais);
    }

    private zzais() {
    }

    public static zzais zze() {
        return zzais.zza;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzais.zza, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        } else if (3 == i3) {
            return new zzais();
        } else {
            if (4 == i3) {
                return new zzair(null);
            }
            if (5 == i3) {
                return zzais.zza;
            }
            throw null;
        }
    }
}

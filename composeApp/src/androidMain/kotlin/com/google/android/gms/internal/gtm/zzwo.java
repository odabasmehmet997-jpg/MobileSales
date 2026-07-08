package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzwo extends zzacf {
    
    public static final zzwo zza;
    private int zzd;
    private zzwr zze;
    private byte zzf = 2;

    static {
        final zzwo zzwo = new zzwo();
        zza = zzwo;
        zzao(zzwo.class, zzwo);
    }

    private zzwo() {
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zzf);
        }
        if (2 == i3) {
            return zzal(zzwo.zza, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0001\u0001ᐉ\u0000", new Object[]{"zzd", "zze"});
        } else if (3 == i3) {
            return new zzwo();
        } else {
            if (4 == i3) {
                return new zzwn(null);
            }
            if (5 == i3) {
                return zzwo.zza;
            }
            zzf = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

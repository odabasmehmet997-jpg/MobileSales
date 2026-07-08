package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzgb extends zzjz {
    
    public static final zzgb zza;
    private int zze;
    private final int zzf = 1;
    private zzkg zzg = zzby();

    static {
        final zzgb zzgb = new zzgb();
        zza = zzgb;
        zzbE(zzgb.class, zzgb);
    }

    private zzgb() {
    }

    public static zzfz zza() {
        return (zzfz) zzgb.zza.zzbs();
    }

    static void zzc(final zzgb zzgb, final zzfq zzfq) {
        zzfq.getClass();
        final zzkg zzkg = zzgb.zzg;
        if (!zzkg.zzc()) {
            zzgb.zzg = zzbz(zzkg);
        }
        zzgb.zzg.add(zzfq);
    }

    
    public Object zzl(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzbD(zzgb.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဌ\u0000\u0002\u001b", new Object[]{"zze", "zzf", zzga.zza, "zzg", zzfq.class});
        } else if (3 == i3) {
            return new zzgb();
        } else {
            if (4 == i3) {
                return new zzfz(null);
            }
            if (5 != i3) {
                return null;
            }
            return zzgb.zza;
        }
    }
}

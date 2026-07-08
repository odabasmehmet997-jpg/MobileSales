package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzfk extends zzjz {
    
    public static final zzfk zza;
    private int zze;
    private int zzf;
    private zzgd zzg;
    private zzgd zzh;
    private boolean zzi;

    static {
        final zzfk zzfk = new zzfk();
        zza = zzfk;
        zzbE(zzfk.class, zzfk);
    }

    private zzfk() {
    }

    public static zzfj zzb() {
        return (zzfj) zzfk.zza.zzbs();
    }

    static void zzf(final zzfk zzfk, final int i2) {
        zzfk.zze |= 1;
        zzfk.zzf = i2;
    }

    static void zzg(final zzfk zzfk, final zzgd zzgd) {
        zzgd.getClass();
        zzfk.zzg = zzgd;
        zzfk.zze |= 2;
    }

    static void zzh(final zzfk zzfk, final zzgd zzgd) {
        zzfk.zzh = zzgd;
        zzfk.zze |= 4;
    }

    static void zzi(final zzfk zzfk, final boolean z) {
        zzfk.zze |= 8;
        zzfk.zzi = z;
    }

    public int zza() {
        return zzf;
    }

    public zzgd zzd() {
        final zzgd zzgd = zzg;
        return null == zzgd ? com.google.android.gms.internal.measurement.zzgd.zzh() : zzgd;
    }

    public zzgd zze() {
        final zzgd zzgd = zzh;
        return null == zzgd ? com.google.android.gms.internal.measurement.zzgd.zzh() : zzgd;
    }

    public boolean zzj() {
        return zzi;
    }

    public boolean zzk() {
        return 0 != (this.zze & 1);
    }

    
    public Object zzl(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzbD(zzfk.zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဇ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
        } else if (3 == i3) {
            return new zzfk();
        } else {
            if (4 == i3) {
                return new zzfj(null);
            }
            if (5 != i3) {
                return null;
            }
            return zzfk.zza;
        }
    }

    public boolean zzm() {
        return 0 != (this.zze & 8);
    }

    public boolean zzn() {
        return 0 != (this.zze & 4);
    }
}

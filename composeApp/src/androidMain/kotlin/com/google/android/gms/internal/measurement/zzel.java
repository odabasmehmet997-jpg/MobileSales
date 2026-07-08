package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzel extends zzjz {
    
    public static final zzel zza;
    private int zze;
    private zzex zzf;
    private zzeq zzg;
    private boolean zzh;
    private String zzi = "";

    static {
        final zzel zzel = new zzel();
        zza = zzel;
        zzbE(zzel.class, zzel);
    }

    private zzel() {
    }

    public static zzel zzb() {
        return zzel.zza;
    }

    static void zzf(final zzel zzel, final String str) {
        zzel.zze |= 8;
        zzel.zzi = str;
    }

    public zzeq zzc() {
        final zzeq zzeq = zzg;
        return null == zzeq ? com.google.android.gms.internal.measurement.zzeq.zzb() : zzeq;
    }

    public zzex zzd() {
        final zzex zzex = zzf;
        return null == zzex ? com.google.android.gms.internal.measurement.zzex.zzc() : zzex;
    }

    public String zze() {
        return zzi;
    }

    public boolean zzg() {
        return zzh;
    }

    public boolean zzh() {
        return 0 != (this.zze & 4);
    }

    public boolean zzi() {
        return 0 != (this.zze & 2);
    }

    public boolean zzj() {
        return 0 != (this.zze & 8);
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
            return zzbD(zzel.zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဇ\u0002\u0004ဈ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
        } else if (3 == i3) {
            return new zzel();
        } else {
            if (4 == i3) {
                return new zzek(null);
            }
            if (5 != i3) {
                return null;
            }
            return zzel.zza;
        }
    }
}

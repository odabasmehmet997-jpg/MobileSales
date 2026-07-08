package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzes extends zzjz {
    
    public static final zzes zza;
    private int zze;
    private int zzf;
    private String zzg = "";
    private zzel zzh;
    private boolean zzi;
    private boolean zzj;
    private boolean zzk;

    static {
        final zzes zzes = new zzes();
        zza = zzes;
        zzbE(zzes.class, zzes);
    }

    private zzes() {
    }

    public static zzer zzc() {
        return (zzer) zzes.zza.zzbs();
    }

    static void zzf(final zzes zzes, final String str) {
        zzes.zze |= 2;
        zzes.zzg = str;
    }

    public int zza() {
        return zzf;
    }

    public zzel zzb() {
        final zzel zzel = zzh;
        return null == zzel ? com.google.android.gms.internal.measurement.zzel.zzb() : zzel;
    }

    public String zze() {
        return zzg;
    }

    public boolean zzg() {
        return zzi;
    }

    public boolean zzh() {
        return zzj;
    }

    public boolean zzi() {
        return zzk;
    }

    public boolean zzj() {
        return 0 != (this.zze & 1);
    }

    public boolean zzk() {
        return 0 != (this.zze & 32);
    }

    
    public Object zzl(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzbD(zzes.zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0003ဉ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဇ\u0005", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        } else if (3 == i3) {
            return new zzes();
        } else {
            if (4 == i3) {
                return new zzer(null);
            }
            if (5 != i3) {
                return null;
            }
            return zzes.zza;
        }
    }
}

package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzt extends zzacf {
    
    public static final zzt zza;
    private int zzd;
    private final String zze = "";
    private long zzf;
    private final long zzg = 2147483647L;
    private boolean zzh;
    private long zzi;

    static {
        zzt zzt = new zzt();
        zza = zzt;
        zzacf.zzao(zzt.class, zzt);
    }

    private zzt() {
    }

    public long zza() {
        return this.zzi;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဇ\u0003\u0005ဂ\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
        } else if (3 == i3) {
            return new zzt();
        } else {
            if (4 == i3) {
                return new zzs(null);
            }
            if (5 == i3) {
                return zza;
            }
            throw null;
        }
    }

    public long zzc() {
        return this.zzg;
    }

    public long zzd() {
        return this.zzf;
    }

    public String zzf() {
        return this.zze;
    }

    public boolean zzg() {
        return this.zzh;
    }

    public boolean zzh() {
        return 0 != (zzd & 1);
    }
}

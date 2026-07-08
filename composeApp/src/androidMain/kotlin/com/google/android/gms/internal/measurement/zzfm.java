package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzfm extends zzjz {
    
    public static final zzfm zza;
    private int zze;
    private int zzf;
    private long zzg;

    static {
        zzfm zzfm = new zzfm();
        zza = zzfm;
        zzjz.zzbE(zzfm.class, zzfm);
    }

    private zzfm() {
    }

    public static zzfl zzc() {
        return (zzfl) zza.zzbs();
    }

    static void zze(zzfm zzfm, int i2) {
        zzfm.zze |= 1;
        zzfm.zzf = i2;
    }

    static void zzf(zzfm zzfm, long j2) {
        zzfm.zze |= 2;
        zzfm.zzg = j2;
    }

    public int zza() {
        return this.zzf;
    }

    public long zzb() {
        return this.zzg;
    }

    public boolean zzg() {
        return 0 != (zze & 2);
    }

    public boolean zzh() {
        return 0 != (zze & 1);
    }

    
    public Object zzl(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzjz.zzbD(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", "zzg"});
        } else if (3 == i3) {
            return new zzfm();
        } else {
            if (4 == i3) {
                return new zzfl(null);
            }
            if (5 != i3) {
                return null;
            }
            return zza;
        }
    }
}

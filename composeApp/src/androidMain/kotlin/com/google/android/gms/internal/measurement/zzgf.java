package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzgf extends zzjz {
    
    public static final zzgf zza;
    private int zze;
    private int zzf;
    private zzkf zzg = zzjz.zzbw();

    static {
        zzgf zzgf = new zzgf();
        zza = zzgf;
        zzjz.zzbE(zzgf.class, zzgf);
    }

    private zzgf() {
    }

    public static zzge zzd() {
        return (zzge) zza.zzbs();
    }

    static void zzg(zzgf zzgf, int i2) {
        zzgf.zze |= 1;
        zzgf.zzf = i2;
    }

    static void zzh(zzgf zzgf, Iterable iterable) {
        zzkf zzkf = zzgf.zzg;
        if (!zzkf.zzc()) {
            zzgf.zzg = zzjz.zzbx(zzkf);
        }
        zzih.zzbo(iterable, zzgf.zzg);
    }

    public int zza() {
        return this.zzg.size();
    }

    public int zzb() {
        return this.zzf;
    }

    public long zzc(int i2) {
        return this.zzg.zza(i2);
    }

    public List zzf() {
        return this.zzg;
    }

    public boolean zzi() {
        return 0 != (zze & 1);
    }

    
    public Object zzl(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzjz.zzbD(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001င\u0000\u0002\u0014", new Object[]{"zze", "zzf", "zzg"});
        } else if (3 == i3) {
            return new zzgf();
        } else {
            if (4 == i3) {
                return new zzge(null);
            }
            if (5 != i3) {
                return null;
            }
            return zza;
        }
    }
}

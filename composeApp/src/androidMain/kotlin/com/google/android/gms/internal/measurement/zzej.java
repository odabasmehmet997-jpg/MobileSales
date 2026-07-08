package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzej extends zzjz {
    
    public static final zzej zza;
    private int zze;
    private int zzf;
    private String zzg = "";
    private zzkg zzh = zzjz.zzby();
    private boolean zzi;
    private zzeq zzj;
    private boolean zzk;
    private boolean zzl;
    private boolean zzm;

    static {
        zzej zzej = new zzej();
        zza = zzej;
        zzjz.zzbE(zzej.class, zzej);
    }

    private zzej() {
    }

    public static zzei zzc() {
        return (zzei) zza.zzbs();
    }

    static void zzi(zzej zzej, String str) {
        zzej.zze |= 2;
        zzej.zzg = str;
    }

    static void zzj(zzej zzej, int i2, zzel zzel) {
        zzel.getClass();
        zzkg zzkg = zzej.zzh;
        if (!zzkg.zzc()) {
            zzej.zzh = zzjz.zzbz(zzkg);
        }
        zzej.zzh.set(i2, zzel);
    }

    public int zza() {
        return this.zzh.size();
    }

    public int zzb() {
        return this.zzf;
    }

    public zzel zze(int i2) {
        return (zzel) this.zzh.get(i2);
    }

    public zzeq zzf() {
        zzeq zzeq = this.zzj;
        return null == zzeq ? com.google.android.gms.internal.measurement.zzeq.zzb() : zzeq;
    }

    public String zzg() {
        return this.zzg;
    }

    public List zzh() {
        return this.zzh;
    }

    public boolean zzk() {
        return this.zzk;
    }

    
    public Object zzl(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzjz.zzbD(zza, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001င\u0000\u0002ဈ\u0001\u0003\u001b\u0004ဇ\u0002\u0005ဉ\u0003\u0006ဇ\u0004\u0007ဇ\u0005\bဇ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", zzel.class, "zzi", "zzj", "zzk", "zzl", "zzm"});
        } else if (3 == i3) {
            return new zzej();
        } else {
            if (4 == i3) {
                return new zzei(null);
            }
            if (5 != i3) {
                return null;
            }
            return zza;
        }
    }

    public boolean zzm() {
        return this.zzl;
    }

    public boolean zzn() {
        return this.zzm;
    }

    public boolean zzo() {
        return 0 != (zze & 8);
    }

    public boolean zzp() {
        return 0 != (zze & 1);
    }

    public boolean zzq() {
        return 0 != (zze & 64);
    }
}

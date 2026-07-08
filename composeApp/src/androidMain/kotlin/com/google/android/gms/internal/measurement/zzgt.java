package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzgt extends zzjz {
    
    public static final zzgt zza;
    private int zze;
    private int zzf;
    private final zzkg zzg = zzjz.zzby();
    private final String zzh = "";
    private final String zzi = "";
    private boolean zzj;
    private double zzk;

    static {
        zzgt zzgt = new zzgt();
        zza = zzgt;
        zzjz.zzbE(zzgt.class, zzgt);
    }

    private zzgt() {
    }

    public double zza() {
        return this.zzk;
    }

    public String zzc() {
        return this.zzh;
    }

    public String zzd() {
        return this.zzi;
    }

    public List zze() {
        return this.zzg;
    }

    public boolean zzf() {
        return this.zzj;
    }

    public boolean zzg() {
        return 0 != (zze & 8);
    }

    public boolean zzh() {
        return 0 != (zze & 16);
    }

    public boolean zzi() {
        return 0 != (zze & 4);
    }

    public int zzj() {
        int zza2 = zzgs.zza(this.zzf);
        if (0 == zza2) {
            return 1;
        }
        return zza2;
    }

    
    public Object zzl(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzjz.zzbD(zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001ဌ\u0000\u0002\u001b\u0003ဈ\u0001\u0004ဈ\u0002\u0005ဇ\u0003\u0006က\u0004", new Object[]{"zze", "zzf", zzgr.zza, "zzg", zzgt.class, "zzh", "zzi", "zzj", "zzk"});
        } else if (3 == i3) {
            return new zzgt();
        } else {
            if (4 == i3) {
                return new zzgp(null);
            }
            if (5 != i3) {
                return null;
            }
            return zza;
        }
    }
}

package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzex extends zzjz {
    
    public static final zzex zza;
    private int zze;
    private int zzf;
    private final String zzg = "";
    private boolean zzh;
    private final zzkg zzi = zzjz.zzby();

    static {
        zzex zzex = new zzex();
        zza = zzex;
        zzjz.zzbE(zzex.class, zzex);
    }

    private zzex() {
    }

    public static zzex zzc() {
        return zza;
    }

    public int zza() {
        return this.zzi.size();
    }

    public String zzd() {
        return this.zzg;
    }

    public List zze() {
        return this.zzi;
    }

    public boolean zzf() {
        return this.zzh;
    }

    public boolean zzg() {
        return 0 != (zze & 4);
    }

    public boolean zzh() {
        return 0 != (zze & 2);
    }

    public boolean zzi() {
        return 0 != (zze & 1);
    }

    public int zzj() {
        int zza2 = zzew.zza(this.zzf);
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
            return zzjz.zzbD(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001ဌ\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004\u001a", new Object[]{"zze", "zzf", zzev.zza, "zzg", "zzh", "zzi"});
        } else if (3 == i3) {
            return new zzex();
        } else {
            if (4 == i3) {
                return new zzet(null);
            }
            if (5 != i3) {
                return null;
            }
            return zza;
        }
    }
}

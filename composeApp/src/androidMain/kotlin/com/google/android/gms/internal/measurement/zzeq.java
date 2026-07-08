package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzeq extends zzjz {
    
    public static final zzeq zza;
    private int zze;
    private int zzf;
    private boolean zzg;
    private final String zzh = "";
    private final String zzi = "";
    private final String zzj = "";

    static {
        zzeq zzeq = new zzeq();
        zza = zzeq;
        zzjz.zzbE(zzeq.class, zzeq);
    }

    private zzeq() {
    }

    public static zzeq zzb() {
        return zza;
    }

    public String zzc() {
        return this.zzh;
    }

    public String zzd() {
        return this.zzj;
    }

    public String zze() {
        return this.zzi;
    }

    public boolean zzf() {
        return this.zzg;
    }

    public boolean zzg() {
        return 0 != (zze & 1);
    }

    public boolean zzh() {
        return 0 != (zze & 4);
    }

    public boolean zzi() {
        return 0 != (zze & 2);
    }

    public boolean zzj() {
        return 0 != (zze & 16);
    }

    public boolean zzk() {
        return 0 != (zze & 8);
    }

    
    public Object zzl(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzjz.zzbD(zza, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဇ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004", new Object[]{"zze", "zzf", zzeo.zza, "zzg", "zzh", "zzi", "zzj"});
        } else if (3 == i3) {
            return new zzeq();
        } else {
            if (4 == i3) {
                return new zzem(null);
            }
            if (5 != i3) {
                return null;
            }
            return zza;
        }
    }

    public int zzm() {
        int zza2 = zzep.zza(this.zzf);
        if (0 == zza2) {
            return 1;
        }
        return zza2;
    }
}

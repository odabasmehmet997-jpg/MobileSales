package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzaf extends zzacf {
    
    public static final zzaf zza;
    private int zzd;
    private final String zze = "";
    private zzap zzf;
    private zzv zzg;
    private byte zzh = 2;

    static {
        zzaf zzaf = new zzaf();
        zza = zzaf;
        zzacf.zzao(zzaf.class, zzaf);
    }

    private zzaf() {
    }

    public zzv zza() {
        zzv zzv = this.zzg;
        return null == zzv ? com.google.android.gms.internal.gtm.zzv.zzc() : zzv;
    }

    public String zzd() {
        return this.zze;
    }

    public boolean zze() {
        return 0 != (zzd & 4);
    }

    public boolean zzf() {
        return 0 != (zzd & 1);
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(this.zzh);
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0002\u0001ဈ\u0000\u0002ᐉ\u0001\u0003ᐉ\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        } else if (3 == i3) {
            return new zzaf();
        } else {
            if (4 == i3) {
                return new zzae(null);
            }
            if (5 == i3) {
                return zza;
            }
            this.zzh = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

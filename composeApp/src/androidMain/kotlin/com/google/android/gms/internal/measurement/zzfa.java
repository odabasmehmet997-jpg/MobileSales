package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzfa extends zzjz {
    
    public static final zzfa zza;
    private int zze;
    private String zzf = "";
    private boolean zzg;
    private boolean zzh;
    private int zzi;

    static {
        zzfa zzfa = new zzfa();
        zza = zzfa;
        zzjz.zzbE(zzfa.class, zzfa);
    }

    private zzfa() {
    }

    static void zzd(zzfa zzfa, String str) {
        str.getClass();
        zzfa.zze |= 1;
        zzfa.zzf = str;
    }

    public int zza() {
        return this.zzi;
    }

    public String zzc() {
        return this.zzf;
    }

    public boolean zze() {
        return this.zzg;
    }

    public boolean zzf() {
        return this.zzh;
    }

    public boolean zzg() {
        return 0 != (zze & 2);
    }

    public boolean zzh() {
        return 0 != (zze & 4);
    }

    public boolean zzi() {
        return 0 != (zze & 8);
    }

    
    public Object zzl(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzjz.zzbD(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004င\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
        } else if (3 == i3) {
            return new zzfa();
        } else {
            if (4 == i3) {
                return new zzez(null);
            }
            if (5 != i3) {
                return null;
            }
            return zza;
        }
    }
}

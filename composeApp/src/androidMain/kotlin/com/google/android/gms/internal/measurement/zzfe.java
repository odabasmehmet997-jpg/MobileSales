package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzfe extends zzjz {
    
    public static final zzfe zza;
    private int zze;
    private final String zzf = "";
    private final String zzg = "";

    static {
        zzfe zzfe = new zzfe();
        zza = zzfe;
        zzjz.zzbE(zzfe.class, zzfe);
    }

    private zzfe() {
    }

    public String zzb() {
        return this.zzf;
    }

    public String zzc() {
        return this.zzg;
    }

    
    public Object zzl(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzjz.zzbD(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", "zzg"});
        } else if (3 == i3) {
            return new zzfe();
        } else {
            if (4 == i3) {
                return new zzfd(null);
            }
            if (5 != i3) {
                return null;
            }
            return zza;
        }
    }
}

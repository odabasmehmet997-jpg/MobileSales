package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzfq extends zzjz {
    
    public static final zzfq zza;
    private int zze;
    private String zzf = "";
    private long zzg;

    static {
        final zzfq zzfq = new zzfq();
        zza = zzfq;
        zzbE(zzfq.class, zzfq);
    }

    private zzfq() {
    }

    public static zzfp zza() {
        return (zzfp) zzfq.zza.zzbs();
    }

    static void zzc(final zzfq zzfq, final String str) {
        str.getClass();
        zzfq.zze |= 1;
        zzfq.zzf = str;
    }

    static void zzd(final zzfq zzfq, final long j2) {
        zzfq.zze |= 2;
        zzfq.zzg = j2;
    }

    
    public Object zzl(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzbD(zzfq.zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", "zzg"});
        } else if (3 == i3) {
            return new zzfq();
        } else {
            if (4 == i3) {
                return new zzfp(null);
            }
            if (5 != i3) {
                return null;
            }
            return zzfq.zza;
        }
    }
}

package com.google.android.gms.internal.gtm;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzr extends zzacf {
    
    public static final zzr zza;
    private int zzd;
    private final zzack zze = zzacf.zzah();
    private int zzf;
    private int zzg;
    private boolean zzh;
    private boolean zzi;
    private byte zzj = 2;

    static {
        zzr zzr = new zzr();
        zza = zzr;
        zzacf.zzao(zzr.class, zzr);
    }

    private zzr() {
    }

    public List zzc() {
        return this.zze;
    }

    
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(this.zzj);
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0004\u0005\u0000\u0001\u0001\u0006\u0005\u0000\u0001\u0001\u0001ဇ\u0003\u0002ᔄ\u0000\u0003\u0016\u0004င\u0001\u0006ဇ\u0002", new Object[]{"zzd", "zzi", "zzf", "zze", "zzg", "zzh"});
        } else if (3 == i3) {
            return new zzr();
        } else {
            if (4 == i3) {
                return new zzq(null);
            }
            if (5 == i3) {
                return zza;
            }
            this.zzj = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

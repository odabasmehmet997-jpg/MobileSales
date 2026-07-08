package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzeh extends zzjz {
    
    public static final zzeh zza;
    private int zze;
    private int zzf;
    private zzkg zzg = zzjz.zzby();
    private zzkg zzh = zzjz.zzby();
    private boolean zzi;
    private boolean zzj;

    static {
        zzeh zzeh = new zzeh();
        zza = zzeh;
        zzjz.zzbE(zzeh.class, zzeh);
    }

    private zzeh() {
    }

    static void zzi(zzeh zzeh, int i2, zzes zzes) {
        zzes.getClass();
        zzkg zzkg = zzeh.zzg;
        if (!zzkg.zzc()) {
            zzeh.zzg = zzjz.zzbz(zzkg);
        }
        zzeh.zzg.set(i2, zzes);
    }

    static void zzj(zzeh zzeh, int i2, zzej zzej) {
        zzej.getClass();
        zzkg zzkg = zzeh.zzh;
        if (!zzkg.zzc()) {
            zzeh.zzh = zzjz.zzbz(zzkg);
        }
        zzeh.zzh.set(i2, zzej);
    }

    public int zza() {
        return this.zzf;
    }

    public int zzb() {
        return this.zzh.size();
    }

    public int zzc() {
        return this.zzg.size();
    }

    public zzej zze(int i2) {
        return (zzej) this.zzh.get(i2);
    }

    public zzes zzf(int i2) {
        return (zzes) this.zzg.get(i2);
    }

    public List zzg() {
        return this.zzh;
    }

    public List zzh() {
        return this.zzg;
    }

    public boolean zzk() {
        return 0 != (zze & 1);
    }

    
    public Object zzl(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzjz.zzbD(zza, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဇ\u0001\u0005ဇ\u0002", new Object[]{"zze", "zzf", "zzg", zzes.class, "zzh", zzej.class, "zzi", "zzj"});
        } else if (3 == i3) {
            return new zzeh();
        } else {
            if (4 == i3) {
                return new zzeg(null);
            }
            if (5 != i3) {
                return null;
            }
            return zza;
        }
    }
}

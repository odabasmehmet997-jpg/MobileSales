package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzfc extends zzjz {
    
    public static final zzfc zza;
    private int zze;
    private long zzf;
    private final String zzg = "";
    private int zzh;
    private final zzkg zzi = zzjz.zzby();
    private zzkg zzj = zzjz.zzby();
    
    public zzkg zzk = zzjz.zzby();
    private final String zzl = "";
    private boolean zzm;
    private final zzkg zzn = zzjz.zzby();

    static {
        zzfc zzfc = new zzfc();
        zza = zzfc;
        zzjz.zzbE(zzfc.class, zzfc);
    }

    private zzfc() {
    }

    public static zzfb zze() {
        return (zzfb) zza.zzbs();
    }

    public static zzfc zzg() {
        return zza;
    }

    static void zzm(zzfc zzfc, int i2, zzfa zzfa) {
        zzfa.getClass();
        zzkg zzkg = zzfc.zzj;
        if (!zzkg.zzc()) {
            zzfc.zzj = zzjz.zzbz(zzkg);
        }
        zzfc.zzj.set(i2, zzfa);
    }

    public int zza() {
        return this.zzn.size();
    }

    public int zzb() {
        return this.zzj.size();
    }

    public long zzc() {
        return this.zzf;
    }

    public zzfa zzd(int i2) {
        return (zzfa) this.zzj.get(i2);
    }

    public String zzh() {
        return this.zzg;
    }

    public List zzi() {
        return this.zzk;
    }

    public List zzj() {
        return this.zzn;
    }

    public List zzk() {
        return this.zzi;
    }

    
    public Object zzl(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzjz.zzbD(zza, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0004\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003င\u0002\u0004\u001b\u0005\u001b\u0006\u001b\u0007ဈ\u0003\bဇ\u0004\t\u001b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", zzfe.class, "zzj", zzfa.class, "zzk", zzeh.class, "zzl", "zzm", "zzn", zzgo.class});
        } else if (3 == i3) {
            return new zzfc();
        } else {
            if (4 == i3) {
                return new zzfb(null);
            }
            if (5 != i3) {
                return null;
            }
            return zza;
        }
    }

    public boolean zzo() {
        return this.zzm;
    }

    public boolean zzp() {
        return 0 != (zze & 2);
    }

    public boolean zzq() {
        return 0 != (zze & 1);
    }
}

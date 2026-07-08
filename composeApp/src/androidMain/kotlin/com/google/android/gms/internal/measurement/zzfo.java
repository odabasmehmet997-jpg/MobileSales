package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzfo extends zzjz {
    
    public static final zzfo zza;
    private int zze;
    
    public zzkg zzf = zzjz.zzby();
    private String zzg = "";
    private long zzh;
    private long zzi;
    private int zzj;

    static {
        zzfo zzfo = new zzfo();
        zza = zzfo;
        zzjz.zzbE(zzfo.class, zzfo);
    }

    private zzfo() {
    }

    public static zzfn zze() {
        return (zzfn) zza.zzbs();
    }

    static void zzj(zzfo zzfo, int i2, zzfs zzfs) {
        zzfs.getClass();
        zzfo.zzv();
        zzfo.zzf.set(i2, zzfs);
    }

    static void zzk(zzfo zzfo, zzfs zzfs) {
        zzfs.getClass();
        zzfo.zzv();
        zzfo.zzf.add(zzfs);
    }

    static void zzm(zzfo zzfo, Iterable iterable) {
        zzfo.zzv();
        zzih.zzbo(iterable, zzfo.zzf);
    }

    static void zzo(zzfo zzfo, int i2) {
        zzfo.zzv();
        zzfo.zzf.remove(i2);
    }

    static void zzp(zzfo zzfo, String str) {
        str.getClass();
        zzfo.zze |= 1;
        zzfo.zzg = str;
    }

    static void zzq(zzfo zzfo, long j2) {
        zzfo.zze |= 2;
        zzfo.zzh = j2;
    }

    static void zzr(zzfo zzfo, long j2) {
        zzfo.zze |= 4;
        zzfo.zzi = j2;
    }

    private void zzv() {
        zzkg zzkg = this.zzf;
        if (!zzkg.zzc()) {
            this.zzf = zzjz.zzbz(zzkg);
        }
    }

    public int zza() {
        return this.zzj;
    }

    public int zzb() {
        return this.zzf.size();
    }

    public long zzc() {
        return this.zzi;
    }

    public long zzd() {
        return this.zzh;
    }

    public zzfs zzg(int i2) {
        return (zzfs) this.zzf.get(i2);
    }

    public String zzh() {
        return this.zzg;
    }

    public List zzi() {
        return this.zzf;
    }

    
    public Object zzl(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzjz.zzbD(zza, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\u001b\u0002ဈ\u0000\u0003ဂ\u0001\u0004ဂ\u0002\u0005င\u0003", new Object[]{"zze", "zzf", zzfs.class, "zzg", "zzh", "zzi", "zzj"});
        } else if (3 == i3) {
            return new zzfo();
        } else {
            if (4 == i3) {
                return new zzfn(null);
            }
            if (5 != i3) {
                return null;
            }
            return zza;
        }
    }

    public boolean zzs() {
        return 0 != (zze & 8);
    }

    public boolean zzt() {
        return 0 != (zze & 4);
    }

    public boolean zzu() {
        return 0 != (zze & 2);
    }
}

package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzgd extends zzjz {
    
    public static final zzgd zza;
    
    public zzkf zze = zzjz.zzbw();
    
    public zzkf zzf = zzjz.zzbw();
    private zzkg zzg = zzjz.zzby();
    private zzkg zzh = zzjz.zzby();

    static {
        zzgd zzgd = new zzgd();
        zza = zzgd;
        zzjz.zzbE(zzgd.class, zzgd);
    }

    private zzgd() {
    }

    public static zzgc zzf() {
        return (zzgc) zza.zzbs();
    }

    public static zzgd zzh() {
        return zza;
    }

    static void zzo(zzgd zzgd, Iterable iterable) {
        zzkf zzkf = zzgd.zze;
        if (!zzkf.zzc()) {
            zzgd.zze = zzjz.zzbx(zzkf);
        }
        zzih.zzbo(iterable, zzgd.zze);
    }

    static void zzq(zzgd zzgd, Iterable iterable) {
        zzkf zzkf = zzgd.zzf;
        if (!zzkf.zzc()) {
            zzgd.zzf = zzjz.zzbx(zzkf);
        }
        zzih.zzbo(iterable, zzgd.zzf);
    }

    static void zzs(zzgd zzgd, Iterable iterable) {
        zzgd.zzw();
        zzih.zzbo(iterable, zzgd.zzg);
    }

    static void zzt(zzgd zzgd, int i2) {
        zzgd.zzw();
        zzgd.zzg.remove(i2);
    }

    static void zzu(zzgd zzgd, Iterable iterable) {
        zzgd.zzx();
        zzih.zzbo(iterable, zzgd.zzh);
    }

    static void zzv(zzgd zzgd, int i2) {
        zzgd.zzx();
        zzgd.zzh.remove(i2);
    }

    private void zzw() {
        zzkg zzkg = this.zzg;
        if (!zzkg.zzc()) {
            this.zzg = zzjz.zzbz(zzkg);
        }
    }

    private void zzx() {
        zzkg zzkg = this.zzh;
        if (!zzkg.zzc()) {
            this.zzh = zzjz.zzbz(zzkg);
        }
    }

    public int zza() {
        return this.zzg.size();
    }

    public int zzb() {
        return this.zzf.size();
    }

    public int zzc() {
        return this.zzh.size();
    }

    public int zzd() {
        return this.zze.size();
    }

    public zzfm zze(int i2) {
        return (zzfm) this.zzg.get(i2);
    }

    public zzgf zzi(int i2) {
        return (zzgf) this.zzh.get(i2);
    }

    public List zzj() {
        return this.zzg;
    }

    public List zzk() {
        return this.zzf;
    }

    
    public Object zzl(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzjz.zzbD(zza, "\u0001\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u0015\u0002\u0015\u0003\u001b\u0004\u001b", new Object[]{"zze", "zzf", "zzg", zzfm.class, "zzh", zzgf.class});
        } else if (3 == i3) {
            return new zzgd();
        } else {
            if (4 == i3) {
                return new zzgc(null);
            }
            if (5 != i3) {
                return null;
            }
            return zza;
        }
    }

    public List zzm() {
        return this.zzh;
    }

    public List zzn() {
        return this.zze;
    }
}

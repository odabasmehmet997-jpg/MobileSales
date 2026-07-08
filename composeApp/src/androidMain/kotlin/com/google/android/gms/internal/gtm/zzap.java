package com.google.android.gms.internal.gtm;

import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzap extends zzacc {
    private static final zzacl zzd = new zzaj();
    
    public static final zzap zze;
    private int zzf;
    private int zzg = 1;
    private String zzh = "";
    
    public zzacn zzi = zzai();
    
    public zzacn zzj = zzai();
    
    public zzacn zzk = zzai();
    private String zzl = "";
    private final String zzm = "";
    private long zzn;
    private boolean zzo;
    
    public zzacn zzp = zzai();
    
    public zzack zzq = zzah();
    private boolean zzr;
    private byte zzs = 2;

    static {
        final zzap zzap = new zzap();
        zze = zzap;
        zzao(zzap.class, zzap);
    }

    private zzap() {
    }

    static void zzA(final zzap zzap, final zzap zzap2) {
        zzap2.getClass();
        final zzacn zzacn = zzap.zzp;
        if (!zzacn.zzc()) {
            zzap.zzp = zzaj(zzacn);
        }
        zzap.zzp.add(zzap2);
    }

    static void zzG(final zzap zzap, final boolean z) {
        zzap.zzf |= 32;
        zzap.zzo = z;
    }

    static void zzH(final zzap zzap, final boolean z) {
        zzap.zzf |= 64;
        zzap.zzr = z;
    }

    static void zzJ(final zzap zzap, final long j2) {
        zzap.zzf |= 16;
        zzap.zzn = j2;
    }

    static void zzK(final zzap zzap, final String str) {
        str.getClass();
        zzap.zzf |= 4;
        zzap.zzl = str;
    }

    static void zzL(final zzap zzap, final String str) {
        str.getClass();
        zzap.zzf |= 2;
        zzap.zzh = str;
    }

    static void zzP(final zzap zzap, final int i2) {
        zzap.zzg = i2;
        zzap.zzf |= 1;
    }

    private void zzas() {
        final zzacn zzacn = zzi;
        if (!zzacn.zzc()) {
            zzi = zzaj(zzacn);
        }
    }

    private void zzat() {
        final zzacn zzacn = zzj;
        if (!zzacn.zzc()) {
            zzj = zzaj(zzacn);
        }
    }

    private void zzau() {
        final zzacn zzacn = zzk;
        if (!zzacn.zzc()) {
            zzk = zzaj(zzacn);
        }
    }

    public static zzak zzg() {
        return (zzak) zzap.zze.zzZ();
    }

    public static zzap zzi() {
        return zzap.zze;
    }

    static void zzt(final zzap zzap, final Iterable iterable) {
        final zzack zzack = zzap.zzq;
        if (!zzack.zzc()) {
            final int size = zzack.size();
            zzap.zzq = zzack.zzg(size + size);
        }
        final Iterator it = iterable.iterator();
        while (it.hasNext()) {
            zzap.zzq.zzh(((zzam) it.next()).zza());
        }
    }

    static void zzu(final zzap zzap, final Iterable iterable) {
        zzap.zzas();
        zzS(iterable, zzap.zzi);
    }

    static void zzv(final zzap zzap, final Iterable iterable) {
        zzap.zzat();
        zzS(iterable, zzap.zzj);
    }

    static void zzw(final zzap zzap, final Iterable iterable) {
        zzap.zzau();
        zzS(iterable, zzap.zzk);
    }

    static void zzx(final zzap zzap, final zzap zzap2) {
        zzap2.getClass();
        zzap.zzas();
        zzap.zzi.add(zzap2);
    }

    static void zzy(final zzap zzap, final zzap zzap2) {
        zzap2.getClass();
        zzap.zzat();
        zzap.zzj.add(zzap2);
    }

    static void zzz(final zzap zzap, final zzap zzap2) {
        zzap2.getClass();
        zzap.zzau();
        zzap.zzk.add(zzap2);
    }

    public boolean zzM() {
        return zzo;
    }

    public boolean zzN() {
        return zzr;
    }

    public int zzO() {
        final int zza = zzao.zza(zzg);
        if (0 == zza) {
            return 1;
        }
        return zza;
    }

    public int zza() {
        return zzi.size();
    }

    public int zzc() {
        return zzj.size();
    }

    public int zzd() {
        return zzk.size();
    }

    public int zze() {
        return zzp.size();
    }

    public long zzf() {
        return zzn;
    }

    public zzap zzj(final int i2) {
        return (zzap) zzi.get(i2);
    }

    public zzap zzk(final int i2) {
        return (zzap) zzj.get(i2);
    }

    public zzap zzl(final int i2) {
        return (zzap) zzk.get(i2);
    }

    public zzap zzm(final int i2) {
        return (zzap) zzp.get(i2);
    }

    public String zzn() {
        return zzm;
    }

    public String zzo() {
        return zzl;
    }

    public String zzp() {
        return zzh;
    }

    public List zzq() {
        return new zzacm(zzq, zzap.zzd);
    }

    public List zzr() {
        return zzi;
    }

    public List zzs() {
        return zzp;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zzs);
        }
        if (2 == i3) {
            return zzal(zzap.zze, "\u0004\f\u0000\u0001\u0001\f\f\u0000\u0005\u0005\u0001ᴌ\u0000\u0002ဈ\u0001\u0003Л\u0004Л\u0005Л\u0006ဈ\u0002\u0007ဈ\u0003\bဂ\u0004\tဇ\u0006\nࠞ\u000bЛ\fဇ\u0005", new Object[]{"zzf", "zzg", zzan.zza, "zzh", "zzi", zzap.class, "zzj", zzap.class, "zzk", zzap.class, "zzl", "zzm", "zzn", "zzr", "zzq", zzal.zza, "zzp", zzap.class, "zzo"});
        } else if (3 == i3) {
            return new zzap();
        } else {
            if (4 == i3) {
                return new zzak(null);
            }
            if (5 == i3) {
                return zzap.zze;
            }
            zzs = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

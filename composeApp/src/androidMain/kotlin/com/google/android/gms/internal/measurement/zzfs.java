package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzfs extends zzjz {
    
    public static final zzfs zza;
    private int zze;
    private String zzf = "";
    private String zzg = "";
    private long zzh;
    private float zzi;
    private double zzj;
    
    public zzkg zzk = zzby();

    static {
        final zzfs zzfs = new zzfs();
        zza = zzfs;
        zzbE(zzfs.class, zzfs);
    }

    private zzfs() {
    }

    public static zzfr zze() {
        return (zzfr) zzfs.zza.zzbs();
    }

    static void zzj(final zzfs zzfs, final String str) {
        str.getClass();
        zzfs.zze |= 1;
        zzfs.zzf = str;
    }

    static void zzk(final zzfs zzfs, final String str) {
        str.getClass();
        zzfs.zze |= 2;
        zzfs.zzg = str;
    }

    static void zzm(final zzfs zzfs) {
        zzfs.zze &= -3;
        zzfs.zzg = zza.zzg;
    }

    static void zzn(final zzfs zzfs, final long j2) {
        zzfs.zze |= 4;
        zzfs.zzh = j2;
    }

    static void zzo(final zzfs zzfs) {
        zzfs.zze &= -5;
        zzfs.zzh = 0;
    }

    static void zzp(final zzfs zzfs, final double d2) {
        zzfs.zze |= 16;
        zzfs.zzj = d2;
    }

    static void zzq(final zzfs zzfs) {
        zzfs.zze &= -17;
        zzfs.zzj = 0.0d;
    }

    static void zzr(final zzfs zzfs, final zzfs zzfs2) {
        zzfs2.getClass();
        zzfs.zzz();
        zzfs.zzk.add(zzfs2);
    }

    static void zzs(final zzfs zzfs, final Iterable iterable) {
        zzfs.zzz();
        zzbo(iterable, zzfs.zzk);
    }

    private void zzz() {
        final zzkg zzkg = zzk;
        if (!zzkg.zzc()) {
            zzk = zzbz(zzkg);
        }
    }

    public double zza() {
        return zzj;
    }

    public float zzb() {
        return zzi;
    }

    public int zzc() {
        return zzk.size();
    }

    public long zzd() {
        return zzh;
    }

    public String zzg() {
        return zzf;
    }

    public String zzh() {
        return zzg;
    }

    public List zzi() {
        return zzk;
    }

    
    public Object zzl(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzbD(zzfs.zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ခ\u0003\u0005က\u0004\u0006\u001b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzfs.class});
        } else if (3 == i3) {
            return new zzfs();
        } else {
            if (4 == i3) {
                return new zzfr(null);
            }
            if (5 != i3) {
                return null;
            }
            return zzfs.zza;
        }
    }

    public boolean zzu() {
        return 0 != (this.zze & 16);
    }

    public boolean zzv() {
        return 0 != (this.zze & 8);
    }

    public boolean zzw() {
        return 0 != (this.zze & 4);
    }

    public boolean zzx() {
        return 0 != (this.zze & 1);
    }

    public boolean zzy() {
        return 0 != (this.zze & 2);
    }
}

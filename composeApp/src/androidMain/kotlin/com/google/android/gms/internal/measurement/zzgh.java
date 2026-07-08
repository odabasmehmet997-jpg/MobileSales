package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzgh extends zzjz {
    
    public static final zzgh zza;
    private int zze;
    private long zzf;
    private String zzg = "";
    private String zzh = "";
    private long zzi;
    private float zzj;
    private double zzk;

    static {
        zzgh zzgh = new zzgh();
        zza = zzgh;
        zzjz.zzbE(zzgh.class, zzgh);
    }

    private zzgh() {
    }

    public static zzgg zzd() {
        return (zzgg) zza.zzbs();
    }

    static void zzh(zzgh zzgh, long j2) {
        zzgh.zze |= 1;
        zzgh.zzf = j2;
    }

    static void zzi(zzgh zzgh, String str) {
        str.getClass();
        zzgh.zze |= 2;
        zzgh.zzg = str;
    }

    static void zzj(zzgh zzgh, String str) {
        str.getClass();
        zzgh.zze |= 4;
        zzgh.zzh = str;
    }

    static void zzk(zzgh zzgh) {
        zzgh.zze &= -5;
        zzgh.zzh = zza.zzh;
    }

    static void zzm(zzgh zzgh, long j2) {
        zzgh.zze |= 8;
        zzgh.zzi = j2;
    }

    static void zzn(zzgh zzgh) {
        zzgh.zze &= -9;
        zzgh.zzi = 0;
    }

    static void zzo(zzgh zzgh, double d2) {
        zzgh.zze |= 32;
        zzgh.zzk = d2;
    }

    static void zzp(zzgh zzgh) {
        zzgh.zze &= -33;
        zzgh.zzk = 0.0d;
    }

    public double zza() {
        return this.zzk;
    }

    public long zzb() {
        return this.zzi;
    }

    public long zzc() {
        return this.zzf;
    }

    public String zzf() {
        return this.zzg;
    }

    public String zzg() {
        return this.zzh;
    }

    
    public Object zzl(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzjz.zzbD(zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ခ\u0004\u0006က\u0005", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        } else if (3 == i3) {
            return new zzgh();
        } else {
            if (4 == i3) {
                return new zzgg(null);
            }
            if (5 != i3) {
                return null;
            }
            return zza;
        }
    }

    public boolean zzq() {
        return 0 != (zze & 32);
    }

    public boolean zzr() {
        return 0 != (zze & 8);
    }

    public boolean zzs() {
        return 0 != (zze & 1);
    }

    public boolean zzt() {
        return 0 != (zze & 4);
    }
}

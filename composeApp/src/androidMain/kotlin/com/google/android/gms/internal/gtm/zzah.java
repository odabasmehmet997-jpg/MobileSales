package com.google.android.gms.internal.gtm;

import java.util.List;

public final class zzah extends zzacf {
    
    public static final zzah zza;
    private int zzd;
    public zzacn zze = zzacf.zzai();
    private zzz zzf;
    private String zzg = "";
    private byte zzh = 2;
    static {
        zzah zzah = new zzah();
        zza = zzah;
        zzacf.zzao(zzah.class, zzah);
    }
    private zzah() {
    }
    public static zzag zzd() {
        return (zzag) zza.zzZ();
    }
    public static zzah zzf() {
        return zza;
    }
    public static zzah zzg(byte[] bArr, zzabq zzabq) throws zzacq {
        return (zzah) zzacf.zzag(zza, bArr, zzabq);
    }
    static void zzk(zzah zzah, String str) {
        str.getClass();
        zzah.zzd |= 2;
        zzah.zzg = str;
    }
    static void zzl(zzah zzah, zzz zzz) {
        zzz.getClass();
        zzah.zzf = zzz;
        zzah.zzd |= 1;
    }
    public int zza() {
        return this.zze.size();
    }
    public zzz zzc() {
        zzz zzz = this.zzf;
        return null == zzz ? com.google.android.gms.internal.gtm.zzz.zzk() : zzz;
    }
    public String zzh() {
        return this.zzg;
    }
    public List zzi() {
        return this.zze;
    }
    public boolean zzm() {
        return 0 != (zzd & 1);
    }
    public Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(this.zzh);
        }
        if (2 == i3) {
            return zzacf.zzal(zza, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0002\u0001Л\u0002ᐉ\u0000\u0003ဈ\u0001", new Object[]{"zzd", "zze", zzaf.class, "zzf", "zzg"});
        }
        if (3 == i3) {
            return new zzah();
        }
        if (4 == i3) {
            return new zzag(null);
        }
        if (5 == i3) {
            return zza;
        }
        this.zzh = null == obj ? (byte) 0 : 1;
        return null;
    }
}

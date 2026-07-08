package com.google.android.gms.internal.gtm;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzz extends zzacf {
    
    public static final zzz zza;
    private int zzd;
    private final zzacn zze = zzai();
    private final zzacn zzf = zzai();
    private final zzacn zzg = zzai();
    private final zzacn zzh = zzai();
    private final zzacn zzi = zzai();
    private final zzacn zzj = zzai();
    private final zzacn zzk = zzai();
    private final zzacn zzl = zzai();
    private final String zzm = "";
    private final String zzn = "";
    private final String zzo = "0";
    private final String zzp = "";
    private zzp zzq;
    private float zzr;
    private boolean zzs;
    private final zzacn zzt = zzai();
    private int zzu;
    private byte zzv = 2;

    static {
        final zzz zzz = new zzz();
        zza = zzz;
        zzao(zzz.class, zzz);
    }

    private zzz() {
    }

    public static zzz zzk() {
        return zzz.zza;
    }

    public int zza() {
        return zzi.size();
    }

    public int zzc() {
        return zzk.size();
    }

    public int zzd() {
        return zzu;
    }

    public int zze() {
        return zzj.size();
    }

    public int zzf() {
        return zzg.size();
    }

    public zzr zzg(final int i2) {
        return (zzr) zzi.get(i2);
    }

    public zzr zzh(final int i2) {
        return (zzr) zzk.get(i2);
    }

    public zzr zzi(final int i2) {
        return (zzr) zzj.get(i2);
    }

    public zzap zzm(final int i2) {
        return (zzap) zzg.get(i2);
    }

    public String zzn() {
        return zzp;
    }

    public List zzo() {
        return zzf;
    }

    public List zzp() {
        return zzh;
    }

    public List zzq() {
        return zzl;
    }

    public List zzr() {
        return zzg;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zzv);
        }
        if (2 == i3) {
            return zzal(zzz.zza, "\u0004\u0011\u0000\u0001\u0001\u0013\u0011\u0000\t\u0005\u0001\u001a\u0002Л\u0003Л\u0004Л\u0005Л\u0006Л\u0007\u001b\tဈ\u0000\nဈ\u0001\fဈ\u0002\rဈ\u0003\u000eဉ\u0004\u000fခ\u0005\u0010\u001a\u0011င\u0007\u0012ဇ\u0006\u0013\u001a", new Object[]{"zzd", "zzf", "zzg", zzap.class, "zzh", zzx.class, "zzi", zzr.class, "zzj", zzr.class, "zzk", zzr.class, "zzl", zzab.class, "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzt", "zzu", "zzs", "zze"});
        } else if (3 == i3) {
            return new zzz();
        } else {
            if (4 == i3) {
                return new zzy(null);
            }
            if (5 == i3) {
                return zzz.zza;
            }
            zzv = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

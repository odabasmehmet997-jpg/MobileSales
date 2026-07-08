package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public final class zzrc extends zzacf {
    
    public static final zzrc zza;
    private int zzd;
    private long zze;
    private zzz zzf;
    private zzah zzg;
    private byte zzh = 2;

    static {
        final zzrc zzrc = new zzrc();
        zza = zzrc;
        zzao(zzrc.class, zzrc);
    }

    private zzrc() {
    }

    public static zzrb zze() {
        return (zzrb) zzrc.zza.zzZ();
    }

    public static zzrc zzg(final InputStream inputStream, final zzabq zzabq) throws IOException {
        return (zzrc) zzaf(zzrc.zza, inputStream, zzabq);
    }

    static void zzh(final zzrc zzrc, final zzz zzz) {
        zzz.getClass();
        zzrc.zzf = zzz;
        zzrc.zzd |= 2;
    }

    static void zzi(final zzrc zzrc, final zzah zzah) {
        zzah.getClass();
        zzrc.zzg = zzah;
        zzrc.zzd |= 4;
    }

    static void zzj(final zzrc zzrc, final long j2) {
        zzrc.zzd |= 1;
        zzrc.zze = j2;
    }

    public long zza() {
        return zze;
    }

    public zzz zzc() {
        final zzz zzz = zzf;
        return null == zzz ? com.google.android.gms.internal.gtm.zzz.zzk() : zzz;
    }

    public zzah zzd() {
        final zzah zzah = zzg;
        return null == zzah ? com.google.android.gms.internal.gtm.zzah.zzf() : zzah;
    }

    public boolean zzk() {
        return 0 != (this.zzd & 2);
    }

    public boolean zzl() {
        return 0 != (this.zzd & 4);
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return Byte.valueOf(zzh);
        }
        if (2 == i3) {
            return zzal(zzrc.zza, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0003\u0001ᔂ\u0000\u0002ᔉ\u0001\u0003ᐉ\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        } else if (3 == i3) {
            return new zzrc();
        } else {
            if (4 == i3) {
                return new zzrb(null);
            }
            if (5 == i3) {
                return zzrc.zza;
            }
            zzh = null == obj ? (byte) 0 : 1;
            return null;
        }
    }
}

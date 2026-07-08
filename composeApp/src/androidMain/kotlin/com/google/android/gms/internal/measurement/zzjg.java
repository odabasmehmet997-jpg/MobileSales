package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public abstract class zzjg extends zzin {
    private static final Logger zzb = Logger.getLogger(zzjg.class.getName());
    
    public static final boolean zzc = zzms.zzx();
    zzjh zza;

    private zzjg() {
    }

    zzjg(final zzjf zzjf) {
    }

    public static int zzA(final int i2) {
        if (0 == (i2 & -128)) {
            return 1;
        }
        if (0 == (i2 & -16384)) {
            return 2;
        }
        if (0 == (-2097152 & i2)) {
            return 3;
        }
        return 0 == (i2 & -268435456) ? 4 : 5;
    }

    public static int zzB(long j2) {
        int i2;
        if (0 == (-128 & j2)) {
            return 1;
        }
        if (0 > j2) {
            return 10;
        }
        if (0 != (-34359738368L & j2)) {
            j2 >>>= 28;
            i2 = 6;
        } else {
            i2 = 2;
        }
        if (0 != (-2097152 & j2)) {
            i2 += 2;
            j2 >>>= 14;
        }
        return 0 != (j2 & -16384) ? i2 + 1 : i2;
    }

    public static zzjg zzC(final byte[] bArr) {
        return new zzjd(bArr, 0, bArr.length);
    }

    public static int zzt(final zziy zziy) {
        final int zzd = zziy.zzd();
        return zzjg.zzA(zzd) + zzd;
    }

    @Deprecated
    static int zzu(final int i2, final zzlg zzlg, final zzlr zzlr) {
        final int zzA = zzjg.zzA(i2 << 3);
        final int i3 = zzA + zzA;
        final zzih zzih = (zzih) zzlg;
        int zzbm = zzih.zzbm();
        if (-1 == zzbm) {
            zzbm = zzlr.zza(zzih);
            zzih.zzbp(zzbm);
        }
        return i3 + zzbm;
    }

    public static int zzv(final int i2) {
        if (0 <= i2) {
            return zzjg.zzA(i2);
        }
        return 10;
    }

    public static int zzw(final zzkm zzkm) {
        final int zza2 = zzkm.zza();
        return zzjg.zzA(zza2) + zza2;
    }

    static int zzx(final zzlg zzlg, final zzlr zzlr) {
        final zzih zzih = (zzih) zzlg;
        int zzbm = zzih.zzbm();
        if (-1 == zzbm) {
            zzbm = zzlr.zza(zzih);
            zzih.zzbp(zzbm);
        }
        return zzjg.zzA(zzbm) + zzbm;
    }

    public static int zzy(final String str) {
        int i2;
        try {
            i2 = zzmx.zzc(str);
        } catch (final zzmw unused) {
            i2 = str.getBytes(zzkh.zzb).length;
        }
        return zzjg.zzA(i2) + i2;
    }

    public static int zzz(final int i2) {
        return zzjg.zzA(i2 << 3);
    }

    public final void zzD() {
        if (0 != zza()) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    
    public final void zzE(final String str, final zzmw zzmw) throws IOException {
        zzjg.zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzmw);
        final byte[] bytes = str.getBytes(zzkh.zzb);
        try {
            final int length = bytes.length;
            this.zzq(length);
            this.zzl(bytes, 0, length);
        } catch (final IndexOutOfBoundsException e2) {
            throw new zzje(e2);
        } catch (final zzje e3) {
            throw e3;
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b2) throws IOException;

    public abstract void zzd(int i2, boolean z) throws IOException;

    public abstract void zze(int i2, zziy zziy) throws IOException;

    public abstract void zzf(int i2, int i3) throws IOException;

    public abstract void zzg(int i2) throws IOException;

    public abstract void zzh(int i2, long j2) throws IOException;

    public abstract void zzi(long j2) throws IOException;

    public abstract void zzj(int i2, int i3) throws IOException;

    public abstract void zzk(int i2) throws IOException;

    public abstract void zzl(byte[] bArr, int i2, int i3) throws IOException;

    public abstract void zzm(int i2, String str) throws IOException;

    public abstract void zzo(int i2, int i3) throws IOException;

    public abstract void zzp(int i2, int i3) throws IOException;

    public abstract void zzq(int i2) throws IOException;

    public abstract void zzr(int i2, long j2) throws IOException;

    public abstract void zzs(long j2) throws IOException;
}

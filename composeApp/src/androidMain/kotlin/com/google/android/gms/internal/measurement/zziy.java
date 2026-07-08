package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public abstract class zziy implements Iterable, Serializable {
    private static final Comparator zza = new zziq();
    public static final zziy zzb = new zziv(zzkh.zzd);
    private static final zzix zzd = new zzix(null);
    private int zzc;

    static {
        final int i2 = zzij.r8clinit;
    }

    zziy() {
    }

    public static zziy zzl(byte[] bArr, int i2, int i3) {
        zzj(i2, i2 + i3, bArr.length);
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        return new zziv(bArr2);
    }

    public static zziy zzm(String str) {
        return new zziv(str.getBytes(zzkh.zzb));
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i2 = this.zzc;
        if (0 == i2) {
            int zzd2 = zzd();
            i2 = zze(zzd2, 0, zzd2);
            if (0 == i2) {
                i2 = 1;
            }
            this.zzc = i2;
        }
        return i2;
    }

    public final Iterator iterator() {
        return new zzio(this);
    }

    public final String toString() {
        return String.format(Locale.ROOT, "<ByteString@%s size=%d contents=\"%s\">", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(zzd()), 50 >= this.zzd() ? zzmg.zza(this) : zzmg.zza(zzf(0, 47)) + "...");
    }

    public abstract byte zza(int i2);

    
    public abstract byte zzb(int i2);

    public abstract int zzd();

    
    public abstract int zze(int i2, int i3, int i4);

    public abstract zziy zzf(int i2, int i3);

    
    public abstract String zzg(Charset charset);

    
    public abstract void zzh(zzin zzin) throws IOException;

    public abstract boolean zzi();

    
    public final int zzk() {
        return this.zzc;
    }

    public final String zzn(Charset charset) {
        return 0 == this.zzd() ? "" : zzg(charset);
    }

    static int zzj(int i2, int i3, int i4) {
        int i5 = i3 - i2;
        if (0 <= (i2 | i3 | i5 | (i4 - i3))) {
            return i5;
        }
        if (0 > i2) {
            final String sb = "Beginning index: " +
                    i2 +
                    " < 0";
            throw new IndexOutOfBoundsException(sb);
        } else if (i3 < i2) {
            final String sb2 = "Beginning index larger than ending index: " +
                    i2 +
                    ", " +
                    i3;
            throw new IndexOutOfBoundsException(sb2);
        } else {
            final String sb3 = "End index: " +
                    i3 +
                    " >= " +
                    i4;
            throw new IndexOutOfBoundsException(sb3);
        }
    }
}

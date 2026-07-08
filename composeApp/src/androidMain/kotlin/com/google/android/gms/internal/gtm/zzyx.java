package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public abstract class zzyx implements Iterable, Serializable {
    public static final zzyx zzb = new zzyv(zzaco.zzb);
    private int zza;

    static {
        final int i2 = zzyk.r8clinit;
    }

    zzyx() {
    }

    public static zzyx zzj(byte[] bArr, int i2, int i3) {
        zzh(i2, i2 + i3, bArr.length);
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        return new zzyv(bArr2);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i2 = this.zza;
        if (0 == i2) {
            int zzd = zzd();
            i2 = zze(zzd, 0, zzd);
            if (0 == i2) {
                i2 = 1;
            }
            this.zza = i2;
        }
        return i2;
    }

    public final Iterator iterator() {
        return new zzyq(this);
    }

    public final String toString() {
        return String.format(Locale.ROOT, "<ByteString@%s size=%d contents=\"%s\">", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(zzd()), 50 >= this.zzd() ? zzaeh.zza(this) : zzaeh.zza(zzf(0, 47)) + "...");
    }

    public abstract byte zza(int i2);

    
    public abstract byte zzb(int i2);

    public abstract int zzd();

    
    public abstract int zze(int i2, int i3, int i4);

    public abstract zzyx zzf(int i2, int i3);

    
    public abstract void zzg(zzyp zzyp) throws IOException;

    
    public final int zzi() {
        return this.zza;
    }

    static int zzh(int i2, int i3, int i4) {
        int i5 = i3 - i2;
        if (0 <= (i2 | i3 | i5 | (i4 - i3))) {
            return i5;
        }
        if (0 > i2) {
            throw new IndexOutOfBoundsException("Beginning index: " + i2 + " < 0");
        } else if (i3 < i2) {
            throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i2 + ", " + i3);
        } else {
            throw new IndexOutOfBoundsException("End index: " + i3 + " >= " + i4);
        }
    }
}

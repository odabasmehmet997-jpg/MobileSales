package com.google.android.gms.internal.gtm;

import java.util.Arrays;
import java.util.Collection;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzyo extends zzyj {
    private static final boolean[] zza;
    private boolean[] zzb;
    private int zzc;

    static {
        final boolean[] zArr = new boolean[0];
        zza = zArr;
        new zzyo(zArr, 0, false);
    }

    zzyo() {
        this(zzyo.zza, 0, true);
    }

    private static int zzg(final int i2) {
        return Math.max(((i2 * 3) / 2) + 1, 10);
    }

    private String zzh(final int i2) {
        final int i3 = zzc;
        return "Index:" + i2 + ", Size:" + i3;
    }

    private void zzi(final int i2) {
        if (0 > i2 || i2 >= zzc) {
            throw new IndexOutOfBoundsException(this.zzh(i2));
        }
    }

    public void add(final int i2, final Object obj) {
        final int i3;
        final boolean booleanValue = ((Boolean) obj).booleanValue();
        this.zza();
        if (0 > i2 || i2 > (i3 = zzc)) {
            throw new IndexOutOfBoundsException(this.zzh(i2));
        }
        final int i4 = i2 + 1;
        final boolean[] zArr = zzb;
        final int length = zArr.length;
        if (i3 < length) {
            System.arraycopy(zArr, i2, zArr, i4, i3 - i2);
        } else {
            final boolean[] zArr2 = new boolean[zzyo.zzg(length)];
            System.arraycopy(zzb, 0, zArr2, 0, i2);
            System.arraycopy(zzb, i2, zArr2, i4, zzc - i2);
            zzb = zArr2;
        }
        zzb[i2] = booleanValue;
        zzc++;
        modCount++;
    }

    public boolean addAll(final Collection collection) {
        this.zza();
        final byte[] bArr = zzaco.zzb;
        collection.getClass();
        if (!(collection instanceof zzyo zzyo)) {
            return super.addAll(collection);
        }
        final int i2 = zzyo.zzc;
        if (0 == i2) {
            return false;
        }
        final int i3 = zzc;
        if (Integer.MAX_VALUE - i3 >= i2) {
            final int i4 = i3 + i2;
            final boolean[] zArr = zzb;
            if (i4 > zArr.length) {
                zzb = Arrays.copyOf(zArr, i4);
            }
            System.arraycopy(zzyo.zzb, 0, zzb, zzc, zzyo.zzc);
            zzc = i4;
            modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public boolean contains(final Object obj) {
        return -1 != indexOf(obj);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzyo zzyo)) {
            return super.equals(obj);
        }
        if (zzc != zzyo.zzc) {
            return false;
        }
        final boolean[] zArr = zzyo.zzb;
        for (int i2 = 0; i2 < zzc; i2++) {
            if (zzb[i2] != zArr[i2]) {
                return false;
            }
        }
        return true;
    }

    public Object get(final int i2) {
        this.zzi(i2);
        return Boolean.valueOf(zzb[i2]);
    }

    public int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < zzc; i3++) {
            i2 = (i2 * 31) + zzaco.zza(zzb[i3]);
        }
        return i2;
    }

    public int indexOf(final Object obj) {
        if (!(obj instanceof Boolean)) {
            return -1;
        }
        final boolean booleanValue = ((Boolean) obj).booleanValue();
        final int i2 = zzc;
        for (int i3 = 0; i3 < i2; i3++) {
            if (zzb[i3] == booleanValue) {
                return i3;
            }
        }
        return -1;
    }

    public Object remove(final int i2) {
        this.zza();
        this.zzi(i2);
        final boolean[] zArr = zzb;
        final boolean z = zArr[i2];
        final int i3 = zzc;
        if (i2 < i3 - 1) {
            System.arraycopy(zArr, i2 + 1, zArr, i2, (i3 - i2) - 1);
        }
        zzc--;
        modCount++;
        return Boolean.valueOf(z);
    }

    
    public void removeRange(final int i2, final int i3) {
        this.zza();
        if (i3 >= i2) {
            final boolean[] zArr = zzb;
            System.arraycopy(zArr, i3, zArr, i2, zzc - i3);
            zzc -= i3 - i2;
            modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public Object set(final int i2, final Object obj) {
        final boolean booleanValue = ((Boolean) obj).booleanValue();
        this.zza();
        this.zzi(i2);
        final boolean[] zArr = zzb;
        final boolean z = zArr[i2];
        zArr[i2] = booleanValue;
        return Boolean.valueOf(z);
    }

    public int size() {
        return zzc;
    }

    public zzacn zzd(final int i2) {
        final boolean[] zArr;
        if (i2 >= zzc) {
            if (0 == i2) {
                zArr = zzyo.zza;
            } else {
                zArr = Arrays.copyOf(zzb, i2);
            }
            return new zzyo(zArr, zzc, true);
        }
        throw new IllegalArgumentException();
    }

    public void zze(final boolean z) {
        this.zza();
        final int i2 = zzc;
        final int length = zzb.length;
        if (i2 == length) {
            final boolean[] zArr = new boolean[zzyo.zzg(length)];
            System.arraycopy(zzb, 0, zArr, 0, zzc);
            zzb = zArr;
        }
        final boolean[] zArr2 = zzb;
        final int i3 = zzc;
        zzc = i3 + 1;
        zArr2[i3] = z;
    }

    public boolean zzf(final int i2) {
        this.zzi(i2);
        return zzb[i2];
    }

    private zzyo(final boolean[] zArr, final int i2, final boolean z) {
        super(z);
        zzb = zArr;
        zzc = i2;
    }

    public boolean add(final Object obj) {
        this.zze(((Boolean) obj).booleanValue());
        return true;
    }
}

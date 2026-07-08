package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzkv extends zzii implements zzkf, zzln {
    private static final zzkv zza;
    private long[] zzb;
    private int zzc;

    static {
        final zzkv zzkv = new zzkv(new long[0], 0);
        zza = zzkv;
        zzkv.zzb();
    }

    zzkv() {
        this(new long[10], 0);
    }

    public static zzkv zzf() {
        return zzkv.zza;
    }

    private String zzh(final int i2) {
        final int i3 = zzc;
        String sb = "Index:" +
                i2 +
                ", Size:" +
                i3;
        return sb;
    }

    private void zzi(final int i2) {
        if (0 > i2 || i2 >= zzc) {
            throw new IndexOutOfBoundsException(this.zzh(i2));
        }
    }

    public void add(final int i2, final Object obj) {
        final int i3;
        final long longValue = ((Long) obj).longValue();
        this.zzbK();
        if (0 > i2 || i2 > (i3 = zzc)) {
            throw new IndexOutOfBoundsException(this.zzh(i2));
        }
        final long[] jArr = zzb;
        if (i3 < jArr.length) {
            System.arraycopy(jArr, i2, jArr, i2 + 1, i3 - i2);
        } else {
            final long[] jArr2 = new long[(((i3 * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i2);
            System.arraycopy(zzb, i2, jArr2, i2 + 1, zzc - i2);
            zzb = jArr2;
        }
        zzb[i2] = longValue;
        zzc++;
        modCount++;
    }

    public boolean addAll(final Collection collection) {
        this.zzbK();
        zzkh.zze(collection);
        if (!(collection instanceof zzkv zzkv)) {
            return super.addAll(collection);
        }
        final int i2 = zzkv.zzc;
        if (0 == i2) {
            return false;
        }
        final int i3 = zzc;
        if (Integer.MAX_VALUE - i3 >= i2) {
            final int i4 = i3 + i2;
            final long[] jArr = zzb;
            if (i4 > jArr.length) {
                zzb = Arrays.copyOf(jArr, i4);
            }
            System.arraycopy(zzkv.zzb, 0, zzb, zzc, zzkv.zzc);
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
        if (!(obj instanceof zzkv zzkv)) {
            return super.equals(obj);
        }
        if (zzc != zzkv.zzc) {
            return false;
        }
        final long[] jArr = zzkv.zzb;
        for (int i2 = 0; i2 < zzc; i2++) {
            if (zzb[i2] != jArr[i2]) {
                return false;
            }
        }
        return true;
    }

    public Object get(final int i2) {
        this.zzi(i2);
        return Long.valueOf(zzb[i2]);
    }

    public int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < zzc; i3++) {
            i2 = (i2 * 31) + zzkh.zzc(zzb[i3]);
        }
        return i2;
    }

    public int indexOf(final Object obj) {
        if (!(obj instanceof Long)) {
            return -1;
        }
        final long longValue = ((Long) obj).longValue();
        final int i2 = zzc;
        for (int i3 = 0; i3 < i2; i3++) {
            if (zzb[i3] == longValue) {
                return i3;
            }
        }
        return -1;
    }

    public Object remove(final int i2) {
        this.zzbK();
        this.zzi(i2);
        final long[] jArr = zzb;
        final long j2 = jArr[i2];
        final int i3 = zzc;
        if (i2 < i3 - 1) {
            System.arraycopy(jArr, i2 + 1, jArr, i2, (i3 - i2) - 1);
        }
        zzc--;
        modCount++;
        return Long.valueOf(j2);
    }

    
    public void removeRange(final int i2, final int i3) {
        this.zzbK();
        if (i3 >= i2) {
            final long[] jArr = zzb;
            System.arraycopy(jArr, i3, jArr, i2, zzc - i3);
            zzc -= i3 - i2;
            modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public Object set(final int i2, final Object obj) {
        final long longValue = ((Long) obj).longValue();
        this.zzbK();
        this.zzi(i2);
        final long[] jArr = zzb;
        final long j2 = jArr[i2];
        jArr[i2] = longValue;
        return Long.valueOf(j2);
    }

    public int size() {
        return zzc;
    }

    public long zza(final int i2) {
        this.zzi(i2);
        return zzb[i2];
    }

    /* renamed from: zze */
    public zzkf zzd(final int i2) {
        if (i2 >= zzc) {
            return new zzkv(Arrays.copyOf(zzb, i2), zzc);
        }
        throw new IllegalArgumentException();
    }

    public void zzg(final long j2) {
        this.zzbK();
        final int i2 = zzc;
        final long[] jArr = zzb;
        if (i2 == jArr.length) {
            final long[] jArr2 = new long[(((i2 * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i2);
            zzb = jArr2;
        }
        final long[] jArr3 = zzb;
        final int i3 = zzc;
        zzc = i3 + 1;
        jArr3[i3] = j2;
    }

    private zzkv(final long[] jArr, final int i2) {
        zzb = jArr;
        zzc = i2;
    }

    public boolean add(final Object obj) {
        this.zzg(((Long) obj).longValue());
        return true;
    }
}

package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzim extends zzii implements zzln {
    private static final zzim zza;
    private boolean[] zzb;
    private int zzc;

    static {
        final zzim zzim = new zzim(new boolean[0], 0);
        zza = zzim;
        zzim.zzb();
    }

    zzim() {
        this(new boolean[10], 0);
    }

    private String zzf(final int i2) {
        final int i3 = zzc;
        String sb = "Index:" +
                i2 +
                ", Size:" +
                i3;
        return sb;
    }

    private void zzg(final int i2) {
        if (0 > i2 || i2 >= zzc) {
            throw new IndexOutOfBoundsException(this.zzf(i2));
        }
    }

    public void add(final int i2, final Object obj) {
        final int i3;
        final boolean booleanValue = ((Boolean) obj).booleanValue();
        this.zzbK();
        if (0 > i2 || i2 > (i3 = zzc)) {
            throw new IndexOutOfBoundsException(this.zzf(i2));
        }
        final boolean[] zArr = zzb;
        if (i3 < zArr.length) {
            System.arraycopy(zArr, i2, zArr, i2 + 1, i3 - i2);
        } else {
            final boolean[] zArr2 = new boolean[(((i3 * 3) / 2) + 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i2);
            System.arraycopy(zzb, i2, zArr2, i2 + 1, zzc - i2);
            zzb = zArr2;
        }
        zzb[i2] = booleanValue;
        zzc++;
        modCount++;
    }

    public boolean addAll(final Collection collection) {
        this.zzbK();
        zzkh.zze(collection);
        if (!(collection instanceof zzim zzim)) {
            return super.addAll(collection);
        }
        final int i2 = zzim.zzc;
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
            System.arraycopy(zzim.zzb, 0, zzb, zzc, zzim.zzc);
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
        if (!(obj instanceof zzim zzim)) {
            return super.equals(obj);
        }
        if (zzc != zzim.zzc) {
            return false;
        }
        final boolean[] zArr = zzim.zzb;
        for (int i2 = 0; i2 < zzc; i2++) {
            if (zzb[i2] != zArr[i2]) {
                return false;
            }
        }
        return true;
    }

    public Object get(final int i2) {
        this.zzg(i2);
        return Boolean.valueOf(zzb[i2]);
    }

    public int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < zzc; i3++) {
            i2 = (i2 * 31) + zzkh.zza(zzb[i3]);
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
        this.zzbK();
        this.zzg(i2);
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
        this.zzbK();
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
        this.zzbK();
        this.zzg(i2);
        final boolean[] zArr = zzb;
        final boolean z = zArr[i2];
        zArr[i2] = booleanValue;
        return Boolean.valueOf(z);
    }

    public int size() {
        return zzc;
    }

    public zzkg zzd(final int i2) {
        if (i2 >= zzc) {
            return new zzim(Arrays.copyOf(zzb, i2), zzc);
        }
        throw new IllegalArgumentException();
    }

    public void zze(final boolean z) {
        this.zzbK();
        final int i2 = zzc;
        final boolean[] zArr = zzb;
        if (i2 == zArr.length) {
            final boolean[] zArr2 = new boolean[(((i2 * 3) / 2) + 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i2);
            zzb = zArr2;
        }
        final boolean[] zArr3 = zzb;
        final int i3 = zzc;
        zzc = i3 + 1;
        zArr3[i3] = z;
    }

    private zzim(final boolean[] zArr, final int i2) {
        zzb = zArr;
        zzc = i2;
    }

    public boolean add(final Object obj) {
        this.zze(((Boolean) obj).booleanValue());
        return true;
    }
}

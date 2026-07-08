package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzji extends zzii implements zzln {
    private static final zzji zza;
    private double[] zzb;
    private int zzc;

    static {
        zzji zzji = new zzji(new double[0], 0);
        zza = zzji;
        zzji.zzb();
    }

    zzji() {
        this(new double[10], 0);
    }

    private String zzf(int i2) {
        int i3 = this.zzc;
        final String sb = "Index:" +
                i2 +
                ", Size:" +
                i3;
        return sb;
    }

    private void zzg(int i2) {
        if (0 > i2 || i2 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzf(i2));
        }
    }

    public void add(int i2, Object obj) {
        int i3;
        double doubleValue = ((Double) obj).doubleValue();
        zzbK();
        if (0 > i2 || i2 > (i3 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzf(i2));
        }
        double[] dArr = this.zzb;
        if (i3 < dArr.length) {
            System.arraycopy(dArr, i2, dArr, i2 + 1, i3 - i2);
        } else {
            double[] dArr2 = new double[(((i3 * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i2);
            System.arraycopy(this.zzb, i2, dArr2, i2 + 1, this.zzc - i2);
            this.zzb = dArr2;
        }
        this.zzb[i2] = doubleValue;
        this.zzc++;
        this.modCount++;
    }

    public boolean addAll(Collection collection) {
        zzbK();
        zzkh.zze(collection);
        if (!(collection instanceof final zzji zzji)) {
            return super.addAll(collection);
        }
        int i2 = zzji.zzc;
        if (0 == i2) {
            return false;
        }
        int i3 = this.zzc;
        if (Integer.MAX_VALUE - i3 >= i2) {
            int i4 = i3 + i2;
            double[] dArr = this.zzb;
            if (i4 > dArr.length) {
                this.zzb = Arrays.copyOf(dArr, i4);
            }
            System.arraycopy(zzji.zzb, 0, this.zzb, this.zzc, zzji.zzc);
            this.zzc = i4;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public boolean contains(Object obj) {
        return this.contains(obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof final zzji zzji)) {
            return super.equals(obj);
        }
        if (this.zzc != zzji.zzc) {
            return false;
        }
        double[] dArr = zzji.zzb;
        for (int i2 = 0; i2 < this.zzc; i2++) {
            if (Double.doubleToLongBits(this.zzb[i2]) != Double.doubleToLongBits(dArr[i2])) {
                return false;
            }
        }
        return true;
    }

    public Object get(int i2) {
        zzg(i2);
        return Double.valueOf(this.zzb[i2]);
    }

    public int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < this.zzc; i3++) {
            i2 = (i2 * 31) + zzkh.zzc(Double.doubleToLongBits(this.zzb[i3]));
        }
        return i2;
    }

    public int indexOf(Object obj) {
        if (!(obj instanceof Double)) {
            return -1;
        }
        double doubleValue = ((Double) obj).doubleValue();
        int i2 = this.zzc;
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.zzb[i3] == doubleValue) {
                return i3;
            }
        }
        return -1;
    }

    public Object remove(int i2) {
        zzbK();
        zzg(i2);
        double[] dArr = this.zzb;
        double d2 = dArr[i2];
        int i3 = this.zzc;
        if (i2 < i3 - 1) {
            System.arraycopy(dArr, i2 + 1, dArr, i2, (i3 - i2) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Double.valueOf(d2);
    }

    
    public void removeRange(int i2, int i3) {
        zzbK();
        if (i3 >= i2) {
            double[] dArr = this.zzb;
            System.arraycopy(dArr, i3, dArr, i2, this.zzc - i3);
            this.zzc -= i3 - i2;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public Object set(int i2, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zzbK();
        zzg(i2);
        double[] dArr = this.zzb;
        double d2 = dArr[i2];
        dArr[i2] = doubleValue;
        return Double.valueOf(d2);
    }

    public int size() {
        return this.zzc;
    }

    public zzkg zzd(int i2) {
        if (i2 >= this.zzc) {
            return new zzji(Arrays.copyOf(this.zzb, i2), this.zzc);
        }
        throw new IllegalArgumentException();
    }

    public void zze(double d2) {
        zzbK();
        int i2 = this.zzc;
        double[] dArr = this.zzb;
        if (i2 == dArr.length) {
            double[] dArr2 = new double[(((i2 * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i2);
            this.zzb = dArr2;
        }
        double[] dArr3 = this.zzb;
        int i3 = this.zzc;
        this.zzc = i3 + 1;
        dArr3[i3] = d2;
    }

    private zzji(double[] dArr, int i2) {
        this.zzb = dArr;
        this.zzc = i2;
    }

    public boolean add(Object obj) {
        zze(((Double) obj).doubleValue());
        return true;
    }
}

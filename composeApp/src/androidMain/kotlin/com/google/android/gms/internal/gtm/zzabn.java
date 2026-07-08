package com.google.android.gms.internal.gtm;

import java.util.Arrays;
import java.util.Collection;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzabn extends zzyj {
    private static final double[] zza;
    private double[] zzb;
    private int zzc;

    static {
        double[] dArr = new double[0];
        zza = dArr;
        new zzabn(dArr, 0, false);
    }

    zzabn() {
        this(zza, 0, true);
    }

    private static int zzh(int i2) {
        return Math.max(((i2 * 3) / 2) + 1, 10);
    }

    private String zzi(int i2) {
        int i3 = this.zzc;
        return "Index:" + i2 + ", Size:" + i3;
    }

    private void zzj(int i2) {
        if (0 > i2 || i2 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzi(i2));
        }
    }

    public void add(int i2, Object obj) {
        int i3;
        double doubleValue = ((Double) obj).doubleValue();
        zza();
        if (0 > i2 || i2 > (i3 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzi(i2));
        }
        int i4 = i2 + 1;
        double[] dArr = this.zzb;
        int length = dArr.length;
        if (i3 < length) {
            System.arraycopy(dArr, i2, dArr, i4, i3 - i2);
        } else {
            double[] dArr2 = new double[zzh(length)];
            System.arraycopy(this.zzb, 0, dArr2, 0, i2);
            System.arraycopy(this.zzb, i2, dArr2, i4, this.zzc - i2);
            this.zzb = dArr2;
        }
        this.zzb[i2] = doubleValue;
        this.zzc++;
        this.modCount++;
    }

    public boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzaco.zzb;
        collection.getClass();
        if (!(collection instanceof final zzabn zzabn)) {
            return super.addAll(collection);
        }
        int i2 = zzabn.zzc;
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
            System.arraycopy(zzabn.zzb, 0, this.zzb, this.zzc, zzabn.zzc);
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
        if (!(obj instanceof final zzabn zzabn)) {
            return super.equals(obj);
        }
        if (this.zzc != zzabn.zzc) {
            return false;
        }
        double[] dArr = zzabn.zzb;
        for (int i2 = 0; i2 < this.zzc; i2++) {
            if (Double.doubleToLongBits(this.zzb[i2]) != Double.doubleToLongBits(dArr[i2])) {
                return false;
            }
        }
        return true;
    }

    public Object get(int i2) {
        zzj(i2);
        return Double.valueOf(this.zzb[i2]);
    }

    public int hashCode() {
        int i2 = 1;
        for (int i3 = 0; i3 < this.zzc; i3++) {
            long doubleToLongBits = Double.doubleToLongBits(this.zzb[i3]);
            byte[] bArr = zzaco.zzb;
            i2 = (i2 * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
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
        zza();
        zzj(i2);
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
        zza();
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
        zza();
        zzj(i2);
        double[] dArr = this.zzb;
        double d2 = dArr[i2];
        dArr[i2] = doubleValue;
        return Double.valueOf(d2);
    }

    public int size() {
        return this.zzc;
    }

    public zzacn zzd(int i2) {
        double[] dArr;
        if (i2 >= this.zzc) {
            if (0 == i2) {
                dArr = zza;
            } else {
                dArr = Arrays.copyOf(this.zzb, i2);
            }
            return new zzabn(dArr, this.zzc, true);
        }
        throw new IllegalArgumentException();
    }

    public double zze(int i2) {
        zzj(i2);
        return this.zzb[i2];
    }

    public void zzf(double d2) {
        zza();
        int i2 = this.zzc;
        int length = this.zzb.length;
        if (i2 == length) {
            double[] dArr = new double[zzh(length)];
            System.arraycopy(this.zzb, 0, dArr, 0, this.zzc);
            this.zzb = dArr;
        }
        double[] dArr2 = this.zzb;
        int i3 = this.zzc;
        this.zzc = i3 + 1;
        dArr2[i3] = d2;
    }

    
    public void zzg(int i2) {
        int length = this.zzb.length;
        if (i2 > length) {
            if (0 != length) {
                while (length < i2) {
                    length = zzh(length);
                }
                this.zzb = Arrays.copyOf(this.zzb, length);
                return;
            }
            this.zzb = new double[Math.max(i2, 10)];
        }
    }

    private zzabn(double[] dArr, int i2, boolean z) {
        super(z);
        this.zzb = dArr;
        this.zzc = i2;
    }

    public boolean add(Object obj) {
        zzf(((Double) obj).doubleValue());
        return true;
    }
}
